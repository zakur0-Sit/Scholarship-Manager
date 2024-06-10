package school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.Collections;



@SpringBootApplication
public class AppStarter {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AppStarter.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8666"));
        app.run(args);
    }
}
