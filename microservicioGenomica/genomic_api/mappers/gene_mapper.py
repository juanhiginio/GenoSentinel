from genomic_api.models import Gene
from genomic_api.dto.gene_dto import GeneResponseDTO

class GeneMapper:
    """
    Mapper para el modelo Gene.

    Convierte instancias del modelo Gene a GeneResponseDTO y facilita
    la transferencia de datos entre la capa de persistencia y los servicios.
    """

    @staticmethod
    def to_dto(gene: Gene) -> GeneResponseDTO:
        """
        Convierte un objeto Gene a un DTO de respuesta GeneResponseDTO.

        Args:
        - gene (Gene): Instancia del modelo Gene a convertir.

        Returns:
        - GeneResponseDTO: DTO que representa el gen con los campos
          necesarios para exponer a la API.
        """
        return GeneResponseDTO(
            id=gene.id,
            symbol=gene.symbol,
            fullName=gene.fullName,
            functionSummary=gene.functionSummary,
            created_at=gene.created_at.isoformat(),
            updated_at=gene.updated_at.isoformat()
        )
