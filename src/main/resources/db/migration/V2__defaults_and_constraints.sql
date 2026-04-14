-- Clés étrangères
ALTER TABLE livraison
ADD CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES client(id),
ADD CONSTRAINT fk_vehicule FOREIGN KEY (vehicule_id) REFERENCES vehicule(id),
ADD CONSTRAINT fk_chauffeur FOREIGN KEY (chauffeur_id) REFERENCES chauffeur(id);

-- Valeurs par défaut
ALTER TABLE vehicule
    ADD COLUMN statut VARCHAR(50) DEFAULT 'DISPONIBLE';

ALTER TABLE livraison
    MODIFY statut VARCHAR(50) DEFAULT 'EN_ATTENTE';

ALTER TABLE chauffeur
    MODIFY disponible BOOLEAN DEFAULT TRUE;

-- Contrainte de cohérence
ALTER TABLE vehicule
    ADD CONSTRAINT chk_capacite CHECK (capacite > 0);