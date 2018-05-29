package com.techademy.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import com.techademy.common.ExceptionCodes;
import com.techademy.common.LMSException;
import com.techademy.dao.JPADAO;
import com.techademy.dao.PointMatrixAtCourseModuleLevelDao;
import com.techademy.domain.PointMatrix;
import com.techademy.domain.PointMatrixAtCourseModuleLevel;
import com.techademy.services.PointMatrixAtCourseModuleLevelService;

@Service("pointMatrixAtCourseModuleLevelService")
@org.springframework.transaction.annotation.Transactional(propagation= Propagation.REQUIRED, rollbackFor=LMSException.class)
public class PointMatrixAtCourseModuleLevelServiceImpl extends BaseServiceImpl<Long, PointMatrixAtCourseModuleLevel> implements PointMatrixAtCourseModuleLevelService{
	@Autowired
    protected PointMatrixAtCourseModuleLevelDao dao;
	
	Logger logger = org.slf4j.LoggerFactory.getLogger(PointMatrixAtCourseModuleLevelServiceImpl.class);
	

	
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

  /*  @Autowired
    protected EntityManager em;
    */
    
    
	@Override
	public void saveOrUpdate(PointMatrixAtCourseModuleLevel notes) throws LMSException {
		logger.info("In Method of save-update Of PMCML");
		
		if(notes.getOrganizationIdentifier()==null || notes.getCourseIdentifier()==null || notes.getModuleName()==null)
		{
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		
		if(notes.getOrganizationIdentifier().trim().length()==0 || notes.getCourseIdentifier().trim().length()==0 || notes.getModuleName().trim().length()==0)
		{
			throw new LMSException(ExceptionCodes.MISSING_MANDATORY_PARAMS);
		}
		
		PointMatrixAtCourseModuleLevel courseModuleLevel=getUniquePointMatrixCModuleLevel(notes.getOrganizationIdentifier(), notes.getCourseIdentifier(),notes.getModuleName());
		
		if(courseModuleLevel==null)
		{
			dao.persist(notes);
		}
		else
		{
			Mapper mapper=new DozerBeanMapper();
			notes.setId(courseModuleLevel.getId());
			mapper.map(notes,courseModuleLevel);
			dao.merge(courseModuleLevel);
		}
	}

	@Override
	public void delete(Long id) throws LMSException {
		// TODO Auto-generated method stub
		
		PointMatrixAtCourseModuleLevel atCourseModuleLevel=dao.findById(id);
		dao.remove(atCourseModuleLevel);
		
	}

	@Override
	public PointMatrixAtCourseModuleLevel getUniquePointMatrixCModuleLevel(String organizationIdentifier, String courseIdentifier,String moduleName) 
	{
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("organizationIdentifier", organizationIdentifier);
		queryParams.put("courseIdentifier", courseIdentifier);
		queryParams.put("moduleName",moduleName);
		
		List<PointMatrixAtCourseModuleLevel> courseModuleLevels = findByNamedQueryAndNamedParams("PointMatrixAtCourseModuleLevel.getUniquePointMatrixCModuleLevel", queryParams);
		if(courseModuleLevels.size() > 1) {
			throw new LMSException(ExceptionCodes.MORE_THAN_ONE_UNIQUE_RECORD_FOUND);
		}
		
		if(courseModuleLevels.size() == 0) {
			return null;
		}
		else {
			return courseModuleLevels.get(0);
		}
	}


	public PointMatrixAtCourseModuleLevel findByOrganizationIdentifier(String organizationIdentifier) {
		
		// TODO Auto-generated method stub
		PointMatrixAtCourseModuleLevel atCourseModuleLevel=getUniquePointMatrixCModuleLevelByOrganizationIdentifier(organizationIdentifier);
		if(atCourseModuleLevel!=null)
		{
			return atCourseModuleLevel;
		}
		
		return null;
	}

	@Override
	public PointMatrixAtCourseModuleLevel getUniquePointMatrixCModuleLevelByOrganizationIdentifier(
			String organizationIdentifier) {
		// TODO Auto-generated method stub
		Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put("organizationIdentifier", organizationIdentifier);
		
		List<PointMatrixAtCourseModuleLevel> courseModuleLevels = findByNamedQueryAndNamedParams("PointMatrixAtCourseModuleLevel.getUniquePointMatrixCModuleLevelByOrganizationIdentifier", queryParams);
		if(courseModuleLevels.size() == 0) {
			return null;
		}
		else {
			return courseModuleLevels.get(0);
		}
	}

	

		@Override
		public List<PointMatrixAtCourseModuleLevel> findAll() 
		{
			return dao.findAll();
		}
	
	

	
	
    
	
	

}


