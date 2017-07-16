package org.qianrenxi.pms.service;

import org.qianrenxi.core.common.service.BaseService;
import org.qianrenxi.pms.entity.Requirement;
import org.qianrenxi.pms.repository.jpa.RequirementJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RequirementService extends BaseService<Requirement, Long, RequirementJpaRepository> {

}
