import { IsString, IsNotEmpty, IsOptional } from 'class-validator';
import { ApiProperty } from '@nestjs/swagger';

export class CreateTumorTypeDto {
  @ApiProperty({ example: 'Cancer de Mama' })
  @IsString()
  @IsNotEmpty()
  name: string;

  @ApiProperty({ example: 'Glandulas Mamarias', required: false })
  @IsString()
  @IsOptional() // En el SQL esta como NULL
  systemAffected?: string;
}
