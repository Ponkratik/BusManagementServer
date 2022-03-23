package com.ponkratov.busmanagementserver.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@IdClass(RoutebusstopPK.class)
public class Routebusstop {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "routeId")
    private long routeId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "stopId")
    private long stopId;
    @Basic
    @Column(name = "order")
    private long order;
    @Basic
    @Column(name = "timeDelta")
    private Time timeDelta;
    @ManyToOne
    @JoinColumn(name = "routeId", referencedColumnName = "routeId", nullable = false)
    private Route routeByRouteId;
    @ManyToOne
    @JoinColumn(name = "stopId", referencedColumnName = "stopId", nullable = false)
    private Busstop busstopByStopId;

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

    public long getOrder() {
        return order;
    }

    public void setOrder(long order) {
        this.order = order;
    }

    public Time getTimeDelta() {
        return timeDelta;
    }

    public void setTimeDelta(Time timeDelta) {
        this.timeDelta = timeDelta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Routebusstop that = (Routebusstop) o;
        return routeId == that.routeId && stopId == that.stopId && order == that.order && Objects.equals(timeDelta, that.timeDelta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(routeId, stopId, order, timeDelta);
    }

    public Route getRouteByRouteId() {
        return routeByRouteId;
    }

    public void setRouteByRouteId(Route routeByRouteId) {
        this.routeByRouteId = routeByRouteId;
    }

    public Busstop getBusstopByStopId() {
        return busstopByStopId;
    }

    public void setBusstopByStopId(Busstop busstopByStopId) {
        this.busstopByStopId = busstopByStopId;
    }
}
