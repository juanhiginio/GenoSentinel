# 🧬 GenoSentinel

## Plataforma de análisis genómico y gestión clínica basada en microservicios

**Autores:**  
- **Juan Diego Higinio Aranzazu** – CC 1054862785  
- **Tomás Restrepo Chica** – CC 1055358329

---

# 📌 Descripción general del proyecto
**GenoSentinel** es una plataforma distribuida construida bajo una arquitectura de **microservicios desacoplados**, enfocada en la integración de datos genómicos y clínicos para análisis oncológicos. El sistema permite consultar, procesar y exponer información biomédica mediante un **API Gateway centralizado**, garantizando escalabilidad, modularidad y facilidad de despliegue.

La aplicación está compuesta por **3 microservicios independientes**, cada uno con su propia base de datos, siguiendo principios de:

- **Domain-Driven Design (DDD)**
- **Separación de responsabilidades**
- **Despliegue independiente**
- **Escalabilidad horizontal**

Además, todo el ecosistema está preparado para despliegues modernos en **Kubernetes**, utilizando **Deployments**, **Services**, **Ingress**, **PVCs** y **Secrets**.

---

# 🏗️ Arquitectura del sistema
La plataforma está compuesta por tres microservicios principales, cada uno implementado en una tecnología diferente, optimizada para su dominio funcional.

```
                +-------------------+
                |  Client (Postman) |
                +-------------------+
                          |
                          v
                 +----------------+
                 | API Gateway    |
                 |  (Spring Boot) |
                 +----------------+
                  /             \
                 v               v
    +-------------------+   +----------------+
    | Genómica Service  |   | Clínica Service|
    |   (Django)        |   |   (NestJS)     |
    +-------------------+   +----------------+
           |                          |
           v                          v
   +-------------+             +-------------+
   | MySQL DB 1 |             | MySQL DB 2  |
   +-------------+             +-------------+

                 +--------------+
                 | MySQL DB 3   |
                 +--------------+
```

Cada microservicio posee su **propia base de datos**, respetando el patrón de **Database-per-Service**.

---

# ⚙️ Microservicios

## 1️⃣ Microservicio Genómico (Django / Python)
Encargado de la gestión y procesamiento de datos genéticos.

**Funciones principales:**
- Recepción y almacenamiento de datos genómicos.
- Generación de endpoints REST usando Django REST Framework.
- Documentación generada automáticamente con **drf-spectacular** (OpenAPI/Swagger).

**Tecnologías:**
- Django 5.2.8
- Gunicorn
- MySQL
- Python-Decouple

---

## 2️⃣ Microservicio Clínico (NestJS / Node.js)
Se encarga del manejo de datos clínicos relevantes para correlacionar información con el servicio genómico.

**Funciones principales:**
- Exposición de API REST modular.
- Validación de datos clínicos.
- Integración con el Gateway.

**Tecnologías:**
- NestJS
- Node.js 20
- MySQL

---

## 3️⃣ API Gateway (Spring Boot / Java)
Es el punto central de acceso a la plataforma. Orquesta los microservicios internos.

**Funciones principales:**
- Enrutamiento centralizado.
- Comunicación entre servicios.
- Gestión de errores.
- Exposición pública mediante NGINX Ingress en Kubernetes.

**Tecnologías:**
- Spring Boot 3
- Maven
- MySQL

---

# 🐳 Contenedores Docker
Cada microservicio cuenta con su propio Dockerfile optimizado:

- `Dockerfile` para Django con Gunicorn.
- `Dockerfile` para NestJS con build multietapa.
- `Dockerfile` para Spring Boot usando Maven + JRE.

Los contenedores están listos para ejecutarse tanto localmente como dentro de Kubernetes.

---

# ☸️ Kubernetes
La plataforma está preparada para despliegue completo en Kubernetes siguiendo la arquitectura **B** (una base de datos por microservicio).

## Componentes incluidos:

### 📁 `k8s/databases/`
- 3 Deployments MySQL
- 3 Services
- 3 PVC (almacenamiento persistente)
- 3 Secrets (contraseñas por DB)

### 📁 `k8s/microservices/`
- 3 Deployments (Django, Nest, Spring)
- 3 Services (ClusterIP)

### 📁 `k8s/secrets/`
- Archivo central con los secretos de cada DB

### 📁 `k8s/ingress.yaml`
- Configuración NGINX apuntando al Gateway principal

### 📁 `deploy.sh`
- Script automático para build, push y despliegue en el cluster

---

# 🧪 Ejecución local con Minikube
Para levantar el sistema completo:

```bash
minikube start --cpus=4 --memory=8192 --driver=docker
minikube addons enable ingress
```

Construcción de imágenes:

```bash
./k8s/deploy.sh
```

Acceso vía navegador:

```
http://genosentinel.local
```

(Se debe agregar `genosentinel.local` al archivo `/etc/hosts`).

---

# 📄 Licencia
Este proyecto es desarrollado como parte de un trabajo académico y no posee licencia restrictiva. Puede ser utilizado como base para estudios, despliegues educativos y proyectos derivados.

---

# 🙌 Agradecimientos
A todos los profesores y compañeros que apoyaron este proyecto, especialmente en la comprensión de arquitecturas distribuidas, contenedores y despliegues modernos con Kubernetes.

---

# ✉️ Contacto
Para soporte o consultas:
- **Juan Diego Higinio Aranzazu** – 1054862785
- **Tomás Restrepo Chica** – 1055358329

Gracias por consultar el repositorio de **GenoSentinel**.
