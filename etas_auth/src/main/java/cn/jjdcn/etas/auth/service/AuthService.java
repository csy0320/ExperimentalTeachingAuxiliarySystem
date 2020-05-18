package cn.jjdcn.etas.auth.service;

import cn.jjdcn.etas.common.bean.Result;
import cn.jjdcn.etas.auth.feign.UserClient;
import cn.jjdcn.etas.common.utils.MyJwtUtil;
import cn.jjdcn.etas.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserClient userClient;

    public String accredit(String username, String password) {
        Result<User> result = userClient.doQueryUser(username, password);
        User user = result.getData();
        if (user == null) {
            return "";
        }

        String jwt = MyJwtUtil.createJWT(user.getId(), user.getUsername());
        return jwt;
    }
}
