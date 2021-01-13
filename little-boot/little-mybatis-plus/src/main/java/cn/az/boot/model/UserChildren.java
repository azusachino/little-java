package cn.az.boot.model;

import cn.az.boot.entity.Child;
import cn.az.boot.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author azusachino
 * @version 12/13/2019
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserChildren extends User {

    private List<Child> children;
}

