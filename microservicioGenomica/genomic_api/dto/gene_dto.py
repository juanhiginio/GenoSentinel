from dataclasses import dataclass
from typing import Optional
from uuid import UUID

@dataclass(frozen=True)
class GeneCreateDTO:
    """
    DTO para la creación de un gen.

    Este objeto se utiliza para recibir datos cuando se crea un gen
    a través del servicio correspondiente.

    Attributes:
    - symbol: Símbolo del gen (ej. BRCA1), obligatorio
    - fullName: Nombre completo del gen, opcional
    - functionSummary: Resumen de la función del gen, opcional
    """
    symbol: str
    fullName: Optional[str]
    functionSummary: Optional[str]

@dataclass(frozen=True)
class GeneUpdateDTO:
    """
    DTO para la actualización de un gen existente.

    Permite actualizar uno o más campos del gen.

    Attributes:
    - symbol: Nuevo símbolo del gen, opcional
    - fullName: Nuevo nombre completo del gen, opcional
    - functionSummary: Nuevo resumen de la función, opcional
    """
    symbol: Optional[str]
    fullName: Optional[str]
    functionSummary: Optional[str]

@dataclass(frozen=True)
class GeneResponseDTO:
    """
    DTO de respuesta para un gen.

    Este objeto se utiliza para devolver información completa de un gen
    después de ser creado o consultado.

    Attributes:
    - id: Identificador único del gen (UUID)
    - symbol: Símbolo del gen
    - fullName: Nombre completo del gen
    - functionSummary: Resumen de la función del gen
    - created_at: Fecha de creación
    - updated_at: Fecha de última actualización
    """
    id: UUID
    symbol: str
    fullName: Optional[str]
    functionSummary: Optional[str]
    created_at: str
    updated_at: str
