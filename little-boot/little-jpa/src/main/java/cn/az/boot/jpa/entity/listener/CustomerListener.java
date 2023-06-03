package cn.az.boot.jpa.entity.listener;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;

/**
 * @author az
 */
public class CustomerListener {

    @PrePersist
    public void prePersis(Object source) {
        System.out.println("@PrePersis:" + source);
    }

    @PostPersist
    public void postPersis(Object source) {
        System.out.println("@PostPersis:" + source);
    }
}
