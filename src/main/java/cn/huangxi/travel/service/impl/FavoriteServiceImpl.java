package cn.huangxi.travel.service.impl;

import cn.huangxi.travel.dao.FavoriteDao;
import cn.huangxi.travel.dao.impl.FavoriteDaoImpl;
import cn.huangxi.travel.domain.Favorite;
import cn.huangxi.travel.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {

    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    @Override
    public boolean isFavorite(String rid, int uid) {
        Favorite favorite = favoriteDao.findByRidAndUid(Integer.parseInt(rid), uid);

        return favorite != null ;
    }

    @Override
    public void add(String rid, int uid) {
        favoriteDao.add(Integer.parseInt(rid),uid);
    }
}
