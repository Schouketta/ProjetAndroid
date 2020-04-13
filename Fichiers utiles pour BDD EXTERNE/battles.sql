-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  lun. 13 avr. 2020 à 13:56
-- Version du serveur :  5.7.26
-- Version de PHP :  7.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `battledb`
--

-- --------------------------------------------------------

--
-- Structure de la table `battles`
--

DROP TABLE IF EXISTS `battles`;
CREATE TABLE IF NOT EXISTS `battles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `team1` text NOT NULL,
  `team2` text NOT NULL,
  `tech1` text NOT NULL,
  `art1` text NOT NULL,
  `espace1` text NOT NULL,
  `style1` text NOT NULL,
  `originalite1` text NOT NULL,
  `total1` text NOT NULL,
  `tech2` text NOT NULL,
  `art2` text NOT NULL,
  `espace2` text NOT NULL,
  `style2` text NOT NULL,
  `originalite2` text NOT NULL,
  `total2` text NOT NULL,
  `longitude` text NOT NULL,
  `latitude` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `battles`
--

INSERT INTO `battles` (`id`, `team1`, `team2`, `tech1`, `art1`, `espace1`, `style1`, `originalite1`, `total1`, `tech2`, `art2`, `espace2`, `style2`, `originalite2`, `total2`, `longitude`, `latitude`) VALUES
(1, 'MoveYourFeet', 'ESILVDance', '2', '2', '1', '2', '1', '8', '3', '3', '1', '3', '3', '13', '2.286121', '48.851917'),
(2, 'BBoysSeattle', 'BBoysNYC', '2', '2', '1', '2', '1', '8', '3', '3', '1', '3', '3', '13', '1.960054', '48.828707'),
(3, 'SalsaTeamCuba', 'SalsaTeamSpain', '2', '2', '1', '2', '1', '8', '3', '3', '1', '3', '3', '13', '-3.709007', '40.415869'),
(4, 'GirlzTectonik', 'TectonikBoyz', '2', '2', '1', '2', '1', '8', '3', '3', '1', '3', '3', '13', '6.138288', '46.199846'),
(5, 'LesTwins', 'DosSista', '2', '2', '1', '2', '1', '8', '3', '3', '1', '3', '3', '13', '121.466637', '25.007662'),
(6, 'Fauve', 'Denitsa', '2', '2', '1', '2', '1', '8', '3', '3', '1', '3', '3', '13', '138.551443', '-34.891580'),
(7, 'Dance4Life', 'BeatMove', '2', '2', '1', '2', '1', '8', '3', '3', '1', '3', '3', '13', '137.995234', '6.138288');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
