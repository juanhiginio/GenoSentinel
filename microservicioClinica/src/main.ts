import { NestFactory } from '@nestjs/core';
import { ValidationPipe } from '@nestjs/common';
import { SwaggerModule, DocumentBuilder } from '@nestjs/swagger';
import { AppModule } from './app.module';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);

  // 1. Activar validación global usando los decoradores de los DTOs
  app.useGlobalPipes(
    new ValidationPipe({
      whitelist: true, // Elimina campos que no estén en el DTO
      forbidNonWhitelisted: true, // Lanza error si envían campos extra
    }),
  );

  // 2. Configurar Swagger
  const config = new DocumentBuilder()
    .setTitle('GenoSentinel - Microservicio Clínica')
    .setDescription('API para la gestión de información clínica oncológica')
    .setVersion('1.0')
    .build();
  const document = SwaggerModule.createDocument(app, config);
  // La documentación estará en /api
  SwaggerModule.setup('api', app, document);

  // Configuración para poder recibir peticiones desde cualquier URL
  app.enableCors({
    origin: '*',
    methods: '*',
    allowedHeaders: '*',
  });

  // El microservicio correrá en el puerto 4000
  await app.listen(4000);
  console.log(`Microservicio Clínica corriendo en: http://localhost:4000`);
  console.log(`Documentación Swagger en: http://localhost:4000/api`);
}
bootstrap().catch((err) => console.error(err));
