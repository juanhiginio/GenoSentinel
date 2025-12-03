import os
from decouple import config, Csv
import requests


class ClinicService:
    """
    Servicio para interacción con la API externa de clínicas.

    Permite obtener información de pacientes desde un sistema externo.
    """

    @staticmethod
    def get_patient_info(patient_id: str):
        """
        Obtiene la información de un paciente desde el microservicio de Clínica.

        Args:
        - patient_id (str): Identificador del paciente.

        Returns:
        - dict | None: Información del paciente como diccionario, o None
          si ocurre un error o no está definida la URL de la API.
        """
        CLINIC_API_URL = config('CLINIC_API_URL')

        if CLINIC_API_URL is None:
            print("ERROR: CLINIC_API_URL is not defined in environment variables")
            return None

        url = f"{CLINIC_API_URL}{patient_id}/"

        try:
            response = requests.get(url)
            response.raise_for_status()  # Detecta errores HTTP
            return response.json()

        except requests.RequestException as e:
            print(f"Error contacting Clinic API: {e}")
            return None
