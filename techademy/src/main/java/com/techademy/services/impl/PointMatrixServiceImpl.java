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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.techademy.common.ExceptionCodes;
import com.techademy.common.LMSException;
import com.techademy.dao.JPADAO;
import com.techademy.dao.PointMatrixDao;
import com.techademy.domain.PointMatrix;

import com.techademy.services.PointMatrixService;

@Service("pointMatrixService")
@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=LMSException.class)
public class PointMatrixServiceImpl extends BaseServiceImpl<Long, PointMatrix> implements PointMatrixService{
	@Autowired
    protected PointMatrixDao dao;
	
	Logger logger = org.slf4j.LoggerFactory.getLogger(PointMatrixServiceImpl.class);
	
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
	public void saveOrUpdate(PointMatrix pointMatrix) throws LMSException {
		// TODO Auto-generated method stub
		logger.info("IN SAVE-UPDATE METHOD");
		
		if(pointMatrix.getPointMatrixFor()== null || pointMatrix.getOrganizationIdentifier() == null || pointMatrix.getIdentifier() == null)
		{
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		
		if(pointMatrix.getPointMatrixFor().trim().length()==0 || pointMatrix.getOrganizationIdentifier().trim().length()==0 || pointMatrix.getIdentifier().trim().length()==0 )
		{
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		
		PointMatrix pointMatrix2=getUniquePointMatrix(pointMatrix.getPointMatrixFor(),pointMatrix.getOrganizationIdentifier(),pointMatrix.getIdentifier());
		
		if(pointMatrix2==null)
		{
			dao.persist(pointMatrix);
		}
		else
		{
			Mapper mapper=new DozerBeanMapper();
			pointMatrix.setId(pointMatrix2.getId());
			mapper.map(pointMatrix,pointMatrix2);
			dao.merge(pointMatrix2);
		}
	}
	
	@Override
	public void delete(Long id) throws LMSException {
		// TODO Auto-generated method stub
		PointMatrix matrix=dao.findById(id);
		dao.remove(matrix);
		
	}

	@Override
	public PointMatrix getUniquePointMatrix(String orgIdentity, String pointMatrixFor, String identifier) {
		// TODO Auto-generated method stub
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("pointMatrixFor",pointMatrixFor );
		queryParams.put("organizationIdentifier", orgIdentity);
		queryParams.put("identifier",identifier);
		
		List<PointMatrix> pointMatrixs = findByNamedQueryAndNamedParams("PointMatrix.getUniquePointMatrix", queryParams);
		if(pointMatrixs.size() > 1) {
			throw new LMSException(ExceptionCodes.MORE_THAN_ONE_UNIQUE_RECORD_FOUND);
		}
		
		if(pointMatrixs.size() == 0) {
			return null;
		}
		else {
			return pointMatrixs.get(0);
		}
	}

	@Override
	public PointMatrix findByOrganizationIdentifierAndPointMatrixForAndIdentifier(String organizationIdentifier,
			String pointMatrixFor, String identifier) {
		// TODO Auto-generated method stub
		PointMatrix matrix=getUniquePointMatrix(organizationIdentifier, pointMatrixFor, identifier);
		if(matrix!=null)
		{
			return matrix;
		}
		return null;
	}

	@Override
	public PointMatrix findByOrganizationIdentifier(String organizationIdentifier) {
		// TODO Auto-generated method stub
		PointMatrix matrix=getByOrganizationIdentifier(organizationIdentifier);
		if(matrix!=null)
		{
			return matrix;
		}
		return null;
	}

	@Override
	public PointMatrix getByOrganizationIdentifier(String organizationIdentifier) {
		// TODO Auto-generated method stub
		
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("organizationIdentifier", organizationIdentifier);
		List<PointMatrix> pointMatrixs = findByNamedQueryAndNamedParams("PointMatrix.getByOrganizationIdentifier", queryParams);
		return pointMatrixs.get(0);
	}


	@Override
	public List<PointMatrix> findAll()
	{
		return dao.findAll();
	}
	
}
	

	
	
	
	

	

    
	
	

