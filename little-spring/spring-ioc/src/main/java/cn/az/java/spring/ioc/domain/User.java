package cn.az.java.spring.ioc.domain;

import cn.az.java.spring.ioc.enums.City;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.core.io.ClassPathResource;
import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * @author az
 * @date 2020/3/25
 */
public class User implements BeanNameAware {

    private Long id;
    private String name;
    private String beanName;
    private City city;
    private City[] workCities;
    private List<String> lifeCities;
    private ClassPathResource configFileLocation;

    private Properties context;

    private String contextAsText;

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

    public City[] getWorkCities() {
        return workCities;
    }

    public void setWorkCities(City[] workCities) {
        this.workCities = workCities;
    }

    public List<String> getLifeCities() {
        return lifeCities;
    }

    public void setLifeCities(List<String> lifeCities) {
        this.lifeCities = lifeCities;
    }

    public ClassPathResource getConfigFileLocation() {
        return configFileLocation;
    }

    public void setConfigFileLocation(ClassPathResource configFileLocation) {
        this.configFileLocation = configFileLocation;
    }

    public Properties getContext() {
        return context;
    }

    public void setContext(Properties context) {
        this.context = context;
    }

    public String getContextAsText() {
        return contextAsText;
    }

    public void setContextAsText(String contextAsText) {
        this.contextAsText = contextAsText;
    }

    @Override
    public void setBeanName(@NonNull String name) {
        this.beanName = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", beanName='" + beanName + '\'' +
                ", city=" + city +
                ", workCities=" + Arrays.toString(workCities) +
                ", lifeCities=" + lifeCities +
                ", configFileLocation=" + configFileLocation +
                ", context=" + context +
                ", contextAsText='" + contextAsText + '\'' +
                '}';
    }
}
