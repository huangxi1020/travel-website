package cn.huangxi.travel.service.impl;

import cn.huangxi.travel.dao.CategoryDao;
import cn.huangxi.travel.dao.impl.CategoryDaoImpl;
import cn.huangxi.travel.domain.Category;
import cn.huangxi.travel.service.CategoryService;
import cn.huangxi.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();
    @Override
    public List<Category> findAll() {
        Jedis jedis = JedisUtil.getJedis();

        //Set<String> categorys = jedis.zrange("category", 0, -1);
        Set<Tuple> categorys = jedis.zrangeWithScores("category", 0, -1);

        List<Category> cs = null;

        if(categorys==null || categorys.size() == 0){
            cs = categoryDao.findAll();
            for(int i = 0;i <cs.size();i++){
                jedis.zadd("category",cs.get(i).getCid(),cs.get(i).getCname());
            }

        }else{
                cs = new ArrayList<Category>();
            for (Tuple tuple : categorys) {
                Category category = new Category();
                category.setCname(tuple.getElement());
                category.setCid((int)tuple.getScore());
                cs.add(category);
            }
        }

        return cs;
    }
}
