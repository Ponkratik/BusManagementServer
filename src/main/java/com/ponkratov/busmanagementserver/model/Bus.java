package com.ponkratov.busmanagementserver.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Bus {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "busId")
    private long busId;
    @Basic
    @Column(name = "busModel")
    private String busModel;
    @Basic
    @Column(name = "number")
    private String number;
    @Basic
    @Column(name = "seatsQty")
    private int seatsQty;
    @Basic
    @Column(name = "VIN")
    private String vin;
    @OneToMany(mappedBy = "busByBusId")
    private Collection<Trip> tripsByBusId;

    public long getBusId() {
        return busId;
    }

    public void setBusId(long busId) {
        this.busId = busId;
    }

    public String getBusModel() {
        return busModel;
    }

    public void setBusModel(String busModel) {
        this.busModel = busModel;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getSeatsQty() {
        return seatsQty;
    }

    public void setSeatsQty(int seatsQty) {
        this.seatsQty = seatsQty;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return busId == bus.busId && seatsQty == bus.seatsQty && Objects.equals(busModel, bus.busModel) && Objects.equals(number, bus.number) && Objects.equals(vin, bus.vin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(busId, busModel, number, seatsQty, vin);
    }

    public Collection<Trip> getTripsByBusId() {
        return tripsByBusId;
    }

    public void setTripsByBusId(Collection<Trip> tripsByBusId) {
        this.tripsByBusId = tripsByBusId;
    }
}
