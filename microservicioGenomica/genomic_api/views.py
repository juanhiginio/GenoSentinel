from rest_framework import viewsets, status
from rest_framework.response import Response
from rest_framework.decorators import action
from drf_spectacular.utils import extend_schema, OpenApiExample

from .serializers.gene_serializers import GeneSerializer
from .serializers.variant_serializers import GeneticVariantSerializer
from .serializers.report_serializers import PatientVariantReportSerializer

from .services.gene_service import GeneService
from .services.variant_service import GeneticVariantService
from .services.report_service import PatientVariantReportService
from .services.clinic_service import ClinicService

from genomic_api.dto.variant_dto import GeneticVariantDTO
from genomic_api.exceptions.domain_exceptions import NotFoundException


# ============================
#          GENES
# ============================

class GeneViewSet(viewsets.ViewSet):
    """
    ViewSet para operaciones CRUD sobre genes.
    """

    @extend_schema(
        summary="Lista todos los genes",
        responses=GeneSerializer(many=True)
    )
    def list(self, request):
        genes = GeneService.list_genes()
        serializer = GeneSerializer(genes, many=True)
        return Response(serializer.data)

    @extend_schema(
        summary="Obtiene un gen por ID",
        responses=GeneSerializer
    )
    def retrieve(self, request, pk=None):
        gene = GeneService.get_gene(pk)
        serializer = GeneSerializer(gene)
        return Response(serializer.data)

    @extend_schema(
        summary="Crea un nuevo gen",
        request=GeneSerializer,
        responses=GeneSerializer,
        examples=[
            OpenApiExample(
                "Ejemplo Gen",
                value={"symbol": "TP53", "fullName": "Tumor protein p53", "functionSummary": "Tumor suppressor"},
                request_only=True
            )
        ]
    )
    def create(self, request):
        serializer = GeneSerializer(data=request.data)
        serializer.is_valid(raise_exception=True)
        gene = GeneService.create_gene(serializer.validated_data)
        return Response(GeneSerializer(gene).data, status=status.HTTP_201_CREATED)

    @extend_schema(
        summary="Actualiza un gen existente",
        request=GeneSerializer,
        responses=GeneSerializer
    )
    def update(self, request, pk=None):
        serializer = GeneSerializer(data=request.data)
        serializer.is_valid(raise_exception=True)
        updated_gene = GeneService.update_gene(pk, serializer.validated_data)
        return Response(GeneSerializer(updated_gene).data)

    @extend_schema(
        summary="Elimina un gen por ID",
        responses=None
    )
    def destroy(self, request, pk=None):
        GeneService.delete_gene(pk)
        return Response({"message": "Gene deleted successfully"}, status=204)


# ============================
#     GENETIC VARIANTS
# ============================

class GeneticVariantViewSet(viewsets.ViewSet):
    """
    ViewSet para operaciones CRUD sobre variantes genéticas.
    """

    @extend_schema(
        summary="Lista todas las variantes genéticas",
        responses=GeneticVariantSerializer(many=True)
    )
    def list(self, request):
        variants = GeneticVariantService.get_all_variants()
        return Response(GeneticVariantSerializer(variants, many=True).data)

    @extend_schema(
        summary="Obtiene una variante genética por ID",
        responses=GeneticVariantSerializer
    )
    def retrieve(self, request, pk=None):
        variant = GeneticVariantService.get_variant_by_id(pk)
        if not variant:
            return Response({"error": "Variant not found"}, status=status.HTTP_404_NOT_FOUND)
        return Response(GeneticVariantSerializer(variant).data)

    @extend_schema(
        summary="Crea una nueva variante genética",
        request=GeneticVariantSerializer,
        responses=GeneticVariantSerializer,
        examples=[
            OpenApiExample(
                "Ejemplo Variante",
                value={
                    "geneId": 1,
                    "chromosome": "17",
                    "position": 7579472,
                    "referenceBase": "C",
                    "alternateBase": "T",
                    "impact": "HIGH"
                },
                request_only=True
            )
        ]
    )
    def create(self, request):
        serializer = GeneticVariantSerializer(data=request.data)
        serializer.is_valid(raise_exception=True)
        dto = GeneticVariantDTO(**serializer.validated_data)
        created = GeneticVariantService.create_variant(dto)
        return Response(GeneticVariantSerializer(created).data, status=status.HTTP_201_CREATED)

    @extend_schema(
        summary="Actualiza una variante genética existente",
        request=GeneticVariantSerializer,
        responses=GeneticVariantSerializer
    )
    def update(self, request, pk=None):
        serializer = GeneticVariantSerializer(data=request.data)
        serializer.is_valid(raise_exception=True)
        dto = GeneticVariantDTO(id=pk, **serializer.validated_data)
        updated = GeneticVariantService.update_variant(pk, dto)
        if not updated:
            return Response({"error": "Variant not found"}, status=status.HTTP_404_NOT_FOUND)
        return Response(GeneticVariantSerializer(updated).data)

    @extend_schema(
        summary="Elimina una variante genética por ID",
        responses=None
    )
    def destroy(self, request, pk=None):
        deleted = GeneticVariantService.delete_variant(pk)
        if deleted == 0:
            return Response({"error": "Variant not found"}, status=status.HTTP_404_NOT_FOUND)
        return Response(status=status.HTTP_204_NO_CONTENT)


