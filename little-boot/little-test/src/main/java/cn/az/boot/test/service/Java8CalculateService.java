package cn.az.boot.test.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

/**
 * @author az
 */
@Profile("java8")
@Service
public class Java8CalculateService implements CalculateService {
    @Override
    public Integer sum(Integer... args) {
        return Stream.of(args).reduce(0, Integer::sum);
    }
}
