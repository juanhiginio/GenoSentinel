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
import { TumorTypesService } from './tumor-types.service';
import { CreateTumorTypeDto } from './dto/create-tumor-type.dto';
import { UpdateTumorTypeDto } from './dto/update-tumor-type.dto';

@ApiTags('Tipos de Tumor')
@Controller('tumor-types')
export class TumorTypesController {
  constructor(private readonly tumorTypesService: TumorTypesService) {}

  @Post()
  @ApiOperation({ summary: 'Crear tipo de tumor' })
  create(@Body() createTumorTypeDto: CreateTumorTypeDto) {
    return this.tumorTypesService.create(createTumorTypeDto);
  }

  @Get()
  @ApiOperation({ summary: 'Listar catálogo de tumores' })
  findAll() {
    return this.tumorTypesService.findAll();
  }

  @Get(':id')
  @ApiOperation({ summary: 'Buscar tumor por ID' })
  findOne(@Param('id') id: string) {
    return this.tumorTypesService.findOne(+id); // El + convierte string a numero
  }

  @Patch(':id')
  @ApiOperation({ summary: 'Actualizar tipo de tumor' })
  update(
    @Param('id') id: string,
    @Body() updateTumorTypeDto: UpdateTumorTypeDto,
  ) {
    return this.tumorTypesService.update(+id, updateTumorTypeDto);
  }

  @Delete(':id')
  @ApiOperation({ summary: 'Eliminar tipo de tumor' })
  remove(@Param('id') id: string) {
    return this.tumorTypesService.remove(+id);
  }
}
