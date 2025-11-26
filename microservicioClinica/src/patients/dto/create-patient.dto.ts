import {
  IsString,
  IsNotEmpty,
  IsDateString,
  IsIn,
  IsOptional,
} from 'class-validator';
import { ApiProperty } from '@nestjs/swagger';

export class CreatePatientDto {
  @ApiProperty({ example: 'Juan', description: 'Nombre del paciente' })
  @IsString()
  @IsNotEmpty()
  firstName: string;

  @ApiProperty({ example: 'Perez', description: 'Apellido del paciente' })
  @IsString()
  @IsNotEmpty()
  lastName: string;

  @ApiProperty({ example: '1990-05-15', required: false })
  @IsDateString()
  @IsOptional() // En tu SQL es NULL, así que puede no venir
  birthDate?: string;

  @ApiProperty({ example: 'Male', enum: ['Male', 'Female', 'Other'] })
  @IsString()
  @IsIn(['Male', 'Female', 'Other']) // Validación estricta según tu SQL
  gender: string;

  @ApiProperty({
    example: 'Activo',
    enum: ['Activo', 'Seguimiento'],
    default: 'Activo',
  })
  @IsString()
  @IsIn(['Activo', 'Seguimiento'])
  status: string;
}
