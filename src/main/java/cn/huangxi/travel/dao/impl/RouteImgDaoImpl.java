package cn.huangxi.travel.dao.impl;

import cn.huangxi.travel.dao.RouteImgDao;
import cn.huangxi.travel.domain.RouteImg;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class RouteImgDaoImpl implements RouteImgDao {
    private JdbcTemplate template = new JdbcTemplate();

    @Override
    public List<RouteImg> findByCid(int rid) {


        String sql = "select * from tab_route_img where rid = ? ";


       return  template.query(sql,new BeanPropertyRowMapper<RouteImg>(RouteImg.class),rid);
    }
}

