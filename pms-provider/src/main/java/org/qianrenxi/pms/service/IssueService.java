package org.qianrenxi.pms.service;

import org.qianrenxi.core.common.service.BaseService;
import org.qianrenxi.pms.entity.Issue;
import org.qianrenxi.pms.repository.jpa.IssueJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class IssueService extends BaseService<Issue, Long, IssueJpaRepository> {

}
