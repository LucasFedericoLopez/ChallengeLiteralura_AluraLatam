package com.example.aluracursos.challengeliteralura;

import com.example.aluracursos.challengeliteralura.principal.Principal;
import com.example.aluracursos.challengeliteralura.repositorio.IAutorRepository;
import com.example.aluracursos.challengeliteralura.repositorio.ILibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChallengeliteraluraApplication implements CommandLineRunner {

	@Autowired
	private ILibroRepository libroRepository;
	@Autowired
	private IAutorRepository autorRepository;

	public static void main(String[] args) {

		SpringApplication.run(ChallengeliteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(libroRepository,autorRepository);
		principal.muestraElMenu();
	}
}
