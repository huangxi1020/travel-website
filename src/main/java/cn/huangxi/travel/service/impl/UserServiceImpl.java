package cn.huangxi.travel.service.impl;

import cn.huangxi.travel.dao.UserDao;
import cn.huangxi.travel.dao.impl.UserDaoImpl;
import cn.huangxi.travel.domain.User;
import cn.huangxi.travel.service.UserService;
import cn.huangxi.travel.util.MailUtils;
import cn.huangxi.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {
private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean regist(User user) {
        User u = userDao.findByUsername(user.getUsername());

        if(u != null){
            return false;
        }

        user.setCode(UuidUtil.getUuid());

        user.setStatus("N");

        userDao.save(user);

        String content ="<a href='http://localhost/travel/user/active?code="+user.getCode()+"'>点击激活【黑马旅游网】</a>";

        MailUtils.sendMail(user.getEmail(),content,"激活邮件");
        return true;
    }

    @Override
    public boolean active(String code) {
      User user = userDao.findByCode(code);
    if(user != null){
        userDao.updateStatus(user);
        return true;
    }else {
        return  false;
    }
        }

    @Override
    public User login(User user) {
        return  userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
