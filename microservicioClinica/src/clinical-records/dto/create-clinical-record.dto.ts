import {
  IsString,
  IsNotEmpty,
  IsDateString,
  IsUUID,
  IsInt,
  IsIn,
  IsOptional,
} from 'class-validator';
import { ApiProperty } from '@nestjs/swagger';

export class CreateClinicalRecordDto {
  @ApiProperty({ description: 'UUID del paciente existente' })
  @IsUUID()
  @IsNotEmpty()
  patientId: string;

  @ApiProperty({ description: 'ID numérico del tipo de tumor existente' })
  @IsInt()
  @IsNotEmpty()
  tumorTypeId: number;

  @ApiProperty({ example: '2023-10-20', required: false })
  @IsDateString()
  @IsOptional()
  diagnosisDate?: string;

  @ApiProperty({ example: 'IIA', enum: ['IIA', 'IV'] })
  @IsString()
  @IsIn(['IIA', 'IV'])
  stage: string;

  @ApiProperty({ example: 'Quimioterapia estándar', required: false })
  @IsString()
  @IsOptional()
  treatmentProtocol?: string;
}
