package org.qianrenxi.pms.service;

import org.qianrenxi.core.common.service.BaseService;
import org.qianrenxi.pms.entity.Plan;
import org.qianrenxi.pms.repository.jpa.PlanJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PlanService extends BaseService<Plan, Long, PlanJpaRepository> {

}
