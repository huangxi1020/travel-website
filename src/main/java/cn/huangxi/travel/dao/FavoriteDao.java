package cn.huangxi.travel.dao;

import cn.huangxi.travel.domain.Favorite;

public interface FavoriteDao {
    public Favorite findByRidAndUid(int rid, int uid);

    int findCountByRid(int rid);

    void add(int rid, int uid);
}
