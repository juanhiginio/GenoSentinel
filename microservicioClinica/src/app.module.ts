import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { PatientsModule } from './patients/patients.module';
import { TumorTypesModule } from './tumor-types/tumor-types.module';
import { ClinicalRecordsModule } from './clinical-records/clinical-records.module';
import { Patient } from './patients/entities/patient.entity';
import { TumorType } from './tumor-types/entities/tumor-type.entity';
import { ClinicalRecord } from './clinical-records/entities/clinical-record.entity';

@Module({
  imports: [
    TypeOrmModule.forRoot({
      type: 'mysql',
      host: 'localhost',
      port: 3306,
      username: 'root', // VERIFICA TU USUARIO
      password: 'TRC12345', // VERIFICA TU CONTRASEÑA
      database: 'GenoClinic',
      entities: [Patient, TumorType, ClinicalRecord],
      synchronize: false,
    }),
    PatientsModule,
    TumorTypesModule,
    ClinicalRecordsModule,
  ],
})
export class AppModule {}
