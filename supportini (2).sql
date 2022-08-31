-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mar. 30 août 2022 à 01:35
-- Version du serveur : 10.4.24-MariaDB
-- Version de PHP : 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `supportini`
--
CREATE DATABASE IF NOT EXISTS supportini;
USE supportini;
-- --------------------------------------------------------

--
-- Structure de la table `coachs`
--

CREATE TABLE `coachs` (
  `id` int(11) NOT NULL,
  `specialite` varchar(200) NOT NULL,
  `fk_idUser` int(11) NOT NULL,
  `fk_idPlaning` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `coachs`
--

INSERT INTO `coachs` (`id`, `specialite`, `fk_idUser`, `fk_idPlaning`) VALUES
(10, 'null, musculation, dance, karate', 34, 0),
(11, 'null, boxing', 36, 0),
(12, 'null, boxing', 36, 0),
(13, 'null, boxing', 36, 0);

-- --------------------------------------------------------

--
-- Structure de la table `entrainees`
--

CREATE TABLE `entrainees` (
  `id` int(11) NOT NULL,
  `age` int(11) NOT NULL,
  `taille` int(11) NOT NULL,
  `poids` int(11) NOT NULL,
  `sexe` varchar(20) NOT NULL,
  `fk_idUser` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `entrainees`
--

INSERT INTO `entrainees` (`id`, `age`, `taille`, `poids`, `sexe`, `fk_idUser`) VALUES
(3, 25, 258, 26, 'homme', 33),
(4, 85, 258, 96, 'homme', 35),
(5, 50, 500, 80, 'homme', 37);

-- --------------------------------------------------------

--
-- Structure de la table `roles`
--

CREATE TABLE `roles` (
  `id` int(11) NOT NULL,
  `type` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `roles`
--

INSERT INTO `roles` (`id`, `type`) VALUES
(1, 'admin'),
(2, 'entrainé'),
(3, 'coach'),
(4, 'propriétaire de la salle');

-- --------------------------------------------------------

--
-- Structure de la table `salledessport`
--

CREATE TABLE `salledessport` (
  `id` int(11) NOT NULL,
  `nomSalle` varchar(20) NOT NULL,
  `numTel` int(11) NOT NULL,
  `ville` varchar(20) NOT NULL,
  `rue` varchar(20) NOT NULL,
  `codePostal` varchar(20) NOT NULL,
  `description` varchar(200) NOT NULL,
  `prix` float NOT NULL,
  `duration` varchar(20) NOT NULL,
  `fk_idUser` int(11) NOT NULL,
  `imageVitrine` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `salledessport`
--

INSERT INTO `salledessport` (`id`, `nomSalle`, `numTel`, `ville`, `rue`, `codePostal`, `description`, `prix`, `duration`, `fk_idUser`, `imageVitrine`) VALUES
(2, 'hassen', 12456576, 'kef', 'dir', '7100', 'HASSEN SALLE DE SPORT', 120, '4H', 33, 'images.jfif'),
(3, 'omar', 23546764, 'beja', 'benanin', '6578', 'ggghjgjghjghjghjghj', 234, '8h', 36, 'salle.jpg');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

CREATE TABLE `utilisateurs` (
  `id` int(11) NOT NULL,
  `nom` varchar(100) NOT NULL,
  `prenom` varchar(100) NOT NULL,
  `cin` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `fk_idRole` int(11) NOT NULL DEFAULT 2,
  `phone` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`id`, `nom`, `prenom`, `cin`, `email`, `password`, `fk_idRole`, `phone`) VALUES
(33, 'ahmed', 'ahm', '78945612', 'ahmed@&&.&&', 'e02cd9869d0320a6487045c7f0db3ce999dec0da47d23fd0f97420a4d4e56984', 2, '21364578'),
(34, 'salem', 'sal', '24635871', 'salem@&&.com', '4bfc5e93a98165c11913f4fa966a04e2d8e4e64f55aaffa52fb2415407201319', 3, '96325874'),
(35, 'mouhib', 'mou', '36985214', 'mouhib@&&.&&', '4da33d4b234f4384d72f76c00a7a98e68a8f469770ca472396cf1defab25018e', 2, '14785236'),
(36, 'hassen', 'rahali', '12345678', 'hassen@gmail.com', '6fd505a73b9dd4474e44362a78211a84a3d1f039b231ae9dfc62c0cd18a6c3ac', 3, '12764876'),
(37, 'hsouna', 'rahali', '23456789', 'hassen@gmail', 'f0f0901dd5cf874c4894d979c118c6ebb3b8831c6b6c2e8a3c2f95d604a23d57', 2, '87654321');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `coachs`
--
ALTER TABLE `coachs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_idUser` (`fk_idUser`);

--
-- Index pour la table `entrainees`
--
ALTER TABLE `entrainees`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_idUser` (`fk_idUser`);

--
-- Index pour la table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `salledessport`
--
ALTER TABLE `salledessport`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `fk_idUser` (`fk_idUser`),
  ADD UNIQUE KEY `numTel` (`numTel`);

--
-- Index pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `cin` (`cin`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `password` (`password`),
  ADD UNIQUE KEY `phone` (`phone`),
  ADD KEY `fk_idRole` (`fk_idRole`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `coachs`
--
ALTER TABLE `coachs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `entrainees`
--
ALTER TABLE `entrainees`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `salledessport`
--
ALTER TABLE `salledessport`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `coachs`
--
ALTER TABLE `coachs`
  ADD CONSTRAINT `coachs_ibfk_1` FOREIGN KEY (`fk_idUser`) REFERENCES `utilisateurs` (`id`);

--
-- Contraintes pour la table `entrainees`
--
ALTER TABLE `entrainees`
  ADD CONSTRAINT `entrainees_ibfk_1` FOREIGN KEY (`fk_idUser`) REFERENCES `utilisateurs` (`id`);

--
-- Contraintes pour la table `salledessport`
--
ALTER TABLE `salledessport`
  ADD CONSTRAINT `salledessport_ibfk_1` FOREIGN KEY (`fk_idUser`) REFERENCES `utilisateurs` (`id`);

--
-- Contraintes pour la table `utilisateurs`
--
ALTER TABLE `utilisateurs`
  ADD CONSTRAINT `utilisateurs_ibfk_1` FOREIGN KEY (`fk_idRole`) REFERENCES `roles` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
