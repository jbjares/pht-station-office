package de.difuture.ekut.pht.station.office.controller;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import de.difuture.ekut.pht.lib.core.model.Station;
import de.difuture.ekut.pht.station.office.repository.StationEntity;
import de.difuture.ekut.pht.station.office.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.NonNull;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/station")
public class StationController {

	private static final ResponseEntity<Station> NOT_FOUND
			= ResponseEntity.notFound().build();
	
	private final StationRepository stationRepository;

	@Autowired
	public StationController(
			@NonNull final StationRepository stationRepository) {

		this.stationRepository = stationRepository;
	}

	@RequestMapping(method = RequestMethod.POST)
	public Iterable<Station> ping(@RequestBody @Valid Station station) {

	    final Instant lastPing = Instant.now();
	    final List<StationEntity> entities = this.stationRepository
                .findAllByStationURI(station.getStationURI());

	    if (entities.isEmpty()) {

	        return Collections.singleton(this.stationRepository.save(new StationEntity(station, lastPing)).toStation());

        } else {

	        return entities.stream().map(x -> {
	            x.setLastPing(lastPing);
	            return this.stationRepository.save(x).toStation();
            }).collect(Collectors.toList());
        }
	}

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Station> doGetAll() {

	    List<Station> result = new ArrayList<>();
		this.stationRepository.findAll().forEach(x -> result.add(x.toStation()));
        return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Station> doGetOne(
			@PathVariable("id") UUID trainID) {

		return this.stationRepository
                .findById(trainID)
                .map(x -> ResponseEntity.ok(x.toStation()))
                .orElse(NOT_FOUND);
	}
}
