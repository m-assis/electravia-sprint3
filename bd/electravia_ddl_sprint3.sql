USE [master]
GO

/****** Objeto:  Database [charging_station]    Data do Script: 03/11/2026 21:24:34 ******/
CREATE DATABASE [charging_station]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'charging_station', FILENAME = N'E:\Program Files\Microsoft SQL Server\MSSQL17.WORKBENCH\MSSQL\DATA\charging_station.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'charging_station_log', FILENAME = N'E:\Program Files\Microsoft SQL Server\MSSQL17.WORKBENCH\MSSQL\DATA\charging_station_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO

IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [charging_station].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO

ALTER DATABASE [charging_station] SET ANSI_NULL_DEFAULT OFF 
GO

ALTER DATABASE [charging_station] SET ANSI_NULLS OFF 
GO

ALTER DATABASE [charging_station] SET ANSI_PADDING OFF 
GO

ALTER DATABASE [charging_station] SET ANSI_WARNINGS OFF 
GO

ALTER DATABASE [charging_station] SET ARITHABORT OFF 
GO

ALTER DATABASE [charging_station] SET AUTO_CLOSE OFF 
GO

ALTER DATABASE [charging_station] SET AUTO_SHRINK OFF 
GO

ALTER DATABASE [charging_station] SET AUTO_UPDATE_STATISTICS ON 
GO

ALTER DATABASE [charging_station] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO

ALTER DATABASE [charging_station] SET CURSOR_DEFAULT  GLOBAL 
GO

ALTER DATABASE [charging_station] SET CONCAT_NULL_YIELDS_NULL OFF 
GO

ALTER DATABASE [charging_station] SET NUMERIC_ROUNDABORT OFF 
GO

ALTER DATABASE [charging_station] SET QUOTED_IDENTIFIER OFF 
GO

ALTER DATABASE [charging_station] SET RECURSIVE_TRIGGERS OFF 
GO

ALTER DATABASE [charging_station] SET  DISABLE_BROKER 
GO

ALTER DATABASE [charging_station] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO

ALTER DATABASE [charging_station] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO

ALTER DATABASE [charging_station] SET TRUSTWORTHY OFF 
GO

ALTER DATABASE [charging_station] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO

ALTER DATABASE [charging_station] SET PARAMETERIZATION SIMPLE 
GO

ALTER DATABASE [charging_station] SET READ_COMMITTED_SNAPSHOT OFF 
GO

ALTER DATABASE [charging_station] SET HONOR_BROKER_PRIORITY OFF 
GO

ALTER DATABASE [charging_station] SET RECOVERY SIMPLE 
GO

ALTER DATABASE [charging_station] SET  MULTI_USER 
GO

ALTER DATABASE [charging_station] SET PAGE_VERIFY CHECKSUM  
GO

ALTER DATABASE [charging_station] SET DB_CHAINING OFF 
GO

ALTER DATABASE [charging_station] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO

ALTER DATABASE [charging_station] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO

ALTER DATABASE [charging_station] SET DELAYED_DURABILITY = DISABLED 
GO

ALTER DATABASE [charging_station] SET OPTIMIZED_LOCKING = OFF 
GO

ALTER DATABASE [charging_station] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO

ALTER DATABASE [charging_station] SET QUERY_STORE = ON
GO

ALTER DATABASE [charging_station] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO

ALTER DATABASE [charging_station] SET  READ_WRITE 
GO

USE charging_station
GO

-- Gerado por Oracle SQL Developer Data Modeler 24.3.1.351.0831
--   em:        2026-11-03 21:21:32 BRT
--   site:      SQL Server 2012
--   tipo:      SQL Server 2012

CREATE TABLE abastecimento 
    (
     id_abastecimento BIGINT NOT NULL , 
     usuario_id_usuario BIGINT NOT NULL , 
     id_veiculo BIGINT , 
     bomba_abastecimento_id_bomba_abastecimento INTEGER NOT NULL , 
     status_id_status INTEGER NOT NULL , 
     data_criacao DATETIME NOT NULL DEFAULT GETDATE() , 
     usuario_criacao VARCHAR (100) NOT NULL , 
     data_atualizacao DATETIME , 
     usuario_atualizacao VARCHAR (100) , 
     data_inicio DATETIME NOT NULL , 
     data_termino DATETIME , 
     quantidade_abastecida FLOAT NOT NULL , 
     valor_kw MONEY NOT NULL , 
     valor_pago MONEY NOT NULL , 
     forma_pagamento CHAR (20) NOT NULL 
    )
GO 

CREATE UNIQUE NONCLUSTERED INDEX 
    abastecimento__IDX ON abastecimento 
    ( 
     bomba_abastecimento_id_bomba_abastecimento 
    ) 
