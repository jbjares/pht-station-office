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

    public StationEntity(final Station station) {

        this.stationURI = station.getStationURI();
    }

    public Station toStation() {

        return new Station(this.stationID, this.stationURI);
    }
}
