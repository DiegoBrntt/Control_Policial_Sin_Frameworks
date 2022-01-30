-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-11-2021 a las 02:14:14
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `control_policial`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `casos`
--

CREATE TABLE `casos` (
  `codigo_caso` int(11) NOT NULL,
  `fecha_caso` date NOT NULL,
  `codigo_juzgado` int(11) NOT NULL,
  `codigo_suc` int(11) NOT NULL,
  `condena` tinyint(1) NOT NULL,
  `tiempo_condena` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `casos`
--

INSERT INTO `casos` (`codigo_caso`, `fecha_caso`, `codigo_juzgado`, `codigo_suc`, `condena`, `tiempo_condena`) VALUES
(34, '2021-11-04', 33, 12, 1, 4),
(44, '2021-11-04', 55, 22, 1, 3),
(2434, '2021-11-19', 33, 11, 0, 34),
(3452, '2021-11-04', 33, 11, 1, 3),
(4242, '2021-11-05', 123, 22, 1, 6),
(4444, '2021-11-04', 33, 11, 1, 3),
(5555, '2021-11-04', 33, 12, 1, 5),
(7878, '2021-11-04', 33, 11, 1, 67),
(56565, '2021-11-12', 33, 11, 0, 65),
(678678, '2021-11-04', 33, 11, 1, 6),
(4434534, '2021-11-04', 33, 11, 1, 44),
(6456564, '2021-11-04', 33, 11, 1, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `condena_det`
--

CREATE TABLE `condena_det` (
  `codigo_detalle` int(11) NOT NULL,
  `codigo_caso` int(11) NOT NULL,
  `codigo_det` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `condena_det`
--

INSERT INTO `condena_det` (`codigo_detalle`, `codigo_caso`, `codigo_det`) VALUES
(54, 4242, 101),
(55, 44, 101),
(56, 44, 201),
(57, 5555, 101),
(58, 5555, 102),
(59, 4444, 101),
(60, 4444, 102),
(61, 4434534, 101),
(62, 6456564, 101),
(63, 34, 101),
(65, 7878, 101),
(66, 3452, 101),
(67, 678678, 101);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contratos`
--

CREATE TABLE `contratos` (
  `codigo_contrato` int(11) NOT NULL,
  `fecha_contratacion` date NOT NULL,
  `codigo_suc` int(11) NOT NULL,
  `codigo_vig` varchar(11) NOT NULL,
  `dias_contratados` int(11) NOT NULL,
  `armado` tinyint(1) NOT NULL,
  `estado` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `contratos`
--

INSERT INTO `contratos` (`codigo_contrato`, `fecha_contratacion`, `codigo_suc`, `codigo_vig`, `dias_contratados`, `armado`, `estado`) VALUES
(35, '2021-10-01', 11, 'V002', 10, 1, 0),
(11001, '2021-10-31', 11, 'V001', 30, 0, 1),
(22001, '2021-11-16', 22, 'V001', 20, 0, 1),
(23001, '2021-11-30', 23, 'V001', 10, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detenidos`
--

CREATE TABLE `detenidos` (
  `codigo_det` int(11) NOT NULL,
  `codigo_banda` int(11) NOT NULL,
  `nombre_ape` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `detenidos`
--

INSERT INTO `detenidos` (`codigo_det`, `codigo_banda`, `nombre_ape`) VALUES
(101, 1, 'Marcos Aguirre'),
(102, 1, 'Franco munio'),
(103, 1, 'Noelia mancuello'),
(201, 2, 'Maria Enriqueta');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entidades`
--

CREATE TABLE `entidades` (
  `codigo` varchar(5) NOT NULL,
  `domicilio` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `entidades`
--

INSERT INTO `entidades` (`codigo`, `domicilio`) VALUES
('01', 'Av. La Plata 4040'),
('02', 'Directorio 3200');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `jueces`
--

CREATE TABLE `jueces` (
  `numero_juzgado` int(11) NOT NULL,
  `nombre_ape` varchar(100) NOT NULL,
  `años_serv` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `jueces`
--

INSERT INTO `jueces` (`numero_juzgado`, `nombre_ape`, `años_serv`) VALUES
(33, 'Lautaro Nahuel', 6),
(55, 'Fernando Ortiva', 30),
(123, 'Damian Gutierrez', 40);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sucursales`
--

CREATE TABLE `sucursales` (
  `codigo_ent` int(11) NOT NULL,
  `codigo_suc` int(5) NOT NULL,
  `domicilio` varchar(100) NOT NULL,
  `empleados` int(11) NOT NULL,
  `fecha_contratacion` date NOT NULL,
  `frecuencia_contratacion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `sucursales`
--

INSERT INTO `sucursales` (`codigo_ent`, `codigo_suc`, `domicilio`, `empleados`, `fecha_contratacion`, `frecuencia_contratacion`) VALUES
(1, 11, 'Caseros 3375', 57, '2021-10-01', 30),
(2, 12, 'Carabobo 2342', 90, '2021-11-10', 30),
(1, 22, 'Daract 322', 34, '2021-11-01', 15),
(2, 23, 'Virrey Liniers 2890', 33, '2021-11-30', 15);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `clave` varchar(4) NOT NULL,
  `pass` varchar(20) NOT NULL,
  `edad` int(2) NOT NULL,
  `tipo` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`clave`, `pass`, `edad`, `tipo`) VALUES
('A001', 'adm001', 43, 'Administrador'),
('fern', 'pppp', 45, 'Administrador'),
('I001', 'inv001', 35, 'Investigador'),
('V001', 'vig001', 27, 'Vigilante'),
('V002', 'vig002', 35, 'Vigilante');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `casos`
--
ALTER TABLE `casos`
  ADD PRIMARY KEY (`codigo_caso`);

--
-- Indices de la tabla `condena_det`
--
ALTER TABLE `condena_det`
  ADD PRIMARY KEY (`codigo_detalle`);

--
-- Indices de la tabla `contratos`
--
ALTER TABLE `contratos`
  ADD PRIMARY KEY (`codigo_contrato`);

--
-- Indices de la tabla `detenidos`
--
ALTER TABLE `detenidos`
  ADD PRIMARY KEY (`codigo_det`);

--
-- Indices de la tabla `entidades`
--
ALTER TABLE `entidades`
  ADD PRIMARY KEY (`codigo`);

--
-- Indices de la tabla `jueces`
--
ALTER TABLE `jueces`
  ADD PRIMARY KEY (`numero_juzgado`);

--
-- Indices de la tabla `sucursales`
--
ALTER TABLE `sucursales`
  ADD PRIMARY KEY (`codigo_suc`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`clave`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `casos`
--
ALTER TABLE `casos`
  MODIFY `codigo_caso` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=808080881;

--
-- AUTO_INCREMENT de la tabla `condena_det`
--
ALTER TABLE `condena_det`
  MODIFY `codigo_detalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;

--
-- AUTO_INCREMENT de la tabla `contratos`
--
ALTER TABLE `contratos`
  MODIFY `codigo_contrato` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=111111112;

--
-- AUTO_INCREMENT de la tabla `jueces`
--
ALTER TABLE `jueces`
  MODIFY `numero_juzgado` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=124;

--
-- AUTO_INCREMENT de la tabla `sucursales`
--
ALTER TABLE `sucursales`
  MODIFY `codigo_suc` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=56;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
