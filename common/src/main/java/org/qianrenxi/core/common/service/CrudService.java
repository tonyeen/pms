package org.qianrenxi.core.common.service;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CrudService<T, ID extends Serializable> {

	/**
	 * Saves a given entity. Use the returned instance for further operations as
	 * the save operation might have changed the entity instance completely.
	 * 
	 * @param entity
	 * @return the saved entity
	 */
	public <S extends T> S save(S entity);
	
	/**
	 * Saves all given entities.
	 * 
	 * @param entities
	 * @return the saved entities
	 * @throws IllegalArgumentException
	 *             in case the given entity is {@literal null}.
	 */
	public <S extends T> Iterable<S> save(Iterable<S> entities);
	
	/**
	 * Retrieves an entity by its id.
	 * 
	 * @param id
	 *            must not be {@literal null}.
	 * @return the entity with the given id or {@literal null} if none found
	 * @throws IllegalArgumentException
	 *             if {@code id} is {@literal null}
	 */
	public T findOne(ID id);
	
	/**
	 * Returns a reference to the entity with the given identifier.
	 * 
	 * @param id must not be {@literal null}.
	 * @return a reference to the entity with the given identifier.
	 * @see EntityManager#getReference(Class, Object)
	 */
	public T getOne(ID id);
	
	/**
	 * Returns whether an entity with the given id exists.
	 * 
	 * @param id
	 *            must not be {@literal null}.
	 * @return true if an entity with the given id exists, {@literal false}
	 *         otherwise
	 * @throws IllegalArgumentException
	 *             if {@code id} is {@literal null}
	 */
	public boolean exists(ID id);
	
	/**
	 * Returns all instances of the type.
	 * 
	 * @return all entities
	 */
	public Iterable<T> findAll();
	
	/**
	 * Returns all instances of the type with the given IDs.
	 * 
	 * @param ids
	 * @return
	 */
	public Iterable<T> findAll(Iterable<ID> ids);
	
	/**
	 * Returns the number of entities available.
	 * 
	 * @return the number of entities
	 */
	public long count();
	
	/**
	 * Deletes the entity with the given id.
	 * 
	 * @param id
	 *            must not be {@literal null}.
	 * @throws IllegalArgumentException
	 *             in case the given {@code id} is {@literal null}
	 */
	public void delete(ID id);
	
	//public void delete(Iterable<ID> ids);
	public void delete(List<ID> ids);
	
	public void delete(ID[] ids);
	
	/**
	 * Deletes a given entity.
	 * 
	 * @param entity
	 * @throws IllegalArgumentException
	 *             in case the given entity is {@literal null}.
	 */
	public void delete(T entity);
	
	/**
	 * Deletes the given entities.
	 * 
	 * @param entities
	 * @throws IllegalArgumentException
	 *             in case the given {@link Iterable} is {@literal null}.
	 */
	public void delete(Iterable<? extends T> entities);
	
	/**
	 * Deletes all entities managed by the repository.
	 */
	public void deleteAll();
	
	public <S extends T> Iterable<S> findAll(S entity);
	
	public <S extends T> Page<S> findAll(S entity, Pageable pageable);
	
	/**
	 * Deletes the entity with the given id.
	 * 
	 * @param id
	 *            must not be {@literal null}.
	 * @param isPhysical 
	 * 			  default 'false' when the entity is reparable 
	 * 		and give 'true' for need deleted by physical.
	 * 		Otherwise it will no use, same as delete(T)
	 * @throws IllegalArgumentException
	 *             in case the given {@code id} is {@literal null}
	 */
	public void delete(ID id, boolean isPhysical);
	
	//public void delete(Iterable<ID> ids);
	public void delete(List<ID> ids, boolean isPhysical);
	
	public void delete(ID[] ids, boolean isPhysical);
	
	/**
	 * Deletes a given entity.
	 * 
	 * @param entity
	 * @param isPhysical
	 * @throws IllegalArgumentException
	 *             in case the given entity is {@literal null}.
	 */
	public void delete(T entity, boolean isPhysical);
	
	/**
	 * Deletes the given entities.
	 * 
	 * @param entities
	 * @param isPhysical
	 * @throws IllegalArgumentException
	 *             in case the given {@link Iterable} is {@literal null}.
	 */
	public void delete(Iterable<? extends T> entities, boolean isPhysical);
	
	/**
	 * Deletes all entities managed by the repository.
	 * 
	 * @param isPhysical
	 */
	public void deleteAll(boolean isPhysical);
}
