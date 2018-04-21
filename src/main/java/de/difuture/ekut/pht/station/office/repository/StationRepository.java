package de.difuture.ekut.pht.station.office.repository;

import org.springframework.data.repository.CrudRepository;

import java.net.URI;
import java.util.List;
import java.util.UUID;


public interface StationRepository extends CrudRepository<StationEntity, UUID> {

    List<StationEntity> findAllByStationURI(URI stationURI);
}
