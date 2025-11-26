import {
  Controller,
  Get,
  Post,
  Body,
  Patch,
  Param,
  Delete,
} from '@nestjs/common';
import { ApiTags, ApiOperation } from '@nestjs/swagger';
import { ClinicalRecordsService } from './clinical-records.service';
import { CreateClinicalRecordDto } from './dto/create-clinical-record.dto';
import { UpdateClinicalRecordDto } from './dto/update-clinical-record.dto';

@ApiTags('Historias Clínicas')
@Controller('clinical-records')
export class ClinicalRecordsController {
  constructor(
    private readonly clinicalRecordsService: ClinicalRecordsService,
  ) {}

  @Post()
  @ApiOperation({
    summary:
      'Crear una nueva historia clínica (requiere patientId y tumorTypeId existentes)',
  })
  create(@Body() createClinicalRecordDto: CreateClinicalRecordDto) {
    return this.clinicalRecordsService.create(createClinicalRecordDto);
  }

  @Get()
  @ApiOperation({
    summary:
      'Listar todas las historias clínicas con detalles de paciente y tumor',
  })
  findAll() {
    return this.clinicalRecordsService.findAll();
  }

  @Get(':id')
  @ApiOperation({ summary: 'Buscar historia clínica por ID (UUID)' })
  findOne(@Param('id') id: string) {
    return this.clinicalRecordsService.findOne(id);
  }

  @Patch(':id')
  @ApiOperation({ summary: 'Actualizar campos de una historia clínica' })
  update(
    @Param('id') id: string,
    @Body() updateClinicalRecordDto: UpdateClinicalRecordDto,
  ) {
    return this.clinicalRecordsService.update(id, updateClinicalRecordDto);
  }

  @Delete(':id')
  @ApiOperation({ summary: 'Eliminar una historia clínica' })
  remove(@Param('id') id: string) {
    return this.clinicalRecordsService.remove(id);
  }
}