GO

ALTER TABLE abastecimento ADD CONSTRAINT abastecimento_PK PRIMARY KEY CLUSTERED (id_abastecimento)
     WITH (
     ALLOW_PAGE_LOCKS = ON , 
     ALLOW_ROW_LOCKS = ON )
GO

CREATE TABLE abastecimento_veiculo 
    (
     id_abastecimento_veiculo BIGINT NOT NULL , 
     veiculo_id_veiculo BIGINT NOT NULL , 
     abastecimento_id_abastecimento BIGINT NOT NULL , 
     data_criacao DATETIME NOT NULL DEFAULT GETDATE() , 
     usuario_criacao VARCHAR (100) NOT NULL , 
     data_atualizacao DATETIME , 
     usuario_atualizacao VARCHAR (100) , 
     status_ativo BIT NOT NULL 
    )
GO

ALTER TABLE abastecimento_veiculo ADD CONSTRAINT abastecimento_veiculo_PK PRIMARY KEY CLUSTERED (id_abastecimento_veiculo)
     WITH (
     ALLOW_PAGE_LOCKS = ON , 
     ALLOW_ROW_LOCKS = ON )
GO

CREATE TABLE bomba_abastecimento 
    (
     id_bomba_abastecimento INTEGER NOT NULL , 
     posto_abastecimento_numero_identificacao_fiscal BIGINT NOT NULL , 
     codigo_bomba VARCHAR (100) NOT NULL , 
     data_criacao DATETIME NOT NULL DEFAULT GETDATE() , 
     usuario_criacao VARCHAR (100) NOT NULL , 
     data_atualizacao DATETIME , 
     usuario_atualizacao VARCHAR (100) , 
     status_ativo BIT NOT NULL , 
     descricao_bomba VARCHAR (100) NOT NULL , 
     capacidade FLOAT NOT NULL , 
     data_ultima_manutencao DATE 
    )
GO

ALTER TABLE bomba_abastecimento ADD CONSTRAINT bomba_abastecimento_PK PRIMARY KEY CLUSTERED (id_bomba_abastecimento)
     WITH (
     ALLOW_PAGE_LOCKS = ON , 
     ALLOW_ROW_LOCKS = ON )
GO
ALTER TABLE bomba_abastecimento ADD CONSTRAINT bomba_abastecimento_codigo_bomba_numero_identificacao_fiscal_UN UNIQUE NONCLUSTERED (codigo_bomba, posto_abastecimento_numero_identificacao_fiscal)
GO

CREATE TABLE cadastro 
    (
     id_cadastro BIGINT NOT NULL , 
     usuario_id_usuario BIGINT NOT NULL , 
     data_criacao DATETIME NOT NULL DEFAULT GETDATE() , 
     usuario_criacao VARCHAR (100) NOT NULL , 
     data_atualizacao DATETIME , 
     usuario_atualizacao VARCHAR (100) , 
     status_ativo BIT NOT NULL , 
     email VARCHAR (100) NOT NULL , 
     telefone INTEGER NOT NULL , 
     plano_contratado INTEGER NOT NULL 
    )
GO

ALTER TABLE cadastro ADD CONSTRAINT cadastro_PK PRIMARY KEY CLUSTERED (id_cadastro)
     WITH (
     ALLOW_PAGE_LOCKS = ON , 
     ALLOW_ROW_LOCKS = ON )
GO

CREATE TABLE documento_identificacao 
    (
     id_documento BIGINT NOT NULL , 
     usuario_id_usuario BIGINT NOT NULL , 
     data_criacao DATETIME NOT NULL DEFAULT GETDATE() , 
     usuario_criacao VARCHAR (100) NOT NULL , 
     data_atualizacao DATETIME , 
     usuario_atualizacao VARCHAR (100) , 
     status_ativo BIT NOT NULL , 
     codigo VARCHAR (100) NOT NULL , 
     descricao VARCHAR (200) NOT NULL , 
     tipo_documento VARCHAR (20) NOT NULL , 
     pais_origem VARCHAR (20) NOT NULL , 
     estado_origem VARCHAR (20) NOT NULL 
    )
GO

ALTER TABLE documento_identificacao ADD CONSTRAINT documento_identificacao_PK PRIMARY KEY CLUSTERED (id_documento)
     WITH (
     ALLOW_PAGE_LOCKS = ON , 
     ALLOW_ROW_LOCKS = ON )
GO
ALTER TABLE documento_identificacao ADD CONSTRAINT documento_identificacao_codigo_tipo_documento_pais_origem_estado_origem_UN UNIQUE NONCLUSTERED (codigo, tipo_documento, pais_origem, estado_origem)
GO

