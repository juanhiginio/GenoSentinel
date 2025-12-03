from rest_framework import serializers
from genomic_api.models import GeneticVariant


class GeneticVariantSerializer(serializers.Serializer):
    """
    Serializador para variantes genéticas.

    Permite la serialización/deserialización de objetos GeneticVariant
    para ser enviados o recibidos mediante la API.

    Fields:
    - id: Identificador único de la variante (UUID), opcional en creación
    - geneId: ID del gen asociado
    - chromosome: Cromosoma donde se encuentra la variante
    - position: Posición de la variante en el cromosoma
    - referenceBase: Base de referencia
    - alternateBase: Base alternante
    - impact: Impacto funcional de la variante
    """
    id = serializers.UUIDField(required=False)
    geneId = serializers.IntegerField()
    chromosome = serializers.CharField()
    position = serializers.IntegerField()
    referenceBase = serializers.CharField()
    alternateBase = serializers.CharField()
    impact = serializers.CharField()
