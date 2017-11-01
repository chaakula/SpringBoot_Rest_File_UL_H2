package com.springboot.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.web.model.MetaInfoModel;

/**
 * Repository
 * @author Chandu A
 *
 */
public interface MetaInformationDao extends CrudRepository<MetaInfoModel, Long>{

}
