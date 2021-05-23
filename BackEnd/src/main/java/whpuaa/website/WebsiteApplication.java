package whpuaa.website;

import org.flywaydb.core.Flyway;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.annotation.PostConstruct;

@SpringBootApplication
public class WebsiteApplication {

    @Autowired
    private Flyway flyway;

    public static void main(String[] args) {
        SpringApplication.run(WebsiteApplication.class, args);
    }

    @PostConstruct
    public void migrateDatabase() {
        flyway.migrate();
    }
}
