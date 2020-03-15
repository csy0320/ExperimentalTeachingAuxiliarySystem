package cn.jjdcn.etas.user.api;

import cn.jjdcn.etas.common.bean.Result;
import cn.jjdcn.etas.user.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserApi {
    @GetMapping("/user/query")
    Result<User> doQueryUser(@RequestParam("username") String username, @RequestParam("password") String password);
}
