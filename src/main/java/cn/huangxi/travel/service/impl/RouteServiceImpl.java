package cn.huangxi.travel.service.impl;

import cn.huangxi.travel.dao.FavoriteDao;
import cn.huangxi.travel.dao.RouteDao;
import cn.huangxi.travel.dao.RouteImgDao;
import cn.huangxi.travel.dao.SellerDao;
import cn.huangxi.travel.dao.impl.FavoriteDaoImpl;
import cn.huangxi.travel.dao.impl.RouteDaoImpl;
import cn.huangxi.travel.dao.impl.RouteImgDaoImpl;
import cn.huangxi.travel.dao.impl.SellerDaoImpl;
import cn.huangxi.travel.domain.*;
import cn.huangxi.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {

private RouteDao routeDao= new RouteDaoImpl();

private RouteImgDao routeImgDao = new RouteImgDaoImpl();

private SellerDao sellerDao =  new SellerDaoImpl();

private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize,String rname) {
        PageBean <Route> pb = new PageBean<Route>();
        pb.setCurrentPage(currentPage);

        pb.setPageSize(pageSize);

        int totalCount = routeDao.findTotalCount(cid,rname);
        pb.setTotalCount(totalCount);

        int start = (currentPage - 1) * pageSize;
        List<Route> list = routeDao.findByPage(cid,start,pageSize,rname);
        pb.setList(list);


        int totalPage = totalCount%pageSize == 0 ?  totalCount/pageSize: totalCount/pageSize+1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    @Override
    public Route findOne(String rid) {
        Route route = routeDao.findOne(Integer.parseInt(rid));

        List<RouteImg> routeImgList = routeImgDao.findByCid(route.getRid());
        route.setRouteImgList(routeImgList);

        Seller seller = sellerDao.findById(route.getSid());
        route.setSeller(seller);

       int count  = favoriteDao.findCountByRid(route.getRid());
        route.setCount(count);

        return route;
    }


}
