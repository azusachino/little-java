package cn.az.boot.jpa.service;

import cn.az.boot.jpa.entity.TravelInfo;

import java.util.List;

public interface TravelService {

    List<TravelInfo> getTravelList();

    TravelInfo findTravelById(Integer travelId);

    void save(TravelInfo travelInfo);

    void update(TravelInfo travelInfo);

    void delete(Integer travelId);
}
