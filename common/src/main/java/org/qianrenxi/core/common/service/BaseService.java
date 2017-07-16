package org.qianrenxi.core.common.service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;

import org.qianrenxi.core.common.entity.Repairable;
import org.qianrenxi.core.common.repository.SupportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class BaseService<T, ID extends Serializable, R extends SupportRepository<T, ID>> implements CrudService<T, ID> {

	protected R repository;
	protected boolean isReparable = false;

	@SuppressWarnings("unchecked")
	public BaseService() {
		Class<T> clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.isReparable = Repairable.class.isAssignableFrom(clazz);
	}

	@Autowired
	public <S extends R> void setRepository(S repository) {
		this.repository = repository;
	}

	@Override
	public <S extends T> S save(S entity) {
		return repository.saveAndFlush(entity);
	}

	@Override
	public <S extends T> Iterable<S> save(Iterable<S> entities) {
		return repository.save(entities);
	}

	@Override
	public T findOne(ID id) {
		return repository.findOne(id);
	}

	@Override
	public T getOne(ID id) {
		return repository.findOne(id);
	}

	@Override
	public boolean exists(ID id) {
		return repository.exists(id);
	}

	@Override
	public Iterable<T> findAll() {
		return repository.findAll();
	}

	@Override
	public Iterable<T> findAll(Iterable<ID> ids) {
		return repository.findAll(ids);
	}

	@Override
	public long count() {
		return repository.count();
	}

	@Override
	public void delete(ID id) {
		delete(id, false);
	}

	@Override
	public void delete(List<ID> ids) {
		for (ID id : ids) {
			delete(id, false);
		}
	}

	@Override
	public void delete(ID[] ids) {
		delete(Arrays.asList(ids), false);
	}

	@Override
	public void delete(T entity) {
		delete(entity, false);
	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		delete(entities, false);
	}

	@Override
	public void deleteAll() {
		deleteAll(false);
	}

	@Override
	public <S extends T> Iterable<S> findAll(S entity) {
		return repository.findAll(Example.of(entity));
	}

	@Override
	public <S extends T> Page<S> findAll(S entity, Pageable pageable) {
		return repository.findAll(Example.of(entity), pageable);
	}

	@Override
	public void delete(ID id, boolean isPhysical) {
		if (isReparable && !isPhysical) {
			tagDelete(id);
		} else {
			repository.delete(id);
		}
	}

	@Override
	public void delete(List<ID> ids, boolean isPhysical) {
		for (ID id : ids) {
			delete(id, isPhysical);
		}
	}

	@Override
	public void delete(ID[] ids, boolean isPhysical) {
		delete(Arrays.asList(ids), isPhysical);
	}

	@Override
	public void delete(T entity, boolean isPhysical) {
		if (isReparable && !isPhysical) {
			tagDelete(entity);
		} else {
			repository.delete(entity);
		}
	}

	@Override
	public void delete(Iterable<? extends T> entities, boolean isPhysical) {
		if (isReparable && !isPhysical) {
			for (T entity : entities) {
				tagDelete(entity);
			}
		} else {
			repository.delete(entities);
		}
	}

	@Override
	public void deleteAll(boolean isPhysical) {
		if (isReparable && !isPhysical) {
			throw new RuntimeException("The Reparable class cannot be tag delete all");
		} else {
			repository.deleteAll();
		}
	}

	protected void tagDelete(ID id) {
		T entity = findOne(id);
		tagDelete(entity);
	}

	protected void tagDelete(T entity) {
		if (null == entity) {
			return;
		}

		if (isReparable) {
			try {
				((Repairable) entity).setIsDeleted(true);
				save(entity);
			} catch (ClassCastException e) {
				throw new RuntimeException("The given class " + entity.getClass() + " is not a Repairable("
						+ Repairable.class + ") class");
			}
		} else {
			throw new RuntimeException(
					"The given class " + entity.getClass() + " is not a Repairable(" + Repairable.class + ") class");
		}
	}

}
