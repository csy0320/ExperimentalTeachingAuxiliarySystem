//package cn.jjdcn.etas.user.mapper;
//
//import User;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
//import org.apache.ibatis.annotations.Update;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class UserMapperTest {
//
//    @Autowired
//    private UserMapper userMapper;
//
//    @Test
//    public void testUserMapper(){
//
//        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
//        updateWrapper.set("login_times", 99999);
//        updateWrapper.eq("id", 2);
////        updateWrapper.
//
////        QueryWrapper<User> wrapper = new QueryWrapper<>();
////        wrapper.eq("id", 2);
////        User user = userMapper.selectOne(wrapper);
//
//        userMapper.update(null, updateWrapper);
////        System.out.println("user = " + user);
//
//    }
//
//}