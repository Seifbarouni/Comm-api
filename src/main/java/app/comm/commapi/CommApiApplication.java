package app.comm.commapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CommApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(CommApiApplication.class, args);
	}

}
