from dataclasses import dataclass
from typing import Optional


@dataclass
class GeneticVariantDTO:
    """
    DTO para variantes genéticas.

    Utilizado para transferir datos de una variante entre servicios,
    repositorios y serializadores.

    Attributes:
    - id: Identificador único de la variante (opcional)
    - geneId: ID del gen asociado
    - chromosome: Cromosoma donde se encuentra la variante, opcional
    - position: Posición de la variante en el cromosoma, opcional
    - referenceBase: Base de referencia, opcional
    - alternateBase: Base alternante, opcional
    - impact: Impacto funcional de la variante, opcional
    """
    id: Optional[str] = None
    geneId: str = None
    chromosome: Optional[str] = None
    position: Optional[int] = None
    referenceBase: Optional[str] = None
    alternateBase: Optional[str] = None
    impact: Optional[str] = None
