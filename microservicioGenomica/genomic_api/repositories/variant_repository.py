from genomic_api.models import GeneticVariant

class GeneticVariantRepository:
    """
    Repositorio para operaciones de persistencia sobre variantes genéticas.

    Encapsula consultas y modificaciones de la base de datos relacionadas
    con GeneticVariant.
    """

    @staticmethod
    def get_all():
        """
        Obtiene todas las variantes genéticas.

        Returns:
        - QuerySet[GeneticVariant]: Lista de variantes.
        """
        return GeneticVariant.objects.all()

    @staticmethod
    def get_by_id(variant_id):
        """
        Obtiene una variante por su ID.

        Args:
        - variant_id: Identificador de la variante.

        Returns:
        - GeneticVariant | None: Objeto encontrado o None.
        """
        return GeneticVariant.objects.filter(id=variant_id).first()

    @staticmethod
    def create(entity: GeneticVariant):
        """
        Crea una nueva variante genética en la base de datos.

        Args:
        - entity: Instancia de GeneticVariant.

        Returns:
        - GeneticVariant: Objeto creado.
        """
        entity.save()
        return entity

    @staticmethod
    def update(entity: GeneticVariant):
        """
        Actualiza una variante existente en la base de datos.

        Args:
        - entity: Instancia de GeneticVariant con cambios.

        Returns:
        - GeneticVariant: Objeto actualizado.
        """
        entity.save()
        return entity

    @staticmethod
    def delete(variant_id):
        """
        Elimina una variante genética por su ID.

        Args:
        - variant_id: Identificador de la variante.

        Returns:
        - bool | GeneticVariant: False si no existe, o el objeto eliminado.
        """
        entity = GeneticVariant.objects.filter(id=variant_id).first()
        if not entity:
            return False
        entity.delete()
        return True
