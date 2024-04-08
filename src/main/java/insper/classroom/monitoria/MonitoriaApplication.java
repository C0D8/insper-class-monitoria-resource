package insper.classroom.monitoria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MonitoriaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitoriaApplication.class, args);
    }
    
}
