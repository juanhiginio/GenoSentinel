"""
Rutas de la app genomic_api.
Se encarga únicamente de exponer los ViewSets de la app y mantener
el routing localizado (single responsibility).
"""

from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .views import GeneViewSet, GeneticVariantViewSet, PatientVariantReportViewSet

router = DefaultRouter()
router.register(r'genes', GeneViewSet, basename='gene')
router.register(r'variants', GeneticVariantViewSet, basename='variant')
router.register(r'reports', PatientVariantReportViewSet, basename='report')

urlpatterns = [
    # /api/genes/, /api/variants/, /api/reports/ (el prefijo '/api/' lo añade el proyecto)
    path('', include(router.urls)),
]