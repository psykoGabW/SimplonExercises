package co.simplon.gwi.spring.exo01;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(scanBasePackages= {"co.simplon.gwi.spring.exo01","co.simplon.repository"})
public class SpringTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringTestApplication.class, args);
	}
	
	@Bean
	CommandLineRunner runner() {
		return args ->{
			// Instructions que l'on souhaite voir exécuter à chaque démarrage de l'application.
			// Initialisation d'objets etc....
		};
	}
}
