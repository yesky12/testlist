package com.leo.test.cglib;

/**
 * 
 * @author lin.gong
 *
 */
public interface PersistenceService {
	public void save(long id,String data);
	public String load(long id);
}
