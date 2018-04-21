package de.difuture.ekut.pht.station.office.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
	public Station postStation(@RequestBody @Valid Station station) {

        return this.stationRepository.save(new StationEntity(station)).toStation();
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