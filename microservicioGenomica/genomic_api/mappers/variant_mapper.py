from genomic_api.dto.variant_dto import GeneticVariantDTO
from genomic_api.models import GeneticVariant


class GeneticVariantMapper:
    """
    Mapper para variantes genéticas.

    Facilita la conversión entre GeneticVariant (modelo de BD) y
    GeneticVariantDTO, permitiendo la transferencia de datos entre capas.
    """

    @staticmethod
    def to_dto(variant: GeneticVariant) -> GeneticVariantDTO:
        """
        Convierte un modelo GeneticVariant a un DTO.

        Args:
        - variant (GeneticVariant): Instancia de la variante genética.

        Returns:
        - GeneticVariantDTO: DTO correspondiente con los campos del modelo.
        """
        return GeneticVariantDTO(
            id=str(variant.id),
            geneId=str(variant.geneId.id),
            chromosome=variant.chromosome,
            position=variant.position,
            referenceBase=variant.referenceBase,
            alternateBase=variant.alternateBase,
            impact=variant.impact
        )

    @staticmethod
    def from_dto(dto: GeneticVariantDTO) -> GeneticVariant:
        """
        Convierte un DTO GeneticVariantDTO a un modelo de base de datos.

        Args:
        - dto (GeneticVariantDTO): DTO con los datos de la variante.

        Returns:
        - GeneticVariant: Instancia lista para ser persistida en la base de datos.
        """
        return GeneticVariant(
            geneId_id=dto.geneId,
            chromosome=dto.chromosome,
            position=dto.position,
            referenceBase=dto.referenceBase,
            alternateBase=dto.alternateBase,
            impact=dto.impact
        )
