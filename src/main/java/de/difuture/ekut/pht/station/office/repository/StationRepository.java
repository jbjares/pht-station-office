package de.difuture.ekut.pht.station.office.repository;

import org.springframework.data.repository.CrudRepository;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;


public interface StationRepository extends CrudRepository<StationEntity, UUID> {

    Optional<StationEntity> findByStationURI(URI stationURI);
}
