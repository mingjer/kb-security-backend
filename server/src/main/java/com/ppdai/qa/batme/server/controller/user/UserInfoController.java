package com.ppdai.qa.batme.server.controller.user;

import com.ppdai.qa.batme.contract.common.GenericResponse;
import com.ppdai.qa.batme.contract.user.request.LoginRequest;
import com.ppdai.qa.batme.contract.user.response.LoginResponse;
import com.ppdai.qa.batme.core.constants.ResponseConstants;
import com.ppdai.qa.batme.core.constants.UserInfoConstants;
import com.ppdai.qa.batme.model.user.entity.UserInfo;
import com.ppdai.qa.batme.server.service.user.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
@Slf4j
public class UserInfoController {
    @Resource
    private IUserInfoService userInfoService;

    /**
     * 登录接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public GenericResponse login(@RequestBody LoginRequest request) {
        GenericResponse response = new GenericResponse();
        Subject subject = SecurityUtils.getSubject();
        response.setStatus(ResponseConstants.FAIL_CODE);
        UserInfo info = userInfoService.findByName(request.getLogin_name());
        UsernamePasswordToken token = new UsernamePasswordToken(request.getLogin_name(), request.getLogin_pwd());
        try {
            //登录验证
            subject.login(token);
            LoginResponse loginResponse = LoginResponse.builder()
                    .token(subject.getSession().getId()).login_name(request.getLogin_name()).build();
            response.setStatus(ResponseConstants.SUCCESS_CODE);
            response.setMessage("登录成功");
            response.setData(loginResponse);
        } catch (UnknownAccountException e) {
            response.setMessage("账号不存在");
            response.setStatus(ResponseConstants.STATUS_WRONG_PWD);
        } catch (IncorrectCredentialsException e) {
            response.setMessage("用户名/密码不正确");
            response.setStatus(ResponseConstants.STATUS_WRONG_PWD);
        } catch (LockedAccountException e) {
            response.setMessage("账号已锁定");
            response.setStatus(ResponseConstants.STATUS_OTHER);
        } catch (AuthenticationException e) {
            response.setMessage("登录异常:" + e.toString());
            response.setStatus(ResponseConstants.STATUS_OTHER);
        }
        return response;

    }

    /**
     * 退出接口
     *
     * @return
     */
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public GenericResponse logout() {
        GenericResponse response = new GenericResponse();
        try {
            SecurityUtils.getSubject().logout();
            response.setStatus(ResponseConstants.SUCCESS_CODE);
            response.setMessage("退出成功");
        } catch (Exception e) {
            log.error("退出异常：" + e.getCause());
            response.setStatus(ResponseConstants.FAIL_CODE);
        }
        return response;

    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public GenericResponse getUserInfo() {
        GenericResponse genericResponse = new GenericResponse();
        Session session = SecurityUtils.getSubject().getSession();
        UserInfo userInfo = (UserInfo) session.getAttribute(UserInfoConstants.CURRENT_USER);
        if (userInfo != null && userInfo.getId() != null) {
            genericResponse.setData(userInfo);
            genericResponse.setStatus(ResponseConstants.SUCCESS_CODE);
            genericResponse.setMessage("获取用户信息成功");
        } else {
            genericResponse.setStatus(ResponseConstants.STATUS_UNLOGIN);
            genericResponse.setMessage("未获取到登录信息");
        }
        return genericResponse;


    }
}
