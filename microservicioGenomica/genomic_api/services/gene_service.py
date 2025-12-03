from ..repositories.gene_repository import GeneRepository

class GeneService:
    """
    Servicio para la gestión de genes.

    Proporciona métodos para listar, obtener, crear, actualizar y eliminar genes
    usando el repositorio GeneRepository.
    """

    @staticmethod
    def list_genes():
        """
        Obtiene todos los genes.

        Returns:
        - QuerySet[Gene]: Lista de objetos Gene.
        """
        return GeneRepository.get_all()

    @staticmethod
    def get_gene(gene_id):
        """
        Obtiene un gen por su ID.

        Args:
        - gene_id: Identificador del gen.

        Returns:
        - Gene | None: Objeto Gene o None si no existe.
        """
        return GeneRepository.get_by_id(gene_id)

    @staticmethod
    def create_gene(data):
        """
        Crea un nuevo gen.

        Args:
        - data (dict): Diccionario con los campos del gen a crear.

        Returns:
        - Gene: Objeto Gene creado.
        """
        return GeneRepository.create(**data)

    @staticmethod
    def update_gene(gene_id, data):
        """
        Actualiza un gen existente.

        Args:
        - gene_id: ID del gen a actualizar.
        - data (dict): Campos a modificar.

        Returns:
        - Gene: Objeto Gene actualizado.
        """
        return GeneRepository.update(gene_id, data)

    @staticmethod
    def delete_gene(gene_id):
        """
        Elimina un gen por su ID.

        Args:
        - gene_id: ID del gen a eliminar.

        Returns:
        - Gene: Objeto Gene eliminado.
        """
        return GeneRepository.delete(gene_id)
