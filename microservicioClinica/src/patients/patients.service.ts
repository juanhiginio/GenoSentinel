import { Injectable, NotFoundException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { CreatePatientDto } from './dto/create-patient.dto';
import { UpdatePatientDto } from './dto/update-patient.dto';
import { Patient } from './entities/patient.entity';

@Injectable()
export class PatientsService {
  constructor(
    @InjectRepository(Patient)
    private patientsRepository: Repository<Patient>,
  ) {}

  create(createPatientDto: CreatePatientDto) {
    // Creamos la instancia. El ID se generará automáticamente por el Trigger de SQL o TypeORM
    const newPatient = this.patientsRepository.create(createPatientDto);
    return this.patientsRepository.save(newPatient);
  }

  findAll() {
    return this.patientsRepository.find();
  }

  async findOne(id: string) {
    const patient = await this.patientsRepository.findOneBy({ id });
    if (!patient)
      throw new NotFoundException(`Patient with ID ${id} not found`);
    return patient;
  }

  async update(id: string, updatePatientDto: UpdatePatientDto) {
    const patient = await this.findOne(id);
    // Merge actualiza solo los campos que vienen en el DTO
    this.patientsRepository.merge(patient, updatePatientDto);
    return this.patientsRepository.save(patient);
  }

  async remove(id: string) {
    const patient = await this.findOne(id);
    return this.patientsRepository.remove(patient);
  }
}
