package cn.huangxi.travel.service;

import cn.huangxi.travel.domain.PageBean;
import cn.huangxi.travel.domain.Route;

public interface RouteService {
    public PageBean<Route> pageQuery(int cid,int currentPage,int pageSize,String ranem);

    public  Route findOne(String rid);
}