CREATE TABLE endereco 
    (
     id_endereco BIGINT NOT NULL , 
     codigo_postal VARCHAR (20) NOT NULL , 
     data_criacao DATETIME NOT NULL DEFAULT GETDATE() , 
     usuario_criacao VARCHAR (100) NOT NULL , 
     data_atualizacao DATETIME , 
     usuario_atualizacao VARCHAR (100) , 
     status_ativo BIT NOT NULL , 
     numero_endereco INTEGER NOT NULL , 
     complemento_endereco VARCHAR (20) , 
     tipo_local VARCHAR (20) NOT NULL , 
     pais VARCHAR (20) NOT NULL , 
     estado VARCHAR (20) NOT NULL 
    )
GO

ALTER TABLE endereco ADD CONSTRAINT endereco_PK PRIMARY KEY CLUSTERED (id_endereco)
     WITH (
     ALLOW_PAGE_LOCKS = ON , 
     ALLOW_ROW_LOCKS = ON )
GO
ALTER TABLE endereco ADD CONSTRAINT endereco_codigo_postal_pais_estado_numero_endereco_complemento_endereco_UN UNIQUE NONCLUSTERED (codigo_postal, pais, estado, numero_endereco, complemento_endereco)
GO

CREATE TABLE possuir 
    (
     usuario_id_usuario BIGINT NOT NULL , 
     veiculo_id_veiculo BIGINT NOT NULL 
    )
GO

ALTER TABLE possuir ADD CONSTRAINT possuir_PK PRIMARY KEY CLUSTERED (usuario_id_usuario, veiculo_id_veiculo)
     WITH (
     ALLOW_PAGE_LOCKS = ON , 
     ALLOW_ROW_LOCKS = ON )
GO

CREATE TABLE posto_abastecimento 
    (
     numero_identificacao_fiscal BIGINT NOT NULL , 
     endereco_id_endereco BIGINT NOT NULL , 
     data_criacao DATETIME NOT NULL DEFAULT GETDATE() , 
     usuario_criacao VARCHAR (100) NOT NULL , 
     data_atualizacao DATETIME , 
     usuario_atualizacao VARCHAR (100) , 
     status_ativo BIT NOT NULL , 
     codigo_posto VARCHAR (100) NOT NULL , 
     descricao_posto VARCHAR (100) NOT NULL 
    )
GO 

CREATE UNIQUE NONCLUSTERED INDEX 
    posto_abastecimento__IDX ON posto_abastecimento 
    ( 
     endereco_id_endereco 
    ) 
GO

ALTER TABLE posto_abastecimento ADD CONSTRAINT posto_abastecimento_PK PRIMARY KEY CLUSTERED (numero_identificacao_fiscal)
     WITH (
     ALLOW_PAGE_LOCKS = ON , 
     ALLOW_ROW_LOCKS = ON )
GO
ALTER TABLE posto_abastecimento ADD CONSTRAINT posto_abastecimento_numero_identificacao_fiscal_UN UNIQUE NONCLUSTERED (numero_identificacao_fiscal)
GO

CREATE TABLE status 
    (
     id_status INTEGER NOT NULL , 
     data_criacao DATETIME NOT NULL DEFAULT GETDATE() , 
     usuario_criacao VARCHAR (100) NOT NULL , 
     data_atualizacao DATETIME , 
     usuario_atualizacao VARCHAR (100) , 
     status_ativo BIT NOT NULL , 
     codigo VARCHAR (100) NOT NULL , 
     descricao VARCHAR (100) NOT NULL 
    )
GO

ALTER TABLE status ADD CONSTRAINT status_PK PRIMARY KEY CLUSTERED (id_status)
     WITH (
     ALLOW_PAGE_LOCKS = ON , 
     ALLOW_ROW_LOCKS = ON )
GO

CREATE TABLE usuario 
    (
     id_usuario BIGINT NOT NULL , 
     endereco_id_endereco BIGINT NOT NULL , 
     data_criacao DATETIME NOT NULL DEFAULT GETDATE() , 
     usuario_criacao VARCHAR (100) NOT NULL , 
     data_atualizacao DATETIME , 
     usuario_atualizacao VARCHAR (100) , 
     status_ativo BIT NOT NULL , 
     nome_completo VARCHAR (100) NOT NULL 
    )
GO

ALTER TABLE usuario ADD CONSTRAINT usuario_PK PRIMARY KEY CLUSTERED (id_usuario)
     WITH (
     ALLOW_PAGE_LOCKS = ON , 
     ALLOW_ROW_LOCKS = ON )
GO

