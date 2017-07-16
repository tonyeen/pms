package org.qianrenxi.pms.service;

import org.qianrenxi.core.common.service.BaseService;
import org.qianrenxi.pms.entity.Module;
import org.qianrenxi.pms.repository.jpa.ModuleJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ModuleService extends BaseService<Module, Long, ModuleJpaRepository> {

}
