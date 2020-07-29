package cn.huangxi.travel.web.servlet;

import cn.huangxi.travel.domain.ResultInfo;
import cn.huangxi.travel.domain.User;
import cn.huangxi.travel.service.UserService;
import cn.huangxi.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {

private UserService service = new UserServiceImpl();


    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();

        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //UserService service = new UserServiceImpl();
        User u= service.login(user);

        ResultInfo info = new ResultInfo();

        if(u == null){
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        if(u != null && !"Y".equals(u.getStatus())){
            info.setFlag(false);
            info.setErrorMsg("您尚未激活请登录邮箱激活");
        }
        if(u != null && "Y".equals(u.getStatus())){
           request.getSession().setAttribute("user",u);
        }
        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        User user = new User();

        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

       // UserService service = new UserServiceImpl();
        User u= service.login(user);

        ResultInfo info = new ResultInfo();

        if(u == null){
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        if(u != null && !"Y".equals(u.getStatus())){
            info.setFlag(false);
            info.setErrorMsg("您尚未激活请登录邮箱激活");
        }
        if(u != null && "Y".equals(u.getStatus())){
            info.setFlag(true);
        }
        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }


    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object user = request.getSession().getAttribute("name");


        ObjectMapper mapper =new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),user);

    }

    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();

        response.sendRedirect(request.getContextPath()+"/login.html");

    }

    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");

        if(code != null){
           // UserService service =new UserServiceImpl();
            boolean flag = service.active(code);

            String msg = null;
            if(flag){
                msg = "激活成功，请登录<a href='login.html'>登录</a>";
            }else{
                msg = "激活失败，请联系管理员";
            }
            response.setContentType("text/html;charset = utf-8");
            response.getWriter().write(msg);
        }

    }


}
