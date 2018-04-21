package de.difuture.ekut.pht.station.office.repository;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;


public interface StationRepository extends CrudRepository<StationEntity, UUID> {}
