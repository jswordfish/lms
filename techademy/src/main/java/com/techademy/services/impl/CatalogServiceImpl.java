package com.techademy.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;

import com.techademy.common.ExceptionCodes;
import com.techademy.common.LMSException;
import com.techademy.dao.CatalogDao;
import com.techademy.dao.JPADAO;
import com.techademy.domain.Badge;
import com.techademy.domain.Catalog;
import com.techademy.services.CatalogService;


@Component("catalogService")
@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=LMSException.class)
public class CatalogServiceImpl extends BaseServiceImpl<Long, Catalog> implements CatalogService{
	@Autowired
    protected CatalogDao dao;
	
	Logger logger = org.slf4j.LoggerFactory.getLogger(CatalogServiceImpl.class);
	
	@PostConstruct
    public void init() throws Exception {
	 super.setDAO((JPADAO) dao);
    }
    
    @PreDestroy
    public void destroy() {
    }
    
    @Override
    public void setEntityManagerOnDao(EntityManager entityManager){
    	dao.setEntityManager(entityManager);
    }

	@Override
	public void saveOrUpdate(Catalog catalog) throws LMSException {
		
		logger.info("In the method saveOrUpdate");
		/**
		 * step 0 - perform validations whether user object has all mandatory attributes
		 */
		if(catalog.getCatalogName() == null || catalog.getOrganizationIdentifier() == null) {
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		if(catalog.getCatalogName().trim().length() == 0 || catalog.getOrganizationIdentifier().trim().length() == 0) {
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		
		/**
		 * Check whether the group record actually exists
		 */
		
		/**
		 * Check whether the organization exists or not
		 */
		
		//step 1 FGigure out user is for save or update?
		Catalog catalog2 = getUniqueCatalog(catalog.getOrganizationIdentifier(),catalog.getCatalogName());
		//step 2
		//for save-create
		if(catalog2 == null) {
			//create
			dao.persist(catalog);
		}
		else {
			//update
			Mapper mapper = new DozerBeanMapper();
			catalog.setId(catalog2.getId());
			mapper.map(catalog, catalog2);
			dao.merge(catalog2);
		}
		
		
	}

	@Override
	public void delete(Long id) throws LMSException {
		
		Catalog catalog=dao.findById(id);
		dao.remove(catalog);
		
	}

	@Override
	public Catalog getUniqueCatalog(String orgIdentity, String catalogName) {
		
		logger.info("In the method getUniqueUser ");
		// TODO Auto-generated method stub
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("catalogName", catalogName);
		queryParams.put("organizationIdentifier", orgIdentity);
		
		List<Catalog> catalogNames = findByNamedQueryAndNamedParams(
				"Catalog.getUniqueCatalog", queryParams);
		if(catalogNames.size() > 1) {
			throw new LMSException(ExceptionCodes.MORE_THAN_ONE_UNIQUE_RECORD_FOUND);
		}
		
		if(catalogNames.size() == 0) {
			return null;
		}
		else {
			return catalogNames.get(0);
		}
	}

	@Override
	public Catalog findByOrganizationIdentifierAndCatalogName(String orgIdentity, String catalogName) 
	{
		logger.info("In the method findByOrganizationIdentifierAndCatalogName ");
		Catalog c=getUniqueCatalog(orgIdentity, catalogName);
		if(c!=null)
		{
			return c;
		}
		return null;
	}

	@Override
	public Catalog findByOrganizationIdentifier(String orgIdentity) {
		// TODO Auto-generated method stub
		logger.info("In the method findByOrganizationIdentifier ");
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("organizationIdentifier", orgIdentity);
		
		List<Catalog> catalog = findByNamedQueryAndNamedParams("Catalog.findByOrganizationIdentifier", queryParams);
		if(catalog.size() == 0) {
			return null;
		}
		else {
			return catalog.get(0);
		}
	}

	

	

	
    
    
	
	

}

