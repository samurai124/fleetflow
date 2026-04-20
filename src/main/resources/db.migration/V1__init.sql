CREATE TABLE chauffeurs (
                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                            nom VARCHAR(100) NOT NULL,
                            telephone VARCHAR(20),
                            permis_type VARCHAR(50),
                            disponible BOOLEAN
);




CREATE TABLE clients (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        nom VARCHAR(100) NOT NULL,
                        email VARCHAR(150) NOT NULL UNIQUE,
                        ville VARCHAR(100),
                        telephone VARCHAR(20)
);

CREATE TABLE livraisons (
                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                            date_livraison DATE,
                            adresse_depart VARCHAR(255),
                            adresse_destination VARCHAR(255),
                            statut VARCHAR(50),
                            client_id BIGINT,
                            vehicule_id BIGINT,
                            chauffeur_id BIGINT
);

CREATE TABLE vehicules (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          matricule VARCHAR(50) NOT NULL UNIQUE,
                          type VARCHAR(50),
                          capacite DOUBLE NOT NULL,
                          statut VARCHAR(50)
);


