import uuid
from django.db import models

# -----------------------------
#        GENE
# -----------------------------
class Gene(models.Model):
    id = models.AutoField(primary_key=True)
    symbol = models.CharField(max_length=10, unique=True)
    fullName = models.CharField(max_length=255)
    functionSummary = models.TextField()

    def __str__(self):
        return self.symbol


# -----------------------------
#     GENETIC VARIANT
# -----------------------------
class GeneticVariant(models.Model):
    IMPACT_CHOICES = [
        ('Missense', 'Missense'),
        ('Frameshift', 'Frameshift'),
        ('Nonsense', 'Nonsense'),
        ('Silent', 'Silent'),
    ]

    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)
    geneId = models.ForeignKey(Gene, on_delete=models.CASCADE, related_name='variants')

    chromosome = models.CharField(max_length=5)  # e.g. chr17
    position = models.IntegerField()
    referenceBase = models.CharField(max_length=1)  # e.g. A
    alternateBase = models.CharField(max_length=1)  # e.g. G
    impact = models.CharField(max_length=20, choices=IMPACT_CHOICES)

    def __str__(self):
        return f"{self.geneId.symbol} {self.referenceBase}>{self.alternateBase} ({self.position})"


# -----------------------------
#   PATIENT VARIANT REPORT
# -----------------------------
class PatientVariantReport(models.Model):
    id = models.UUIDField(primary_key=True, default=uuid.uuid4, editable=False)

    # Paciente viene desde otro microservicio →
    patientId = models.UUIDField()

    variantId = models.ForeignKey(
        GeneticVariant,
        on_delete=models.CASCADE,
        related_name='patientReports'
    )

    detectionDate = models.DateField()
    alleleFrequency = models.DecimalField(max_digits=5, decimal_places=2)  # VAF %

    def __str__(self):
        return f"Report for patient {self.patientId}"
