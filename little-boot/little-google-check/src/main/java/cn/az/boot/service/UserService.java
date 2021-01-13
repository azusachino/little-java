package cn.az.boot.service;

import cn.az.boot.dto.UserDto;
import cn.az.boot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.warrenstrange.googleauth.ICredentialRepository;

/**
 * @author azusachino
 * @version 12/16/2019
 */
public interface UserService extends IService<User>, ICredentialRepository {

    boolean login(UserDto user);

}
