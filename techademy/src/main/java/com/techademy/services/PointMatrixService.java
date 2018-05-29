package com.techademy.services;

import java.util.List;

import com.techademy.common.LMSException;
import com.techademy.domain.Organization;
import com.techademy.domain.PointMatrix;

public interface PointMatrixService extends BaseService{

	public void saveOrUpdate(PointMatrix pointMatrix) throws LMSException;
	
	public PointMatrix getUniquePointMatrix(String orgIdentity,String pointMatrixFor,String identifier);
	
	public PointMatrix getByOrganizationIdentifier(String organizationIdentifier);
	
	public PointMatrix findByOrganizationIdentifierAndPointMatrixForAndIdentifier(String organizationIdentifier,
			String pointMatrixFor,String identifier) ;
	
	public PointMatrix findByOrganizationIdentifier(String organizationIdentifier) ;
	
	public List<PointMatrix> findAll();
	
	 public void delete(Long id) throws LMSException;
	 
	
}
