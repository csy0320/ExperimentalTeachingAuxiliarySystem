package cn.jjdcn.etas.user.service.impl;

import cn.jjdcn.etas.common.bean.PageVo;
import cn.jjdcn.etas.common.bean.Query;
import cn.jjdcn.etas.common.bean.QueryCondition;
import cn.jjdcn.etas.user.entity.User;
import cn.jjdcn.etas.user.mapper.UserMapper;
import cn.jjdcn.etas.user.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;


@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageVo queryPage(QueryCondition params) {
        IPage<User> page = this.page(
                new Query<User>().getPage(params),
                new QueryWrapper<User>()
        );

        return new PageVo(page);
    }

    /**
     * 注册用户
     * @param user
     * @return
     */
    public Boolean registerUser(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.like("username",user.getUsername());
        Integer userCount = userMapper.selectCount(wrapper);
        if(userCount <= 0){
            String salt = UUID.randomUUID().toString().substring(0,6);
            user.setSalt(salt);
            user.setPassword(DigestUtils.md5Hex(user.getPassword()+salt));
            user.setCreateTime(new Date());
            user.setLoginTimes(0L);
            this.userMapper.insert(user);
            return true;
        }
        return false;
    }

    @Override
    public User selectOneByUsernameAndPassword(String username, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        User user = userMapper.selectOne(wrapper);
        if (user == null) return null;
        wrapper.eq("password",DigestUtils.md5Hex(password + user.getSalt()));
        return userMapper.selectOne(wrapper);
    }

    @Override
    public Boolean updateUser(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id",user.getId());
        Integer userCount = userMapper.selectCount(wrapper);
        if(userCount >= 1){
            this.userMapper.updateById(user);
            return true;
        }
        return false;
    }

}