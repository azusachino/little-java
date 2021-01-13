package cn.az.java.cloud.service;


import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Liz
 * @date 1/8/2020
 */
@Service
public class UserService implements UserDetailsService {

    private List<User> userList;

    @Resource
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init() {
        String password = passwordEncoder.encode("123");
        userList = Arrays.asList(
             new User("az",password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin")),
             new User("ac",password, AuthorityUtils.commaSeparatedStringToAuthorityList("client")),
             new User("ab",password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin")));
    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<User> resultUser = userList.stream().filter(u -> u.getUsername().equals(s)).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(resultUser)) {
            throw new UsernameNotFoundException("not found");
        } else {
            return resultUser.get(0);
        }
    }



}
