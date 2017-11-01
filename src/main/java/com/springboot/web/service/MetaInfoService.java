package com.springboot.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.web.model.MetaInfoModel;
import com.springboot.web.repository.MetaInformationDao;

@Service
public class MetaInfoService {

	@Autowired
	private MetaInformationDao metaInfoDao;
	
    public void updateRecords(MetaInfoModel metaInfo) {
    	metaInfoDao.save(metaInfo);
    }

}