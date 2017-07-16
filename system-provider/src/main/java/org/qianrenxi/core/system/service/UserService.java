package org.qianrenxi.core.system.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.qianrenxi.core.common.service.BaseService;
import org.qianrenxi.core.system.enity.User;
import org.qianrenxi.core.system.repository.jpa.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User, Long, UserRepository> {

	@Override
	public Page<User> findAll(User entity, Pageable pageable) {

		return repository.findAll(getFindAllSpecification(entity), pageable);
	}

	protected <S extends User> Specification<S> getFindAllSpecification(final S user) {
		return new Specification<S>() {

			@Override
			public Predicate toPredicate(Root<S> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.equal(root.get("site").get("id"), user.getSite().getId());

				if (null != user.getUsername()) {
					Predicate ul = cb.like(root.get("username"), "%" + user.getUsername() + "%");
					Predicate un = cb.like(root.get("displayName"), "%" + user.getUsername() + "%");
					predicate = cb.and(predicate, cb.or(ul, un));
				}

				if (null != user.getUserGroup()) {
					predicate = cb.and(predicate,
							cb.equal(root.get("userGroup").get("id"), user.getUserGroup().getId()));
				}

				predicate = cb.and(predicate, cb.isFalse(root.get("isDeleted")));

				return predicate;
			}

		};
	}

	public User findByUsername(String username) {
		return repository.findByUsername(username);
	}

}
