package cn.jjdcn.soa.etas.service;

import cn.jjdcn.soa.etas.entity.User;
import cn.jjdcn.soa.etas.mapper.user.UserMapper;
import cn.jjdcn.soa.etas.utils.MyJwtUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    public String login(String username, String password) {

        User user = userService.selectOneByUsernameAndPassword(username,password);
        String jwt = null;
        // 密码加盐验证
        if (user != null && user.getPassword().equals(DigestUtils.md5Hex(password+user.getSalt()))) {
            jwt = MyJwtUtil.createJWT(user.getId(), user.getUsername());
            user.setLoginTimes(user.getLoginTimes() + 1);
            user.setLastLoginTime(new Date());
            userMapper.update(user);
        }
        return jwt;
    }
}
