package org.qianrenxi.pms.service;

import org.qianrenxi.core.common.service.BaseService;
import org.qianrenxi.pms.entity.Activity;
import org.qianrenxi.pms.repository.jpa.ActivityJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class ActivityService extends BaseService<Activity, Long, ActivityJpaRepository> {

}
