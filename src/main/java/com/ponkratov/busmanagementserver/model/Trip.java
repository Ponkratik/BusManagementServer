package com.ponkratov.busmanagementserver.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
public class Trip {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "tripId")
    private long tripId;
    @Basic
    @Column(name = "depTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date depTime;
    @Basic
    @Column(name = "price")
    private double price;
    @ManyToOne
    @JoinColumn(name = "routeId", referencedColumnName = "routeId")
    private Route routeByRouteId;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User userByUserId;
    @ManyToOne
    @JoinColumn(name = "busId", referencedColumnName = "busId")
    private Bus busByBusId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return tripId == trip.tripId && routeByRouteId == trip.routeByRouteId && userByUserId == trip.userByUserId && busByBusId == trip.busByBusId && Double.compare(trip.price, price) == 0 && Objects.equals(depTime, trip.depTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripId, routeByRouteId, userByUserId, busByBusId, depTime, price);
    }
}
