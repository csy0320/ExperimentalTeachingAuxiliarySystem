package cn.jjdcn.etas.user.service;

import cn.jjdcn.etas.common.utils.MyJwtUtil;
import cn.jjdcn.etas.user.entity.User;
import cn.jjdcn.etas.user.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LoginService {

    @Autowired
    private UserMapper userMapper;

    public String login(String username, String password) {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("username",username);
        User user = userMapper.selectOne(wrapper);
        String jwt = null;
        // 密码加盐验证
        if (user != null && user.getPassword().equals(DigestUtils.md5Hex(password+user.getSalt()))) {
            jwt = MyJwtUtil.createJWT(user.getId(), user.getUsername());
            user.setLoginTimes(user.getLoginTimes() + 1);
            user.setLastLoginTime(new Date());
            userMapper.updateById(user);
        }
        return jwt;
    }
}
