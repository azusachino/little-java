package cn.az.boot.jpa.service.impl;

import cn.az.boot.jpa.entity.TravelInfo;
import cn.az.boot.jpa.repository.TravelRepository;
import cn.az.boot.jpa.service.TravelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author az
 */
@Service
public class TravelServiceImpl implements TravelService {

    @Resource
    private TravelRepository travelRepository;

    @Override
    public List<TravelInfo> getTravelList() {
        return travelRepository.findAll();
    }

    @Override
    public TravelInfo findTravelById(Integer travelId) {
        return travelRepository.getOne(travelId);
    }

    @Override
    public void save(TravelInfo travelInfo) {

        travelRepository.save(travelInfo);
    }

    @Override
    public void update(TravelInfo travelInfo) {

        travelRepository.save(travelInfo);
    }

    @Override
    public void delete(Integer travelId) {
        travelRepository.deleteById(travelId);
    }
}
