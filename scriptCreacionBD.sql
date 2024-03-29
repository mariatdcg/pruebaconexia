-- MySQL Script generated by MySQL Workbench
-- Wed May  8 21:33:59 2019
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Cliente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Cliente` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Cliente` (
  `idCliente` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido1` VARCHAR(45) NULL,
  `apellido2` VARCHAR(45) NULL,
  `observaciones` VARCHAR(100) NULL,
  PRIMARY KEY (`idCliente`),
  UNIQUE INDEX `IdCliente_UNIQUE` (`idCliente` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Mesa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Mesa` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Mesa` (
  `idMesa` INT NOT NULL AUTO_INCREMENT,
  `numMaxComensales` INT NOT NULL,
  `ubicacion` VARCHAR(45) NULL,
  PRIMARY KEY (`idMesa`),
  UNIQUE INDEX `IdMesa_UNIQUE` (`idMesa` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Camarero`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Camarero` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Camarero` (
  `idCamarero` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido1` VARCHAR(45) NOT NULL,
  `apellido2` VARCHAR(45) NULL,
  PRIMARY KEY (`idCamarero`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Factura`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Factura` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Factura` (
  `idFactura` INT NOT NULL AUTO_INCREMENT,
  `idCliente` INT NOT NULL,
  `idCamarero` INT NOT NULL,
  `idMesa` INT NOT NULL,
  `fechaFactura` DATE NOT NULL,
  PRIMARY KEY (`idFactura`),
  INDEX `FK_FAC_CLIENTE_idx` (`idCliente` ASC) VISIBLE,
  INDEX `FK_FAC_CAMARERO_idx` (`idCamarero` ASC) VISIBLE,
  INDEX `FK_FAC_MESA_idx` (`idMesa` ASC) VISIBLE,
  CONSTRAINT `FK_FAC_CLIENTE`
    FOREIGN KEY (`idCliente`)
    REFERENCES `mydb`.`Cliente` (`idCliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_FAC_CAMARERO`
    FOREIGN KEY (`idCamarero`)
    REFERENCES `mydb`.`Camarero` (`idCamarero`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_FAC_MESA`
    FOREIGN KEY (`idMesa`)
    REFERENCES `mydb`.`Mesa` (`idMesa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Cocinero`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`Cocinero` ;

CREATE TABLE IF NOT EXISTS `mydb`.`Cocinero` (
  `idCocinero` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `apellido1` VARCHAR(45) NOT NULL,
  `apellido2` VARCHAR(45) NULL,
  PRIMARY KEY (`idCocinero`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`DetalleFactura`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`DetalleFactura` ;

CREATE TABLE IF NOT EXISTS `mydb`.`DetalleFactura` (
  `idDetalleFactura` INT NOT NULL AUTO_INCREMENT,
  `idFactura` INT NOT NULL,
  `idCocinero` INT NULL,
  `plato` VARCHAR(100) NOT NULL,
  `importe` FLOAT NOT NULL,
  PRIMARY KEY (`idDetalleFactura`, `idFactura`),
  INDEX `FK_DETALLE_FACTURA_idx` (`idFactura` ASC) VISIBLE,
  INDEX `FK_DETALLE_COCINERO_idx` (`idCocinero` ASC) VISIBLE,
  CONSTRAINT `FK_DETALLE_FACTURA`
    FOREIGN KEY (`idFactura`)
    REFERENCES `mydb`.`Factura` (`idFactura`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_DETALLE_COCINERO`
    FOREIGN KEY (`idCocinero`)
    REFERENCES `mydb`.`Cocinero` (`idCocinero`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
