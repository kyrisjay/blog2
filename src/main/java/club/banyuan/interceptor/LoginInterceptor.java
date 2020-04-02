package club.banyuan.interceptor;

import club.banyuan.bean.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public  boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                              Object handle) throws IOException {
        //判断用户是否已经登录
        User user=(User)request.getSession().getAttribute("USER");
        if (user!=null){
            return true;
        }else {
            response.sendRedirect("/login?next=".concat(request.getRequestURI()));
            return false;
        }
    }
}
