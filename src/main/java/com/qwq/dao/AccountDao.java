package com.qwq.dao;

import com.qwq.entity.User;
import com.sun.org.apache.xml.internal.res.XMLErrorResources_tr;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账户Dao
 * @Author: QWQ
 * @Date: 2019.12.14 15:56
 * @Version: 1.0
 */
@Repository("accountDao")
public class AccountDao extends publicDao<User>{
    public User userLogin(String userId,String userPwd){
        //System.out.println("==========" + userId + "\t" + userPwd);
        return ( User )super.getHibernateTemplate().findByNamedParam("from User where userName=:userId and userPwd=:userPwd",new String[] { "userId","userPwd" },new String[] { userId,userPwd } ).get( 0 );
    }
}
