package com.techademy.services.impl;


import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import com.techademy.common.LMSException;
import com.techademy.dao.JPADAO;
import com.techademy.services.BaseService;
public class BaseServiceImpl<K,E> implements BaseService{
	private JPADAO dao;

	protected Class<E> entityClass; 

	

		

		@SuppressWarnings("unchecked") 
		public BaseServiceImpl() { 
		    ParameterizedType genericSuperclass = (ParameterizedType) getClass() 
		            .getGenericSuperclass(); 
		    this.entityClass = (Class<E>) genericSuperclass 
		            .getActualTypeArguments()[1]; 
		} 
		
		public void setEntityManagerOnDao(EntityManager entityManager){
	    	dao.setEntityManager(entityManager);
	    	
	    }
		
		protected void setDAO(JPADAO<K, E> dAO){
			this.dao = dAO;
		}

		public E find(long id) throws LMSException {
			try{
			return (E) dao.findById(id);
			}
			catch(Exception e){
				throw new LMSException( entityClass.getName()+" - not found ", e);
			}
		}

		public List<E> findAll() throws LMSException {
			try{
				return dao.findAll();
			}
			catch(Exception e){
				throw new LMSException( entityClass.getName()+" - ", e);
			}
			
		}
	        
	        public List<E> find(int startFrom,int maxResults) throws LMSException {
	            try{
			return dao.find(startFrom, maxResults);
	            }
	            catch(Exception e){
			throw new LMSException( entityClass.getName()+" - ", e);
	            }
		}

		public void save(Object entity) throws LMSException {
			try{
			dao.persist(entity);
			}
			catch(Exception e){
				throw new LMSException( entityClass.getName()+" - ", e);
			}
			
		}

		public E update(Object entity) throws LMSException {
			try{
	                        dao.merge(entity);
	                        return (E) dao.flush(entity);
			}
			catch(Exception e){
				throw new LMSException( entityClass.getName()+" - ", e);
			}
		}

		public E saveOrUpdate(Object entity) throws LMSException {
			try{
				entity = dao.merge(entity);
	            return (E) dao.flush(entity);
			}
			catch(Exception e){
				throw new LMSException( entityClass.getName()+" - ", e);
			}
			
		}

		public void delete(long id) throws LMSException {
			E e = null;	
			try{
				e = find(id);
				if(e == null){
					throw new LMSException( entityClass.getName()+" - ");
				}
			}
			catch(Exception e1){
				throw new LMSException( entityClass.getName()+" - ", e1);
			}
			
			
			try{
				if(e != null){
					dao.remove(e);
	                                dao.flush(e);
				}
			}
			catch(Exception e1){
				throw new LMSException( entityClass.getName()+" - ", e1);
			}
			
			
		}

		public void deleteIfExisting(long id) throws Exception {
			try{
				E e = find(id);
					if(e != null){
						dao.remove(e);
	                                        dao.flush(e);
					}
			}
			catch(Exception e){
				throw new LMSException( entityClass.getName()+" - ", e);
			}
			
		}

		public List findByNamedQueryAndNamedParams(String queryName, Map params) {
			// TODO Auto-generated method stub
			return dao.findByNamedQueryAndNamedParams(queryName, params);
		}

		public List findByNamedQueryAndNamedParams(String queryName, Map params,
				int startFrom, int maxResults) {
			return dao.findByNamedQueryAndNamedParams(queryName, params, startFrom, maxResults);
		}

		public int recordCount(String name, Map params) {
			return dao.recordCount(name, params);
		}
		


		
	}

