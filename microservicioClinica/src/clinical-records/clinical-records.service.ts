import { Injectable, NotFoundException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { CreateClinicalRecordDto } from './dto/create-clinical-record.dto';
import { UpdateClinicalRecordDto } from './dto/update-clinical-record.dto';
import { ClinicalRecord } from './entities/clinical-record.entity';

@Injectable()
export class ClinicalRecordsService {
  constructor(
    @InjectRepository(ClinicalRecord)
    private clinicalRecordRepository: Repository<ClinicalRecord>,
  ) {}

  async create(createClinicalRecordDto: CreateClinicalRecordDto) {
    // Al pasar el DTO con patientId y tumorTypeId, TypeORM automáticamente maneja las claves foráneas
    const newRecord = this.clinicalRecordRepository.create(
      createClinicalRecordDto,
    );
    return this.clinicalRecordRepository.save(newRecord);
  }

  findAll() {
    // IMPORTANTE: 'relations' trae los datos completos de Patient y TumorType
    return this.clinicalRecordRepository.find({
      relations: ['patient', 'tumorType'],
    });
  }

  async findOne(id: string) {
    const record = await this.clinicalRecordRepository.findOne({
      where: { id },
      relations: ['patient', 'tumorType'],
    });
    if (!record) throw new NotFoundException(`Clinical Record ${id} not found`);
    return record;
  }

  async update(id: string, updateClinicalRecordDto: UpdateClinicalRecordDto) {
    const record = await this.findOne(id);
    // Evitamos que intenten cambiar las relaciones (patientId, tumorTypeId) con un Patch simple
    if (updateClinicalRecordDto.patientId)
      delete updateClinicalRecordDto.patientId;
    if (updateClinicalRecordDto.tumorTypeId)
      delete updateClinicalRecordDto.tumorTypeId;

    this.clinicalRecordRepository.merge(record, updateClinicalRecordDto);
    return this.clinicalRecordRepository.save(record);
  }

  async remove(id: string) {
    const record = await this.findOne(id);
    return this.clinicalRecordRepository.remove(record);
  }
}
