package cn.az.java.spring.lifecycle.domain;

import cn.az.java.spring.lifecycle.enums.City;

import java.util.Collection;

/**
 * @author az
 */
public class User {

    private Long id;
    private String name;

    private City city;

    private Collection<City> lifeCities;

    private String configFileLocation;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Collection<City> getLifeCities() {
        return lifeCities;
    }

    public void setLifeCities(Collection<City> lifeCities) {
        this.lifeCities = lifeCities;
    }

    public String getConfigFileLocation() {
        return configFileLocation;
    }

    public void setConfigFileLocation(String configFileLocation) {
        this.configFileLocation = configFileLocation;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", city=" + city +
            ", lifeCities=" + lifeCities +
            ", configFileLocation='" + configFileLocation + '\'' +
            '}';
    }

}
