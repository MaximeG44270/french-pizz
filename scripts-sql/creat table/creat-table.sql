CREATE TABLE `produit` (
  `id_produit` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `description` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `prix` decimal(10,2) NOT NULL,
  `image_url` varchar(800) DEFAULT NULL,
  `TYPE_PRODUIT_id_type_produit` int NOT NULL,
  PRIMARY KEY (`id_produit`),
  KEY `fk_type_produit` (`TYPE_PRODUIT_id_type_produit`),
  CONSTRAINT `fk_type_produit` FOREIGN KEY (`TYPE_PRODUIT_id_type_produit`) REFERENCES `type_produit` (`id_type_produit`),
  CONSTRAINT `PRODUIT_TYPE_PRODUIT_FK` FOREIGN KEY (`TYPE_PRODUIT_id_type_produit`) REFERENCES `type_produit` (`id_type_produit`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;
SELECT * FROM db_pizza.produit;CREATE TABLE `client` (
  `id_client` int NOT NULL AUTO_INCREMENT,
  `prenom` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `nom` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `rue` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `code_postal` varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `ville` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id_client`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `commande` (
  `id_commande` int NOT NULL AUTO_INCREMENT,
  `date_heure_livraison` datetime NOT NULL,
  `CLIENT_id_client` int NOT NULL,
  `livraison` bit(1) NOT NULL,
  `ETAT_id_etat` int NOT NULL,
  `UTILISATEUR_id_utilisateur` int DEFAULT NULL,
  `prix_total` decimal(10,2) NOT NULL,
  `est_paye` bit(1) NOT NULL,
  PRIMARY KEY (`id_commande`),
  KEY `COMMANDE_CLIENT_FK` (`CLIENT_id_client`),
  KEY `COMMANDE_ETAT_FK` (`ETAT_id_etat`),
  KEY `COMMANDE_UTILISATEUR_FK` (`UTILISATEUR_id_utilisateur`),
  CONSTRAINT `COMMANDE_CLIENT_FK` FOREIGN KEY (`CLIENT_id_client`) REFERENCES `client` (`id_client`),
  CONSTRAINT `COMMANDE_ETAT_FK` FOREIGN KEY (`ETAT_id_etat`) REFERENCES `etat` (`id_etat`),
  CONSTRAINT `COMMANDE_UTILISATEUR_FK` FOREIGN KEY (`UTILISATEUR_id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `detail_commande` (
  `quantite` int NOT NULL,
  `COMMANDE_id_commande` int NOT NULL,
  `PRODUIT_id_produit` int NOT NULL,
  PRIMARY KEY (`COMMANDE_id_commande`,`PRODUIT_id_produit`),
  KEY `DETAIL_COMMANDE_PRODUIT_FK` (`PRODUIT_id_produit`),
  CONSTRAINT `DETAIL_COMMANDE_COMMANDE_FK` FOREIGN KEY (`COMMANDE_id_commande`) REFERENCES `commande` (`id_commande`),
  CONSTRAINT `DETAIL_COMMANDE_PRODUIT_FK` FOREIGN KEY (`PRODUIT_id_produit`) REFERENCES `produit` (`id_produit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `etat` (
  `id_etat` int NOT NULL,
  `libelle` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id_etat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `produit` (
  `id_produit` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `description` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `prix` decimal(10,2) NOT NULL,
  `image_url` varchar(800) DEFAULT NULL,
  `TYPE_PRODUIT_id_type_produit` int NOT NULL,
  PRIMARY KEY (`id_produit`),
  KEY `fk_type_produit` (`TYPE_PRODUIT_id_type_produit`),
  CONSTRAINT `fk_type_produit` FOREIGN KEY (`TYPE_PRODUIT_id_type_produit`) REFERENCES `type_produit` (`id_type_produit`),
  CONSTRAINT `PRODUIT_TYPE_PRODUIT_FK` FOREIGN KEY (`TYPE_PRODUIT_id_type_produit`) REFERENCES `type_produit` (`id_type_produit`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb3;

CREATE TABLE `role` (
  `id_role` int NOT NULL,
  `libelle` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `role_utilisateur` (
  `UTILISATEUR_id_utilisateur` int NOT NULL,
  `ROLE_id_role` int NOT NULL,
  PRIMARY KEY (`UTILISATEUR_id_utilisateur`,`ROLE_id_role`),
  KEY `ROLE_UTILISATEUR_ROLE_FK` (`ROLE_id_role`),
  CONSTRAINT `ROLE_UTILISATEUR_ROLE_FK` FOREIGN KEY (`ROLE_id_role`) REFERENCES `role` (`id_role`),
  CONSTRAINT `ROLE_UTILISATEUR_UTILISATEUR_FK` FOREIGN KEY (`UTILISATEUR_id_utilisateur`) REFERENCES `utilisateur` (`id_utilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `type_produit` (
  `id_type_produit` int NOT NULL,
  `libelle` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id_type_produit`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `utilisateur` (
  `id_utilisateur` int NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `prenom` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `email` varchar(50) NOT NULL,
  `mot_de_passe` varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  PRIMARY KEY (`id_utilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
