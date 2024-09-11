SELECT * FROM db_pizza.produit;
-- Insertion des types de produits

INSERT INTO TYPE_PRODUIT (id_type_produit, libelle) VALUES
(1, 'pizza'),
(2, 'boisson');

-- Insertion de 5 pizzas
INSERT INTO produit (id_produit, nom, description, prix, image_url, TYPE_PRODUIT_id_type_produit) VALUES
(1, 'Pizza marseillaise', 'Pizza douce et somptueuse', 15.50, 'https://i.postimg.cc/hvFDSFxP/pizza-1.png', 1),
(2, 'Pizza franklin', 'Pizza pour les champions', 17.00, 'https://i.postimg.cc/PJmtJ6zb/pizza-2.png', 1),
(3, 'Pizza frenchie', 'Pizza signature de la FRENCH PIZZ', 18.75, 'https://i.postimg.cc/7ZPxPnC7/pizza-7.png', 1),
(4, 'Pizza Moorea', 'Pizza corse, mauvais caractère mais délicieuse', 16.25, 'https://i.postimg.cc/Gmbp81f4/pizza-9.png', 1),
(5, 'Pizza gourde', 'Pizza de la saison', 19.00, 'https://i.postimg.cc/5NdyJzqq/pizza-4.png', 1);

-- Insertion de 5 boissons
INSERT INTO produit (id_produit, nom, description, prix, image_url, TYPE_PRODUIT_id_type_produit) VALUES
(6, 'Coca-Cola', 'Boisson gazeuse classique', 2.50, 'https://i.postimg.cc/3N6w0yP7/pngwing-com.png', 2),
(7, 'Sprite', 'Boisson gazeuse au citron', 3.00, 'https://i.postimg.cc/mrw2Np0m/pngwing-com-3.png', 2),
(8, 'Fanta', 'Boisson gazeuse à l\'orange', 2.75, 'https://i.postimg.cc/pdxLjM19/pngwing-com-4.png', 2),
(9, 'Orangina', 'Boisson gazeuse à l\'orange', 2.00, 'https://i.postimg.cc/sXHjbQfS/pngwing-com-2.png', 2),
(10, 'Oasis', 'Boisson exotique', 4.50, 'https://i.postimg.cc/NFWgf3Hj/pngwing-com-1.png', 2);