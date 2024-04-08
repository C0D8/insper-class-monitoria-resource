package insper.classroom.monitoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MonitoriaRepository extends CrudRepository<MonitoriaModel, String>{

    // public Optional<AulaModel> findByEmailAndHash(String email, String hash);
    public List<MonitoriaModel> findByDepartamento(String id_departamento);
    


}
