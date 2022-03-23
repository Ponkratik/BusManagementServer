package com.ponkratov.busmanagementserver.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class RoutebusstopPK implements Serializable {
    @Column(name = "routeId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long routeId;
    @Column(name = "stopId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long stopId;

    public long getRouteId() {
        return routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }

    public long getStopId() {
        return stopId;
    }

    public void setStopId(long stopId) {
        this.stopId = stopId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutebusstopPK that = (RoutebusstopPK) o;
        return routeId == that.routeId && stopId == that.stopId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeId, stopId);
    }
}
