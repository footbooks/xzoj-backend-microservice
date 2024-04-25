package com.zengjing.xzojbackendserviceclient.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zengjing.xzojbackendcommon.common.ErrorCode;
import com.zengjing.xzojbackendcommon.exception.BusinessException;
import com.zengjing.xzojbackendmodel.model.dto.user.UserQueryRequest;
import com.zengjing.xzojbackendmodel.model.entity.User;
import com.zengjing.xzojbackendmodel.model.enums.UserRoleEnum;
import com.zengjing.xzojbackendmodel.model.vo.LoginUserVO;
import com.zengjing.xzojbackendmodel.model.vo.UserVO;
import org.apache.tomcat.util.buf.ByteChunk;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.List;

import static com.zengjing.xzojbackendcommon.constant.UserConstant.USER_LOGIN_STATE;

/**
 * 用户服务
 *
 * 
 */
@FeignClient(name = "xzoj-backend-user-service",path = "/api/user/inner")
public interface UserFeignClient {
    /**
     * 根据id获取用户信息
     */
    @GetMapping("/get/id")
    User getById(@RequestParam("userId") Long userId);
    /**
     * 根据id列表获取用户信息
     */
    @GetMapping("/get/ids")
    List<User> getByIds(@RequestParam("idList") Collection<Long> idList);
    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    default User getLoginUser(HttpServletRequest request){
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User loginUser = (User) userObj;
        if(loginUser==null || loginUser.getId()==null){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return loginUser;
    }


    /**
     * 是否为管理员
     *
     * @param user
     * @return
     */
    default boolean isAdmin(User user){
        return user != null && UserRoleEnum.ADMIN.getValue().equals(user.getUserRole());
    }

    /**
     * 获取脱敏的用户信息
     *
     * @param user
     * @return
     */
    default UserVO getUserVO(User user){
        if (user == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        return userVO;
    }


}
