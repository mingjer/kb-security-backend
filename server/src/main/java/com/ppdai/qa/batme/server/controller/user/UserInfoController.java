package com.ppdai.qa.batme.server.controller.user;

import com.ppdai.qa.batme.contract.common.GenericResponse;
import com.ppdai.qa.batme.contract.user.request.LoginRequest;
import com.ppdai.qa.batme.contract.user.response.LoginResponse;
import com.ppdai.qa.batme.core.constants.CookieConstants;
import com.ppdai.qa.batme.core.constants.ResponseConstants;
import com.ppdai.qa.batme.core.constants.UserInfoConstants;
import com.ppdai.qa.batme.core.enums.CookieTypeEnum;
import com.ppdai.qa.batme.core.utils.PPBase64Utils;
import com.ppdai.qa.batme.model.user.entity.UserInfo;
import com.ppdai.qa.batme.server.service.user.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


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
    public GenericResponse login(@RequestBody LoginRequest request, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
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

            if (request.getCookie_type() == CookieTypeEnum.ID.getCode()) {
                //登录成功   设置ID_COOKIE
                addCookieAndDelSession(httpServletRequest, httpServletResponse, CookieConstants.ID_SESSION, String.valueOf(info.getId()));
            }

            if (request.getCookie_type() == CookieTypeEnum.BASE64_ID.getCode()) {
                //登录成功   设置BASE64_ID_COOKIE
                addCookieAndDelSession(httpServletRequest, httpServletResponse, CookieConstants.BASE64_ID_SESSION, PPBase64Utils.encodeBase64(info.getId().toString()));
            }

            if (request.getCookie_type() == CookieTypeEnum.SESSION_ID.getCode()) {
                //登录成功   设置BASE64_ID_COOKIE
                addCookieAndDelSession(httpServletRequest, httpServletResponse, CookieConstants.BATME_SESSIONID, SecurityUtils.getSubject().getSession().getId() == null ? null : (String) SecurityUtils.getSubject().getSession().getId());
            }

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
     * 添加cookie
     *
     * @param response
     * @param name
     * @param value
     */
    private void addCookieAndDelSession(HttpServletRequest request, HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name, null);
        cookie.setHttpOnly(false);
        cookie.setPath("/");
        cookie.setValue(value);
        response.addCookie(cookie);

        if (null != request.getCookies())
            for (Cookie requestCookie : request.getCookies()) {
                if (requestCookie.getName().contains("SESSION") && !requestCookie.getName().equals(name)) {
                    Cookie session = new Cookie(requestCookie.getName(), null);
                    session.setMaxAge(0);
                    session.setPath("/");
                    session.setValue(null);
                    response.addCookie(session);
                }
            }
    }

    /**
     * 删除session
     *
     * @param request
     * @param response
     */
    private void delSession(HttpServletRequest request, HttpServletResponse response) {
        Cookie session = new Cookie(CookieConstants.BATME_SESSIONID, null);
        session.setMaxAge(0);
        session.setPath("/");
        session.setValue(null);
        response.addCookie(session);
    }

    /**
     * 退出接口
     *
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public GenericResponse logout(HttpServletRequest request, HttpServletResponse response) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            SecurityUtils.getSubject().logout();
            if (request.getCookies() != null)
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals(CookieConstants.ID_SESSION) || cookie.getName().equals(CookieConstants.BASE64_ID_SESSION)) {
                        cookie.setMaxAge(0);
                        cookie.setValue(null);
                        cookie.setPath("/");
                        response.addCookie(cookie);
                    }
                }
            genericResponse.setStatus(ResponseConstants.SUCCESS_CODE);
            genericResponse.setMessage("退出成功");
        } catch (Exception e) {
            log.error("退出异常：" + e.getCause());
            genericResponse.setStatus(ResponseConstants.FAIL_CODE);
        }
        return genericResponse;

    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public GenericResponse getUserInfo(HttpServletRequest request, HttpServletResponse response) {
        GenericResponse genericResponse = new GenericResponse();
        try {
            Session session = SecurityUtils.getSubject().getSession();

            UserInfo userInfo = null;
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(CookieConstants.ID_SESSION)) {
                    userInfo = userInfoService.get(Integer.valueOf(cookie.getValue()));
                    delSession(request, response);
                } else if (cookie.getName().equals(CookieConstants.BASE64_ID_SESSION)) {
                    userInfo = userInfoService.get(Integer.valueOf(PPBase64Utils.decodeBase64(cookie.getValue())));
                    delSession(request, response);
                }
            }
            if (ObjectUtils.isEmpty(userInfo))
                userInfo = (UserInfo) session.getAttribute(UserInfoConstants.CURRENT_USER);
            if (userInfo != null && userInfo.getId() != null) {
                genericResponse.setData(userInfo);
                genericResponse.setStatus(ResponseConstants.SUCCESS_CODE);
                genericResponse.setMessage("获取用户信息成功");
            } else {
                genericResponse.setStatus(ResponseConstants.STATUS_UNLOGIN);
                genericResponse.setMessage("未获取到登录信息");
            }
        } catch (Exception e) {
            genericResponse.setStatus(ResponseConstants.FAIL_CODE);
        }
        return genericResponse;

    }
}