package cn.az.boot.service.impl;

import cn.az.boot.dao.UserDao;
import cn.az.boot.dto.UserDto;
import cn.az.boot.entity.User;
import cn.az.boot.service.UserService;
import cn.az.boot.util.CommonUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author azusachino
 * @version 12/16/2019
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {
    /**
     * This method retrieves the Base32-encoded private key of the given user.
     *
     * @param username the user whose private key shall be retrieved.
     * @return the private key of the specified user.
     */
    @Override
    public String getSecretKey(String username) {
        return getOne(Wrappers.<User>query().eq("username",username)).getSecretKey();
    }

    /**
     * This method saves the user credentials.
     *
     * @param username       the user whose data shall be saved.
     * @param secretKey      the generated key.
     * @param validationCode the validation code.
     * @param scratchCodes   the list of scratch codes.
     */
    @Override
    public void saveUserCredentials(String username, String secretKey, int validationCode, List<Integer> scratchCodes) {
        User u = getOne(Wrappers.<User>query().eq("username",username));
        u.setSecretKey(secretKey);
        u.setValidationCode(validationCode);
        StringBuilder sb = new StringBuilder();
        scratchCodes.forEach(sb::append);
        u.setScratchCodes(sb.toString());
        saveOrUpdate(u);
    }

    @Override
    public boolean login(UserDto u) {
        u.setPassword(CommonUtil.sha256(u.getPassword()));
        User login = getOne(Wrappers.<User>query().nested(i -> i.eq("username",u.getUsername()).eq("password",u.getPassword())));
        if (login == null) {
            return false;
        }
        GoogleAuthenticator ga = new GoogleAuthenticator();
        return ga.authorize(getSecretKey(u.getUsername()), u.getCode());
    }

}
