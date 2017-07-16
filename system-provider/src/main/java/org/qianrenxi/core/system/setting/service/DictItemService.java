package org.qianrenxi.core.system.setting.service;

import java.util.List;
import java.util.Map;

import org.qianrenxi.core.common.service.BaseService;
import org.qianrenxi.core.system.setting.entity.DictGroup;
import org.qianrenxi.core.system.setting.entity.DictItem;
import org.qianrenxi.core.system.setting.repository.jpa.DictItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beust.jcommander.internal.Lists;
import com.google.common.collect.Maps;

@Service
public class DictItemService extends BaseService<DictItem, Long, DictItemRepository> {

	@Autowired
	DictGroupService dictGroupService;
	DictItem findByKey(String key){
		return super.repository.findByKeyName(key);
	}
	
	List<DictItem> findByDictGroupIdentifier(String identifier){
		DictGroup dictGroup = new DictGroup();
		dictGroup.setIdentifier(identifier);
		return repository.findByDictGroup(dictGroup);
	}
	
	Map<String, String> findKeyValueByDictGroupIdentifier(String identifier) {
		List<DictItem> dictItems = findByDictGroupIdentifier(identifier);
		Map<String, String> kv = Maps.newHashMap();
		for(DictItem dictItem : dictItems){
			kv.put(dictItem.getKeyName(), dictItem.getValue());
		}
		return kv;
	}

	public Iterable<DictItem> save(Long groupId,DictItem[] dictItems) {
		DictGroup dictGroup = dictGroupService.findOne(groupId);
		List<DictItem> dictItemList = Lists.newArrayList();
		for(DictItem item : dictItems){
			if (item.getId() == null) {
				item.setDictGroup(dictGroup);
				dictItemList.add(item);
			} else {
				DictItem exist = findOne(item.getId());
				exist.setValue(item.getValue());
				dictItemList.add(exist);
			}
		}
		return save(dictItemList);
	}
}
