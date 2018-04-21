package de.difuture.ekut.pht.station.office.repository;


import de.difuture.ekut.pht.lib.core.model.Station;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.net.URI;
import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID stationID;

    private URI stationURI;

    private String stationName;

    private Instant lastPing;

    public StationEntity(
            final Station station,
            final Instant lastPing) {

        this.stationURI = station.getStationURI();
        this.lastPing = lastPing;
        this.stationName = station.getStationName();
    }

    public Station toStation() {

        return new Station(this.stationID, this.stationName, this.stationURI);
    }
}
