package hqu.dzr.graduation.conf.web_mvc_conf;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截器");
        Object loginUser = request.getSession().getAttribute("loginUser");
        System.out.println("拦截器内的 loginUser = " + loginUser);
        if (loginUser == null) {
            System.out.println("!!!!!!!!!");
//            request.getSession().setAttribute("msg1", "请重新登录!!!");
            request.setAttribute("msg", "请重新登录!!!");
            request.getRequestDispatcher("/").forward(request, response);
//            response.sendRedirect("/");
            return false;
        }

        return true;
    }
}
