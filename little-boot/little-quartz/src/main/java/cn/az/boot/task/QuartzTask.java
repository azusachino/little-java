package cn.az.boot.task;

import cn.az.boot.service.NotifyService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Liz
 * @date 1/8/2020
 */
@Slf4j
public class QuartzTask extends QuartzJobBean {

    @Resource
    private NotifyService notifyService;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        log.info(LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE) + " : {}","QuartzTask activated");
        notifyService.sendMsg();
    }
}
