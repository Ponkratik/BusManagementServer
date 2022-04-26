package com.ponkratov.busmanagementserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Busstop {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "stopId")
    private long stopId;
    @Basic
    @Column(name = "stopName")
    private String stopName;
    @Basic
    @Column(name = "intermediate")
    private boolean intermediate;
    @Basic
    @Column(name = "latitude")
    private double latitude;
    @Basic
    @Column(name = "longitude")
    private double longitude;

    @ManyToOne
    @JoinColumn(name = "cityId", referencedColumnName = "cityId")
    private City cityByCityId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Busstop busstop = (Busstop) o;
        return stopId == busstop.stopId && intermediate == busstop.intermediate && Double.compare(busstop.latitude, latitude) == 0 && Double.compare(busstop.longitude, longitude) == 0 && Objects.equals(stopName, busstop.stopName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stopId, stopName, intermediate, latitude, longitude);
    }
}
