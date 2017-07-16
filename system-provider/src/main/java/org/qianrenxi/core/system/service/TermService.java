package org.qianrenxi.core.system.service;

import org.qianrenxi.core.common.service.BaseService;
import org.qianrenxi.core.system.enity.Term;
import org.qianrenxi.core.system.repository.jpa.TermRepository;
import org.springframework.stereotype.Service;

@Service
public class TermService extends BaseService<Term, Long, TermRepository> {

}
