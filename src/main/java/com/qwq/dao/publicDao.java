package com.qwq.dao;

import com.qwq.dao.implement.publicDaoImplements;
import com.qwq.util.getSuperTypeUtil;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 账户操作Dao
 * @Author: QWQ
 * @Date: 2019.12.14 14:48
 * @Version: 1.0
 */
@Repository("publicDao")
public class publicDao<T> extends HibernateDaoSupport implements publicDaoImplements<T> {
    private Class  clazz;
    private String clazzName;
    public publicDao(){
        this.clazz = getSuperTypeUtil.getSuperType(this.getClass());
        this.clazzName  = this.clazz.getSimpleName();
    }
    @Autowired
    public void getSession( SessionFactory sessionFactory ){
        super.setSessionFactory( sessionFactory );
    }
    public void saveOrUpdate(T obj){
        this.getHibernateTemplate().saveOrUpdate( obj );
    }
    public void delete(T obj){
        this.getHibernateTemplate().delete( obj );
    }
    @SuppressWarnings("unchecked")
    public List<T> getAll(Integer page,Integer rows){
        return ( List<T> )this.getHibernateTemplate().find( this.clazzName );
    }
    public Integer getAllNum(){
        return ( Integer )this.getHibernateTemplate().find( "select count(*) from User" ).get( 0 );
    }
    @SuppressWarnings("unchecked")
    public T get( Integer id ) {
        return (T)super.getHibernateTemplate().get(this.clazz,id);
    }
}
