package de.difuture.ekut.pht.station.office.repository;

import de.difuture.ekut.pht.lib.core.neo4j.entity.Station;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.net.URI;
import java.util.List;
import java.util.UUID;


public interface StationRepository extends Neo4jRepository<Station, UUID> {

    List<Station> findAllByStationURI(URI stationURI);
}
