package com.qwq.dao.implement;

import java.util.List;

/**
 * 公共类接口
 * @Author: QWQ
 * @Date: 2019.12.14 15:23
 * @Version: 1.0
 */
public interface publicDaoImplements <T> {
    public void saveOrUpdate(T obj);
    public void delete(T obj);
    public List<T> getAll(Integer page,Integer rows);
    public Integer getAllNum();
    public T get(Integer id);
}
