ALTER TABLE chauffeurs
    MODIFY disponible BOOLEAN NOT NULL DEFAULT TRUE;

ALTER TABLE livraisons
    ADD CONSTRAINT client_fk
        FOREIGN KEY (client_id) REFERENCES clients(id);


ALTER TABLE livraisons
    ADD CONSTRAINT chauffeur_fk
        FOREIGN KEY (chauffeur_id) REFERENCES chauffeurs (id);

ALTER TABLE livraisons
    ADD CONSTRAINT vehicule_fk
        FOREIGN KEY (vehicule_id) REFERENCES vehicules (id);