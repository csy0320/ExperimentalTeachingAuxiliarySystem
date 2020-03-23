package cn.jjdcn.etas.user.service;

import cn.jjdcn.etas.common.bean.PageVo;
import cn.jjdcn.etas.common.bean.QueryCondition;
import com.baomidou.mybatisplus.extension.service.IService;
import cn.jjdcn.etas.user.entity.User;


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

