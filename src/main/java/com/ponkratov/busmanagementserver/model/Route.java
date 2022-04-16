package com.ponkratov.busmanagementserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Route {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "routeId")
    private long routeId;
    @Basic
    @Column(name = "routeName")
    private String routeName;
    @JsonIgnore
    @OneToMany(mappedBy = "routeByRouteId")
    private Collection<Routebusstop> routebusstopsByRouteId;
    @JsonIgnore
    @OneToMany(mappedBy = "routeByRouteId")
    private Collection<Trip> tripsByRouteId;

    public long getRouteId() {
        return routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return routeId == route.routeId && Objects.equals(routeName, route.routeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeId, routeName);
    }

    public Collection<Routebusstop> getRoutebusstopsByRouteId() {
        return routebusstopsByRouteId;
    }

    public void setRoutebusstopsByRouteId(Collection<Routebusstop> routebusstopsByRouteId) {
        this.routebusstopsByRouteId = routebusstopsByRouteId;
    }

    public Collection<Trip> getTripsByRouteId() {
        return tripsByRouteId;
    }

    public void setTripsByRouteId(Collection<Trip> tripsByRouteId) {
        this.tripsByRouteId = tripsByRouteId;
    }
}
