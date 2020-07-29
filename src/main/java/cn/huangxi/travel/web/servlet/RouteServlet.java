package cn.huangxi.travel.web.servlet;

import cn.huangxi.travel.domain.PageBean;
import cn.huangxi.travel.domain.Route;
import cn.huangxi.travel.domain.User;
import cn.huangxi.travel.service.FavoriteService;
import cn.huangxi.travel.service.RouteService;
import cn.huangxi.travel.service.impl.FavoriteServiceImpl;
import cn.huangxi.travel.service.impl.RouteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends  BaseServlet{
    private RouteService routeService=new RouteServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentPagestr = request.getParameter("currentPage");
        String pageSizestr = request.getParameter("pageSize");
        String cidstr = request.getParameter("cid");

        String rname = request.getParameter("rname");
        rname = new String(rname.getBytes("iso-8859-1"),"utf-8");

        int cid = 0;
        if(cidstr!=null&& cidstr.length()>0&& !"null".equals(cidstr)){
            cid = Integer.parseInt(cidstr);
        }
        int currentPage = 0;
        if(currentPagestr!=null&& currentPagestr.length()>0){
            currentPage = Integer.parseInt(currentPagestr);
        }else{
            currentPage = 1;
        }

        int pageSize = 0;
        if(pageSizestr !=null && pageSizestr.length()>0){
            pageSize = Integer.parseInt(pageSizestr);
        }else {
            pageSize= 5;
        }

        PageBean<Route> pb = routeService.pageQuery(cid, currentPage, pageSize,rname);

        writeValue(pb,response);
    }

    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");

         Route route =  routeService.findOne(rid);

         writeValue(route,response);

    }

    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String rid = request.getParameter("rid");

        User user =(User) request.getSession().getAttribute("user");
        int uid;
        if(user == null){
            uid= 0;
        }else{
            uid = user.getUid();
        }

        boolean flag  = favoriteService.isFavorite(rid, uid);

        writeValue(flag,response);
    }

    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        User user =(User) request.getSession().getAttribute("user");
        int uid;
        if(user == null){
           return;
        }else{
            uid = user.getUid();
        }

    favoriteService.add(rid,uid);

    }
    }
