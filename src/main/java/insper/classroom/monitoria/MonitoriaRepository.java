package insper.classroom.monitoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoriaRepository extends CrudRepository<MonitoriaModel, String>{

    // public Optional<AulaModel> findByEmailAndHash(String email, String hash);
    

}
