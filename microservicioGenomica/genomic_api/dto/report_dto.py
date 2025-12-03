class PatientVariantReportDTO:
    """
    DTO para reportes de variantes genéticas de pacientes.

    Este objeto se utiliza para transferir datos entre las capas de la aplicación
    sin exponer directamente el modelo de base de datos.

    Attributes:
    - id: Identificador único del reporte (UUID), opcional
    - patientId: Identificador del paciente asociado (UUID)
    - variantId: Identificador de la variante genética
    - detectionDate: Fecha de detección de la variante
    - alleleFrequency: Frecuencia alélica detectada, decimal
    """
    def __init__(self, id=None, patientId=None, variantId=None,
                 detectionDate=None, alleleFrequency=None):
        self.id = id
        self.patientId = patientId
        self.variantId = variantId
        self.detectionDate = detectionDate
        self.alleleFrequency = alleleFrequency