# ============================
#   PATIENT VARIANT REPORTS
# ============================

class PatientVariantReportViewSet(viewsets.ViewSet):
    """
    ViewSet para operaciones CRUD sobre reportes de pacientes.
    """

    @extend_schema(
        summary="Lista todos los reportes de pacientes",
        responses=PatientVariantReportSerializer(many=True)
    )
    def list(self, request):
        reports = PatientVariantReportService.list_reports()
        return Response(PatientVariantReportSerializer(reports, many=True).data)

    @extend_schema(
        summary="Obtiene un reporte por ID",
        responses=PatientVariantReportSerializer
    )
    def retrieve(self, request, pk=None):
        try:
            report = PatientVariantReportService.get_report(pk)
        except NotFoundException:
            return Response({"error": "Report not found"}, status=status.HTTP_404_NOT_FOUND)
        return Response(PatientVariantReportSerializer(report).data)

    @extend_schema(
        summary="Crea un nuevo reporte de paciente",
        request=PatientVariantReportSerializer,
        responses=PatientVariantReportSerializer
    )
    def create(self, request):
        serializer = PatientVariantReportSerializer(data=request.data)
        serializer.is_valid(raise_exception=True)
        patient_id = serializer.validated_data.get("patientId")
        patient_info = ClinicService.get_patient_info(patient_id)
        if not patient_info:
            return Response(
                {"error": f"Patient {patient_id} does not exist in the clinic system"},
                status=status.HTTP_400_BAD_REQUEST
            )
        created = PatientVariantReportService.create_report(serializer.validated_data)
        return Response(PatientVariantReportSerializer(created).data, status=status.HTTP_201_CREATED)

    @extend_schema(
        summary="Actualiza un reporte de paciente existente",
        request=PatientVariantReportSerializer,
        responses=PatientVariantReportSerializer
    )
    def update(self, request, pk=None):
        serializer = PatientVariantReportSerializer(data=request.data)
        serializer.is_valid(raise_exception=True)
        try:
            patient_id = serializer.validated_data.get("patientId")
            patient_info = ClinicService.get_patient_info(patient_id)
            if not patient_info:
                return Response(
                    {"error": f"Patient {patient_id} does not exist in the clinic system"},
                    status=status.HTTP_400_BAD_REQUEST
                )
            updated = PatientVariantReportService.update_report(pk, serializer.validated_data)
        except NotFoundException:
            return Response({"error": "Report not found"}, status=status.HTTP_404_NOT_FOUND)
        return Response(PatientVariantReportSerializer(updated).data)

    @extend_schema(
        summary="Elimina un reporte de paciente por ID",
        responses=None
    )
    def destroy(self, request, pk=None):
        try:
            PatientVariantReportService.delete_report(pk)
        except NotFoundException:
            return Response({"error": "Report not found"}, status=status.HTTP_404_NOT_FOUND)
        return Response(status=status.HTTP_204_NO_CONTENT)

    @extend_schema(
        summary="Obtiene información clínica de un paciente desde el microservicio clínico",
        responses={"200": "Información clínica", "400": "Error", "404": "Report not found"}
    )
    @action(detail=True, methods=['get'])
    def get_clinical_info(self, request, pk=None):
        try:
            report = PatientVariantReportService.get_report(pk)
        except NotFoundException:
            return Response({"error": "Report not found"}, status=status.HTTP_404_NOT_FOUND)
        patient_id = str(report.patientId)
        patient_info = ClinicService.get_patient_info(patient_id)
        if patient_info:
            return Response(patient_info)
        return Response(
            {"error": f"Unable to retrieve clinical info for patient {patient_id}"},
            status=status.HTTP_400_BAD_REQUEST
        )
