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
public class Route {
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "routeId")
    private long routeId;
    @Basic
    @Column(name = "routeName")
    private String routeName;

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
}
