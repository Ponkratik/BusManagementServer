package com.ponkratov.busmanagementserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@IdClass(RoutebusstopPK.class)
@Getter
@Setter
public class Routebusstop {
    @Id
    @Column(name = "routeId")
    private long routeId;

    @Id
    @Column(name = "stopId")
    private long stopId;
    @Basic
    @Column(name = "`order`")
    private long order;
    @Basic
    @Column(name = "timeDelta")
    private Time timeDelta;

    @ManyToOne
    @JoinColumn(name = "routeId", referencedColumnName = "routeId", nullable = false, insertable = false, updatable = false)
    private Route routeByRouteId;
    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "stopId", referencedColumnName = "stopId", nullable = false, insertable = false, updatable = false)
    private Busstop busstopByStopId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Routebusstop that = (Routebusstop) o;
        return routeByRouteId == that.routeByRouteId && busstopByStopId == that.busstopByStopId && order == that.order && Objects.equals(timeDelta, that.timeDelta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeByRouteId, busstopByStopId, order, timeDelta);
    }
}
