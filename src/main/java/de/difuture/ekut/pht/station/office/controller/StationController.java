package de.difuture.ekut.pht.station.office.controller;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import de.difuture.ekut.pht.lib.core.api.APIStation;
import de.difuture.ekut.pht.lib.core.neo4j.entity.Station;
import de.difuture.ekut.pht.station.office.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@CrossOrigin
@RestController
@RequestMapping(value = "/station")
public class StationController {

	private static final ResponseEntity<APIStation> NOT_FOUND
			= ResponseEntity.notFound().build();
	
	private final StationRepository stationRepository;

	@Autowired
	public StationController(
			@NotNull final StationRepository stationRepository) {

		this.stationRepository = stationRepository;
	}

	@RequestMapping(method = RequestMethod.POST)
	public Iterable<APIStation> ping(@RequestBody @Valid APIStation apiStation) {

	    final Instant lastPing = Instant.now();
	    final List<Station> entities = this.stationRepository
                .findAllByStationURI(apiStation.getStationURI());

	    if (entities.isEmpty()) {

	        final Station station = new Station(apiStation, lastPing);
	        station.setActive(true);
	        return Collections.singleton(this.stationRepository.save(station).toStation());

        } else {

	        return entities.stream().map(x -> {
	            x.setLastPing(lastPing);
	            x.setActive(true);
	            return this.stationRepository.save(x).toStation();
            }).collect(Collectors.toList());
        }
	}

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<APIStation> getAll() {

		return StreamSupport.stream(this.stationRepository.findAll().spliterator(), false)
                .map(Station::toStation)
                .collect(Collectors.toList());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<APIStation> getOne(
			@PathVariable("id") UUID trainID) {

		return this.stationRepository
                .findById(trainID)
                .map(x -> ResponseEntity.ok(x.toStation()))
                .orElse(NOT_FOUND);
	}
}
