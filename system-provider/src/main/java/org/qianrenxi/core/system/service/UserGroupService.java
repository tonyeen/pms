package org.qianrenxi.core.system.service;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.qianrenxi.core.common.service.BaseService;
import org.qianrenxi.core.system.enity.Site;
import org.qianrenxi.core.system.enity.UserGroup;
import org.qianrenxi.core.system.repository.jpa.UserGroupRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class UserGroupService extends BaseService<UserGroup, Long, UserGroupRepository> {
	
	@Override
	public void delete(Long id) {
		// super.delete(id);
		UserGroup userGroup = getOne(id);
		userGroup.setIsDeleted(true);
		save(userGroup);
	}
	
	public List<UserGroup> getRoots(Site site){
		return repository.findAll(getRootsSpecification(site.getId()));
	}
	
	public List<UserGroup> getChildren(Site site, Long parentId){
		return repository.findAll(getChildrenSpecification(site.getId(), parentId));
	}
	
	protected Specification<UserGroup> getRootsSpecification(final Long siteId){
		return new Specification<UserGroup>() {

			@Override
			public Predicate toPredicate(Root<UserGroup> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.equal(root.get("site").get("id"), siteId);
				predicate = cb.and(predicate, cb.isNull(root.get("parent")));
				predicate = cb.and(predicate, cb.equal(root.get("isDeleted"), false));
				return predicate;
			}
		
		};
	}
	
	protected Specification<UserGroup> getChildrenSpecification(final Long siteId, final Long parentId){
		return new Specification<UserGroup>() {
			
			@Override
			public Predicate toPredicate(Root<UserGroup> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.equal(root.get("site").get("id"), siteId);
				predicate = cb.and(predicate, cb.equal(root.get("parent").get("id"), parentId));
				predicate = cb.and(predicate, cb.equal(root.get("isDeleted"), false));
				return predicate;
			}
			
		};
	}
}
