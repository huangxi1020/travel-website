package cn.huangxi.travel.dao.impl;

import cn.huangxi.travel.dao.FavoriteDao;
import cn.huangxi.travel.domain.Favorite;
import cn.huangxi.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

public class FavoriteDaoImpl implements FavoriteDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Favorite findByRidAndUid(int rid, int uid) {
        Favorite favorite = null;
        try {
            String sql = "select * from tab_favorite where rid = ? and uid = ?";

            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);
        }catch (Exception e){
            e.printStackTrace();
        }
                return favorite;

            }

    @Override
    public int findCountByRid(int rid) {

        String sql = "select count(*) from tab_favorite where rid = ?";

        return template.queryForObject(sql,Integer.class,rid);
    }

    @Override
    public void add(int rid, int uid) {
        String sql = "insert into tab_favorite value(?,?,?)";

        template.update(sql,rid,new Date(),uid);
    }
}
