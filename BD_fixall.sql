-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: registro_prueba_servicio
-- ------------------------------------------------------
-- Server version	8.0.41

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `datos_personales`
--

DROP TABLE IF EXISTS `datos_personales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `datos_personales` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cedula` varchar(255) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `telefono` varchar(255) NOT NULL,
  `usuario_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_h4shk9dqxf5ayh643u62isp66` (`cedula`),
  KEY `FK50woc2nobs1mumixhxllldwd8` (`usuario_id`),
  CONSTRAINT `FK50woc2nobs1mumixhxllldwd8` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datos_personales`
--

LOCK TABLES `datos_personales` WRITE;
/*!40000 ALTER TABLE `datos_personales` DISABLE KEYS */;
INSERT INTO `datos_personales` VALUES (1,'123456','Cra 3 # 2-18','uno','123456',1),(2,'123654','Cra 3 # 2-18','dos','3698574',2),(3,'654321','Cra 3 # 2-18','tres','654987',3),(4,'3548493','Cra 3 # 2-18','cuatro','321654',4),(5,'123456553','Cra 3 # 2-18','cinco','654321',5),(6,'1236542','Cra 3 # 2-18','siete','654321',6),(7,'5648941','Cra 3 # 2-18','sis','654987',7),(8,'8972135','Cra 3 # 2-18','Ocho','32165474',8),(9,'32816574','Cra 3 # 2-18','Nueve','654987',9);
/*!40000 ALTER TABLE `datos_personales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `datos_personales_especializaciones`
--

DROP TABLE IF EXISTS `datos_personales_especializaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `datos_personales_especializaciones` (
  `datos_personales_id` bigint NOT NULL,
  `especializacion_id` bigint NOT NULL,
  PRIMARY KEY (`datos_personales_id`,`especializacion_id`),
  KEY `FKirsvgtvh5k0pq35eowxnrlee9` (`especializacion_id`),
  CONSTRAINT `FK2xqplo83xn8akjpkynlyl07ev` FOREIGN KEY (`datos_personales_id`) REFERENCES `datos_personales` (`id`),
  CONSTRAINT `FKirsvgtvh5k0pq35eowxnrlee9` FOREIGN KEY (`especializacion_id`) REFERENCES `especializaciones` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `datos_personales_especializaciones`
--

LOCK TABLES `datos_personales_especializaciones` WRITE;
/*!40000 ALTER TABLE `datos_personales_especializaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `datos_personales_especializaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_servicio`
--

DROP TABLE IF EXISTS `detalle_servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_servicio` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) NOT NULL,
  `fecha_registro` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_servicio`
--

LOCK TABLES `detalle_servicio` WRITE;
/*!40000 ALTER TABLE `detalle_servicio` DISABLE KEYS */;
INSERT INTO `detalle_servicio` VALUES (4,'Reparación de celular ','2025-06-07 13:28:06'),(5,'Reparación de aire acondicionado','2025-06-07 13:45:44'),(6,'Instalación de aire acondicionado','2025-07-21 20:52:54');
/*!40000 ALTER TABLE `detalle_servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `especializaciones`
--

DROP TABLE IF EXISTS `especializaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `especializaciones` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_7gxk1f53ho9lq09ec5ih6bn1h` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `especializaciones`
--

