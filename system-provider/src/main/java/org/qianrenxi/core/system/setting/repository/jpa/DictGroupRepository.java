package org.qianrenxi.core.system.setting.repository.jpa;

import java.util.List;

import org.qianrenxi.core.common.repository.SupportRepository;
import org.qianrenxi.core.system.setting.entity.DictGroup;

public interface DictGroupRepository extends SupportRepository<DictGroup, Long> {

	DictGroup findDictGroupByIdentifier(String identifier);

	List<DictGroup> findByVisible(boolean visible);

}
