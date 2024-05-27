package insper.classroom.monitoria;


// import java.nio.charset.StandardCharsets;
// import java.security.MessageDigest;
// import java.security.NoSuchAlgorithmException;
// import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.cache.annotation.Cacheable;

import lombok.NonNull;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class MonitoriaService {

    @Autowired
    private MonitoriaRepository monitoriaRepository;

    @CircuitBreaker(name = "monitoriaService", fallbackMethod = "fallbackMonitoriaCreate")
    public Monitoria create(Monitoria in) {
        // in.hash(calculateHash(in.password()));
        // in.password(null);
        return monitoriaRepository.save(new MonitoriaModel(in)).to();
    }

    public Monitoria fallbackMonitoriaCreate(Monitoria in, Throwable t) {
        throw new RuntimeException("Failed to create monitoria", t);
    }

    @CircuitBreaker(name = "monitoriaService", fallbackMethod = "fallbackMonitoriaRead")
    @Cacheable(value = "monitorias", key = "#id", unless = "#result == null")
    public Monitoria read(@NonNull String id) {
        return monitoriaRepository.findById(id).map(MonitoriaModel::to).orElse(null);
    }

    public Monitoria fallbackMonitoriaRead(String id, Throwable t) {
        throw new RuntimeException("Failed to read monitoria", t);
    }

    @CircuitBreaker(name = "monitoriaService", fallbackMethod = "fallbackMonitoriaReadByDepartamento")
    public List<Monitoria> readByDepartamento(@NonNull String id_departamento) {
        return monitoriaRepository.findByDepartamento(id_departamento).stream().map(MonitoriaModel::to).collect(Collectors.toList());
    }

    public List<Monitoria> fallbackMonitoriaReadByDepartamento(String id_departamento, Throwable t) {
        throw new RuntimeException("Failed to read monitoria by departamento", t);
    }

    // public Account read(@NonNull String id) {
    //     return accountRepository.findById(id).map(AccountModel::to).orElse(null);
    // }

    // public Account auth(String email, String password) {
    //     String hash = calculateHash(password);
    //     return accountRepository.findByEmailAndHash(email, hash).map(AccountModel::to).orElse(null);
    // }

    // private String calculateHash(String text) {
    //     try {
    //         MessageDigest digest = MessageDigest.getInstance("SHA-256");
    //         byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
    //         byte[] encoded = Base64.getEncoder().encode(hash);
    //         return new String(encoded);
    //     } catch (NoSuchAlgorithmException e) {
    //         throw new RuntimeException(e);
    //     }
    // }

}