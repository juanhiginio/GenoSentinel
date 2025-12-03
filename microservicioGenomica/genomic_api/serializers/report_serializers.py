from rest_framework import serializers
from ..models import PatientVariantReport

class PatientVariantReportSerializer(serializers.Serializer):
    """
    Serializador para reportes de variantes genéticas de pacientes.

    Convierte objetos de tipo PatientVariantReport a JSON para exponerlos
    a través de la API o recibirlos desde peticiones HTTP.

    Fields:
    - id: Identificador único del reporte (UUID), opcional para creación
    - patientId: Identificador del paciente asociado (UUID)
    - variantId: Identificador de la variante genética
    - detectionDate: Fecha de detección de la variante
    - alleleFrequency: Frecuencia alélica detectada, valor decimal con hasta 4 decimales
    """
    id = serializers.UUIDField(required=False)
    patientId = serializers.UUIDField()
    variantId = serializers.CharField()
    detectionDate = serializers.CharField()
    alleleFrequency = serializers.DecimalField(max_digits=5, decimal_places=4)