package com.techademy.services;

import com.techademy.common.LMSException;
import com.techademy.domain.Catalog;

public interface CatalogService  extends BaseService{

	public void saveOrUpdate(com.techademy.domain.Catalog catalog) throws LMSException;

	public Catalog getUniqueCatalog(String orgIdentity, String catalogName);

	public void delete(Long id) throws LMSException;

	public Catalog findByOrganizationIdentifierAndCatalogName(String orgIdentity, String catalogName);

	public Catalog findByOrganizationIdentifier(String orgIdentity);


}
