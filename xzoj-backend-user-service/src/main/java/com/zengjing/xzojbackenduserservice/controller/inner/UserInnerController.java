package com.zengjing.xzojbackenduserservice.controller.inner;

import com.zengjing.xzojbackendmodel.model.entity.User;
import com.zengjing.xzojbackendserviceclient.service.UserFeignClient;
import com.zengjing.xzojbackenduserservice.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
@RestController
@RequestMapping("/inner")
public class UserInnerController implements UserFeignClient {
    @Resource
    private UserService userService;
    /**
     * 根据id获取用户信息
     */
    @Override
    @GetMapping("/get/id")
    public User getById(@RequestParam("userId") Long userId){
        return userService.getById(userId);
    }
    /**
     * 根据id列表获取用户信息
     */
    @Override
    @GetMapping("/get/ids")
    public List<User> getByIds(@RequestParam("idList") Collection<Long> idList){
        return userService.listByIds(idList);
    }
}
