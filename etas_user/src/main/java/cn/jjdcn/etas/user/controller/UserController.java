package cn.jjdcn.etas.user.controller;

import java.util.Arrays;


import cn.jjdcn.etas.common.bean.PageVo;
import cn.jjdcn.etas.common.bean.QueryCondition;
import cn.jjdcn.etas.common.bean.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import cn.jjdcn.etas.user.entity.User;
import cn.jjdcn.etas.user.service.UserService;

import javax.servlet.http.HttpServletRequest;

/**
 * @author jjdcn
 * @email shzu_csy@163.com
 * @date 2020-02-14 15:12:01
 */
@Api(tags = " 用户管理")
@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @ApiOperation("查询用户")
    @GetMapping("query")
    public Result<User> doQueryUser(@RequestParam("username") String username, @RequestParam("password") String password){
        return Result.ok().data(userService.selectOneByUsernameAndPassword(username,password));
    }

    /**
     * 列表
     */
    @ApiOperation("分页查询(排序)")
    @GetMapping("list")
//    @PreAuthorize("hasAuthority('user:user:list')")
    public Result<PageVo> list(QueryCondition queryCondition) {
        PageVo page = userService.queryPage(queryCondition);

        return Result.ok().data(page);
    }


    /**
     * 信息
     */
    @ApiOperation("详情查询")
    @GetMapping("info/{id}")
//    @PreAuthorize("hasAuthority('user:user:info')")
    public Result<User> info(@PathVariable("id") Long id) {
        User user = userService.getById(id);

        return Result.ok().data(user);
    }

    /**
     * 保存
     *
     * @return
     */
    @ApiOperation("保存")
    @PostMapping("save")
//    @PreAuthorize("hasAuthority('user:user:save')")
    public Result<Boolean> save( @RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("captcha") String captcha, HttpServletRequest request) {

        String code = stringRedisTemplate.opsForValue().get("captcha" + request.getSession().getId());
        if (StringUtils.isBlank(captcha)) return Result.error().message("请输入验证码");
        if (!captcha.equalsIgnoreCase(code)) return Result.error().message("验证码错误,请重新获取");
        stringRedisTemplate.delete(stringRedisTemplate.keys("captcha" + request.getSession().getId()));
        Boolean reg = false;
        if (code.equalsIgnoreCase(captcha)) {
            reg = userService.registerUser(User.builder().username(username).password(password).build());
        }
        if (reg) {
            return Result.ok().message("注册成功").data(reg);
        } else {
            return Result.error().message("用户已存在").data(reg);
        }
    }

    /**
     * 修改
     */
    @ApiOperation("修改")
    @PostMapping("update")
//    @PreAuthorize("hasAuthority('user:user:update')")
    public Result<Object> update(@RequestBody User user) {
        userService.updateById(user);

        return Result.ok().data(null);
    }

    /**
     * 删除
     */
    @ApiOperation("删除")
    @PostMapping("delete")
//    @PreAuthorize("hasAuthority('user:user:delete')")
    public Result<Object> delete(@RequestBody Long[] ids) {
        userService.removeByIds(Arrays.asList(ids));

        return Result.ok().data(null);
    }

}
