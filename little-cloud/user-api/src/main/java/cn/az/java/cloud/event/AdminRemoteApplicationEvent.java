package cn.az.java.cloud.event;

import cn.az.java.cloud.domain.Admin;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
 * @author az
 * @date 2020/4/19
 */
public class AdminRemoteApplicationEvent extends RemoteApplicationEvent {

    private AdminRemoteApplicationEvent(){

    }

    public AdminRemoteApplicationEvent(Admin admin, String originService, String destinationService) {
        super(admin, originService, destinationService);
    }
}
