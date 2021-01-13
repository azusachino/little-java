package cn.az.java.spring.event;

/**
 * @author az
 * @since 08/13/20
 */
public class AnotherSpringEvent extends MySpringEvent {

    public AnotherSpringEvent(String msg) {
        super(msg);
    }

    @Override
    public String getSource() {
        return super.getSource();
    }

    @Override
    public String getMessage() {
        return getSource();
    }
}
