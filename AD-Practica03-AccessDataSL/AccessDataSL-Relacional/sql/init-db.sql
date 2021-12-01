SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

USE adSL;

SET NAMES utf8mb4;


CREATE TABLE `commit`
(
    `idCommit`    varchar(36)  NOT NULL,
    `titulo`      varchar(15)  NOT NULL,
    `texto`       varchar(150) NOT NULL,
    `fecha`       date         NOT NULL,
    `repositorio` varchar(36)  NOT NULL,
    `proyecto`    varchar(36)  NOT NULL,
    `autor`       varchar(36)  NOT NULL,
    `issue`       varchar(36)  NOT NULL,
    PRIMARY KEY (`idCommit`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `commit` (`idCommit`, `titulo`, `texto`, `fecha`,
                      `repositorio`, `proyecto`, `autor`, `issue`)
VALUES ('5b64bfd6-08e4-4325-b037-bd4fcfafe783', 'commit 1', 'texto 1',
        '2002-02-01', 'f64c5364-faa7-41b7-bca9-3b27f95d8fa8',
        '81ee1211-760c-493d-968a-380e6af67363', '53269670-1586-49ac-9df5-62ddd55f96cc',
        '53269670-1586-49ac-9df5-62ddd55f96cc'),

       ('649ad8bd-6d6e-4430-af04-9fcfe370db84', 'commit 2', 'texto 2',
        '2012-12-10', 'f1174508-8659-4654-82ce-af2704a152de',
        'f89062d9-ba34-40a4-b6af-a21a0dc093be', '606aba4c-b76e-4fa3-9eb8-48e20d729353',
        '6c5b7c5a-d30b-400f-9c11-84dc2b49f01e'),

       ('3a1690b0-b7f3-4303-8413-1f63578c3194', 'commit 3', 'texto 3',
        '2016-05-10', 'f1174508-8659-4654-82ce-af2704a152de',
        'f89062d9-ba34-40a4-b6af-a21a0dc093be', '606aba4c-b76e-4fa3-9eb8-48e20d729353',
        '6c5b7c5a-d30b-400f-9c11-84dc2b49f01e');



CREATE TABLE `conocimiento`
(
    `idConocimiento` varchar(36)  NOT NULL,
    `idProgramador` varchar(36)  NOT NULL,
    `tecnologias`   varchar(255) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `conocimiento` (`idConocimiento`,`idProgramador`, `tecnologias`)

VALUES ('df59793e-0d47-4347-bcfa-e4c86d6974cb','1376acc9-79a9-4bf1-9084-c82e9a07f432',
        '20bcca63-7b60-4a43-bb10-4c9735587d16'),

       ('de9eeebf-fe95-4d9c-a127-1a86d22c1363','53269670-1586-49ac-9df5-62ddd55f96cc',
        '20bcca63-7b60-4a43-bb10-4c9735587d16;4f119f1b-7ccf-49f4-b56f-fdace8589b1c'),

       ('60aff2c7-f86b-44cf-9b35-25449fffcd28','606aba4c-b76e-4fa3-9eb8-48e20d729353',
        '20bcca63-7b60-4a43-bb10-4c9735587d16;cb231a1d-ffc8-4a64-b090-1334f5f4f740');

CREATE TABLE `creaciones`
(
    `idCreaciones`  varchar(36)  NOT NULL,
    `idIssue`       varchar(36)  NOT NULL,
    `idProgramador` varchar(255) NOT NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


INSERT INTO `creaciones` (`idCreaciones`,`idIssue`, `idProgramador`)

VALUES ('a6ce0e70-c34d-4b05-a141-8be59b60f16b','1f9b764e-570f-4041-a0c9-fc58a794eb0d',
        '53269670-1586-49ac-9df5-62ddd55f96cc;1376acc9-79a9-4bf1-9084-c82e9a07f432'),

       ('e2f660b2-a7a1-4600-86f0-cff7ff4838e2','7e96e277-26bc-4c08-a21e-6d92eb7638de',
        'd63f0d73-3f1b-4afd-b5d0-821449daa4a3'),

       ('176c21fe-fcf1-4918-9fe8-5fb6c219308f','6c5b7c5a-d30b-400f-9c11-84dc2b49f01e',
        '606aba4c-b76e-4fa3-9eb8-48e20d729353');


CREATE TABLE `departamento`
(
    `idDepartamento`   varchar(36)  NOT NULL,
    `nombre`           varchar(40)  NOT NULL,
    `idJefe`           varchar(36)  NOT NULL,
    `trabajadores`     varchar(255) NOT NULL,
    `presupuesto`      double       NOT NULL,
    `proyFinalizados`  varchar(255) NOT NULL,
    `proyDesarrollo`   varchar(255) NOT NULL,
    `presupuestoAnual` double       NOT NULL,
    `historialJefes`   varchar(255) NOT NULL,
    PRIMARY KEY (`idDepartamento`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `departamento` (`idDepartamento`, `nombre`, `idJefe`, `trabajadores`, `presupuesto`, `proyFinalizados`,
                            `proyDesarrollo`, `presupuestoAnual`, `historialJefes`)
VALUES ('1e89386d-be37-4930-b6ae-bcba6c9917b4', 'Recursos Humanos', '53269670-1586-49ac-9df5-62ddd55f96cc',
        'ba2bfe86-855d-4f2c-bb92-2ad8f1db788e;53269670-1586-49ac-9df5-62ddd55f96cc', 5000,
        '81ee1211-760c-493d-968a-380e6af67363', 'f89062d9-ba34-40a4-b6af-a21a0dc093be', 25000,
        '606aba4c-b76e-4fa3-9eb8-48e20d729353;53269670-1586-49ac-9df5-62ddd55f96cc12q'),


       ('2d1d1422-fede-4e27-8883-3ffdb1be1a7c', 'Marketing', '1376acc9-79a9-4bf1-9084-c82e9a07f432',
        '1376acc9-79a9-4bf1-9084-c82e9a07f432;5cc55142-469b-4d42-9b9b-b9df2614bcc7;d63f0d73-3f1b-4afd-b5d0-821449daa4a3',
        12000, '',
        'f89062d9-ba34-40a4-b6af-a21a0dc093be;10f2db5c-a0c3-40ec-a1bf-a95cab6bebdf', 100000,
        '606aba4c-b76e-4fa3-9eb8-48e20d729353;d63f0d73-3f1b-4afd-b5d0-821449daa4a3;1376acc9-79a9-4bf1-9084-c82e9a07f432'),


       ('512a0695-3294-4c2c-86d9-4babd4485fa8', 'Logistica', '606aba4c-b76e-4fa3-9eb8-48e20d729353',
        '606aba4c-b76e-4fa3-9eb8-48e20d729353;6a69db52-f903-4978-ac08-dc32831d362e;6ba7cbcb-7f95-4c6f-b735-2a5e0a363e52',
        15500, '3730b275-3ed2-4d77-8ff4-f5ce82ea98ea;81ee1211-760c-493d-968a-380e6af67363',
        'f89062d9-ba34-40a4-b6af-a21a0dc093be', 92800,
        '5cc55142-469b-4d42-9b9b-b9df2614bcc7;606aba4c-b76e-4fa3-9eb8-48e20d729353');


CREATE TABLE `issue`
(
    `idIssue`       varchar(36)  NOT NULL,
    `titulo`        varchar(36)  NOT NULL,
    `texto`         varchar(150) NOT NULL,
    `fecha`         date         NOT NULL,
    `programadores` varchar(255) NOT NULL,
    `proyecto`      varchar(36)  NOT NULL,
    `repositorio`   varchar(36)  NOT NULL,
    `estado`        varchar(10)  NOT NULL,
    PRIMARY KEY (`idIssue`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `issue` (`idIssue`, `titulo`, `texto`, `fecha`, `programadores`, `proyecto`,
                     `repositorio`, `estado`)

VALUES ('1f9b764e-570f-4041-a0c9-fc58a794eb0d', 'arreglo 1', 'se ha encontrado un fallo en la clase',
        '2001-02-01', '53269670-1586-49ac-9df5-62ddd55f96cc;1376acc9-79a9-4bf1-9084-c82e9a07f432',
        '81ee1211-760c-493d-968a-380e6af67363', 'f64c5364-faa7-41b7-bca9-3b27f95d8fa8', 'terminada'),

       ('7e96e277-26bc-4c08-a21e-6d92eb7638de', 'arreglo 2', 'se ha encontrado un fallo en el atributo',
        '2012-03-04', 'd63f0d73-3f1b-4afd-b5d0-821449daa4a3',
        'f89062d9-ba34-40a4-b6af-a21a0dc093be', 'f1174508-8659-4654-82ce-af2704a152de', 'terminada'),

       ('6c5b7c5a-d30b-400f-9c11-84dc2b49f01e', 'arreglo 3', 'se ha encontrado un fallo en el metodo',
        '2018-10-22', '606aba4c-b76e-4fa3-9eb8-48e20d729353',
        '2d1d1422-fede-4e27-8883-3ffdb1be1a7c', 'ed38db33-7fd3-4242-91e4-a411d5fe3b1f', 'pendiente');



CREATE TABLE `programador`
(
    `idProgramador`      varchar(36)  NOT NULL,
    `nombre`             varchar(40)  NOT NULL,
    `fechaAlta`          date         NOT NULL,
    `idDepartamento`     varchar(36)  NOT NULL,
    `proyectosParticipa` varchar(255) NOT NULL,
    `commits`            varchar(255) NOT NULL,
    `issues`             varchar(255) NOT NULL,
    `tecnologias`        varchar(255) NOT NULL,
    `salario`            double       NOT NULL,
    PRIMARY KEY (`idProgramador`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `programador` (`idProgramador`, `nombre`, `fechaAlta`, `idDepartamento`, `proyectosParticipa`, `commits`,
                           `issues`, `tecnologias`, `salario`)
VALUES ('1376acc9-79a9-4bf1-9084-c82e9a07f432', 'Barnie Stinson', '2019-06-03', '2d1d1422-fede-4e27-8883-3ffdb1be1a7c',
        '233b5d47-0ced-4e6f-8982-b2f95b6b25b9;81ee1211-760c-493d-968a-380e6af67363',
        '00efadeb-cdc5-456d-9b4a-bdd8d6fc2db5',
        '1f9b764e-570f-4041-a0c9-fc58a794eb0d', '20bcca63-7b60-4a43-bb10-4c9735587d16', 1500),


       ('53269670-1586-49ac-9df5-62ddd55f96cc', 'Rick Sanchez', '2015-11-09', '1e89386d-be37-4930-b6ae-bcba6c9917b4',
        '233b5d47-0ced-4e6f-8982-b2f95b6b25b9;81ee1211-760c-493d-968a-380e6af67363',
        '699b5f3f-8e62-41e1-ba08-1546f0ab5dfb;5b64bfd6-08e4-4325-b037-bd4fcfafe783',
        '7e96e277-26bc-4c08-a21e-6d92eb7638de',
        '20bcca63-7b60-4a43-bb10-4c9735587d16;4f119f1b-7ccf-49f4-b56f-fdace8589b1c', 1200),

       ('606aba4c-b76e-4fa3-9eb8-48e20d729353', 'Andres Iniesta', '2010-02-27', '512a0695-3294-4c2c-86d9-4babd4485fa8',
        '2d1d1422-fede-4e27-8883-3ffdb1be1a7c', '6e0bb43f-c639-4a79-8ea9-eb446d5bd624',
        '6c5b7c5a-d30b-400f-9c11-84dc2b49f01e',
        '20bcca63-7b60-4a43-bb10-4c9735587d16;cb231a1d-ffc8-4a64-b090-1334f5f4f740', 1500),

       ('6a69db52-f903-4978-ac08-dc32831d362e', 'Hulk Hogan', '2001-03-21', '512a0695-3294-4c2c-86d9-4babd4485fa8',
        '2d1d1422-fede-4e27-8883-3ffdb1be1a7c', '1ebfc166-3cd9-4303-a322-f57dc5ee3e5e',
        '',
        '20bcca63-7b60-4a43-bb10-4c9735587d16;cb231a1d-ffc8-4a64-b090-1334f5f4f740', 1300),


       ('6ba7cbcb-7f95-4c6f-b735-2a5e0a363e52', 'Mark Knofler', '1999-05-23', '512a0695-3294-4c2c-86d9-4babd4485fa8',
        '2d1d1422-fede-4e27-8883-3ffdb1be1a7c', 'f89062d9-ba34-40a4-b6af-a21a0dc093be',
        '',
        'cb231a1d-ffc8-4a64-b090-1334f5f4f740', 2000),


       ('ba2bfe86-855d-4f2c-bb92-2ad8f1db788e', 'Jack Sparrow', '2006-12-07', '1e89386d-be37-4930-b6ae-bcba6c9917b4',
        '233b5d47-0ced-4e6f-8982-b2f95b6b25b9', '649ad8bd-6d6e-4430-af04-9fcfe370db84',
        '6390f634-1ef0-48c2-ab98-b39a99b6c810',
        '699b5f3f-8e62-41e1-ba08-1546f0ab5dfb', 900),


       ('5cc55142-469b-4d42-9b9b-b9df2614bcc7', 'Will Smith', '2003-11-11', '2d1d1422-fede-4e27-8883-3ffdb1be1a7c',
        '233b5d47-0ced-4e6f-8982-b2f95b6b25b9', '',
        '',
        '20bcca63-7b60-4a43-bb10-4c9735587d16', 3000),

       ('d63f0d73-3f1b-4afd-b5d0-821449daa4a3', 'Mike Wazowski', '2011-04-03', '2d1d1422-fede-4e27-8883-3ffdb1be1a7c',
        'f89062d9-ba34-40a4-b6af-a21a0dc093be;10f2db5c-a0c3-40ec-a1bf-a95cab6bebdf;3730b275-3ed2-4d77-8ff4-f5ce82ea98ea',
        '3a1690b0-b7f3-4303-8413-1f63578c3194',
        '',
        '20bcca63-7b60-4a43-bb10-4c9735587d16;4f119f1b-7ccf-49f4-b56f-fdace8589b1c', 1600);



CREATE TABLE `proyecto`
(
    `idProyecto`     varchar(36)  NOT NULL,
    `nombre`         varchar(20)  NOT NULL,
    `idJefe`         varchar(36)  NOT NULL,
    `presupuesto`    double       NOT NULL,
    `fechaInicio`    date         NOT NULL,
    `fechaFin`       date         NOT NULL,
    `tecnologias`    varchar(255) NOT NULL,
    `idRepositorio`  varchar(36)  NOT NULL,
    `idDepartamento` varchar(36)  NOT NULL,
    PRIMARY KEY (`idProyecto`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `proyecto` (`idProyecto`, `nombre`,
                        `idJefe`, `presupuesto`,
                        `fechaInicio`, `fechaFin`,
                        `tecnologias`, `idRepositorio`, `idDepartamento`)
VALUES ('81ee1211-760c-493d-968a-380e6af67363', 'Power Project',
        '1376acc9-79a9-4bf1-9084-c82e9a07f432', 8000,
        '2017-05-02', '2019-11-20',
        '20bcca63-7b60-4a43-bb10-4c9735587d16', 'f64c5364-faa7-41b7-bca9-3b27f95d8fa8',
        '1e89386d-be37-4930-b6ae-bcba6c9917b4'),

       ('f89062d9-ba34-40a4-b6af-a21a0dc093be', 'HR Project',
        '1376acc9-79a9-4bf1-9084-c82e9a07f432', 3000,
        '2019-09-22', '',
        '20bcca63-7b60-4a43-bb10-4c9735587d16;4f119f1b-7ccf-49f4-b56f-fdace8589b1c',
        'f1174508-8659-4654-82ce-af2704a152de', '1e89386d-be37-4930-b6ae-bcba6c9917b4'),

       ('10f2db5c-a0c3-40ec-a1bf-a95cab6bebdf', 'DF Project',
        '1376acc9-79a9-4bf1-9084-c82e9a07f432', 1000,
        '2009-02-22', '',
        '20bcca63-7b60-4a43-bb10-4c9735587d16',
        '4863c6e9-606f-42bb-aaff-6e961de25054', '1e89386d-be37-4930-b6ae-bcba6c9917b4'),

       ('2d1d1422-fede-4e27-8883-3ffdb1be1a7c', 'CD Project',
        '1376acc9-79a9-4bf1-9084-c82e9a07f432', 8500,
        '2009-02-22', '',
        'cb231a1d-ffc8-4a64-b090-1334f5f4f740',
        'ed38db33-7fd3-4242-91e4-a411d5fe3b1f', '2d1d1422-fede-4e27-8883-3ffdb1be1a7c'),

       ('233b5d47-0ced-4e6f-8982-b2f95b6b25b9', 'Logic Project',
        '1376acc9-79a9-4bf1-9084-c82e9a07f432', 5000,
        '2008-05-23', '',
        '20bcca63-7b60-4a43-bb10-4c9735587d16;4f119f1b-7ccf-49f4-b56f-fdace8589b1c',
        '2a11c73f-faa0-4346-82ac-fe6115ed4d6a', '512a0695-3294-4c2c-86d9-4babd4485fa8'),

       ('3730b275-3ed2-4d77-8ff4-f5ce82ea98ea', 'JS Project',
        '1376acc9-79a9-4bf1-9084-c82e9a07f432', 7100,
        '1999-05-23', '2004-09-15',
        '20bcca63-7b60-4a43-bb10-4c9735587d16', 'eecd0485-8cc8-426b-81a5-ddffe5d83a67',
        '512a0695-3294-4c2c-86d9-4babd4485fa8');



CREATE TABLE `repositorio`
(
    `idRepositorio` varchar(36)  NOT NULL,
    `idProyecto`    varchar(36)  NOT NULL,
    `fechaCreacion` date         NOT NULL,
    `commits`       varchar(255) NOT NULL,
    `issues`        varchar(255) NOT NULL,
    PRIMARY KEY (`idRepositorio`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `repositorio` (`idRepositorio`, `fechaCreacion`, `idProyecto`,
                           `commits`, `issues`)

VALUES ('f64c5364-faa7-41b7-bca9-3b27f95d8fa8', '2019-11-20', '81ee1211-760c-493d-968a-380e6af67363',
        '5b64bfd6-08e4-4325-b037-bd4fcfafe783',
        '1f9b764e-570f-4041-a0c9-fc58a794eb0d'),

       ('f1174508-8659-4654-82ce-af2704a152de', '2019-02-03', 'f89062d9-ba34-40a4-b6af-a21a0dc093be',
        '649ad8bd-6d6e-4430-af04-9fcfe370db84;3a1690b0-b7f3-4303-8413-1f63578c3194',
        '7e96e277-26bc-4c08-a21e-6d92eb7638de'),

       ('4863c6e9-606f-42bb-aaff-6e961de25054', '2020-05-02', 'f89062d9-ba34-40a4-b6af-a21a0dc093be',
        '', ''),

       ('ed38db33-7fd3-4242-91e4-a411d5fe3b1f', '2016-07-09', '2d1d1422-fede-4e27-8883-3ffdb1be1a7c',
        '6e0bb43f-c639-4a79-8ea9-eb446d5bd624;1ebfc166-3cd9-4303-a322-f57dc5ee3e5e',
        '6c5b7c5a-d30b-400f-9c11-84dc2b49f01e'),

       ('2a11c73f-faa0-4346-82ac-fe6115ed4d6a', '2018-02-03', '233b5d47-0ced-4e6f-8982-b2f95b6b25b9',
        '00efadeb-cdc5-456d-9b4a-bdd8d6fc2db5;699b5f3f-8e62-41e1-ba08-1546f0ab5dfb',
        ''),

       ('eecd0485-8cc8-426b-81a5-ddffe5d83a67', '2016-08-12', '3730b275-3ed2-4d77-8ff4-f5ce82ea98ea',
        '3a1690b0-b7f3-4303-8413-1f63578c3194', '');



CREATE TABLE `tecnologia`
(
    `idTecnologia` varchar(36) NOT NULL,
    `nombre`       varchar(20) NOT NULL,
    PRIMARY KEY (`idTecnologia`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


INSERT INTO `tecnologia` (`idTecnologia`, `nombre`)

VALUES ('20bcca63-7b60-4a43-bb10-4c9735587d16',
        'Python'),

       ('4f119f1b-7ccf-49f4-b56f-fdace8589b1c',
        'Kotlin'),

       ('cb231a1d-ffc8-4a64-b090-1334f5f4f740',
        'Java');


CREATE TABLE `utilidades`
(
    `idUtilidades` varchar(36)  NOT NULL,
    `idProyecto`   varchar(36)  NOT NULL,
    `tecnologias`  varchar(255) NOT NULL,
    PRIMARY KEY (`idUtilidades`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `utilidades` (`idUtilidades`, `idProyecto`, `tecnologias`)

VALUES ('a861eeba-2983-46ea-a3c1-6ccdaed4f21e', '81ee1211-760c-493d-968a-380e6af67363',
        '20bcca63-7b60-4a43-bb10-4c9735587d16'),

       ('69b2ae91-8762-4bb3-8181-cbb8c80334f9', 'f89062d9-ba34-40a4-b6af-a21a0dc093be',
        '20bcca63-7b60-4a43-bb10-4c9735587d16;4f119f1b-7ccf-49f4-b56f-fdace8589b1c'),

       ('405fda30-1677-4f96-8ae4-b37db5d1a99b', '10f2db5c-a0c3-40ec-a1bf-a95cab6bebdf',
        '20bcca63-7b60-4a43-bb10-4c9735587d16');



CREATE TABLE `participaciones`
(
    `idParticipaciones` varchar(36)  NOT NULL,
    `idProyecto`   varchar(36)  NOT NULL,
    `programadores`  varchar(255) NOT NULL,
    PRIMARY KEY (`idParticipaciones`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

INSERT INTO `participaciones` (`idParticipaciones`, `idProyecto`, `programadores`)

VALUES ('37928235-0838-4663-88e8-5fd05330a52d', '81ee1211-760c-493d-968a-380e6af67363',
        '1376acc9-79a9-4bf1-9084-c82e9a07f432'),

       ('21ee0a9c-002f-4cd2-8500-67b65eae015a', 'f89062d9-ba34-40a4-b6af-a21a0dc093be',
        'd63f0d73-3f1b-4afd-b5d0-821449daa4a3'),

       ('dbde1829-9951-44dd-9b41-3f66cc87bd09', '10f2db5c-a0c3-40ec-a1bf-a95cab6bebdf',
        'd63f0d73-3f1b-4afd-b5d0-821449daa4a3');