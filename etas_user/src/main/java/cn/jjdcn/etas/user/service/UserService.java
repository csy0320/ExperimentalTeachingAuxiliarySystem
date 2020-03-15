package cn.jjdcn.etas.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.jjdcn.etas.user.entity.User;
import cn.jjdcn.etas.common.bean.PageVo;
import cn.jjdcn.etas.common.bean.QueryCondition;
import com.sun.org.apache.xpath.internal.operations.Bool;


/**
 * 
 *
 * @author jjdcn
 * @email shzu_csy@163.com
 * @date 2020-02-14 15:12:01
 */
public interface UserService extends IService<User> {

    PageVo queryPage(QueryCondition params);

    Boolean registerUser(User user);

    User selectOneByUsernameAndPassword(String username, String password);
}

