package cn.jjdcn.etas.user.api;

import cn.jjdcn.etas.common.bean.Result;
import cn.jjdcn.etas.user.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserApi {
    @GetMapping("/user/query")
    Result<User> doQueryUser(@RequestParam("username") String username, @RequestParam("password") String password);

    @PostMapping("/user/update")
    Result<Object> update(@RequestBody User user);
}
