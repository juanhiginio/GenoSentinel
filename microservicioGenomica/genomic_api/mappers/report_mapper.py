from ..models import PatientVariantReport
from ..dto.report_dto import PatientVariantReportDTO


class PatientVariantReportMapper:
    """
    Mapper para reportes de variantes genéticas de pacientes.

    Convierte entre PatientVariantReport (entidad) y PatientVariantReportDTO.
    """

    @staticmethod
    def to_entity(dto: PatientVariantReportDTO) -> PatientVariantReport:
        """
        Convierte un DTO de reporte a una entidad de base de datos.

        Args:
        - dto (PatientVariantReportDTO): DTO con los datos del reporte.

        Returns:
        - PatientVariantReport: Entidad lista para persistir en la base de datos.
        """
        return PatientVariantReport(
            id=dto.id,
            patientId=dto.patientId,
            variantId_id=dto.variantId,
            detectionDate=dto.detectionDate,
            alleleFrequency=dto.alleleFrequency,
        )

    @staticmethod
    def to_dto(entity: PatientVariantReport) -> PatientVariantReportDTO:
        """
        Convierte una entidad de reporte a su DTO correspondiente.

        Args:
        - entity (PatientVariantReport): Objeto de la base de datos.

        Returns:
        - PatientVariantReportDTO: DTO con los datos del reporte
          listos para ser devueltos por la API.
        """
        return PatientVariantReportDTO(
            id=str(entity.id),
            patientId=str(entity.patientId),
            variantId=str(entity.variantId.id),
            detectionDate=str(entity.detectionDate),
            alleleFrequency=str(entity.alleleFrequency)
        )
