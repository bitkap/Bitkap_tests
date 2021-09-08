-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 01 sep. 2021 à 14:43
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bdbitkap`
--

-- --------------------------------------------------------

--
-- Structure de la table `comment`
--

CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL,
  `date` datetime DEFAULT NULL,
  `description` varchar(100) NOT NULL,
  `event_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `comment`
--

INSERT INTO `comment` (`id`, `date`, `description`, `event_id`, `user_id`) VALUES
(8, '2021-08-31 19:18:19', 'je suis mort de rire', 5, 1),
(9, '2021-08-31 19:18:19', 'vraiment satisfait par la lecture en groupe du roman l\' amour existe t-il ?', 6, 2),
(10, '2021-08-31 21:35:32', 'je suis très partant,car la programmation est une passion pour moi', 7, 4),
(11, '2021-08-31 21:31:42', 'je suis au sol', 5, 1);

-- --------------------------------------------------------

--
-- Structure de la table `event`
--

CREATE TABLE `event` (
  `id` bigint(20) NOT NULL,
  `date` varchar(255) DEFAULT NULL,
  `description` varchar(250) NOT NULL,
  `time` varchar(255) DEFAULT NULL,
  `titre` varchar(100) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  `locality` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `event`
--

INSERT INTO `event` (`id`, `date`, `description`, `time`, `titre`, `user_id`, `locality`) VALUES
(5, '29-09-2021', 'venez nombreux pour rire avec nous', '15:30', 'rire à gogo', 1, 'yaoundé'),
(6, '10-11-2021', 'prions ensemble', '1:30', 'louang', 2, 'Limbe'),
(7, '30-19-2021', 'venez on programme', '20:30', 'java spring', 3, 'Bertoua'),
(13, '10-11-2021', 'some hacking pro together for put a fire', '21:30', 'hacking pro', 1, 'Kribi');

-- --------------------------------------------------------

--
-- Structure de la table `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(15);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `name`, `surname`) VALUES
(1, 'pierre', 'corneille'),
(2, 'rene', 'jean'),
(3, 'victor', 'hugo'),
(4, 'louis', 'bernard');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhr48nopy5aorw0ta1ii704tpu` (`event_id`),
  ADD KEY `FK8kcum44fvpupyw6f5baccx25c` (`user_id`);

--
-- Index pour la table `event`
--
ALTER TABLE `event`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_6pdqdobxahfckky7f1duntbo3` (`titre`),
  ADD KEY `FKi8bsvlthqr8lngsyshiqsodak` (`user_id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
