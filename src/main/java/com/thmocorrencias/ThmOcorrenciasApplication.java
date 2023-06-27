package com.thmocorrencias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class ThmOcorrenciasApplication {

	public static void main(String[] args) {
		SpringApplication.run(ThmOcorrenciasApplication.class, args);

		File imagensDir = new File("imagens");
		if (!imagensDir.exists()) {
			imagensDir.mkdirs();
		}



	}

}
