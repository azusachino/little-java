package cn.az.java.mp.service.impl;

import cn.az.java.mp.entity.User;
import cn.az.java.mp.mapper.UserMapper;
import cn.az.java.mp.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author az
 * @since 2020-04-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
