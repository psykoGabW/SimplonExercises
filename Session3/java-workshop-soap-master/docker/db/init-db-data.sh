#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL

  CREATE user fooduser LOGIN PASSWORD 'fooddb';
	CREATE DATABASE food;
	GRANT ALL PRIVILEGES ON DATABASE food TO "fooduser";

EOSQL

psql -v ON_ERROR_STOP=1 -d food --username "fooduser" <<-EOSQL
  
  CREATE TABLE public.food_category (
	  id serial NOT NULL,
	  name text NULL,
	  CONSTRAINT food_category_pkey PRIMARY KEY (id)
  )
  WITH (
	  OIDS=FALSE
  ) ;

  CREATE TABLE public.food_attribute (
    id serial NOT NULL,
    category_id int4 NULL,
    "name" text NULL,
    energy int4 NULL,
    protein int4 NULL,
    carb int4 NULL,
    fat int4 NULL,
    CONSTRAINT food_attribute_pkey PRIMARY KEY (id)
  )
  WITH (
    OIDS=FALSE
  ) ;

  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(2, 'Agar Agar', 160, 2, 0, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(2, 'Agar Agar', 160, 2, 0, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(3, 'Sirop d''agave', 293, 0, 73, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(4, 'Macaronis de l''alpage, préparés', 184, 6, 19, 9);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(5, 'Amaretti (biscuits aux amandes)', 451, 8, 76, 12);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(6, 'Ananas, sucré, conserve', 87, 0, 20, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(7, 'Ananas, cru', 51, 0, 11, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(6, 'Ananas, non sucré , conserve', 51, 0, 11, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(6, 'Pomme, cuite,  égouttée (sans adjonction de sucre)', 72, 0, 15, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(8, 'Pomme, pelée, sechée', 295, 2, 63, 2);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(7, 'Pomme, crue', 55, 0, 12, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(9, 'Tarte aux pommes, cuite au four (pâte feuilletée)', 142, 2, 17, 7);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(9, 'Tarte aux pommes, cuite au four (pâte à gateau)', 134, 3, 19, 5);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(6, 'Purée de pommes, sucrée, conserve', 90, 0, 21, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(6, 'Purée de pommes, non sucrée, conserve', 55, 0, 12, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(10, 'Jus de pomme', 44, 0, 11, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(11, 'Cidre de pomme, 4 vol%', 32, 0, 3, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(11, 'Cidre de pomme, 6.2 vol%', 64, 0, 7, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(12, 'Appenzeller, quart-gras', 238, 34, 0, 11);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(12, 'Appenzeller, gras', 386, 25, 0, 32);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(6, 'Abricot, cuit,  égoutté (sans adjonction de sucre)', 60, 1, 13, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(8, 'Abricot, sec', 275, 5, 48, 1);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(6, 'Abricot, sucré, conserve', 84, 1, 19, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(7, 'Abricot, cru', 48, 1, 10, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(6, 'Abricot, non sucré, conserve', 48, 1, 10, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(9, 'Tarte aux abricots, cuite au four (pâte feuilletée)', 137, 3, 15, 7);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(9, 'Tarte aux abricots, cuite au four (pâte à gateau)', 128, 3, 18, 5);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(13, 'Aubergine, à l''étuveée (sans adjonction de graisse ni de sel)', 28, 1, 4, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(14, 'Aubergine, crue', 23, 1, 3, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(4, 'Piccata d''aubergines, préparée', 169, 5, 9, 12);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(4, 'Tranches d''aubergine, panées, préparées', 139, 5, 15, 6);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(15, 'Charcuterie (moyenne)', 289, 14, 1, 26);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(16, 'Avocat, cru', 144, 2, 1, 14);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(17, 'Levure de boulangerie, pressée', 96, 17, 1, 1);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(18, 'Bami Goreng, préparé', 137, 14, 14, 3);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(8, 'Banane, déshydratée (chips)', 370, 4, 81, 2);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(8, 'Banane, mi-sèche', 273, 3, 60, 1);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(7, 'Banane, crue', 95, 1, 21, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(19, 'Basilic, cru', 46, 3, 5, 1);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(5, 'Leckerli de Bâle', 404, 7, 75, 8);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(20, 'Saucisse à rôtir de porc, grillée (sans adjonction de graisse ni de sel)', 270, 21, 0, 20);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(20, 'Saucisse à rôtir de porc, crue', 243, 17, 0, 19);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(21, 'Pain paysan', 240, 9, 45, 2);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(20, 'Salami à la mode campagnarde', 532, 21, 1, 50);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(20, 'Schüblig de campagne', 524, 21, 0, 49);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(22, 'Noix', 742, 16, 7, 71);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(21, 'Pain aux noix', 338, 9, 31, 19);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(23, 'Sauce Béchamel fait maison', 148, 4, 10, 10);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(6, 'Baie (moyenne), cuite (sans adjonction de sucre)', 44, 1, 8, 1);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(7, 'Baie (moyenne), crue', 42, 1, 7, 1);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(24, 'Boule de Berlin', 326, 8, 43, 13);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(12, 'Fromage des alpes bernoises', 456, 28, 0, 38);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(12, 'Berner Hobelkäse (fromage à rebibes)', 493, 31, 0, 41);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(15, 'Saucisse bernoise (à la langue)', 334, 17, 0, 29);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(24, 'Biber', 464, 10, 57, 20);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(25, 'Bière panachée (moyenne)', 38, 0, 7, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(26, 'Levure de bière, séchée', 354, 48, 27, 4);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(15, 'Saucisse du brasseur', 350, 17, 0, 31);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(27, 'Birchermüesli, préparé (non sucré)', 87, 3, 11, 3);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(6, 'Poire, cuite,  égouttée (sans adjonction de sucre)', 75, 1, 16, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(8, 'Poire, séchée', 288, 2, 60, 1);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(6, 'Poire, sucrée, conserve', 92, 0, 21, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(7, 'Poire, crue', 58, 0, 12, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(6, 'Poire, non sucrée, conserve', 58, 0, 12, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(9, 'Tarte aux poires, cuite au four (pâte feuilletée)', 144, 2, 17, 7);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(9, 'Tarte aux poires, cuite au four (pâte à gateau)', 136, 3, 19, 5);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(10, 'Jus de poire', 50, 1, 11, 1);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(24, 'Chausson aux poires', 358, 5, 57, 10);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(28, 'Blanc battu', 43, 8, 3, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(28, 'Blanc battu aux fruits light', 68, 8, 8, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(29, 'Pâte feuillettée faite maison (au beurre), crue', 364, 5, 29, 25);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(29, 'Pâte feuilletée faite maison (avec graisse végétale), crue', 358, 5, 29, 24);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(30, 'Vol-au-vent au beurre', 564, 6, 43, 41);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(31, 'Flûte feuilletté', 497, 7, 40, 34);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(13, 'Légumes feuille (moyenne sans salade), à la vapeur  (sans adjonction de sel)', 23, 2, 2, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(13, 'Légumes feuille (moyenne sans salade), à l''étuvée (sans adjonction de graisse ni de sel)', 25, 2, 3, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(13, 'Légumes feuille (moyenne sans salade), cuits (sans adjonction de graisse ni de sel)', 24, 2, 2, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(14, 'Salade verte (moyenne), crue', 18, 2, 1, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(12, 'Fromage bleu (moyenne)', 361, 19, 0, 32);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(13, 'Chou-fleur, à la vapeur (sans adjonction de sel)', 27, 3, 2, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(14, 'Chou-fleur, cru', 26, 2, 2, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(32, 'Boudin', 151, 11, 3, 11);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(33, 'Haricot commun (tout type), grain mature, cuit (sans adjonction de graisse ni de sel)', 114, 8, 15, 1);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(33, 'Haricot commun (tout type), grain mature, sec', 305, 21, 41, 2);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(13, 'Haricot (vert), à la vapeur (sans adjonction de sel)', 34, 2, 4, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(34, 'Haricot (vert) sec, cru', 252, 20, 26, 2);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(14, 'Haricot vert, cru', 31, 2, 4, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(23, 'Sauce bolognaise', 96, 6, 8, 4);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(35, 'Bouillon de viande, preparé', 5, 0, 0, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(35, 'Bouillon de poule, preparé', 5, 0, 0, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(35, 'Bouillon de légumes, preparé', 6, 0, 0, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(36, 'Chocolat au lait, fourré à la crème de noisettes ', 557, 8, 49, 36);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(37, 'Eau-de-vie de céréales (p.ex. Whisky)', 252, 0, 0, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(37, 'Eau-de-vie de vin (p. ex. Cognac)', 240, 0, 2, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(37, 'Eau-de-vie de canne à sucre (p.ex. Rhum)', 234, 0, 0, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(38, 'Beurre à rôtir', 885, 0, 0, 98);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(23, 'Sauce de rôti, liée', 53, 2, 5, 3);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(23, 'Sauce de rôti, claire', 44, 2, 4, 3);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(20, 'Bresaola', 166, 32, 0, 4);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(39, 'Brie, mi-gras', 200, 24, 0, 12);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(39, 'Brie, à la crème', 335, 16, 0, 30);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(39, 'Brie, gras', 300, 22, 0, 24);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(13, 'Brocoli, à la vapeur (sans adjonction de sel)', 30, 3, 2, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(14, 'Brocoli, cru', 31, 3, 2, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(7, 'Mûre, crue', 44, 1, 6, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(9, 'Tarte aux mûres, cuite au four (pâte feuilletée)', 135, 3, 13, 7);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(9, 'Tarte aux mûres, cuite au four (pâte à gateau)', 126, 3, 15, 5);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(9, 'Tarte aux mûres, cuite au four (pâte brisée sucrée)', 199, 3, 19, 11);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(21, 'Pain (moyenne)', 265, 9, 45, 5);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(15, 'Produits de charcuterie échaudés, fumés (moyenne)', 263, 14, 0, 23);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(15, 'Produits de charcuterie échaudés, non fumés (moyenne)', 256, 13, 1, 22);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(14, 'Cresson, cru', 17, 2, 0, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(5, 'Brun de Bâle', 493, 13, 42, 29);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(30, 'Sarrasin, grains décortiqués', 343, 9, 71, 2);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(40, 'Farine de sarrasin', 344, 12, 65, 3);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(35, 'Soupe d''orge des Grisons', 42, 1, 7, 1);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(21, '"Petit pain Bürli"" (farine mi-blanche)"""', 216, 9, 42, 1);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(21, 'Croissant blanc au beurre', 432, 9, 44, 24);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(21, 'Croissant au beurre, complet', 366, 6, 39, 20);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(41, 'Babeurre', 33, 3, 4, 1);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(21, 'Petit pain au lait', 310, 9, 47, 9);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(21, 'Tresse au beurre', 326, 9, 46, 11);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(21, 'Tresse au beurre, au blé entier', 272, 9, 41, 6);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(42, 'Café crème, non sucré', 14, 0, 1, 1);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(43, 'Cake financier', 426, 10, 37, 26);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(44, 'Calimocho (Vin rouge avec cola)', 58, 0, 5, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(39, 'Camembert, mi-gras', 178, 22, 0, 10);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(39, 'Camembert, à la crème', 352, 16, 0, 32);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(39, 'Camembert, gras', 299, 21, 0, 24);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(45, 'Cannellonis à la viande, préparés', 193, 10, 15, 10);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(45, 'Cannellonis aux épinards à la ricotta, préparés', 222, 9, 15, 14);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(46, 'Cappuccino (sans poudre de chocolat), non sucré', 37, 2, 3, 2);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(4, 'Capuns, préparés', 168, 8, 10, 10);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(22, 'Noix de cajou', 593, 18, 27, 45);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(15, 'Cervelas', 249, 13, 1, 22);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(47, 'Champignons de Paris, conserve', 19, 2, 1, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(47, 'Champignon, à l''étuvée (sans adjonction de graisse ni de sel)', 27, 4, 1, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(47, 'Champignon, cru', 22, 3, 1, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(13, 'Endive, à la vapeur (sans adjonction de sel)', 14, 1, 1, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(14, 'Endive, crue', 14, 1, 1, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(35, 'Chili con carne, préparé', 125, 11, 9, 4);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(13, 'Chou de Chine, à l''étuvée (sans adjonction de graisse ni de sel)', 16, 1, 1, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(14, 'Chou de Chine, cru', 16, 1, 1, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(5, 'Petit gâteau à l''anice', 366, 9, 75, 3);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(14, 'Chicorée rouge, crue', 19, 1, 2, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(23, 'Sauce cocktail, préparée à la maison avec la mayonnaise de colza', 695, 2, 4, 74);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(48, 'Soda au cola, sucré', 40, 0, 10, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(49, 'Soda au cola, édulcoré', 0, 0, 0, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(50, 'Salade Coleslaw, préparée', 120, 2, 4, 10);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(20, 'Coppa', 319, 29, 0, 23);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(51, 'Cordon bleu de veau, sauté', 240, 24, 10, 11);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(52, 'Cordon bleu de porc, sauté', 249, 24, 10, 12);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(53, 'Cornflakes', 359, 7, 79, 1);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(32, 'Cotechino', 450, 17, 0, 42);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(32, 'Cotto (produit à base de viande)', 93, 16, 1, 3);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(54, 'Crackers, biscuits salés', 449, 9, 66, 16);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(24, 'Feuilleté à la crème', 318, 4, 35, 18);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(4, 'Crêpes au camembert, préparés', 249, 12, 15, 16);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(4, 'Crêpes aux champignons, préparés', 137, 5, 8, 9);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(4, 'Crêpes aux légumes, préparés', 165, 6, 14, 9);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(55, 'Crêpes au Nutella, préparés', 376, 8, 36, 22);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(4, 'Crêpes au jambon, préparés', 193, 13, 13, 10);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(4, 'Crêpes au lard, préparés', 253, 11, 15, 16);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(55, 'Crêpes au sucre et cannelle, préparés', 256, 8, 29, 12);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(4, 'Crêpes nature, préparés', 234, 9, 19, 13);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(50, 'Cocktail au crevette, préparé', 174, 6, 5, 13);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(8, 'Datte, sèche', 305, 3, 69, 1);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(40, 'Farine d''épeautre bise, type 1100', 348, 14, 65, 2);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(40, 'Farine d''épeautre, complète, type 1900', 346, 16, 60, 3);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(40, 'Farine d''épeautre blanche, type 550', 348, 13, 69, 2);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(56, 'Huile de chardon', 806, 0, 0, 90);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(57, 'Double crème, pasteurisée', 425, 2, 3, 45);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(58, 'Cabillaud, filet, à la vapeur (sans adjonction de graisse ni de sel)', 95, 23, 0, 1);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(58, 'Morue, cru', 76, 18, 0, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(59, 'Dulce de Leche', 300, 6, 53, 7);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(22, 'Châtaigne, crue', 198, 3, 42, 2);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(60, 'Perche, crue', 78, 18, 0, 1);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(4, 'Omelette à la française au fromage, préparée', 195, 14, 1, 15);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(4, 'Omelette à la française au jambon, préparée', 146, 13, 1, 10);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(4, 'Omelette à la française au lard, préparée', 182, 12, 1, 14);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(4, 'Omelette à la française, préparée', 154, 11, 1, 12);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(47, 'Chanterelle, à l''étuvée (sans adjonction de graisse ni de sel)', 26, 2, 0, 1);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(47, 'Chanterelle, crue', 21, 2, 0, 1);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(14, 'Salade Iceberg, crue', 16, 1, 2, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(61, 'Thé froid, sucré', 30, 0, 8, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(12, 'Emmentaler, gras', 411, 29, 0, 33);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(14, 'Chicorée scarole, crue', 16, 2, 1, 0);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(43, 'Gateau au noix d'' Engadine', 486, 7, 50, 28);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(13, 'Petit-pois, conserve', 88, 5, 13, 1);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(13, 'Petit-pois (conserve), réchauffé (sans adjonction de sel)', 94, 5, 14, 1);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(13, 'Petit-pois, à la vapeur (sans adjonction de sel)', 104, 7, 14, 1);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(14, 'Petit-poid, cru', 90, 6, 12, 1);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(33, 'Pois, grain mature, décortiqué, sec', 331, 21, 52, 2);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(33, 'Pois, grain mature, cuit (sans adjonction de graisse ni de sel)', 142, 9, 22, 1);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(13, 'Petit-pois et carottes, conserve', 67, 4, 10, 1);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(13, 'Petits-pois et carottes, réchauffés (sans adjonction de sel)', 71, 4, 10, 1);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(7, 'Fraise, crue', 40, 1, 7, 1);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(22, 'Cacahuète', 600, 26, 11, 49);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(62, 'Flips aux cacahuètes', 506, 13, 57, 24);
  INSERT INTO public.food_attribute
  (category_id, "name", energy, protein, carb, fat)
  VALUES(22, 'Cacahuète, grillée', 593, 25, 9, 49);

  


  INSERT INTO public.food_category
  ("name")
  VALUES('Méta Catégorie');
  INSERT INTO public.food_category
  ("name")
  VALUES('Divers/Gélifiants et liants');
  INSERT INTO public.food_category
  ("name")
  VALUES('Sucreries/Sucres et édulcorants');
  INSERT INTO public.food_category
  ("name")
  VALUES('Plats/Autres plats salés/épicés');
  INSERT INTO public.food_category
  ("name")
  VALUES('Sucreries/Biscuits');
  INSERT INTO public.food_category
  ("name")
  VALUES('Fruits/Fruits cuits (conserves comprises)');
  INSERT INTO public.food_category
  ("name")
  VALUES('Fruits/Fruits frais');
  INSERT INTO public.food_category
  ("name")
  VALUES('Fruits/Fruits secs');
  INSERT INTO public.food_category
  ("name")
  VALUES('Sucreries/Gâteaux, tourtes et cakes;Plats/Tartes sucrées et salées et gratins');
  INSERT INTO public.food_category
  ("name")
  VALUES('Fruits/Jus de fruits;Boissons sans alcool/Jus de fruits et de légumes');
  INSERT INTO public.food_category
  ("name")
  VALUES('Boissons alcoolisées/Vin');
  INSERT INTO public.food_category
  ("name")
  VALUES('Lait et produits laitiers/Fromage à pâte dure');
  INSERT INTO public.food_category
  ("name")
  VALUES('Légumes/Légumes cuits (conserves comprises)');
  INSERT INTO public.food_category
  ("name")
  VALUES('Légumes/Légumes frais');
  INSERT INTO public.food_category
  ("name")
  VALUES('Produits carnés et charcuterie/Produits de charcuterie bouillis');
  INSERT INTO public.food_category
  ("name")
  VALUES('Légumes/Légumes frais;Noix, graines et produits oléagineux');
  INSERT INTO public.food_category
  ("name")
  VALUES('Divers/Levure;Divers/Ingrédients de boulangerie-pâtisserie');
  INSERT INTO public.food_category
  ("name")
  VALUES('Plats/Plats asiatiques');
  INSERT INTO public.food_category
  ("name")
  VALUES('Légumes/Herbes aromatiques');
  INSERT INTO public.food_category
  ("name")
  VALUES('Produits carnés et charcuterie/Produits de charcuterie crus');
  INSERT INTO public.food_category
  ("name")
  VALUES('Pains, flocons et céréales pour le petit-déjeuner/Pains et produits de boulangerie');
  INSERT INTO public.food_category
  ("name")
  VALUES('Noix, graines et produits oléagineux');
  INSERT INTO public.food_category
  ("name")
  VALUES('Divers/Sauces');
  INSERT INTO public.food_category
  ("name")
  VALUES('Sucreries/Autres articles de pâtisserie');
  INSERT INTO public.food_category
  ("name")
  VALUES('Boissons alcoolisées/Bière');
  INSERT INTO public.food_category
  ("name")
  VALUES('Denrées alimentaires spéciales/Suppléments;Divers/Levure');
  INSERT INTO public.food_category
  ("name")
  VALUES('Plats/Müesli');
  INSERT INTO public.food_category
  ("name")
  VALUES('Lait et produits laitiers/Fromage frais et séré');
  INSERT INTO public.food_category
  ("name")
  VALUES('Produits céréaliers, légumineuses et pommes de terre/Pâtes');
  INSERT INTO public.food_category
  ("name")
  VALUES('Produits céréaliers, légumineuses et pommes de terre/Autres produits céréaliers');
  INSERT INTO public.food_category
  ("name")
  VALUES('Snacks salés/Produits en pâte feuilletée');
  INSERT INTO public.food_category
  ("name")
  VALUES('Produits carnés et charcuterie/Produits de charcuterie cuits');
  INSERT INTO public.food_category
  ("name")
  VALUES('Produits céréaliers, légumineuses et pommes de terre/Légumineuses');
  INSERT INTO public.food_category
  ("name")
  VALUES('Légumes/Légumes secs');
  INSERT INTO public.food_category
  ("name")
  VALUES('Plats/Potages et soupes');
  INSERT INTO public.food_category
  ("name")
  VALUES('Sucreries/Chocolat et produits chocolatés');
  INSERT INTO public.food_category
  ("name")
  VALUES('Boissons alcoolisées/Spiritueux');
  INSERT INTO public.food_category
  ("name")
  VALUES('Graisses et huiles/Graisses;Lait et produits laitiers/Crème et beurre');
  INSERT INTO public.food_category
  ("name")
  VALUES('Lait et produits laitiers/Fromage à pâte molle');
  INSERT INTO public.food_category
  ("name")
  VALUES('Produits céréaliers, légumineuses et pommes de terre/Farines et amidon');
  INSERT INTO public.food_category
  ("name")
  VALUES('Lait et produits laitiers/Boissons lactées et yogourts à boire');
  INSERT INTO public.food_category
  ("name")
  VALUES('Boissons sans alcool/Café');
  INSERT INTO public.food_category
  ("name")
  VALUES('Sucreries/Gâteaux, tourtes et cakes');
  INSERT INTO public.food_category
  ("name")
  VALUES('Boissons sans alcool/Boissons sucrées;Boissons alcoolisées/Autres boissons alcoolisées');
  INSERT INTO public.food_category
  ("name")
  VALUES('Plats/Plats italiens');
  INSERT INTO public.food_category
  ("name")
  VALUES('Lait et produits laitiers/Boissons lactées et yogourts à boire;Boissons sans alcool/Café');
  INSERT INTO public.food_category
  ("name")
  VALUES('Légumes/Champignons');
  INSERT INTO public.food_category
  ("name")
  VALUES('Boissons sans alcool/Boissons sucrées');
  INSERT INTO public.food_category
  ("name")
  VALUES('Boissons sans alcool/Boissons à teneur reduite en sucre');
  INSERT INTO public.food_category
  ("name")
  VALUES('Plats/Salades');
  INSERT INTO public.food_category
  ("name")
  VALUES('Viande et abats/Veau');
  INSERT INTO public.food_category
  ("name")
  VALUES('Viande et abats/Porc');
  INSERT INTO public.food_category
  ("name")
  VALUES('Pains, flocons et céréales pour le petit-déjeuner/Mélanges de müesli et céréales pour petit-déjeuner');
  INSERT INTO public.food_category
  ("name")
  VALUES('Snacks salés/Sticks salés et bretzels');
  INSERT INTO public.food_category
  ("name")
  VALUES('Plats/Autres plats sucrés');
  INSERT INTO public.food_category
  ("name")
  VALUES('Graisses et huiles/Huiles');
  INSERT INTO public.food_category
  ("name")
  VALUES('Graisses et huiles/Crème;Lait et produits laitiers/Crème et beurre');
  INSERT INTO public.food_category
  ("name")
  VALUES('Poisson/Poissons de mer');
  INSERT INTO public.food_category
  ("name")
  VALUES('Sucreries/Confitures et produits à tartiner sucrés');
  INSERT INTO public.food_category
  ("name")
  VALUES('Poisson/Poissons d''eau douce');
  INSERT INTO public.food_category
  ("name")
  VALUES('Boissons sans alcool/Boissons sucrées;Boissons sans alcool/Thé');
  INSERT INTO public.food_category
  ("name")
  VALUES('Snacks salés/Chips');
  INSERT INTO public.food_category
  ("name")
  VALUES('Divers/Sel, épices et arômes');
  INSERT INTO public.food_category
  ("name")
  VALUES('Graisses et huiles/Sauces à salade');
  INSERT INTO public.food_category
  ("name")
  VALUES('Poisson');
  INSERT INTO public.food_category
  ("name")
  VALUES('Poisson/Poissons d''eau douce;Poisson/Poissons de mer');
  INSERT INTO public.food_category
  ("name")
  VALUES('Plats/Tartes sucrées et salées et gratins');
  INSERT INTO public.food_category
  ("name")
  VALUES('Viande et abats/Veau;Viande et abats/Volaille;Viande et abats/Agneau, mouton;Viande et abats/Bœuf;Viande et abats/Porc;Viande et abats/Gibier');
  INSERT INTO public.food_category
  ("name")
  VALUES('Boissons sans alcool/Jus de fruits et de légumes');
  INSERT INTO public.food_category
  ("name")
  VALUES('Poisson/Fruits de mer et crustacés');
  INSERT INTO public.food_category
  ("name")
  VALUES('Viande et abats/Volaille');
  INSERT INTO public.food_category
  ("name")
  VALUES('Viande et abats');
  INSERT INTO public.food_category
  ("name")
  VALUES('Viande et abats/Veau;Viande et abats/Bœuf;Viande et abats/Porc;Viande et abats/Volaille');
  INSERT INTO public.food_category
  ("name")
  VALUES('Pains, flocons et céréales pour le petit-déjeuner/Flocons, son et germes');
  INSERT INTO public.food_category
  ("name")
  VALUES('Viande et abats/Veau;Viande et abats/Porc;Viande et abats/Bœuf;Viande et abats/Volaille');
  INSERT INTO public.food_category
  ("name")
  VALUES('Sucreries/Crèmes et pouding');
  INSERT INTO public.food_category
  ("name")
  VALUES('Sucreries/Bonbons, bonbons gélifiés aux fruits et chewing-gums');
  INSERT INTO public.food_category
  ("name")
  VALUES('Viande et abats/Bœuf');
  INSERT INTO public.food_category
  ("name")
  VALUES('Viande et abats/Autres espèces animales');
  INSERT INTO public.food_category
  ("name")
  VALUES('Viande et abats/Gibier');
  INSERT INTO public.food_category
  ("name")
  VALUES('Œufs');
  INSERT INTO public.food_category
  ("name")
  VALUES('Produits céréaliers, légumineuses et pommes de terre/Pommes de terre');
  INSERT INTO public.food_category
  ("name")
  VALUES('Lait et produits laitiers/Yogourt et lait acidulé');
  INSERT INTO public.food_category
  ("name")
  VALUES('Sucreries/Chocolat et produits chocolatés;Boissons sans alcool/Boissons chocolatées');
  INSERT INTO public.food_category
  ("name")
  VALUES('Boissons sans alcool/Jus de fruits et de légumes;Légumes/Jus de légumes');
  INSERT INTO public.food_category
  ("name")
  VALUES('Produits céréaliers, légumineuses et pommes de terre/Farines et amidon;Divers/Gélifiants et liants');
  INSERT INTO public.food_category
  ("name")
  VALUES('Lait et produits laitiers/Fromage frais et séré;Lait et produits laitiers/Fromage à pâte molle');
  INSERT INTO public.food_category
  ("name")
  VALUES('Plats/Sandwich');
  INSERT INTO public.food_category
  ("name")
  VALUES('Pains, flocons et céréales pour le petit-déjeuner/Pains croustillants, biscottes, crackers et gallettes');
  INSERT INTO public.food_category
  ("name")
  VALUES('Graisses et huiles/Graisses');
  INSERT INTO public.food_category
  ("name")
  VALUES('Lait et produits laitiers/Lait');
  INSERT INTO public.food_category
  ("name")
  VALUES('Viande et abats/Porc;Viande et abats/Veau;Viande et abats/Agneau, mouton');
  INSERT INTO public.food_category
  ("name")
  VALUES('Viande et abats/Agneau, mouton');
  INSERT INTO public.food_category
  ("name")
  VALUES('Viande et abats/Volaille;Viande et abats/Veau;Viande et abats/Bœuf;Viande et abats/Porc');
  INSERT INTO public.food_category
  ("name")
  VALUES('Produits céréaliers, légumineuses et pommes de terre/Maïs');
  INSERT INTO public.food_category
  ("name")
  VALUES('Snacks salés/Noix et graines salées');
  INSERT INTO public.food_category
  ("name")
  VALUES('Sucreries/Autres sucreries');
  INSERT INTO public.food_category
  ("name")
  VALUES('Graisses et huiles/Mayonnaises');
  INSERT INTO public.food_category
  ("name")
  VALUES('Snacks salés/Autres snacks salés');
  INSERT INTO public.food_category
  ("name")
  VALUES('Lait et produits laitiers/Fromage à pâte molle;Lait et produits laitiers/Fromage frais et séré');
  INSERT INTO public.food_category
  ("name")
  VALUES('Boissons sans alcool/Boissons sucrées;Boissons sans alcool/Jus de fruits et de légumes');
  INSERT INTO public.food_category
  ("name")
  VALUES('Graisses et huiles/Huiles;Graisses et huiles/Graisses');
  INSERT INTO public.food_category
  ("name")
  VALUES('Sucreries/Glaces à base de lait');
  INSERT INTO public.food_category
  ("name")
  VALUES('Produits céréaliers, légumineuses et pommes de terre/Riz');
  INSERT INTO public.food_category
  ("name")
  VALUES('Poisson/Produits à base de poisson');
  INSERT INTO public.food_category
  ("name")
  VALUES('Produits carnés et charcuterie/Autres produits carnés et produits de charcuterie');
  INSERT INTO public.food_category
  ("name")
  VALUES('Divers/Pâtes à tartiner');
  INSERT INTO public.food_category
  ("name")
  VALUES('Lait et produits laitiers/Produits à base de fromage');
  INSERT INTO public.food_category
  ("name")
  VALUES('Boissons sans alcool/Sirop');
  INSERT INTO public.food_category
  ("name")
  VALUES('Boissons sans alcool/Sirop;Boissons sans alcool/Boissons sucrées');
  INSERT INTO public.food_category
  ("name")
  VALUES('Lait et produits laitiers/Substituts du lait');
  INSERT INTO public.food_category
  ("name")
  VALUES('Légumes/Pousses et germes');
  INSERT INTO public.food_category
  ("name")
  VALUES('Sucreries/Glaces à base d''eau');
  INSERT INTO public.food_category
  ("name")
  VALUES('Boissons sans alcool/Thé');
  INSERT INTO public.food_category
  ("name")
  VALUES('Produits céréaliers, légumineuses et pommes de terre/Pâtes alimentaires');
  INSERT INTO public.food_category
  ("name")
  VALUES('Substituts de viande');
  INSERT INTO public.food_category
  ("name")
  VALUES('Boissons sans alcool/Eau potable');
  INSERT INTO public.food_category
  ("name")
  VALUES('Pains, flocons et céréales pour le petit-déjeuner/Flocons, son et germes;Denrées alimentaires spéciales/Suppléments');
  INSERT INTO public.food_category
  ("name")
  VALUES('Viande et abats/Veau;Viande et abats/Bœuf');


EOSQL