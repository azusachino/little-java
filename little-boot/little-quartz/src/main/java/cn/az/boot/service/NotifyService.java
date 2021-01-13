package cn.az.boot.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Liz
 * @date 1/8/2020
 */
@Slf4j
@Service
public class NotifyService {

    public void sendMsg() {
        log.info(LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE) + " : {}","notify function activated");
    }
}
