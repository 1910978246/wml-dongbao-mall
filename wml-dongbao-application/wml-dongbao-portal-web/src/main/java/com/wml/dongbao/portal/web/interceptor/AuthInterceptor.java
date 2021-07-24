package com.wml.dongbao.portal.web.interceptor;

import com.wml.dongbao.common.base.TokenException;
import com.wml.dongbao.common.base.annotations.TokenCheck;
import com.wml.dongbao.common.util.JwtUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @Auther: 王明礼
 * @Date: 2021/7/17 - 07 - 17 - 23:38
 * @Description: com.wml.dongbao.portal.web.interceptor
 * @version: 1.0
 */
//解析注解

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("拦截器进入");//开发时不能写

        String token = request.getHeader("token");//拿到token
        if (StringUtils.isBlank(token))
        {

            //throw new LoginException("token 为空");
            throw new TokenException("token 为空");
            // return false;

        }

        HandlerMethod handlerMethod = (HandlerMethod)handler;
        Method method = handlerMethod.getMethod();

        if (method.isAnnotationPresent(TokenCheck.class))
        {
            TokenCheck annotation = method.getAnnotation(TokenCheck.class);
            if (annotation.required())
            {
                //校验token
               try
               {
                   JwtUtil.parseToken(token);

               }catch(Exception e)
               {
                   //throw new LoginException("token 异常");
                   throw new TokenException("token 异常");
                   //return false;
               }
               return true;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
