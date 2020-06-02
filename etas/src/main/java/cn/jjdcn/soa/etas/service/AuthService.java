package cn.jjdcn.soa.etas.service;

import cn.jjdcn.soa.etas.entity.User;
import cn.jjdcn.soa.etas.utils.MyJwtUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AuthService {

    @Resource
    private UserService userService;

    public String accredit(String username, String password) {
        User user = userService.selectOneByUsernameAndPassword(username,password);
        if (user == null) {
            return "";
        }

        String jwt = MyJwtUtil.createJWT(user.getId(), user.getUsername());
        return jwt;
    }
}
