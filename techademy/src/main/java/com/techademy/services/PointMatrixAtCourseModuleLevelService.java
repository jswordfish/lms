package com.techademy.services;

import java.util.List;

import com.techademy.common.LMSException;
import com.techademy.domain.PointMatrixAtCourseModuleLevel;

public interface PointMatrixAtCourseModuleLevelService extends BaseService{

	public void saveOrUpdate(PointMatrixAtCourseModuleLevel pointMatrix) throws LMSException;
	
	public PointMatrixAtCourseModuleLevel getUniquePointMatrixCModuleLevel(String organizationIdentifier,String courseIdentifier,String moduleName);
	
	public PointMatrixAtCourseModuleLevel getUniquePointMatrixCModuleLevelByOrganizationIdentifier(String organizationIdentifier);
	
	public List<PointMatrixAtCourseModuleLevel> findAll();
	
	public void delete(Long id) throws LMSException;
	 
	
}
