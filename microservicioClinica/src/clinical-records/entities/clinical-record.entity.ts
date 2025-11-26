import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  ManyToOne,
  JoinColumn,
  CreateDateColumn,
  UpdateDateColumn,
} from 'typeorm';
import { Patient } from '../../patients/entities/patient.entity';
import { TumorType } from '../../tumor-types/entities/tumor-type.entity';

@Entity('clinicalRecord')
export class ClinicalRecord {
  @PrimaryGeneratedColumn('uuid')
  id: string;

  // Columna explicita para poder guardar solo enviando el ID
  @Column({ name: 'patientId' })
  patientId: string;

  @ManyToOne(() => Patient)
  @JoinColumn({ name: 'patientId' })
  patient: Patient;

  // Columna explicita para poder guardar solo enviando el ID
  @Column({ name: 'tumorTypeId' })
  tumorTypeId: number;

  @ManyToOne(() => TumorType)
  @JoinColumn({ name: 'tumorTypeId' })
  tumorType: TumorType;

  @Column({ type: 'date', nullable: true })
  diagnosisDate: Date;

  @Column({ type: 'enum', enum: ['IIA', 'IV'], nullable: true })
  stage: string;

  @Column('text', { nullable: true })
  treatmentProtocol: string;

  @CreateDateColumn({ type: 'timestamp' })
  created_at: Date;

  @UpdateDateColumn({ type: 'timestamp' })
  updated_at: Date;
}
