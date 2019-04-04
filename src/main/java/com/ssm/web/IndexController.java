package com.ssm.web;


import com.ssm.dto.result.Result;
import com.ssm.dto.result.ResultEnum;
import com.ssm.dto.result.ResultUtil;
import com.ssm.utils.CommonUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author: Think
 * @Date: 2019/3/18
 * @Time: 15:59
 */

@RestController
@RequestMapping("")
public class IndexController extends BaseController {


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public Result index() {
        return ResultUtil.success(null);
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Result login(HttpServletRequest httpServletRequest,
                        @RequestParam(value = "username", defaultValue = "") String username,
                        @RequestParam(value = "password", defaultValue = "") String password) {
        if (username.equals("123") && password.equals("123")) {
            HttpSession session = httpServletRequest.getSession(true);
            session.setAttribute("user", username);
            session.setAttribute("isLogin", true);
            return ResultUtil.success(null);
        } else {
            return ResultUtil.error(ResultEnum.ACCOUNT_NOT_EXIST);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public void logout(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (CommonUtil.notEmpty(session)) {
            logger.debug("---------- session {} -----------" , session.getAttribute("user").toString());
            session.removeAttribute("user");
        }
        response.sendRedirect("/WEB-INF/static/html/404.html");

    }


}
