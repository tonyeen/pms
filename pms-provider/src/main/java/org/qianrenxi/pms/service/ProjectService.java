package org.qianrenxi.pms.service;

import org.qianrenxi.core.common.service.BaseService;
import org.qianrenxi.pms.entity.Project;
import org.qianrenxi.pms.repository.jpa.ProjectJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ProjectService extends BaseService<Project, Long, ProjectJpaRepository> {

}
