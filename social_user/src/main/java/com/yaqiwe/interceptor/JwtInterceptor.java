package com.yaqiwe.interceptor;

import com.yaqiwe.exception.UserException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author yaqiwe
 * @Date 2020/3/20 16:16
 * @Version 1.0
 * Jwt的拦截器
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {
    @Autowired
    JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String hander=request.getHeader("Authorization");
        if(hander==null || hander.isEmpty()){
            //未找到token
            throw new UserException("请先登录");
        }else if(! hander.startsWith("Bearer ")){
            //token 不符合规则
            throw new UserException("令牌不正确");
        }else {
            String token=hander.substring(7);
            try {
                Claims claims = jwtUtil.parseJWT(token);
                request.setAttribute("userId",(String) claims.getId());
                request.setAttribute("userName",(String) claims.getSubject());
                String roles=(String) claims.get("roles");
                if(roles!=null && roles.equals("admin")){
                    request.setAttribute("claims_admin",roles);
                }
                if(roles!=null && roles.equals("user")){
                    request.setAttribute("claims_user",roles);
                }
                return true;
            }catch (Exception e){
                throw new UserException("登录过期请重新登录");
            }
        }
    }

}
