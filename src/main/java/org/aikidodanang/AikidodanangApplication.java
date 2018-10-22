package org.aikidodanang;

import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AikidodanangApplication {

	public static void main(String[] args) {
		SpringApplication.run(AikidodanangApplication.class, args);
	}

	@Bean
    public PegDownProcessor pegDownProcessor(){
        return new PegDownProcessor(Extensions.ALL);
    }
}
