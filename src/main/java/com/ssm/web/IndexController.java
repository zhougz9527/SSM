package com.ssm.web;


import com.ssm.dto.result.Result;
import com.ssm.dto.result.ResultEnum;
import com.ssm.dto.result.ResultUtil;
import com.ssm.entity.User;
import com.ssm.service.UserService;
import com.ssm.utils.CommonUtil;
import com.ssm.utils.GzipUtil;
import org.apache.commons.io.FileUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;

/**
 * @Author: Think
 * @Date: 2019/3/18
 * @Time: 15:59
 */

@RestController
@RequestMapping("")
public class IndexController extends BaseController {

    @Autowired
    UserService userService;


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public Result index() {
        return ResultUtil.success(null);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(HttpServletRequest httpServletRequest,
                        @RequestParam(value = "username", defaultValue = "") String username,
                        @RequestParam(value = "password", defaultValue = "") String password) {
        if (CommonUtil.notEmpty(username) && CommonUtil.notEmpty(password)) {
            return userService.findByUserName(username).map(user -> {
                if (BCrypt.checkpw(password, user.getPassword())) {
                    user.setLastLogin(new Date(System.currentTimeMillis()));
                    userService.saveUser(user);
                    HttpSession session = httpServletRequest.getSession(true);
                    session.setAttribute("user", username);
                    session.setAttribute("isLogin", true);
                    return ResultUtil.success(null);
                } else {
                    return ResultUtil.error(ResultEnum.INCORRECT_USERNAME_OR_PASSWORD);
                }
            }).orElse(ResultUtil.error(ResultEnum.INVALID_PARAMETER));
        } else {
            return ResultUtil.error(ResultEnum.ACCOUNT_NOT_EXIST);
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Result register(@RequestParam(value = "username", defaultValue = "") String username,
                           @RequestParam(value = "password", defaultValue = "") String password) {
        if (CommonUtil.notEmpty(username) && CommonUtil.notEmpty(password)) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            userService.saveUser(user);
            return ResultUtil.success(null);
        } else {
            return ResultUtil.error(ResultEnum.INVALID_PARAMETER);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        response.sendRedirect("/html/404.html");
    }

    @RequestMapping(value = "/gzipTest", method = RequestMethod.GET)
    public void gzipTest(HttpServletResponse response) throws Exception {
        File file = new File("F:\\private_workspace\\压缩测试文件.txt");
        GzipUtil.returnGzipResponse(response, FileUtils.readFileToString(file, GzipUtil.GZIP_ENCODE_UTF_8), GzipUtil.GZIP_ENCODE_UTF_8);
    }


}
