package org.qianrenxi.core.system.service;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.qianrenxi.core.common.service.BaseService;
import org.qianrenxi.core.system.enity.Role;
import org.qianrenxi.core.system.enity.User;
import org.qianrenxi.core.system.repository.jpa.RoleRepository;
import org.qianrenxi.core.system.security.UserToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * ClassName: RoleService  
 * @author gujun    
 * date: 2017年7月10日 上午9:26:47 
 */
@Service
public class RoleService extends BaseService<Role, Long, RoleRepository>{

	
	/**
	 * findAllWithPage:分页条件查询.  
	 */
	public Page<Role> findAllWithPage(Role entity, Pageable pageable) {
		return repository.findAll((root,query,cb) -> {
			
			List<Predicate> list = new ArrayList<Predicate>();
			if (StringUtils.isNotEmpty(entity.getName())) {
				list.add(cb.like(root.get("name"), entity.getName()));
			}
			
			UserToken userToken = (UserToken) SecurityUtils.getSubject();
			if (userToken != null && userToken.getUser().getSite() != null) {
				list.add(cb.equal(root.get("site").get("id"), userToken.getUser().getSite().getId()));
			}
			return cb.and(list.toArray(new Predicate[list.size()]));
		}, pageable);
	}
	
	
	/**
	 * 测试批量保存自动填充siteId
	 * @param roles
	 */
	public List<Role> saveBatch(List<Role> roles){
		return repository.save(roles);
	}
	
}
