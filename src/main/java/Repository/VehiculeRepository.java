package Repository;

import Enums.Statutvehicule;
import Model.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {

    List<Vehicule> findByStatut(Statutvehicule statut);
    List<Vehicule> findByCapaciteGreaterThan(Double capacite);


//    @Query("SELECT v FROM Vehicule v WHERE v.statut = :Statut")
//    List<Vehicule> findByStatut(@Param("Statut") Statutvehicule statutvehicule);
//
//    @Query("SELECT v FROM Vehicule v WHERE v.capacite > :capacite")
//    List<Vehicule> findByCapaciteGreaterThan(@Param("capacite") Double capacite);

}
