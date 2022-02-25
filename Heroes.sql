/*
SQLyog Professional v12.09 (64 bit)
MySQL - 5.7.35-log : Database - heroes
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`heroes` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `heroes`;

/*Table structure for table `editor` */

DROP TABLE IF EXISTS `editor`;

CREATE TABLE `editor` (
  `id_editor` int(11) NOT NULL AUTO_INCREMENT,
  `nombre_editor` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_editor`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `editor` */

insert  into `editor`(`id_editor`,`nombre_editor`) values (1,'DC'),(2,'MARVEL');

/*Table structure for table `heroes` */

DROP TABLE IF EXISTS `heroes`;

CREATE TABLE `heroes` (
  `id_heroes` int(11) NOT NULL AUTO_INCREMENT,
  `editor` int(50) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `alterEgo` varchar(50) DEFAULT NULL,
  `personaje` varchar(50) DEFAULT NULL,
  `fechaAparicion` date DEFAULT NULL,
  PRIMARY KEY (`id_heroes`),
  KEY `editor` (`editor`),
  CONSTRAINT `heroes_ibfk_1` FOREIGN KEY (`editor`) REFERENCES `editor` (`id_editor`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `heroes` */

insert  into `heroes`(`id_heroes`,`editor`,`nombre`,`alterEgo`,`personaje`,`fechaAparicion`) values (1,2,'Piter Parker','Piter Parker','SpiderMan','1995-06-07');

/*Table structure for table `tipousuario` */

DROP TABLE IF EXISTS `tipousuario`;

CREATE TABLE `tipousuario` (
  `id_tipoUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombreTipoUsuario` varchar(45) NOT NULL,
  PRIMARY KEY (`id_tipoUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `tipousuario` */

insert  into `tipousuario`(`id_tipoUsuario`,`nombreTipoUsuario`) values (1,'Normal'),(2,'Administrativo');

/*Table structure for table `usuarios` */

DROP TABLE IF EXISTS `usuarios`;

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombreUsuario` varchar(45) DEFAULT NULL,
  `tipoUsuario` int(11) DEFAULT NULL,
  `password` char(10) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`),
  KEY `tipoUsuario_id_idx` (`tipoUsuario`),
  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`tipoUsuario`) REFERENCES `tipousuario` (`id_tipoUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `usuarios` */

insert  into `usuarios`(`id_usuario`,`nombreUsuario`,`tipoUsuario`,`password`,`email`) values (1,'Stephen',1,'12345','hola@gmail.com'),(4,'StephenX',NULL,'123434','email@gmail.com'),(5,NULL,NULL,NULL,NULL),(6,'Nuevo',NULL,'12345','asfasfas@gmail.com'),(7,'0',1,'0',NULL),(8,NULL,NULL,NULL,NULL),(10,'nuevo',NULL,'12345','nuevo@gmail.com');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
