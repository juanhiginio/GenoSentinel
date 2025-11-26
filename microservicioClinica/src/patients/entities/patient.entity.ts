import {
  Entity,
  PrimaryGeneratedColumn,
  Column,
  CreateDateColumn,
  UpdateDateColumn,
} from 'typeorm';

@Entity('patient') // Debe coincidir con tu tabla SQL
export class Patient {
  @PrimaryGeneratedColumn('uuid')
  id: string;

  @Column({ length: 150 })
  firstName: string;

  @Column({ length: 150 })
  lastName: string;

  @Column({ type: 'date', nullable: true })
  birthDate: Date;

  @Column({ type: 'enum', enum: ['Male', 'Female', 'Other'] })
  gender: string;

  @Column({ type: 'enum', enum: ['Activo', 'Seguimiento'], default: 'Activo' })
  status: string;

  @CreateDateColumn({ type: 'timestamp' })
  created_at: Date;

  @UpdateDateColumn({ type: 'timestamp' })
  updated_at: Date;
}
