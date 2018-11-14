CREATE DATABASE IF NOT EXISTS motoStore;
   
USE motoStore;
   
 
   
-- -------------TABELA CLIENTE -----------------
CREATE TABLE IF NOT EXISTS cliente(
    codCliente                      INT     NOT NULL AUTO_INCREMENT,
    nomeCliente                     VARCHAR(40) NOT NULL,
    telefone                        VARCHAR(15) NOT NULL,
    celular                         VARCHAR(15) NOT NULL,
    email                           VARCHAR(20) NOT NULL,
    cnh                             VARCHAR(11) NOT NULL,
    cpf                             VARCHAR(15) NOT NULL UNIQUE,
    renda                           DOUBLE UNSIGNED NOT NULL,
   
    CONSTRAINT chaveCliente PRIMARY KEY(codCliente)
);

  
-- -------------TABELA ENDERECO -----------------
CREATE TABLE IF NOT EXISTS endereco(
    codEndereco                    INT         NOT NULL AUTO_INCREMENT ,
    cep                            VARCHAR(9)  NOT NULL,
    cidade                         VARCHAR(50) NOT NULL,
    rua                            VARCHAR(20) NOT NULL,
    numero                         INT(5)      NOT NULL,
	codCliente					   INT NOT NULL,
    CONSTRAINT chaveEndereco PRIMARY KEY(codEndereco),
	FOREIGN KEY(codCliente) REFERENCES cliente(codCliente)
	ON DELETE CASCADE
	ON UPDATE CASCADE
);
   
-- -------------TABELA FABRICANTE -----------------
CREATE TABLE IF NOT EXISTS fabricante(
    codFabricante                       INT NOT NULL AUTO_INCREMENT ,
    nomeFabricante                      VARCHAR(20) NULL,
   
    CONSTRAINT chaveFabricante PRIMARY KEY(codFabricante)
);
   
-- -------------TABELA MODELO -----------------
CREATE TABLE IF NOT EXISTS modelo(
    codModelo                       INT NOT NULL AUTO_INCREMENT,
    nomeModelo                      VARCHAR(60) NOT NULL,
    codFabricante                   INT NOT NULL,
   
    CONSTRAINT chaveModelo PRIMARY KEY(codModelo),
    FOREIGN KEY(codFabricante) REFERENCES fabricante(codFabricante)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
   
-- -------------TABELA MOTOCICLETA -----------------
CREATE TABLE IF NOT EXISTS motocicleta(
    codMotocicleta                      INT NOT NULL AUTO_INCREMENT,
    placa                               VARCHAR(8) NULL,
    cor                                 VARCHAR(15) NOT NULL,
    km                                  DOUBLE UNSIGNED NOT NULL,
    estilo                              VARCHAR(15) NOT NULL,
    categoria                           VARCHAR(12) NOT NULL,
    ano                                 INT UNSIGNED NOT NULL,
    combustivel                         VARCHAR(10) NOT NULL,
    codModelo                           INT NOT NULL,
   
    CONSTRAINT chaveMotocicleta PRIMARY KEY(codMotocicleta),
    FOREIGN KEY(codModelo) REFERENCES modelo(codModelo)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
   
   
   
-- -------------TABELA VENDEDOR-----------------
CREATE TABLE IF NOT EXISTS vendedor(
    codVendedor                      INT NOT NULL AUTO_INCREMENT,
    nomeVendedor                     VARCHAR(25) NOT NULL,
   
    CONSTRAINT chaveVendedor PRIMARY KEY(codVendedor)
);
   
   
-- -------------TABELA VENDA -----------------
CREATE TABLE IF NOT EXISTS venda(
codVenda                            INT NOT NULL AUTO_INCREMENT,
formaPagamento                      VARCHAR(15) NOT NULL,
dataVenda                           DATE NOT NULL,
valor                               DOUBLE UNSIGNED NOT NULL,
quantidade              			INT UNSIGNED NOT NULL,
codCliente                          INT NOT NULL,
codMotocicleta                      INT NOT NULL,
codVendedor                    		INT NOT NULL,
   
    CONSTRAINT chaveVenda PRIMARY KEY(codVenda),
    FOREIGN KEY(codCliente) REFERENCES cliente(codCliente)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY(codMotocicleta) REFERENCES motocicleta(codMotocicleta)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY(codVendedor) REFERENCES vendedor(codVendedor)
    ON DELETE CASCADE
    ON UPDATE CASCADE
   
);

-- --------- TABELA SAIDA --------------------
CREATE TABLE IF NOT EXISTS saida(
codSaida 							INT NOT NULL AUTO_INCREMENT,
dataSaida 							DATE NOT NULL,
codCliente 							INT NOT NULL,
codMotocicleta						INT NOT NULL,

CONSTRAINT chaveSaida PRIMARY KEY(codSaida),
FOREIGN KEY(codCliente) REFERENCES cliente(codCliente)
ON DELETE CASCADE
ON UPDATE CASCADE,
FOREIGN KEY(codMotocicleta) REFERENCES motocicleta(codMotocicleta)
ON DELETE CASCADE
ON UPDATE CASCADE 

);

  
  
-- -------INSERTS VENDEDOR ------------
  
INSERT INTO vendedor(codVendedor, nomeVendedor) values(1, 'LEONARDO');
  
INSERT INTO vendedor(codVendedor, nomeVendedor) values(2, 'CAIO');
  
INSERT INTO vendedor(codVendedor, nomeVendedor) values(3, 'MARCELO');
  
INSERT INTO vendedor(codVendedor, nomeVendedor) values(4, 'MARIA');
  
INSERT INTO vendedor(codVendedor, nomeVendedor) values(5, 'JORDANA');
  
  
  
