package cn.az.boot.jpa.repository;

import cn.az.boot.jpa.entity.TravelInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author az
 */
public interface TravelRepository extends JpaRepository<TravelInfo, Integer> {

    @Override
    List<TravelInfo> findAll();

    @Override
    TravelInfo getOne(Integer integer);

    @Override
    void deleteById(Integer integer);
}
