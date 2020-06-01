CREATE DATABASE  IF NOT EXISTS `institut` /*!40100 DEFAULT CHARACTER SET latin1 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `institut`;
-- MySQL dump 10.13  Distrib 8.0.18, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: institut
-- ------------------------------------------------------
-- Server version	8.0.18

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
-- Table structure for table `alumnos`
--

DROP TABLE IF EXISTS `alumnos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alumnos` (
  `id` varchar(10) NOT NULL,
  `NIA` varchar(10) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `apellido1` varchar(50) DEFAULT NULL,
  `apellido2` varchar(50) DEFAULT NULL,
  `fecha_nac` varchar(10) DEFAULT NULL,
  `municipio_nac` varchar(5) DEFAULT NULL,
  `municipio_nac_ext` varchar(5) DEFAULT NULL,
  `provincia_nac` varchar(5) DEFAULT NULL,
  `pais_nac` varchar(5) DEFAULT NULL,
  `nacionalidad` varchar(5) DEFAULT NULL,
  `sexo` varchar(1) DEFAULT NULL,
  `tipo_doc` varchar(1) DEFAULT NULL,
  `documento` varchar(10) DEFAULT NULL,
  `expediente` varchar(5) DEFAULT NULL,
  `libro_escolaridad` varchar(5) DEFAULT NULL,
  `cod_postal` varchar(5) DEFAULT NULL,
  `tipo_via` varchar(5) DEFAULT NULL,
  `domicilio` varchar(100) DEFAULT NULL,
  `numero` varchar(3) DEFAULT NULL,
  `puerta` varchar(2) DEFAULT NULL,
  `escalera` varchar(2) DEFAULT NULL,
  `letra` varchar(2) DEFAULT NULL,
  `piso` varchar(2) DEFAULT NULL,
  `provincia` varchar(5) DEFAULT NULL,
  `municipio` varchar(5) DEFAULT NULL,
  `localidad` varchar(10) DEFAULT NULL,
  `telefono1` varchar(20) DEFAULT NULL,
  `telefono2` varchar(20) DEFAULT NULL,
  `telefono3` varchar(20) DEFAULT NULL,
  `email1` varchar(50) DEFAULT NULL,
  `email2` varchar(50) DEFAULT NULL,
  `sip` varchar(10) DEFAULT NULL,
  `observaciones` varchar(150) DEFAULT NULL,
  `ampa` varchar(1) DEFAULT NULL,
  `seguro` varchar(1) DEFAULT NULL,
  `dictamen` varchar(10) DEFAULT NULL,
  `fecha_resolucion` varchar(10) DEFAULT NULL,
  `informe_psicoped` varchar(10) DEFAULT NULL,
  `informado_posib` varchar(10) DEFAULT NULL,
  `fecha_matricula` varchar(10) DEFAULT NULL,
  `fecha_ingreso_centro` varchar(10) DEFAULT NULL,
  `estado_matricula` varchar(5) DEFAULT NULL,
  `tipo_matricula` varchar(5) DEFAULT NULL,
  `repite` varchar(5) DEFAULT NULL,
  `num_repeticion` varchar(5) DEFAULT NULL,
  `ensenanza` varchar(2) DEFAULT NULL,
  `curso` varchar(15) DEFAULT NULL,
  `grupo` varchar(10) DEFAULT NULL,
  `turno` varchar(2) DEFAULT NULL,
  `linea` varchar(2) DEFAULT NULL,
  `trabaja` varchar(1) DEFAULT NULL,
  `fuera_comunidad` varchar(1) DEFAULT NULL,
  `matricula_parcial` varchar(1) DEFAULT NULL,
  `matricula_condic` varchar(1) DEFAULT NULL,
  `informe_medico` varchar(1) DEFAULT NULL,
  `banco` varchar(4) DEFAULT NULL,
  `sucursal` varchar(4) DEFAULT NULL,
  `digito_control` varchar(2) DEFAULT NULL,
  `cuenta` varchar(12) DEFAULT NULL,
  `modalidad` varchar(5) DEFAULT NULL,
  `iban` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `curso` (`curso`),
  KEY `grupo` (`grupo`),
  CONSTRAINT `alumnoCurso` FOREIGN KEY (`curso`) REFERENCES `cursos` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  CONSTRAINT `alumnoGrupo` FOREIGN KEY (`grupo`) REFERENCES `grupos` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumnos`
--

LOCK TABLES `alumnos` WRITE;
/*!40000 ALTER TABLE `alumnos` DISABLE KEYS */;
INSERT INTO `alumnos` VALUES ('10238383','10238383','JOSE ANTONIO','GALÁN','SIMÓ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'5576 ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1949542197','1CFSN',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),('11472519','11472519','RAÚL','BOIX','PALOMARES',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'5554 ',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'1949542197','1CFSN',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `alumnos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contenido`
--

DROP TABLE IF EXISTS `contenido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contenido` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `curso` varchar(10) NOT NULL,
  `codigo` varchar(15) NOT NULL,
  `ensenanza` varchar(2) NOT NULL,
  `nombre_cas` varchar(100) NOT NULL,
  `nombre_val` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `curso_rel` (`curso`),
  CONSTRAINT `contenidoCurso` FOREIGN KEY (`curso`) REFERENCES `cursos` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1476 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contenido`
--

LOCK TABLES `contenido` WRITE;
/*!40000 ALTER TABLE `contenido` DISABLE KEYS */;
INSERT INTO `contenido` VALUES (1261,'1949539264','BGLOMCE','3','Biología y Geología','Biologia i Geologia'),(1262,'1949539264','VLLLOMCE','3','Valenciano: Lengua y Literatura','Valencià: Llengua i Literatura'),(1263,'1949539264','EFLOMCE','3','Educación Física','Educació Física'),(1264,'1949539264','FQLOMCE','3','Física y Química','Física i Química'),(1265,'1949539264','RELLOMCE','3','Religión','Religió'),(1266,'1949539264','GHLOMCE','3','Geografía e Historia','Geografia i Història'),(1267,'1949539264','LCLLOMCE','3','Lengua Castellana y Literatura','Llengua Castellana i Literatura'),(1268,'1949539264','VELOMCE','3','Valores Éticos','Valors Ètics'),(1269,'1949539264','IAEELOMCE','3','Iniciación a la actividad emprendedora y empresarial','Iniciació a l\'activitat emprenedora i empresarial'),(1270,'1949539264','MUSLOMCE','3','Música','Música'),(1271,'1949539264','MACLOMCE','3','Matemáticas orientadas a las enseñanzas académicas','Matemàtiques orientades a les ensenyances acadèmiques'),(1272,'1949539264','TECLOMCE','3','Tecnología','Tecnologia'),(1273,'1949539264','SLELOMCE','3','Segunda Lengua Extranjera','Segona Llengua Estrangera'),(1274,'1949539264','MAPLOMCE','3','Matemáticas orientadas a las enseñanzas aplicadas','Matemàtiques orientades a las ensenyances aplicades'),(1275,'1949539264','EPVALOMCE','3','Educación plástica, visual y audiovisual','Educació plàstica, visual i audiovisual'),(1276,'1949539264','PLELOMCE','3','Primera Lengua Extranjera','Primera Llengua Estrangera'),(1277,'1949539264','CCLOMCE','3','Cultura Clásica','Cultura Clàssica'),(1278,'1949539264','TUTLOMCE','3','Tutoría','Tutoria'),(1279,'1949539264','ECOLOMCE','3','Economía','Economia'),(1280,'1949539264','LLATLOMCE','3','Latín','Llatí'),(1281,'1949539264','CAAPLOMCE','3','Ciencias aplicadas a la actividad profesional','Ciències aplicades a l\'activitat professional'),(1282,'1949539264','AEDLOMCE','3','Artes escénicas y Danza','Arts escèniques i Dansa'),(1283,'1949539264','CCILOMCE','3','Cultura científica','Cultura científica'),(1284,'1949539264','FILLOMCE','3','Filosofía','Filosofia'),(1285,'1949539264','TICLOMCE','3','Tecnologías de la información y la comunicación','Tecnologies de la informació i la comunicació'),(1286,'1949539264','BGLOMCEEO','3','Biología y Geología','Biologia i Geologia'),(1287,'1949539264','FQLOMCEEO','3','Física y Química','Física i Química'),(1288,'1949539264','ECOLOMCEEO','3','Economía','Economia'),(1289,'1949539264','LLATLOMCEEO','3','Latín','Llatí'),(1290,'1949539264','TECLOMCEEO','3','Tecnología','Tecnologia'),(1291,'1949539264','CAAPLOMCEEO','3','Ciencias aplicadas a la actividad profesional','Ciències aplicades a l\'activitat professional'),(1292,'1949539264','IAEELOMCEEO','3','Iniciación a la actividad emprendedora y empresarial','Iniciació a l\'activitat emprenedora i empresarial'),(1293,'1949539264','TESPLOM','3','Tutoría Específica','Tutoria Específica'),(1294,'1949539264','TRLOMCE','3','Taller de Refuerzo','Taller de Reforç'),(1295,'1949539264','TALOMCE','3','Taller de Profundización','Taller d\'Aprofundiment'),(1296,'1949539264','PILOMCE','3','Proyecto Interdisciplinario','Projecte Interdisciplinari'),(1297,'1949539264','CCOLOMCE','3','Competencia Comunicativa Oral Primera Lengua Extranjera','Competència Comunicativa Oral Primera Llengua Estrangera'),(1298,'1949539245','INFLOMCE','3','Informática','Informàtica'),(1299,'1949539245','IAEELOMCELC','3','Iniciación a la actividad emprendedora y empresarial','Iniciació a l\'activitat emprenedora i empresarial'),(1300,'1949539245','SLELOMCELC','3','Segunda Lengua Extranjera','Segona Llengua Estrangera'),(1301,'1949539245','CCLOMCELC','3','Cultura Clásica','Cultura Clàssica'),(1302,'1949539245','TECLOMCELC','3','Tecnología','Tecnologia'),(1303,'1949539245','ALSLOMQE','3','Ámbito Lingüístico y Social','Àmbit Lingüístic i Social'),(1304,'1949539245','ACMLOMQE','3','Ámbito Científico y Matemático','Àmbit Científic i Matemàtic'),(1305,'1949539245','ALELOMQE','3','Ámbito Lenguas Extranjeras','Àmbit Llengües Estrangeres'),(1306,'1949539212','MATLOMCE','3','Matemáticas','Matemàtiques'),(1307,'1949539199','RMATLOMCE','3','Refuerzo instrumental Matemáticas','Reforç instrumental Matemàtiques'),(1308,'1949539199','RLCLLOMCE','3','Refuerzo instrumental Castellano','Reforç instrumental Castellà'),(1309,'1949539199','RVLLLOMCE','3','Refuerzo instrumental Valenciano','Reforç instrumental Valencià'),(1310,'1949539199','EPVALOMCELC','3','Educación plástica, visual y audiovisual','Educació plàstica, visual i audiovisual'),(1311,'1949540513','RELLOMQE','4','Religión','Religió'),(1312,'1949540513','TUTLOMQE','4','Tutoría','Tutoria'),(1313,'1949540513','HESLOMQE','4','Historia de España','Història d\'Espanya'),(1314,'1949540513','LCL2LOMQE','4','Lengua Castellana y Literatura II','Llengua Castellana i Literatura II'),(1315,'1949540513','PLE2LOMQE','4','Primera lengua extranjera II','Primera llengua estrangera II'),(1316,'1949540513','VLL2LOMQE','4','Valenciano: lengua y literatura II','Valencià: llengua i literatura II'),(1317,'1949540513','LLA2LOMQE','4','Latín II','Llatí II'),(1318,'1949540513','MATCS2LOMQE','4','Matemáticas aplicadas a las Ciencias Sociales II','Matemàtiques aplicades a les Ciències Socials II'),(1319,'1949540513','GRE2LOMQE','4','Griego II','Grec II'),(1320,'1949540513','ECOEMLOMQE','4','Economía de la empresa','Economia de l\'empresa'),(1321,'1949540513','GEOGLOMQE','4','Geografía','Geografia'),(1322,'1949540513','HARTLOMQE','4','Historia del Arte','Història de l\'Art'),(1323,'1949540513','HFILLOMQE','4','Historia de la Filosofía','Història de la Filosofia'),(1324,'1949540513','AMU2LOMQE','4','Análisis musical II','Anàlisi musical II'),(1325,'1949540513','SLE2LOMQE','4','Segunda lengua extranjera II','Segona llengua estrangera II'),(1326,'1949540513','TIC2LOMQE','4','Tecnologías de la Información y de la Comunicación II','Tecnologies de la Informació i de la Comunicació II'),(1327,'1949540513','TECI2LOMQE','4','Tecnología Industrial II','Tecnologia Industrial II'),(1328,'1949540513','CTMLOMQE','4','Ciencias de la Tierra y del Medio Ambiente','Ciències de la Terra i del Medi Ambient'),(1329,'1949540513','FAGLOMQE','4','Fundamentos de administración y gestión ','Fonaments d\'administració i gestió'),(1330,'1949540513','PSILOMQE','4','Psicología','Psicologia'),(1331,'1949540513','TEGLOMQE','4','Técnicas de expresión graficoplástica','Tècniques d\'expressió graficoplàstica'),(1332,'1949540513','IMSLOMQE','4','Imagen y Sonido','Imatge i So'),(1333,'1949540513','FISLOMQEEO','4','Física','Física'),(1334,'1949540513','QUILOMQEEO','4','Química','Química'),(1335,'1949540513','DT2LOMQEEO','4','Dibujo Técnico II','Dibuix Tècnic II'),(1336,'1949540513','BIOLOMQEEO','4','Biología','Biologia'),(1337,'1949540513','GEOLOMQEEO','4','Geología','Geologia'),(1338,'1949540513','LLA2LOMQEEO','4','Latín II','Llatí II'),(1339,'1949540513','GRE2LOMQEEO','4','Griego II','Grec II'),(1340,'1949540513','MATCS2LOMQEO','4','Matemáticas aplicadas a las CCSS II','Matemàtiques aplicades a les CCSS II'),(1341,'1949540513','ECOEMLOMQEEO','4','Economía de la empresa','Economia de l\'empresa'),(1342,'1949540513','GEOGLOMQEEO','4','Geografía','Geografia'),(1343,'1949540513','HARTLOMQEEO','4','Historia del Arte','Història de l\'Art'),(1344,'1949540513','HFILLOMQEEO','4','Historia de la Filosofía','Història de la Filosofia'),(1345,'1949540513','FA2LOMQEEO','4','Fundamentos del Arte II','Fonaments de l\'Art II'),(1346,'1949540513','CA2LOMQEEO','4','Cultura audiovisual II','Cultura audiovisual II'),(1347,'1949540513','AELOMQEEO','4','Artes escénicas','Arts escèniques'),(1348,'1949540513','DISLOMQEEO','4','Diseño','Disseny'),(1349,'1949540513','HMDLOMQE','4','Historia de la Música y de la Danza','Història de la Música i de la Dansa'),(1350,'1949540513','DA2LOMQE','4','Dibujo artístico II','Dibuix artístic II'),(1351,'1949540513','MAT2LOMQEEO','4','Matemáticas II','Matemàtiques II'),(1352,'1949540513','HFILLOMQEEOB','4','Historia de la Filosofía','Història de la Filosofia'),(1353,'1949540513','EFSLOMQE','4','Educación Físicodeportiva y Salud','Educació Físicoesportiva i Salut'),(1354,'1949540524','FILLOMQE','4','Filosofía','Filosofia'),(1355,'1949540524','MATCS1LOMQE','4','Matemáticas aplicadas a las CCSS I','Matemàtiques aplicades a les CCSS I'),(1356,'1949540524','ECOLOMQE','4','Economía','Economia'),(1357,'1949540524','HMCLOMQE','4','Historia del Mundo Contemporáneo','Història del Món Contemporani'),(1358,'1949540524','LCL1LOMQE','4','Lengua Castellana y Literatura I','Llengua Castellana i Literatura I'),(1359,'1949540524','LULOMQE','4','Literatura Universal','Literatura Universal'),(1360,'1949540524','VLL1LOMQE','4','Valenciano: Lengua y Literatura I','Valencià: Llengua i Literatura I'),(1361,'1949540524','PLE1LOMQE','4','Primera Lengua Extranjera I','Primera Llengua Estrangera I'),(1362,'1949540524','EFLOMQE','4','Educación Física','Educació Física'),(1363,'1949540524','AALOMQE','4','Anatomía Aplicada','Anatomia Aplicada'),(1364,'1949540524','CC1LOMQE','4','Cultura Científica ','Cultura Científica '),(1365,'1949540524','LLA1LOMQE','4','Latín I','Llatí I'),(1366,'1949540524','AMU1LOMQE','4','Análisis Musical I','Anàlisi Musical I'),(1367,'1949540524','GRE1LOMQE','4','Griego I','Grec I'),(1368,'1949540524','DA1LOMQE','4','Dibujo Artístico I','Dibuix Artístic I'),(1369,'1949540524','LPMLOMQE','4','Lenguaje y Práctica Musical','Llenguatge i Pràctica Musical'),(1370,'1949540524','SLE1LOMQE','4','Segunda Lengua Extranjera I','Segona Llengua Estrangera I'),(1371,'1949540524','MAT1LOMQEEO','4','Matemáticas I','Matemàtiques I'),(1372,'1949540524','FQLOMQEEO','4','Física y Química','Física i Química'),(1373,'1949540524','DT1LOMQEEO','4','Dibujo Técnico I','Dibuix Tècnic I'),(1374,'1949540524','TECI1LOMQE','4','Tecnología Industrial I','Tecnologia Industrial I'),(1375,'1949540524','LLA1LOMQEEO','4','Latín I','Llatí I'),(1376,'1949540524','HMCLOMQEEO','4','Historia del Mundo Contemporáneo','Història del Món Contemporani'),(1377,'1949540524','BGLOMQEEO','4','Biología y Geología','Biologia i Geologia'),(1378,'1949540524','MCS1LOMQEEO','4','Matemáticas aplicadas a las CCSS I','Matemàtiques aplicades a les CCSS I'),(1379,'1949540524','GRE1LOMQEEO','4','Griego I','Grec I'),(1380,'1949540524','TIC1LOMQE','4','Tecnologías de la Información y la Comunicación I','Tecnologies de la Informació i la Comunicació I'),(1381,'1949540524','ECOLOMQEEO','4','Economía','Economia'),(1382,'1949540524','LULOMQEEO','4','Literatura Universal','Literatura Universal'),(1383,'1949540524','CA1LOMQEEO','4','Cultura Audiovisual I','Cultura Audiovisual I'),(1384,'1949540524','FA1LOMQEEO','4','Fundamentos del Arte I','Fonaments de l\'Art I'),(1385,'1949540524','VOLLOMQE','4','Volumen','Volum'),(1386,'1949540389','MAT1LOMQE','4','Matemáticas I','Matemàtiques I'),(1387,'1949540389','FQLOMQE','4','Física y Química','Física i Química'),(1388,'1949540389','DT1LOMQE','4','Dibujo Técnico I','Dibuix Tècnic I'),(1389,'1949540389','BGLOMQE','4','Biología y Geología','Biologia i Geologia'),(1390,'1949540403','MAT2LOMQE','4','Matemáticas II','Matemàtiques II'),(1391,'1949540403','FISLOMQE','4','Física','Física'),(1392,'1949540403','QUILOMQE','4','Química','Química'),(1393,'1949540403','DT2LOMQE','4','Dibujo Técnico II','Dibuix Tècnic II'),(1394,'1949540403','BIOLOMQE','4','Biología','Biologia'),(1395,'1949540403','GEOLOMQE','4','Geología','Geologia'),(1396,'1949542147','TU02CF','5','Tutoría Segundo','Tutoria Segon'),(1397,'1949542147','CV0002','5','Inglés Técnico II-M / Horario reservado para la docencia en inglés','Anglés Tècnic II-M / Horari reservat per a la docència en anglés'),(1398,'1949542147','0230','5','Empresa e iniciativa emprendedora','Empresa i iniciativa emprenedora'),(1399,'1949542147','0228','5','Aplicaciones web','Aplicacions web'),(1400,'1949542147','0226','5','Seguridad informática','Seguretat informàtica'),(1401,'1949542147','0224','5','Sistemas operativos en red','Sistemes operatius en xarxa'),(1402,'1949542147','0231','5','Formación en Centros de Trabajo','Formació en Centres de Treball'),(1403,'1949542147','0227','5','Servicios en red','Servicis en xarxa'),(1404,'1949542140','TU01CF','5','Tutoría Primero','Tutoria Primer'),(1405,'1949542140','CV0001','5','Inglés Técnico I-M / Horario reservado para la docencia en inglés','Anglés Tècnic I-M / Horari reservat per a la docència en anglés'),(1406,'1949542140','0225','5','Redes locales','Xarxes locals'),(1407,'1949542140','0223','5','Aplicaciones ofimáticas','Aplicacions ofimàtiques'),(1408,'1949542140','0221','5','Montaje y mantenimiento de equipos','Muntatge i manteniment d\'equips'),(1409,'1949542140','0229','5','Formación y orientación laboral','Formació i orientació laboral'),(1410,'1949542140','0222','5','Sistemas operativos monopuesto','Sistemes operatius monolloc'),(1411,'1949542076','3012e','5','Lengua Extranjera II','Llengua Estrangera II'),(1412,'1949542076','3012','5','Comunicación y Sociedad II','Comunicació i Societat II'),(1413,'1949542076','3012s','5','Ciencias Sociales II','Ciències Socials II'),(1414,'1949542076','3012cv','5','Castellano-Valenciano II','Castellà-Valencià II'),(1415,'1949542076','CV0006','5','Formación y Orientación Laboral II','Formació i Orientació Laboral II'),(1416,'1949542076','3019','5','Ciencias Aplicadas II','Ciències Aplicades II'),(1417,'1949542076','3016','5','Instalación y mantenimiento de redes para transmisión de datos','Instal·lació i manteniment de xarxes per a transmissió de dades'),(1418,'1949542076','3030','5','Operaciones auxiliares para la configuración y la explotación','Operacions auxiliars per a la configuració i l\'explotació'),(1419,'1949542076','3033','5','Formación en centros de trabajo','Formació en centres de treball'),(1420,'1949542069','3031','5','Ofimática y archivo de documentos','Ofimàtica i arxiu de documents'),(1421,'1949542069','3029','5','Montaje y mantenimiento de sistemas y componentes informáticos','Muntatge i manteniment de sistemes i components informàtics'),(1422,'1949542069','CV0005','5','Formación y orientación laboral ','Formació i orientació laboral '),(1423,'1949542069','3009','5','Ciencias Aplicadas I','Ciències aplicades I'),(1424,'1949542069','3011','5','Comunicación y Sociedad I','Comunicació i societat I'),(1425,'1949542069','3011s','5','Ciencias Sociales ','Ciències Socials '),(1426,'1949542069','3011e','5','Lengua Extranjera','Llengua Estrangera'),(1427,'1949542069','3011cv','5','Castellano-Valenciano','Castellà-Valencià'),(1428,'1949542204','CV0004','5','Inglés Técnico II-S / Horario reservado para la docencia en inglés','Anglés Tècnic II-S / Horari reservat per a la docència en anglés'),(1429,'1949542204','0495','5','Formación en centros de trabajo','Formació en centres de treball'),(1430,'1949542204','0492','5','Proyecto de desarrollo de aplicaciones multiplataforma','Projecte de desenrotllament d\'aplicacions multiplataforma'),(1431,'1949542204','0494','5','Empresa e iniciativa emprendedora','Empresa i iniciativa emprenedora'),(1432,'1949542204','0491','5','Sistemas de gestión empresarial','Sistemes de gestió empresarial'),(1433,'1949542204','0490','5','Programación de servicios y procesos','Programació de servicis i processos'),(1434,'1949542204','0489','5','Programación multimedia y dispositivos móviles','Programació multimèdia i dispositius mòbils'),(1435,'1949542204','0488','5','Desarrollo de interfaces','Desenrotllament d\'interfícies'),(1436,'1949542204','0486','5','Acceso a datos','Accés a dades'),(1437,'1949542197','CV0003','5','Inglés Técnico I-S / Horario reservado para la docencia en inglés','Inglés Técnico I-S / Horario reservado para la docencia en inglés'),(1438,'1949542197','0373','5','Lenguajes de marcas y sistemas de gestión de información','Llenguatges de marques i sistemes de gestió d\'informació'),(1439,'1949542197','0487','5','Entornos de desarrollo','Entorns de desenrotllament'),(1440,'1949542197','0485','5','Programación','Programació'),(1441,'1949542197','0484','5','Bases de Datos','Bases de Dades'),(1442,'1949542197','0483','5','Sistemas informáticos','Sistemes informàtics'),(1443,'1949542197','0493','5','Formación y orientación laboral','Formació i orientació laboral'),(1444,'1949545378','3018','5','Formación en centros de trabajo','Formació en centres de treball'),(1445,'1949545378','3018s','5','Formación en centros de trabajo (UF2)','Formació en centres de treball (UF2)'),(1446,'1949545378','3014','5','Instalaciones de telecomunicaciones','Instal·lacions de telecomunicacions'),(1447,'1949545371','3015','5','Equipos eléctricos y electrónicos','Equips elèctrics i electrònics.'),(1448,'1949545371','3013','5','Instalaciones eléctricas y domóticas','Instal.lacions elèctriques i domòtiques'),(1449,'1949545371','3018p','5','Formación en centros de trabajo (UF1)','Formació en centres de treball (UF1)'),(1450,'1949544417','0655','5','Gestión logística y comercial','Gestió logística i comercial'),(1451,'1949544417','0657','5','Proyecto de Administración y Finanzas','Projecte d\'Administració i finances'),(1452,'1949544417','0652','5','Gestión de recursos humanos','Gestió de recursos humans'),(1453,'1949544417','0653','5','Gestión financiera','Gestió financera '),(1454,'1949544417','0656','5','Simulación empresarial','Simulació empresarial'),(1455,'1949544417','0660','5','Formación en Centros de Trabajo','Formació en Centres de Treball'),(1456,'1949544417','0654','5','Contabilidad y fiscalidad','Comptabilitat i fiscalitat'),(1457,'1949544424','0179','5','Inglés','Anglés'),(1458,'1949544424','0651','5','Comunicación y atención al cliente','Comunicació i atenció al client'),(1459,'1949544424','0658','5','Formación y orientación laboral','Formació i orientació laboral'),(1460,'1949544424','0650','5','Proceso integral de la actividad comercial','Procés integral de l\'activitat comercial'),(1461,'1949544424','0648','5','Recursos humanos y responsabilidad social corporativa','Recursos humans i responsabilitat social corporativa '),(1462,'1949544424','0649','5','Ofimática y proceso de la información','Ofimàtica i procés de la informació'),(1463,'1949544424','0647','5','Gestión de la documentación jurídica y empresarial','Gestió de la documentació jurídica i empresarial'),(1464,'1949544340','0446','5','Empresa en el aula','Empresa en l\'aula'),(1465,'1949544340','0443','5','Tratamiento de la documentación contable','Tractament de la documentació comptable'),(1466,'1949544340','0442','5','Operaciones administrativas de recursos humanos','Operacions administratives de recursos humans'),(1467,'1949544340','0451','5','Formación en centros de trabajo','Formació en centres de treball'),(1468,'1949544340','0448','5','Operaciones auxiliares de gestión de tesorería','Operacions auxiliars de gestió de tresoreria'),(1469,'1949544333','0437','5','Comunicación empresarial y atención al cliente','Comunicació empresarial i atenció al client'),(1470,'1949544333','0449','5','Formación y orientación laboral','Formació i orientació laboral'),(1471,'1949544333','0441','5','Técnica contable','Tècnica comptable'),(1472,'1949544333','0439','5','Empresa y administración','Empresa i administració'),(1473,'1949544333','0438','5','Operaciones administrativa de compra-venta','Operacions administrativa de compra-venda'),(1474,'1949544333','0440','5','Tratamiento informático de la información ','Tractament informàtic de la informació '),(1475,'1949544333','0156','5','Inglés','Anglés');
/*!40000 ALTER TABLE `contenido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cursos`
--

DROP TABLE IF EXISTS `cursos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cursos` (
  `id` varchar(10) NOT NULL,
  `codigo` varchar(15) NOT NULL,
  `ensenanza` varchar(2) NOT NULL,
  `abreviatura` varchar(10) NOT NULL,
  `nombre_cas` varchar(100) NOT NULL,
  `nombre_val` varchar(100) NOT NULL,
  `idPadre` varchar(100) DEFAULT NULL,
  `curso_escolar` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idPadreReflexiva_idx` (`idPadre`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursos`
--

LOCK TABLES `cursos` WRITE;
/*!40000 ALTER TABLE `cursos` DISABLE KEYS */;
INSERT INTO `cursos` VALUES ('1949539185','1949539185','3','3ESO','Tercero','Tercer',' ',2018),('1949539199','1949539199','3','1ESO','Primero','Primer',' ',2018),('1949539212','1949539212','3','2ESO','Segundo','Segon',' ',2018),('1949539224','1949539224','3','4ESO','Cuarto','Quart',' ',2018),('1949539243','1949539243','3','PMAR','Programa de Mejora del aprendizaje y del rendimiento','Programa de millora del aprenentatge i del rendiment',' ',2018),('1949539245','1949539245','3','3ESO','3º ESO - PMAR Ámbitos','3r ESO - PMAR Àmbits','1949539243',2018),('1949539262','1949539262','3','PR4','Programa de Refuerzo Cuarto','Programa de Reforç Quart',' ',2018),('1949539264','1949539264','3','4ESO','4ESO (PR4)','4ESO (PR4)','1949539262',2018),('1949540387','1949540387','4','BAC','BACHILLERATO DE CIENCIAS','BATXILLERAT DE CIÈNCIES',' ',2018),('1949540389','1949540389','4','1BAC','PRIMERO BACH CIENCIAS ','PRIMER BATX CIÈNCIES ','1949540387',2018),('1949540403','1949540403','4','2BAC','SEGUNDO BACH CIENCIAS','SEGON BATX CIÈNCIES','1949540387',2018),('1949540511','1949540511','4','BAH','BACHILLERATO DE HUMANIDADES Y CIENCIAS SOCIALES','BATXILLERAT D\'HUMANITATS I CIÈNCIES SOCIALS',' ',2018),('1949540513','1949540513','4','2BAH','SEGUNDO BACH HUMANIDADES Y CIENCIAS SOCIALES','SEGON BATX HUMANITATS I CIÈNCIES SOCIALS','1949540511',2018),('1949540524','1949540524','4','1BAH','PRIMERO BACH HUMANIDADES Y CIENCIAS SOCIALES','PRIMER BATX. HUMANITATS I CIÈNCIES SOCIALS','1949540511',2018),('1949542042','1949542042','5','190','INFORMÁTICA Y COMUNICACIONES','INFORMÀTICA I COMUNICACIONS',' ',2018),('1949542044','1949542044','5','FPB','FP BÁSICA','FP BÀSICA','1949542042',2018),('1949542067','1949542067','5','97210A','INFORMÁTICA DE OFICINA','INFORMÀTICA D\'OFICINA','1949542044',2018),('1949542069','1949542069','5','1CFB','Primero','Primer','1949542067',2018),('1949542076','1949542076','5','2CFB','Segundo','Segon','1949542067',2018),('1949542114','1949542114','5','GM','GRADO MEDIO','GRAU MITJÀ','1949542042',2018),('1949542138','1949542138','5','707103','SISTEMAS MICROINFORMÁTICOS Y REDES','SISTEMES MICROINFORMÀTICS I XARXES','1949542114',2018),('1949542140','1949542140','5','1CFM','Primero','Primer','1949542138',2018),('1949542147','1949542147','5','2CFM','Segundo','Segon','1949542138',2018),('1949542164','1949542164','5','GS','GRADO SUPERIOR','GRAU SUPERIOR','1949542042',2018),('1949542195','1949542195','5','836104','DESARROLLO DE APLICACIONES MULTIPLATAFORMA','DESENROTLLAMENT D\'APLICACIONS MULTIPLATAFORMA','1949542164',2018),('1949542197','1949542197','5','1CFS','Primero','Primer','1949542195',2018),('1949542204','1949542204','5','2CFS','Segundo','Segon','1949542195',2018),('1949544249','1949544249','5','001','ADMINISTRACIÓN Y GESTIÓN','ADMINISTRACIÓ I GESTIÓ',' ',2018),('1949544300','1949544300','5','GM','GRADO MEDIO','GRAU MITJÀ','1949544249',2018),('1949544331','1949544331','5','472103','GESTIÓN ADMINISTRATIVA (LOE)','GESTIÓ ADMINISTRATIVA (LOE)','1949544300',2018),('1949544333','1949544333','5','1CFM','Primero','Primer','1949544331',2018),('1949544340','1949544340','5','2CFM','Segundo','Segon','1949544331',2018),('1949544364','1949544364','5','GS','GRADO SUPERIOR','GRAU SUPERIOR','1949544249',2018),('1949544415','1949544415','5','441104','ADMINISTRACIÓN Y FINANZAS (LOE)','ADMINISTRACIÓ I FINANCES (LOE)','1949544364',2018),('1949544417','1949544417','5','2CFS','Segundo','Segon','1949544415',2018),('1949544424','1949544424','5','1CFS','Primero','Primer','1949544415',2018),('1949545365','1949545365','5','031','ELECTRICIDAD Y ELECTRÓNICA','ELECTRICITAT I ELECTRÒNICA',' ',2018),('1949545367','1949545367','5','FPB','FP BÁSICA','FP BÀSICA','1949545365',2018),('1949545369','1949545369','5','95610A','ELECTRICIDAD Y ELECTRÓNICA','ELECTRICITAT I ELECTRÒNICA','1949545367',2018),('1949545371','1949545371','5','1CFB','Primero','Primer','1949545369',2018),('1949545378','1949545378','5','2CFB','Segundo','Segon','1949545369',2018),('1949545683','1949545683','5','GM','GRADO MEDIO','GRAU MITJÀ','1949545365',2018),('1949571046','1949571046','6','190','INFORMÁTICA Y COMUNICACIONES','INFORMÀTICA I COMUNICACIONS',' ',2018);
/*!40000 ALTER TABLE `cursos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ejemplares`
--

DROP TABLE IF EXISTS `ejemplares`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ejemplares` (
  `id` varchar(100) NOT NULL,
  `codigo` varchar(100) NOT NULL,
  `id_libro` varchar(100) NOT NULL,
  `estado` int(11) NOT NULL,
  `prestado` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_libro_rel` (`id_libro`),
  CONSTRAINT `ejemplarLibro` FOREIGN KEY (`id_libro`) REFERENCES `libros` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ejemplares`
--

LOCK TABLES `ejemplares` WRITE;
/*!40000 ALTER TABLE `ejemplares` DISABLE KEYS */;
INSERT INTO `ejemplares` VALUES ('libro1001','libro1001','libro1',2,1),('libro1002','libro1002','libro1',0,0),('vcxvcx001','vcxvcx001','vcxvcx',0,0),('vcxvcx002','vcxvcx002','vcxvcx',0,0),('vcxvcx003','vcxvcx003','vcxvcx',0,0),('vcxvcx004','vcxvcx004','vcxvcx',0,0),('vcxvcx005','vcxvcx005','vcxvcx',0,0),('vcxvcx006','vcxvcx006','vcxvcx',0,0),('vcxvcx007','vcxvcx007','vcxvcx',0,0),('vcxvcx008','vcxvcx008','vcxvcx',0,0);
/*!40000 ALTER TABLE `ejemplares` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupos`
--

DROP TABLE IF EXISTS `grupos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grupos` (
  `id` varchar(10) NOT NULL,
  `codigo` varchar(10) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `ensenanza` varchar(2) NOT NULL,
  `linea` varchar(2) NOT NULL,
  `turno` varchar(2) NOT NULL,
  `modalidad` varchar(5) NOT NULL,
  `aula` varchar(15) NOT NULL,
  `capacidad` smallint(6) NOT NULL,
  `tutor_ppal` varchar(10) NOT NULL,
  `tutor_sec` varchar(10) NOT NULL,
  `oficial` varchar(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupos`
--

LOCK TABLES `grupos` WRITE;
/*!40000 ALTER TABLE `grupos` DISABLE KEYS */;
INSERT INTO `grupos` VALUES ('1BACA','1BACA','1BA.CT.AN.REL.AE','4','3','D','COM','1BA.CT.AN.',30,'022540223Q',' ','S'),('1BACB','1BACB','1BB.HUM.AN.REL','4','3','D','COM','1BB.HUM.AN',30,'024388381D',' ','S'),('1CFBE','1CFBE','1 FP Bàsica Electronica','5','3','D','COM',' ',15,'020784196Q',' ','S'),('1CFBIO','1CFBIO','1 FP Bàsica Inf. Oficina','5','3','D','COM',' ',18,'020437036H',' ','S'),('1CFMA','1CFMA','1.CM.ADMTVO.PIP','5','3','D','COM',' ',30,'052660245C',' ','S'),('1CFMN','1CFMN','1.CM.INFOR.PIP','5','3','D','COM','1.CM.SIS.I',25,'020816980W',' ','S'),('1CFSF','1CFSF','1.CS.AD.FIN.PIP','5','3','D','COM','1.CS.AD.FI',31,'020422492X',' ','S'),('1CFSN','1CFSN','1.CS.DES.MULTIP.','5','3','D','COM','1.CS.INF',24,'020432847S',' ','S'),('1ESOA','1ESOA','1A.AN.REL.AE.PEV','3','2','D','COM',' ',35,'020786939E',' ','S'),('1ESOB','1ESOB','1B.AN.REL.AE.PIP.PEV','3','2','D','COM',' ',35,'020820137P',' ','S'),('1ESOC','1ESOC','1C.AN.REL.PIP','3','3','D','COM',' ',35,'020429313T',' ','S'),('1ESOD','1ESOD','1D.AN.REL.AE.PIP','3','3','D','COM',' ',35,'020832270C',' ','S'),('1ESOE','1ESOE','1E.AN.FR.REL.AE.PIP','3','3','D','COM','1E.AN.FR.R',30,'020828476K',' ','S'),('2BACA','2BACA','2BA.CIENT','4','3','D','COM',' ',35,'052646181D',' ','S'),('2BAHB','2BAHB','2BB.HUM.SOC','4','3','D','COM',' ',35,'020816939F',' ','S'),('2CFBE','2CFBE','2 FP Bàsica Electrònica','5','3','D','COM','2CFBE',25,'073908934K',' ','S'),('2CFBN','2CFBN','2 FP Bàsica Informàtica','5','3','D','COM','2CFBN',18,'052730192R',' ','S'),('2CFMA','2CFMA','2.CM.GES.ADM.PIP','5','3','D','COM',' ',30,'020781229Q',' ','S'),('2CFMN','2CFMN','2.CM.SIS.INF.PIP','5','3','D','COM','2.CM.SIS.I',20,'073776124J',' ','S'),('2CFSF','2CFSF','2.CS.AD.FIN.PIP','5','3','D','COM','2.CS.AD.FI',30,'042995716E',' ','S'),('2CFSN','2CFSN','2.CS.DES.MULTIP.','5','3','D','COM',' ',24,'052660712G',' ','S'),('2ESO-PAC','2ESO-PAC','2ESOPAC','3','3','D','COM',' ',0,' ',' ','N'),('2ESOA','2ESOA','2A.AN.REL.VE','3','2','D','COM',' ',35,'020787797Y',' ','S'),('2ESOB','2ESOB','2B.AN.REL.VE.PEV','3','2','D','COM',' ',35,'020458654Q',' ','S'),('2ESOC','2ESOC','2C.AN.REL.PIP','3','3','D','COM','2C.AN.FRA.',35,'033458717L',' ','S'),('2ESOD','2ESOD','2D.AN.REL.VE.PIP','3','3','D','COM',' ',38,'052733883N',' ','S'),('3ESOA','3ESOA','3A.AN.REL.AE.PEV','3','2','D','COM',' ',35,'020454778G',' ','S'),('3ESOB','3ESOB','3B.AN.REL.AE.PEV','3','2','D','COM','3B.AN.REL.',35,'020791945Z',' ','S'),('3ESOC','3ESOC','3C.AN.FR.REL.AE.PIP','3','3','D','COM','3C.AN.FR.R',35,'020778755A',' ','S'),('3ESOPMAR','3ESOPMAR','3C.AN.FR.REL.VE.PIP','3','3','D','COM',' ',25,'020782928J',' ','S'),('4ESOA','4ESOA','4A.AN.REL.AE.PEV','3','2','D','COM',' ',37,'020792416W',' ','S'),('4ESOB','4ESOB','4B.AN.FR.REL.AE.PIP.PEV','3','3','D','COM',' ',35,'052682739C','020038542E','S'),('4ESOC','4ESOC','4C.AN.FR.REL.AE.PIP.PEV','3','2','D','COM',' ',30,'020807415M','020038542E','S'),('4ESOPR','4ESOPR','4PR.AN.PEV.PIP','3','3','D','COM',' ',0,' ',' ','N'),('4ESOPR4','4ESOPR4','4PR.AN.PEP.PIV','3','2','D','COM',' ',30,' ',' ','S');
/*!40000 ALTER TABLE `grupos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historial`
--

DROP TABLE IF EXISTS `historial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historial` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_ejemplar` varchar(100) DEFAULT NULL,
  `id_alumno` varchar(10) NOT NULL,
  `curso_escolar` int(11) NOT NULL,
  `estado_inicial` int(11) NOT NULL,
  `estado_final` int(11) DEFAULT NULL,
  `fecha_inicial` datetime NOT NULL,
  `fecha_final` datetime DEFAULT NULL,
  `observaciones` varchar(500) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_inventario_rel` (`id_ejemplar`),
  KEY `id_alumno_rel` (`id_alumno`),
  CONSTRAINT `historialAlumno` FOREIGN KEY (`id_alumno`) REFERENCES `alumnos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `historialEjemplar` FOREIGN KEY (`id_ejemplar`) REFERENCES `ejemplares` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historial`
--

LOCK TABLES `historial` WRITE;
/*!40000 ALTER TABLE `historial` DISABLE KEYS */;
INSERT INTO `historial` VALUES (1,'libro1001','11472519',2020,0,0,'2020-05-29 17:02:39','2020-05-29 17:07:15',''),(2,'libro1001','11472519',2020,0,0,'2020-05-29 17:02:58','2020-05-29 17:03:47',''),(3,'libro1001','11472519',2020,0,0,'2020-05-29 17:07:02','2020-05-29 17:07:50',''),(4,'libro1001','11472519',2020,0,0,'2020-05-29 17:09:57','2020-05-29 17:10:03',''),(5,'libro1001','11472519',2020,0,0,'2020-05-29 17:10:09','2020-05-29 17:10:20',''),(6,'libro1001','11472519',2020,0,2,'2020-05-29 17:10:48','2020-05-29 17:10:56',''),(7,'libro1001','11472519',2020,0,0,'2020-05-29 17:14:33','2020-05-29 17:14:50',''),(8,'libro1001','11472519',2020,0,1,'2020-05-29 17:15:22','2020-05-29 17:16:43',''),(9,'libro1001','11472519',2020,1,2,'2020-05-29 17:16:57','2020-05-29 17:17:14',''),(10,'libro1001','11472519',2020,2,NULL,'2020-05-29 17:17:23',NULL,'');
/*!40000 ALTER TABLE `historial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `libros`
--

DROP TABLE IF EXISTS `libros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `libros` (
  `id` varchar(50) NOT NULL,
  `codigo` varchar(50) NOT NULL,
  `id_contenido` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `ISBN` varchar(50) NOT NULL,
  `unidades` int(11) NOT NULL,
  `obsoleto` tinyint(1) NOT NULL,
  `precio` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_asignatura_rel` (`id_contenido`),
  CONSTRAINT `FKcm1qqb36e0drbcm0nwlauab29` FOREIGN KEY (`id_contenido`) REFERENCES `contenido` (`id`),
  CONSTRAINT `libroIDContenido` FOREIGN KEY (`id_contenido`) REFERENCES `contenido` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `libros`
--

LOCK TABLES `libros` WRITE;
/*!40000 ALTER TABLE `libros` DISABLE KEYS */;
INSERT INTO `libros` VALUES ('libro1','libro1',1438,'libro','libro1',2,0,0),('vcxvcx','vcxvcx',1308,'vcvc','cxvcx',8,0,0);
/*!40000 ALTER TABLE `libros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matricula`
--

DROP TABLE IF EXISTS `matricula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `matricula` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `curso_escolar` int(11) NOT NULL,
  `id_alumno` varchar(10) NOT NULL,
  `ensenanza` varchar(2) NOT NULL,
  `curso` varchar(10) NOT NULL,
  `contenido` int(11) NOT NULL,
  `idioma` varchar(25) NOT NULL,
  `tipo_basico` varchar(50) NOT NULL,
  `tipo_predom` varchar(50) NOT NULL,
  `acis` varchar(25) NOT NULL,
  `fec_ini_acis` datetime NOT NULL,
  `fec_fin_acis` datetime NOT NULL,
  `cur_ref_acis` varchar(25) NOT NULL,
  `curso_pendiente` varchar(10) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_alumno_rel_2` (`id_alumno`),
  KEY `id_contenido_rel` (`contenido`),
  KEY `matrilculaCurso_idx` (`curso`),
  CONSTRAINT `FKpmmj4cfy7r1lepuxysx08ffl3` FOREIGN KEY (`contenido`) REFERENCES `contenido` (`id`),
  CONSTRAINT `matriCulaContenido` FOREIGN KEY (`contenido`) REFERENCES `contenido` (`id`),
  CONSTRAINT `matrilculaCurso` FOREIGN KEY (`curso`) REFERENCES `cursos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `matrilculaIDAlumno` FOREIGN KEY (`id_alumno`) REFERENCES `alumnos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8428 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matricula`
--

LOCK TABLES `matricula` WRITE;
/*!40000 ALTER TABLE `matricula` DISABLE KEYS */;
INSERT INTO `matricula` VALUES (8397,2019,'10238383','5','1949542197',1441,' ','N',' ','N','2020-05-16 11:47:52','2020-05-16 11:47:52',' ',' '),(8398,2019,'10238383','5','1949542197',1442,' ','N',' ','N','2020-05-16 11:47:52','2020-05-16 11:47:52',' ',' '),(8399,2019,'10238383','5','1949542197',1404,' ','N',' ','N','2020-05-16 11:47:52','2020-05-16 11:47:52',' ',' '),(8400,2019,'10238383','5','1949542197',1437,' ','N',' ','N','2020-05-16 11:47:52','2020-05-16 11:47:52',' ',' '),(8401,2019,'10238383','5','1949542197',1439,' ','N',' ','N','2020-05-16 11:47:52','2020-05-16 11:47:52',' ',' '),(8402,2019,'10238383','5','1949542197',1440,' ','N',' ','N','2020-05-16 11:47:52','2020-05-16 11:47:52',' ',' '),(8403,2019,'10238383','5','1949542197',1443,' ','N',' ','N','2020-05-16 11:47:52','2020-05-16 11:47:52',' ',' '),(8404,2019,'10238383','5','1949542197',1438,' ','N',' ','N','2020-05-16 11:47:52','2020-05-16 11:47:52',' ',' '),(8405,2019,'11472519','5','1949542197',1441,' ','N',' ','N','2020-05-16 11:47:52','2020-05-16 11:47:52',' ',' '),(8406,2019,'11472519','5','1949542197',1442,' ','N',' ','N','2020-05-16 11:47:52','2020-05-16 11:47:52',' ',' '),(8407,2019,'11472519','5','1949542197',1404,' ','N',' ','N','2020-05-16 11:47:53','2020-05-16 11:47:53',' ',' '),(8408,2019,'11472519','5','1949542197',1437,' ','N',' ','N','2020-05-16 11:47:53','2020-05-16 11:47:53',' ',' '),(8409,2019,'11472519','5','1949542197',1439,' ','N',' ','N','2020-05-16 11:47:53','2020-05-16 11:47:53',' ',' '),(8410,2019,'11472519','5','1949542197',1440,' ','N',' ','N','2020-05-16 11:47:53','2020-05-16 11:47:53',' ',' '),(8411,2019,'11472519','5','1949542197',1443,' ','N','C','N','2020-05-16 11:47:53','2020-05-16 11:47:53',' ',' '),(8412,2019,'11472519','5','1949542197',1438,' ','N',' ','N','2020-05-16 11:47:53','2020-05-16 11:47:53',' ',' '),(8413,2019,'10238383','5','1949542197',1441,' ','N',' ','N','2020-05-16 11:55:52','2020-05-16 11:55:52',' ',' '),(8414,2019,'10238383','5','1949542197',1442,' ','N',' ','N','2020-05-16 11:55:52','2020-05-16 11:55:52',' ',' '),(8415,2019,'10238383','5','1949542197',1404,' ','N',' ','N','2020-05-16 11:55:52','2020-05-16 11:55:52',' ',' '),(8416,2019,'10238383','5','1949542197',1437,' ','N',' ','N','2020-05-16 11:55:52','2020-05-16 11:55:52',' ',' '),(8417,2019,'10238383','5','1949542197',1439,' ','N',' ','N','2020-05-16 11:55:52','2020-05-16 11:55:52',' ',' '),(8418,2019,'10238383','5','1949542197',1440,' ','N',' ','N','2020-05-16 11:55:52','2020-05-16 11:55:52',' ',' '),(8419,2019,'10238383','5','1949542197',1443,' ','N',' ','N','2020-05-16 11:55:52','2020-05-16 11:55:52',' ',' '),(8420,2019,'10238383','5','1949542197',1438,' ','N',' ','N','2020-05-16 11:55:52','2020-05-16 11:55:52',' ',' '),(8421,2019,'11472519','5','1949542197',1441,' ','N',' ','N','2020-05-16 11:55:52','2020-05-16 11:55:52',' ',' '),(8422,2019,'11472519','5','1949542197',1442,' ','N',' ','N','2020-05-16 11:55:52','2020-05-16 11:55:52',' ',' '),(8423,2019,'11472519','5','1949542197',1404,' ','N',' ','N','2020-05-16 11:55:52','2020-05-16 11:55:52',' ',' '),(8424,2019,'11472519','5','1949542197',1437,' ','N',' ','N','2020-05-16 11:55:52','2020-05-16 11:55:52',' ',' '),(8425,2019,'11472519','5','1949542197',1439,' ','N',' ','N','2020-05-16 11:55:52','2020-05-16 11:55:52',' ',' '),(8426,2019,'11472519','5','1949542197',1440,' ','N',' ','N','2020-05-16 11:55:52','2020-05-16 11:55:52',' ',' '),(8427,2019,'11472519','5','1949542197',1443,' ','N','C','N','2020-05-16 11:55:52','2020-05-16 11:55:52',' ',' ');
/*!40000 ALTER TABLE `matricula` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-01 17:43:11
