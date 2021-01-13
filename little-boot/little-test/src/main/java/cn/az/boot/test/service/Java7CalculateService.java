package cn.az.boot.test.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * @author az
 */
@Profile("java7")
@Service
public class Java7CalculateService implements CalculateService {

    @Override
    public Integer sum(Integer... args) {
        int sum = 0;
        for (Integer a : args) {
            sum += a;
        }
        return sum;
    }
}
