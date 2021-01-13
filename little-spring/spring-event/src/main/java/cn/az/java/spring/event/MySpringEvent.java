package cn.az.java.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author az
 * @since 08/13/20
 */
public class MySpringEvent extends ApplicationEvent {

    public MySpringEvent(String msg) {
        super(msg);
    }

    @Override
    public String getSource() {
        return (String) super.getSource();
    }

    public String getMessage() {
        return getSource();
    }
}
