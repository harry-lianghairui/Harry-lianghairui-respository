package com.thinkgem.jeesite.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.thinkgem.jeesite.domain.Resp;
import com.thinkgem.jeesite.utils.Consts;
import com.thinkgem.jeesite.utils.JsonHelper;

public class MyExceptionHandler implements HandlerExceptionResolver {
    protected Logger log = LoggerFactory.getLogger(MyExceptionHandler.class);

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        log.error("{} error", request.getRequestURI(), ex);
        ModelAndView view = new ModelAndView("error");
        request.setAttribute("errorMsg", JsonHelper.toJson(error(ex)));

        return view;
    }

    protected Resp error(Throwable t) {
        GZException ge = null;
        if (t instanceof GZException) {
            ge = (GZException) t;
        } else {
            ge = new GZException();
            ge.setCode(Consts.Code.INTERNAL_ERROR);
            ge.setMsg("error");
        }
        Resp resp = new Resp(ge.getCode(), ge.getMsg());
        return resp;
    }
}