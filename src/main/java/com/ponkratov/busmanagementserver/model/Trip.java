package com.ponkratov.busmanagementserver.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
public class Trip {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "tripId")
    private long tripId;
    @Basic
    @Column(name = "routeId")
    private long routeId;
    @Basic
    @Column(name = "userId")
    private long userId;
    @Basic
    @Column(name = "busId")
    private long busId;
    @Basic
    @Column(name = "depTime")
    private Time depTime;
    @Basic
    @Column(name = "price")
    private double price;
    @ManyToOne
    @JoinColumn(name = "routeId", referencedColumnName = "routeId", nullable = false, insertable = false, updatable = false)
    private Route routeByRouteId;
    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId", nullable = false, insertable = false, updatable = false)
    private User userByUserId;
    @ManyToOne
    @JoinColumn(name = "busId", referencedColumnName = "busId", nullable = false, insertable = false, updatable = false)
    private Bus busByBusId;

    public long getTripId() {
        return tripId;
    }

    public void setTripId(long tripId) {
        this.tripId = tripId;
    }

    public long getRouteId() {
        return routeId;
    }

    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBusId() {
        return busId;
    }

    public void setBusId(long busId) {
        this.busId = busId;
    }

    public Time getDepTime() {
        return depTime;
    }

    public void setDepTime(Time depTime) {
        this.depTime = depTime;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return tripId == trip.tripId && routeId == trip.routeId && userId == trip.userId && busId == trip.busId && Double.compare(trip.price, price) == 0 && Objects.equals(depTime, trip.depTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tripId, routeId, userId, busId, depTime, price);
    }

    public Route getRouteByRouteId() {
        return routeByRouteId;
    }

    public void setRouteByRouteId(Route routeByRouteId) {
        this.routeByRouteId = routeByRouteId;
    }

    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    public Bus getBusByBusId() {
        return busByBusId;
    }

    public void setBusByBusId(Bus busByBusId) {
        this.busByBusId = busByBusId;
    }
}
