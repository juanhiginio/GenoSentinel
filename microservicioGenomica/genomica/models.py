from django.db import models

# Create your models here.
# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.


class Gene(models.Model):
    id = models.CharField(primary_key=True, max_length=36)
    symbol = models.CharField(unique=True, max_length=50)
    fullname = models.CharField(db_column='fullName', max_length=255, blank=True, null=True)  # Field name made lowercase.
    functionsummary = models.TextField(db_column='functionSummary', blank=True, null=True)  # Field name made lowercase.
    created_at = models.DateTimeField()
    updated_at = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'gene'


class Geneticvariant(models.Model):
    id = models.CharField(primary_key=True, max_length=36)
    geneid = models.ForeignKey(Gene, models.DO_NOTHING, db_column='geneId')  # Field name made lowercase.
    chromosome = models.CharField(max_length=50, blank=True, null=True)
    position = models.BigIntegerField(blank=True, null=True)
    referencebase = models.CharField(db_column='referenceBase', max_length=50, blank=True, null=True)  # Field name made lowercase.
    alternatebase = models.CharField(db_column='alternateBase', max_length=50, blank=True, null=True)  # Field name made lowercase.
    impact = models.CharField(max_length=10, blank=True, null=True)
    created_at = models.DateTimeField()
    updated_at = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'geneticVariant'


class Patientvariantreport(models.Model):
    id = models.CharField(primary_key=True, max_length=36)
    patientid = models.CharField(db_column='patientId', max_length=36)  # Field name made lowercase.
    variantid = models.ForeignKey(Geneticvariant, models.DO_NOTHING, db_column='variantId')  # Field name made lowercase.
    detectiondate = models.DateField(db_column='detectionDate', blank=True, null=True)  # Field name made lowercase.
    allelefrequency = models.DecimalField(db_column='alleleFrequency', max_digits=7, decimal_places=6, blank=True, null=True)  # Field name made lowercase.
    notes = models.TextField(blank=True, null=True)
    created_at = models.DateTimeField()
    updated_at = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'patientVariantReport'