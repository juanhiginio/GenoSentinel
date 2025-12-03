from rest_framework import serializers
from ..models import Gene

class GeneSerializer(serializers.ModelSerializer):
    """
    Serializador para el modelo Gene.

    Este serializador convierte instancias del modelo Gene a JSON y viceversa,
    permitiendo exponer los genes a través de la API.

    Fields:
    - id: ID del gen (clave primaria)
    - symbol: Símbolo corto del gen (ej. BRCA1)
    - fullName: Nombre completo del gen
    - functionSummary: Resumen de la función del gen
    """
    class Meta:
        model = Gene
        fields = ['id', 'symbol', 'fullName', 'functionSummary']