package insper.classroom.monitoria;

// import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.common.util.concurrent.Monitor;

import insper.classroom.monitoria.Monitoria;
import insper.classroom.monitoria.MonitoriaParser;
import insper.classroom.monitoria.CreateMonitoriaOut;
import java.util.List;
import java.util.ArrayList;

@RestController
public class MonitoriaResource implements MonitoriaController {

    @Autowired
    private MonitoriaService monitoriaService;

    // @GetMapping("/accounts/info")
    // public ResponseEntity<Map<String, String>> info() {
    //     return new ResponseEntity<Map<String, String>>(
    //             Map.ofEntries(
    //                     Map.entry("microservice.name", AccountApplication.class.getSimpleName()),
    //                     Map.entry("os.arch", System.getProperty("os.arch")),
    //                     Map.entry("os.name", System.getProperty("os.name")),
    //                     Map.entry("os.version", System.getProperty("os.version")),
    //                     Map.entry("file.separator", System.getProperty("file.separator")),
    //                     Map.entry("java.class.path", System.getProperty("java.class.path")),
    //                     Map.entry("java.home", System.getProperty("java.home")),
    //                     Map.entry("java.vendor", System.getProperty("java.vendor")),
    //                     Map.entry("java.vendor.url", System.getProperty("java.vendor.url")),
    //                     Map.entry("java.version", System.getProperty("java.version")),
    //                     Map.entry("line.separator", System.getProperty("line.separator")),
    //                     Map.entry("path.separator", System.getProperty("path.separator")),
    //                     Map.entry("user.dir", System.getProperty("user.dir")),
    //                     Map.entry("user.home", System.getProperty("user.home")),
    //                     Map.entry("user.name", System.getProperty("user.name")),
    //                     Map.entry("jar", new java.io.File(
    //                             AccountApplication.class.getProtectionDomain()
    //                                     .getCodeSource()
    //                                     .getLocation()
    //                                     .getPath()
    //                     ).toString())
    //             ), HttpStatus.OK
    //     );
    // }

    @Override
    public ResponseEntity<CreateMonitoriaOut> create(CreateMonitoriaIn in) {
        // parser
        Monitoria monitoria = MonitoriaParser.to(in);
        // insert using service
        monitoria = monitoriaService.create(monitoria);
        // return
        return ResponseEntity.created(
                        ServletUriComponentsBuilder
                                .fromCurrentRequest()
                                .path("/{id}")
                                .buildAndExpand(monitoria.id())
                                .toUri())
                .body(MonitoriaParser.to(monitoria));
    }

    @Override
    public ResponseEntity<CreateMonitoriaOut> get(String id) {
        Monitoria monitoria = monitoriaService.read(id);
        if (monitoria == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(MonitoriaParser.to(monitoria));
    }


    @Override
    public ResponseEntity<List<CreateMonitoriaOut>> getByDepartamento(String id) {
       // lista de aulas por departamento
        List<Monitoria> monitorias = monitoriaService.readByDepartamento(id);
        if (monitorias == null) {
            return ResponseEntity.notFound().build();
        }

        List<CreateMonitoriaOut> monitoriasOut = new ArrayList<>(); // Initialize monitoriasOut as an empty list

        for (Monitoria monitoria : monitorias) {
            //append monitoria apos parsear 
            monitoriasOut.add(MonitoriaParser.to(monitoria));
        }
        return ResponseEntity.ok(monitoriasOut);
    }


    // @Override
    // public ResponseEntity<AccountOut> update(String id, AccountIn in) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'update'");
    // }

    // @Override
    // public ResponseEntity<AccountOut> auth(AuthIn in) {
    //     Account account = aulaService.auth(in.email(), in.password());
    //     if (account == null) {
    //         return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    //     }
    //     return ResponseEntity.ok(AccountParser.to(account));
    // }

    // @Override
    // public ResponseEntity<AccountOut> read(String idUser, String roleUser) {
    //     final AccountOut account = AccountOut.builder()
    //         .id(idUser)
    //         .name(roleUser)
    //         .build();
    //     return ResponseEntity.ok(account);
    // }
    
}