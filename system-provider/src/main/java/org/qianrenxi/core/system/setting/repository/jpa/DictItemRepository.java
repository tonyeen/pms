package org.qianrenxi.core.system.setting.repository.jpa;

import java.util.List;

import org.qianrenxi.core.common.repository.SupportRepository;
import org.qianrenxi.core.system.setting.entity.DictGroup;
import org.qianrenxi.core.system.setting.entity.DictItem;

public interface DictItemRepository extends SupportRepository<DictItem, Long> {
	/**
	 * 根据键值查找{@link DictItem}
	 * @param key 键值
	 * @return DictItem
	 */
	DictItem findByKeyName(String key);

	List<DictItem> findByDictGroup(DictGroup dictGroup);
}
