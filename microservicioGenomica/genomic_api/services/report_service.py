from ..repositories.report_repository import PatientVariantReportRepository
from ..mappers.report_mapper import PatientVariantReportMapper
from ..dto.report_dto import PatientVariantReportDTO
from ..exceptions.domain_exceptions import NotFoundException

class PatientVariantReportService:
    """
    Servicio para la gestión de reportes de variantes genéticas de pacientes.

    Proporciona métodos para listar, obtener, crear, actualizar y eliminar reportes,
    utilizando repositorios y mappers para convertir entre DTOs y entidades.
    """

    @staticmethod
    def list_reports():
        """
        Lista todos los reportes.

        Returns:
        - List[PatientVariantReportDTO]: Lista de DTOs de reportes.
        """
        entities = PatientVariantReportRepository.list()
        return [PatientVariantReportMapper.to_dto(r) for r in entities]

    @staticmethod
    def get_report(report_id):
        """
        Obtiene un reporte por su ID.

        Args:
        - report_id: Identificador del reporte.

        Returns:
        - PatientVariantReportDTO: DTO del reporte encontrado.

        Raises:
        - NotFoundException: Si el reporte no existe.
        """
        entity = PatientVariantReportRepository.get(report_id)
        if not entity:
            raise NotFoundException(f"Report {report_id} not found")
        return PatientVariantReportMapper.to_dto(entity)

    @staticmethod
    def create_report(data):
        """
        Crea un nuevo reporte.

        Args:
        - data (dict): Diccionario con los datos del reporte.

        Returns:
        - PatientVariantReportDTO: DTO del reporte creado.
        """
        dto = PatientVariantReportDTO(
            patientId=data.get("patientId"),
            variantId=data.get("variantId"),
            detectionDate=data.get("detectionDate"),
            alleleFrequency=data.get("alleleFrequency"),
        )

        entity = PatientVariantReportMapper.to_entity(dto)
        saved = PatientVariantReportRepository.create(entity)
        return PatientVariantReportMapper.to_dto(saved)

    @staticmethod
    def update_report(report_id, data):
        """
        Actualiza un reporte existente.

        Args:
        - report_id: ID del reporte a actualizar.
        - data (dict): Campos a modificar.

        Returns:
        - PatientVariantReportDTO: DTO del reporte actualizado.

        Raises:
        - NotFoundException: Si el reporte no existe.
        """
        entity = PatientVariantReportRepository.get(report_id)
        if not entity:
            raise NotFoundException(f"Report {report_id} not found")

        entity.patientId = data.get("patientId", entity.patientId)
        entity.variantId_id = data.get("variantId", entity.variantId_id)
        entity.detectionDate = data.get("detectionDate", entity.detectionDate)
        entity.alleleFrequency = data.get("alleleFrequency", entity.alleleFrequency)

        updated = PatientVariantReportRepository.update(entity)
        return PatientVariantReportMapper.to_dto(updated)

    @staticmethod
    def delete_report(report_id):
        """
        Elimina un reporte por su ID.

        Args:
        - report_id: ID del reporte a eliminar.

        Raises:
        - NotFoundException: Si el reporte no existe.
        """
        success = PatientVariantReportRepository.delete(report_id)
        if not success:
            raise NotFoundException(f"Report {report_id} not found")
