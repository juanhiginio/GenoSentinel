from ..models import PatientVariantReport
from django.core.exceptions import ObjectDoesNotExist

class PatientVariantReportRepository:
    """
    Repositorio para operaciones de persistencia sobre reportes
    de variantes genéticas de pacientes.
    """

    @staticmethod
    def list():
        """
        Obtiene todos los reportes registrados.

        Returns:
        - QuerySet[PatientVariantReport]: Lista de reportes.
        """
        return PatientVariantReport.objects.all()

    @staticmethod
    def get(report_id):
        """
        Obtiene un reporte por su ID.

        Args:
        - report_id: Identificador del reporte.

        Returns:
        - PatientVariantReport | None: Reporte encontrado o None.
        """
        try:
            return PatientVariantReport.objects.get(id=report_id)
        except ObjectDoesNotExist:
            return None

    @staticmethod
    def create(entity: PatientVariantReport):
        """
        Crea un nuevo reporte en la base de datos.

        Args:
        - entity: Instancia de PatientVariantReport.

        Returns:
        - PatientVariantReport: Reporte creado.
        """
        entity.save()
        return entity

    @staticmethod
    def update(entity: PatientVariantReport):
        """
        Actualiza un reporte existente en la base de datos.

        Args:
        - entity: Instancia de PatientVariantReport con cambios.

        Returns:
        - PatientVariantReport: Reporte actualizado.
        """
        entity.save()
        return entity

    @staticmethod
    def delete(report_id):
        """
        Elimina un reporte por su ID.

        Args:
        - report_id: Identificador del reporte.

        Returns:
        - bool: True si se eliminó, False si no existía.
        """
        entity = PatientVariantReportRepository.get(report_id)
        if not entity:
            return False
        entity.delete()
        return True