CREATE TABLE veiculo 
    (
     id_veiculo BIGINT NOT NULL , 
     data_criacao DATETIME NOT NULL DEFAULT GETDATE() , 
     usuario_criacao VARCHAR (100) NOT NULL , 
     data_atualizacao DATETIME , 
     usuario_atualizacao VARCHAR (100) , 
     status_ativo BIT NOT NULL , 
     marca VARCHAR (20) NOT NULL , 
     modelo VARCHAR (30) NOT NULL , 
     ano_fabricacao DATE NOT NULL , 
     tipo VARCHAR (5) NOT NULL , 
     documento VARCHAR (20) , 
     codigo_identificador VARCHAR (10) NOT NULL 
    )
GO

ALTER TABLE veiculo ADD CONSTRAINT veiculo_PK PRIMARY KEY CLUSTERED (id_veiculo)
     WITH (
     ALLOW_PAGE_LOCKS = ON , 
     ALLOW_ROW_LOCKS = ON )
GO

ALTER TABLE abastecimento 
    ADD CONSTRAINT abastecimento_bomba_abastecimento_FK FOREIGN KEY 
    ( 
     bomba_abastecimento_id_bomba_abastecimento
    ) 
    REFERENCES bomba_abastecimento 
    ( 
     id_bomba_abastecimento 
    ) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION 
GO

ALTER TABLE abastecimento 
    ADD CONSTRAINT abastecimento_status_FK FOREIGN KEY 
    ( 
     status_id_status
    ) 
    REFERENCES status 
    ( 
     id_status 
    ) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION 
GO

ALTER TABLE abastecimento 
    ADD CONSTRAINT abastecimento_usuario_FK FOREIGN KEY 
    ( 
     usuario_id_usuario
    ) 
    REFERENCES usuario 
    ( 
     id_usuario 
    ) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION 
GO

ALTER TABLE abastecimento_veiculo 
    ADD CONSTRAINT abastecimento_veiculo_abastecimento_FK FOREIGN KEY 
    ( 
     abastecimento_id_abastecimento
    ) 
    REFERENCES abastecimento 
    ( 
     id_abastecimento 
    ) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION 
GO

ALTER TABLE abastecimento_veiculo 
    ADD CONSTRAINT abastecimento_veiculo_veiculo_FK FOREIGN KEY 
    ( 
     veiculo_id_veiculo
    ) 
    REFERENCES veiculo 
    ( 
     id_veiculo 
    ) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION 
GO

ALTER TABLE bomba_abastecimento 
    ADD CONSTRAINT bomba_abastecimento_posto_abastecimento_FK FOREIGN KEY 
    ( 
     posto_abastecimento_numero_identificacao_fiscal
    ) 
    REFERENCES posto_abastecimento 
    ( 
     numero_identificacao_fiscal 
    ) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION 
GO

ALTER TABLE cadastro 
    ADD CONSTRAINT cadastro_usuario_FK FOREIGN KEY 
    ( 
     usuario_id_usuario
    ) 
    REFERENCES usuario 
    ( 
     id_usuario 
    ) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION 
GO

ALTER TABLE documento_identificacao 
    ADD CONSTRAINT documento_identificacao_usuario_FK FOREIGN KEY 
    ( 
     usuario_id_usuario
    ) 
    REFERENCES usuario 
    ( 
     id_usuario 
    ) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION 
GO

ALTER TABLE possuir 
    ADD CONSTRAINT possuir_usuario_FK FOREIGN KEY 
    ( 
     usuario_id_usuario
    ) 
    REFERENCES usuario 
    ( 
     id_usuario 
    ) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION 
GO

ALTER TABLE possuir 
    ADD CONSTRAINT possuir_veiculo_FK FOREIGN KEY 
    ( 
     veiculo_id_veiculo
    ) 
    REFERENCES veiculo 
    ( 
     id_veiculo 
    ) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION 
GO

ALTER TABLE posto_abastecimento 
    ADD CONSTRAINT posto_abastecimento_endereco_FK FOREIGN KEY 
    ( 
     endereco_id_endereco
    ) 
    REFERENCES endereco 
    ( 
     id_endereco 
    ) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION 
GO

ALTER TABLE usuario 
    ADD CONSTRAINT usuario_endereco_FK FOREIGN KEY 
    ( 
     endereco_id_endereco
    ) 
    REFERENCES endereco 
    ( 
     id_endereco 
    ) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION 
GO

-- Relatório do Resumo do Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            11
-- CREATE INDEX                             2
-- ALTER TABLE                             27
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE DATABASE                          0
-- CREATE DEFAULT                           0
-- CREATE INDEX ON VIEW                     0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE ROLE                              0
-- CREATE RULE                              0
-- CREATE SCHEMA                            0
-- CREATE SEQUENCE                          0
-- CREATE PARTITION FUNCTION                0
-- CREATE PARTITION SCHEME                  0
-- 
-- DROP DATABASE                            0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
