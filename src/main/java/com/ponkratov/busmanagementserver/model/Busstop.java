package com.ponkratov.busmanagementserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Busstop {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "stopId")
    private long stopId;
    @Basic
    @Column(name = "stopName")
    private String stopName;
    @Basic
    @Column(name = "cityId")
    private long cityId;
    @Basic
    @Column(name = "intermediate")
    private boolean intermediate;
    @Basic
    @Column(name = "latitude")
    private double latitude;
    @Basic
    @Column(name = "longitude")
    private double longitude;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cityId", referencedColumnName = "cityId", nullable = false, insertable = false, updatable = false)
    private City cityByCityId;
    @JsonIgnore
    @OneToMany(mappedBy = "busstopByStopId")
    private Collection<Routebusstop> routebusstopsByStopId;

    public long getStopId() {
        return stopId;
    }

    public void setStopId(long stopId) {
        this.stopId = stopId;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public boolean isIntermediate() {
        return intermediate;
    }

    public void setIntermediate(boolean intermediate) {
        this.intermediate = intermediate;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Busstop busstop = (Busstop) o;
        return stopId == busstop.stopId && cityId == busstop.cityId && intermediate == busstop.intermediate && Double.compare(busstop.latitude, latitude) == 0 && Double.compare(busstop.longitude, longitude) == 0 && Objects.equals(stopName, busstop.stopName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stopId, stopName, cityId, intermediate, latitude, longitude);
    }

    public City getCityByCityId() {
        return cityByCityId;
    }

    public void setCityByCityId(City cityByCityId) {
        this.cityByCityId = cityByCityId;
    }

    public Collection<Routebusstop> getRoutebusstopsByStopId() {
        return routebusstopsByStopId;
    }

    public void setRoutebusstopsByStopId(Collection<Routebusstop> routebusstopsByStopId) {
        this.routebusstopsByStopId = routebusstopsByStopId;
    }
}
