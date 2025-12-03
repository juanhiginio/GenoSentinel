from genomic_api.repositories.variant_repository import GeneticVariantRepository
from genomic_api.mappers.variant_mapper import GeneticVariantMapper
from genomic_api.dto.variant_dto import GeneticVariantDTO


class GeneticVariantService:
    """
    Servicio para la gestión de variantes genéticas.

    Proporciona métodos para listar, obtener, crear, actualizar y eliminar
    variantes, usando repositorios y mappers para conversión entre DTOs
    y entidades de base de datos.
    """

    @staticmethod
    def get_all_variants():
        """
        Obtiene todas las variantes genéticas.

        Returns:
        - List[GeneticVariantDTO]: Lista de DTOs de variantes.
        """
        variants = GeneticVariantRepository.get_all()
        return [GeneticVariantMapper.to_dto(v) for v in variants]

    @staticmethod
    def get_variant_by_id(variant_id):
        """
        Obtiene una variante por su ID.

        Args:
        - variant_id: ID de la variante.

        Returns:
        - GeneticVariantDTO | None: DTO de la variante o None si no existe.
        """
        variant = GeneticVariantRepository.get_by_id(variant_id)
        if not variant:
            return None
        return GeneticVariantMapper.to_dto(variant)

    @staticmethod
    def create_variant(dto: GeneticVariantDTO):
        """
        Crea una nueva variante genética.

        Args:
        - dto (GeneticVariantDTO): DTO con los datos de la variante.

        Returns:
        - GeneticVariantDTO: DTO de la variante creada.
        """
        entity = GeneticVariantMapper.from_dto(dto)
        saved = GeneticVariantRepository.create(entity)
        return GeneticVariantMapper.to_dto(saved)

    @staticmethod
    def update_variant(variant_id, dto: GeneticVariantDTO):
        """
        Actualiza una variante existente.

        Args:
        - variant_id: ID de la variante a actualizar.
        - dto (GeneticVariantDTO): DTO con los nuevos datos.

        Returns:
        - GeneticVariantDTO | None: DTO actualizado o None si no existe.
        """
        entity = GeneticVariantRepository.get_by_id(variant_id)
        if not entity:
            return None

        entity.chromosome = dto.chromosome
        entity.position = dto.position
        entity.referenceBase = dto.referenceBase
        entity.alternateBase = dto.alternateBase
        entity.impact = dto.impact
        entity.geneId_id = dto.geneId

        updated = GeneticVariantRepository.update(entity)
        return GeneticVariantMapper.to_dto(updated)

    @staticmethod
    def delete_variant(variant_id):
        """
        Elimina una variante por su ID.

        Args:
        - variant_id: ID de la variante a eliminar.

        Returns:
        - bool | GeneticVariant: False si no existe, o el objeto eliminado.
        """
        success = GeneticVariantRepository.delete(variant_id)
        return success
