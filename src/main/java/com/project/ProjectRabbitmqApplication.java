package com.project;

import com.project.producer.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProjectRabbitmqApplication {



	@Autowired
	RabbitMQProducer rabbitMQProducer;

	public static void main(String[] args) {
		SpringApplication.run(ProjectRabbitmqApplication.class, args);
	}

	@Bean
	public CommandLineRunner usage() {
		return args -> {
			System.out.println("TES TES TES");

			for (int i=1; i<=10; i++){
				String msg = "Tes kirim dari spring java "+ i;
				// Sending a message
				rabbitMQProducer.sendMessage(msg);
				System.out.println("Producer :"+msg);
			}

		};
	}

}
