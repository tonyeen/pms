package org.qianrenxi.core.system.service;

import org.qianrenxi.core.common.service.BaseService;
import org.qianrenxi.core.system.enity.Condition;
import org.qianrenxi.core.system.repository.jpa.ConditionRepository;
import org.springframework.stereotype.Service;

@Service
public class ConditionService extends BaseService<Condition, Long, ConditionRepository> {

}
