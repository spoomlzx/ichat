package com.lan.ichat.console;

import com.lan.common.annotation.OpenApi;
import com.lan.common.util.BaseResult;
import com.lan.ichat.model.UserEntity;
import com.lan.ichat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * package com.lan.ichat.console
 *
 * @author lanzongxiao
 * @date 2017/10/31
 */
@RestController
@RequestMapping(value = "/console")
public class SysUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @OpenApi
    @GetMapping(value = "/user/{id}")
    public BaseResult getUser(@PathVariable Long id) {
        BaseResult baseResult = new BaseResult();
        try {
            UserEntity userEntity = userService.getUserById(id);
            redisTemplate.opsForValue().set("asdf"+id, userEntity);
            baseResult.setMsg("获取成功");
            baseResult.setData(userEntity);
        } catch (Exception e) {
            baseResult.setCode(HttpStatus.NOT_FOUND.value());
            baseResult.setMsg("获取失败");
        }
        return baseResult;
    }

    @GetMapping(value = "/user/list")
    public BaseResult getUserList() {
        BaseResult baseResult = new BaseResult();
        try {
            List<UserEntity> userList = userService.getUserList();
            baseResult.setMsg("获取成功");
            baseResult.setData(userList);
        } catch (Exception e) {
            baseResult.setCode(HttpStatus.NOT_FOUND.value());
            baseResult.setMsg("获取失败");
        }
        return baseResult;
    }
}
