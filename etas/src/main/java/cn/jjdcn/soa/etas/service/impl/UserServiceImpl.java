package cn.jjdcn.soa.etas.service.impl;

import cn.jjdcn.soa.etas.entity.User;
import cn.jjdcn.soa.etas.mapper.user.UserMapper;
import cn.jjdcn.soa.etas.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User queryById(Long id) {
        return this.userMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userMapper.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userMapper.update(user);
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.userMapper.deleteById(id) > 0;
    }

    @Override
    public User selectOneByUsernameAndPassword(String username, String password) {
        List<User> userList = userMapper.queryAll(User.builder().username(username).build());
        if (userList.size() == 0) return null;
        List<User> users = userMapper.queryAll(User.builder().username(username).password(DigestUtils.md5Hex(password + userList.get(0).getSalt())).build());
        if (users.size() == 1) {
            return users.get(0);
        }
        return null;
    }

    @Override
    public boolean updatePassword(User user) {
        User newUser = userMapper.queryById(user.getId());
        String salt = UUID.randomUUID().toString().substring(0, 6);
        newUser.setSalt(salt);
        newUser.setPassword(DigestUtils.md5Hex(user.getPassword() + salt));
        return userMapper.update(newUser) > 0;
    }

}