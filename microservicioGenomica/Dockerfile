FROM python:3.11-slim

# Actualizar e instalar dependencias necesarias para mysqlclient
RUN apt-get update && apt-get install -y \
    gcc \
    default-libmysqlclient-dev \
    pkg-config \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app

# Copiar requirements e instalarlos
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# Copiar el proyecto
COPY . .

# Variables de entorno (Django recomienda no usar DEBUG en producción)
ENV PYTHONUNBUFFERED=1

EXPOSE 8000

# Ejecutar Django usando Gunicorn
CMD ["gunicorn", "--bind", "0.0.0.0:8000", "microservicioGenomica.wsgi:application"]
