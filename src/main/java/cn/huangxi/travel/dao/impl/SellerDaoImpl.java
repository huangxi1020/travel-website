package cn.huangxi.travel.dao.impl;

import cn.huangxi.travel.dao.SellerDao;
import cn.huangxi.travel.domain.Seller;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class SellerDaoImpl implements SellerDao {
    private JdbcTemplate template = new JdbcTemplate();

    @Override
    public Seller findById(int id) {
        String sql = "select * from tab_seller where sid = ? ";


        return  template.queryForObject(sql,new BeanPropertyRowMapper<Seller>(Seller.class),id);

    }
}
