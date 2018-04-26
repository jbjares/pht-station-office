package de.difuture.ekut.pht.station.office;

import de.difuture.ekut.pht.config.Neo4jDenbiConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.neo4j.Neo4jDataAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;


@SpringBootApplication(
		exclude = Neo4jDataAutoConfiguration.class,
		scanBasePackages = {
				"de.difuture.ekut.pht.config",
				"de.difuture.ekut.pht.station.office"})
@EnableNeo4jRepositories
@Import(Neo4jDenbiConfiguration.class)
public class StationOfficeApplication {

	public static void main(String[] args) {
		SpringApplication.run(StationOfficeApplication.class, args);
	}
}
