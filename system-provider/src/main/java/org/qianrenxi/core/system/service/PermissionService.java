package org.qianrenxi.core.system.service;

import org.qianrenxi.core.common.service.BaseService;
import org.qianrenxi.core.system.enity.Permission;
import org.qianrenxi.core.system.repository.jpa.PermissionRepository;
import org.springframework.stereotype.Service;

@Service
public class PermissionService extends BaseService<Permission, Long, PermissionRepository> {

}
