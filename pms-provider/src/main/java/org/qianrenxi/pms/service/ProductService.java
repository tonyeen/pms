package org.qianrenxi.pms.service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.qianrenxi.core.common.service.BaseService;
import org.qianrenxi.pms.entity.Product;
import org.qianrenxi.pms.repository.jpa.ProductJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseService<Product, Long, ProductJpaRepository> {

	@Override
	public Page<Product> findAll(Product entity, Pageable pageable) {
		return repository.findAll(getFindAllSpecification(entity), pageable);
	}
	
	private Specification<Product> getFindAllSpecification(final Product product) {
		return new Specification<Product>() {

			@Override
			public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return null;
			}
			
		};
	}
}