LOCK TABLES `especializaciones` WRITE;
/*!40000 ALTER TABLE `especializaciones` DISABLE KEYS */;
INSERT INTO `especializaciones` VALUES (1,'Multi-Reparaciones'),(2,'Servicio Técnico de Celulares'),(4,'Servicio Técnico de Electrodomésticos'),(3,'Soporte Técnico Informático');
/*!40000 ALTER TABLE `especializaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado`
--

DROP TABLE IF EXISTS `estado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado`
--

LOCK TABLES `estado` WRITE;
/*!40000 ALTER TABLE `estado` DISABLE KEYS */;
INSERT INTO `estado` VALUES (1,'Pendiente'),(2,'En Proceso'),(3,'Completado'),(4,'Cancelado');
/*!40000 ALTER TABLE `estado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_43kr6s7bts1wqfv43f7jd87kp` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'ADMIN'),(2,'USUARIO');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicio_especializaciones`
--

DROP TABLE IF EXISTS `servicio_especializaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicio_especializaciones` (
  `servicio_id` bigint NOT NULL,
  `especializacion_id` bigint NOT NULL,
  PRIMARY KEY (`servicio_id`,`especializacion_id`),
  KEY `FK2sd42q42rjodu1cnwjrohh6jj` (`especializacion_id`),
  CONSTRAINT `FK2sd42q42rjodu1cnwjrohh6jj` FOREIGN KEY (`especializacion_id`) REFERENCES `especializaciones` (`id`),
  CONSTRAINT `FK3a06xo99jd0xxr06k43pyy43h` FOREIGN KEY (`servicio_id`) REFERENCES `servicios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicio_especializaciones`
--

LOCK TABLES `servicio_especializaciones` WRITE;
/*!40000 ALTER TABLE `servicio_especializaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `servicio_especializaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servicios`
--

DROP TABLE IF EXISTS `servicios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `servicios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `detalle_servicio_id` bigint NOT NULL,
  `estado_id` bigint NOT NULL,
  `tipo_servicio_id` bigint NOT NULL,
  `usuario_id` bigint NOT NULL,
  `tecnico_id` bigint DEFAULT NULL,
  `especializacion_id` bigint NOT NULL,
  `fecha_ultima_actualizacion` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKnxveg1ddbdb90byk5is7mg9rl` (`detalle_servicio_id`),
  KEY `FKgv9b7lfxgxpym5v3c7h1vdniw` (`estado_id`),
  KEY `FKfsuqj0sxrywumaphtxlc06msq` (`tipo_servicio_id`),
  KEY `FKmyixw936gjt5nb18lpmi80yxd` (`usuario_id`),
  KEY `FK8r37jiixjqk8is0hscfspuha7` (`tecnico_id`),
  KEY `FK385imwrn7xbc8wx80byp1wfu6` (`especializacion_id`),
  CONSTRAINT `FK385imwrn7xbc8wx80byp1wfu6` FOREIGN KEY (`especializacion_id`) REFERENCES `especializaciones` (`id`),
  CONSTRAINT `FK8r37jiixjqk8is0hscfspuha7` FOREIGN KEY (`tecnico_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `FKfsuqj0sxrywumaphtxlc06msq` FOREIGN KEY (`tipo_servicio_id`) REFERENCES `tipo_servicio` (`id`),
  CONSTRAINT `FKgv9b7lfxgxpym5v3c7h1vdniw` FOREIGN KEY (`estado_id`) REFERENCES `estado` (`id`),
  CONSTRAINT `FKmyixw936gjt5nb18lpmi80yxd` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`),
  CONSTRAINT `FKnxveg1ddbdb90byk5is7mg9rl` FOREIGN KEY (`detalle_servicio_id`) REFERENCES `detalle_servicio` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servicios`
--

LOCK TABLES `servicios` WRITE;
/*!40000 ALTER TABLE `servicios` DISABLE KEYS */;
INSERT INTO `servicios` VALUES (1,4,3,1,6,2,2,'2025-06-10 22:13:07'),(2,5,4,2,6,2,4,'2025-06-10 22:13:07'),(3,6,1,2,8,NULL,4,'2025-07-21 20:52:54');
/*!40000 ALTER TABLE `servicios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_servicio`
--

DROP TABLE IF EXISTS `tipo_servicio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_servicio` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_servicio`
--

LOCK TABLES `tipo_servicio` WRITE;
/*!40000 ALTER TABLE `tipo_servicio` DISABLE KEYS */;
INSERT INTO `tipo_servicio` VALUES (1,'Reparación'),(2,'Instalación'),(3,'Mantenimiento'),(4,'Diagnóstico');
/*!40000 ALTER TABLE `tipo_servicio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_usuario`
--

DROP TABLE IF EXISTS `tipo_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_usuario` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_6l2he8oq68541b8nx64khjbjv` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_usuario`
--

LOCK TABLES `tipo_usuario` WRITE;
/*!40000 ALTER TABLE `tipo_usuario` DISABLE KEYS */;
INSERT INTO `tipo_usuario` VALUES (1,'Cliente'),(2,'Técnico');
/*!40000 ALTER TABLE `tipo_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_especializaciones`
--

DROP TABLE IF EXISTS `usuario_especializaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_especializaciones` (
  `usuario_id` bigint NOT NULL,
  `especializacion_id` bigint NOT NULL,
  PRIMARY KEY (`usuario_id`,`especializacion_id`),
  KEY `FK36o9yqwjq01wk6su46u6tm8qt` (`especializacion_id`),
  CONSTRAINT `FK36o9yqwjq01wk6su46u6tm8qt` FOREIGN KEY (`especializacion_id`) REFERENCES `especializaciones` (`id`),
  CONSTRAINT `FKfrp3c2y1brduxgqaw06fqo4c3` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_especializaciones`
--

LOCK TABLES `usuario_especializaciones` WRITE;
/*!40000 ALTER TABLE `usuario_especializaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_especializaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `tipo_usuario_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_kfsp0s1tflm1cwlj8idhqsad0` (`email`),
  UNIQUE KEY `UKkfsp0s1tflm1cwlj8idhqsad0` (`email`),
  KEY `FKlax8oeu20d3lamsl4r77wnogo` (`tipo_usuario_id`),
  CONSTRAINT `FKlax8oeu20d3lamsl4r77wnogo` FOREIGN KEY (`tipo_usuario_id`) REFERENCES `tipo_usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'uno@gmail.com','$2a$10$HSdfjJlIUO.bvavQvVPcr.6orm64i2kpQEF1OpVME2idxPWM/M8YO',2),(2,'dos@gmail.com','$2a$10$u0JFryUxbaBKPG1qsa5UfO9Irx0tzE/hqs4Ysp8IReitV.9cYACiC',2),(3,'tres@gmail.com','$2a$10$b/ZaCyQOfmbBTKoWgGd4HuFswdJMtyZNV1JjaomwKKmh0CvQYod4G',2),(4,'cuatro@gmail.com','$2a$10$Z85oaJcNexe5LFGyPfLUIOm5HZUFssA0mPvS45bq/tYs5wFCUBOUO',2),(5,'cinco@gmail.com','$2a$10$jQDvKsPwOrAF9T0VRt5euudl.AxvQ7EQmP3rtfbT.RRox8vHLzwZy',2),(6,'siete@gmail.com','$2a$10$m/QqdBvcuPvRWM1MtGt8dOVR4vnHCXbhFY8L75TNVv9ENzYVNNrhe',1),(7,'seis@gmail.com','$2a$10$mgwPIZ.8KCCUDh2aKCQKJuyW2fXr4LWXPXZfIeeRxY8W3uC5Mv4s.',1),(8,'ocho@gmail.com','$2a$10$0zqfdyh.Nm6MsCl2k8TN0OBHxTs12Or1Tc4s.lkQjN4cBF5u0A8GC',1),(9,'nueve@gmail.com','$2a$10$LLJN66cJm37Gr6WGSDT.DOdbTyWYD3YaobXP5GGNJWJZVHvPv2Ixy',2);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios_roles`
--

DROP TABLE IF EXISTS `usuarios_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios_roles` (
  `usuario_id` bigint NOT NULL,
  `rol_id` bigint NOT NULL,
  PRIMARY KEY (`usuario_id`,`rol_id`),
  KEY `FK6yxg1lhuv5nievqea7rvn6afm` (`rol_id`),
  CONSTRAINT `FK6yxg1lhuv5nievqea7rvn6afm` FOREIGN KEY (`rol_id`) REFERENCES `rol` (`id`),
  CONSTRAINT `FKqcxu02bqipxpr7cjyj9dmhwec` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios_roles`
--

LOCK TABLES `usuarios_roles` WRITE;
/*!40000 ALTER TABLE `usuarios_roles` DISABLE KEYS */;
INSERT INTO `usuarios_roles` VALUES (1,2),(2,2),(3,2);
/*!40000 ALTER TABLE `usuarios_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-21 22:52:42
