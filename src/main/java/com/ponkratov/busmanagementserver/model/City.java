package com.ponkratov.busmanagementserver.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class City {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cityId")
    private long cityId;
    @Basic
    @Column(name = "cityName")
    private String cityName;
    @OneToMany(mappedBy = "cityByCityId")
    private Collection<Busstop> busstopsByCityId;

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return cityId == city.cityId && Objects.equals(cityName, city.cityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId, cityName);
    }

    public Collection<Busstop> getBusstopsByCityId() {
        return busstopsByCityId;
    }

    public void setBusstopsByCityId(Collection<Busstop> busstopsByCityId) {
        this.busstopsByCityId = busstopsByCityId;
    }
}
