package insurenceMain.Repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import insurenceMain.Entity.Repots;

public interface ReportsRepository extends JpaRepository<Repots, Serializable>{
	

    @Query("SELECT DISTINCT r.planeName FROM Repots r")
    List<String> findDistinctPlaneNames();

    @Query("SELECT DISTINCT r.planeStatus FROM Repots r")
    List<String> findDistinctPlanStatuses();

}
