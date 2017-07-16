package org.qianrenxi.pms.service;

import org.qianrenxi.core.common.service.BaseService;
import org.qianrenxi.pms.entity.Task;
import org.qianrenxi.pms.repository.jpa.TaskJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService extends BaseService<Task, Long, TaskJpaRepository> {

}
