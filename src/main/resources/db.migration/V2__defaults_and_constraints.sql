ALTER TABLE chauffeur
    MODIFY disponible BOOLEAN NOT NULL DEFAULT TRUE;

ALTER TABLE livraison
    ADD CONSTRAINT client_fk
        FOREIGN KEY (client_id) REFERENCES client(id);

ALTER TABLE livraison
    ADD CONSTRAINT chauffeur_fk
        FOREIGN KEY (chauffeur_id) REFERENCES chauffeur (id);

ALTER TABLE livraison
    ADD CONSTRAINT vehicule_fk
        FOREIGN KEY (vehicule_id) REFERENCES vehicule (id);