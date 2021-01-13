package cn.az.boot.service.impl;

import cn.az.boot.entity.User;
import cn.az.boot.mapper.UserMapper;
import cn.az.boot.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author azusachino
 * @version 12/13/2019
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
