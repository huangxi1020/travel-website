package cn.huangxi.travel.dao;

import cn.huangxi.travel.domain.RouteImg;

import java.util.List;

public interface RouteImgDao {

    public List<RouteImg> findByCid(int rid);
}
