package org.qianrenxi.core.system.setting.service;

import java.util.List;

import org.qianrenxi.core.common.service.BaseService;
import org.qianrenxi.core.system.setting.entity.DictGroup;
import org.qianrenxi.core.system.setting.repository.jpa.DictGroupRepository;
import org.springframework.stereotype.Service;

@Service
public class DictGroupService extends BaseService<DictGroup, Long, DictGroupRepository> {

	public DictGroup findDictGroupByIdentifier(String identifier){
		return repository.findDictGroupByIdentifier(identifier);
	}
	
	public List<DictGroup> findByVisible(){
		return repository.findByVisible(true);
	}
}
