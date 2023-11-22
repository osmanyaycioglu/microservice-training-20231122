package com.micro.training.nodeconfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableConfigServer
@SpringBootApplication
public class NodeConfigurationApplication {

	public static void main(String[] args) {
		SpringApplication.run(NodeConfigurationApplication.class, args);
	}

}
