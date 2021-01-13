package cn.az.boot.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author azusachino
 * @version 12/16/2019
 */
@Service
public class LogService {

    public String getA() {
        String[] strings = new String[]{"a", "aa", "aaa"};
        List<String> list = new ArrayList<>(Arrays.asList(strings));
        return list.stream().filter(i -> i.startsWith("a")).collect(Collectors.joining());
    }
}
