import { Injectable, NotFoundException } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { CreateTumorTypeDto } from './dto/create-tumor-type.dto';
import { UpdateTumorTypeDto } from './dto/update-tumor-type.dto';
import { TumorType } from './entities/tumor-type.entity';

@Injectable()
export class TumorTypesService {
  constructor(
    @InjectRepository(TumorType)
    private tumorTypeRepository: Repository<TumorType>,
  ) {}

  create(createTumorTypeDto: CreateTumorTypeDto) {
    const newTumor = this.tumorTypeRepository.create(createTumorTypeDto);
    return this.tumorTypeRepository.save(newTumor);
  }

  findAll() {
    return this.tumorTypeRepository.find();
  }

  async findOne(id: number) {
    const tumor = await this.tumorTypeRepository.findOneBy({ id });
    if (!tumor)
      throw new NotFoundException(`TumorType with ID ${id} not found`);
    return tumor;
  }

  async update(id: number, updateTumorTypeDto: UpdateTumorTypeDto) {
    const tumor = await this.findOne(id);
    this.tumorTypeRepository.merge(tumor, updateTumorTypeDto);
    return this.tumorTypeRepository.save(tumor);
  }

  async remove(id: number) {
    const tumor = await this.findOne(id);
    return this.tumorTypeRepository.remove(tumor);
  }
}
