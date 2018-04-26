package de.difuture.ekut.pht.station.office.repository;

import de.difuture.ekut.pht.lib.core.neo4j.entity.StationEntity;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.net.URI;
import java.util.List;
import java.util.UUID;


public interface StationRepository extends Neo4jRepository<StationEntity, UUID> {

    List<StationEntity> findAllByStationURI(URI stationURI);
}
