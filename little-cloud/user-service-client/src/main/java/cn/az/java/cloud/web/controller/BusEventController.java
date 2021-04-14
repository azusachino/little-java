package cn.az.java.cloud.web.controller;

import cn.az.cloud.domain.Admin;
import cn.az.cloud.event.AdminRemoteApplicationEvent;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author az
 */
@RestController
public class BusEventController implements ApplicationContextAware, ApplicationEventPublisherAware {

    private ApplicationEventPublisher eventPublisher;

    private ApplicationContext applicationContext;


    @PostMapping("/bus/event/publish/user")
    public boolean publishUserEvent(@RequestBody Admin admin,
                                    @RequestParam(value = "destination", required = false) String destination) {

        String serviceInstanceId = applicationContext.getId();
        AdminRemoteApplicationEvent event = new AdminRemoteApplicationEvent(admin, serviceInstanceId, destination);
        eventPublisher.publishEvent(event);
        return true;

    }


    @Override
    public void setApplicationEventPublisher(@NonNull ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
