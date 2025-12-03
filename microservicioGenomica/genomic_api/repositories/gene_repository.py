from typing import Optional
from genomic_api.models import Gene

class GeneRepository:
    """
    Repositorio para operaciones de persistencia sobre el modelo Gene.

    Encapsula todas las consultas y modificaciones de la base de datos
    relacionadas con genes.
    """

    @staticmethod
    def get_all():
        """
        Obtiene todos los genes registrados.

        Returns:
        - QuerySet[Gene]: Lista de todos los objetos Gene.
        """
        return Gene.objects.all()

    @staticmethod
    def get_by_id(gene_id):
        """
        Obtiene un gen por su ID.

        Args:
        - gene_id: Identificador del gen.

        Returns:
        - Gene | None: Objeto Gene si existe, None si no se encuentra.
        """
        return Gene.objects.filter(id=gene_id).first()

    @staticmethod
    def create(**kwargs) -> Gene:
        """
        Crea un nuevo gen en la base de datos.

        Args:
        - kwargs: Campos del modelo Gene a crear.

        Returns:
        - Gene: Objeto Gene creado.
        """
        return Gene.objects.create(**kwargs)

    @staticmethod
    def update(gene_id, data):
        """
        Actualiza un gen existente.

        Args:
        - gene_id: ID del gen a actualizar.
        - data: Diccionario con los campos a modificar.

        Returns:
        - Gene: Objeto Gene actualizado.
        """
        gene = Gene.objects.get(id=gene_id)

        for field, value in data.items():
            setattr(gene, field, value)

        gene.save()
        return gene

    @staticmethod
    def delete(gene_id):
        """
        Elimina un gen por su ID.

        Args:
        - gene_id: ID del gen a eliminar.

        Returns:
        - Gene: Objeto Gene eliminado.
        """
        gene = Gene.objects.get(id=gene_id)
        gene.delete()
        return gene
