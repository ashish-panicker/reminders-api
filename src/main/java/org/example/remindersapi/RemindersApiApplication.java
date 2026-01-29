package org.example.remindersapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Define the application
@OpenAPIDefinition(
        info = @Info(
                title = "Reminders API",
                version = "1.0-SNAPSHOT",
                description = "Reminders API using Spring Boot",
                contact = @Contact(name = "Author", email = "contact@reminders-api.com")
        )
)
@SpringBootApplication
public class RemindersApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RemindersApiApplication.class, args);
    }

}
