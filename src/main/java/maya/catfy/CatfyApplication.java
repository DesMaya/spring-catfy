package maya.catfy;

import maya.catfy.principal.Principal;
import maya.catfy.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatfyApplication implements CommandLineRunner {
	@Autowired
	private ArtistaRepository repositorio;

	public static void main(String[] args) {
		SpringApplication.run(CatfyApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorio);
		principal.exibeMenu();
	}
}
