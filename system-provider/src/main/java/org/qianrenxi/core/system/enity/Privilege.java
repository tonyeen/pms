package org.qianrenxi.core.system.enity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 权限
 * @author tony
 *
 */
@Entity
@Table(name="sys_privilege")
public class Privilege implements Serializable {
	private static final long serialVersionUID = -6844741783146113911L;
	
	@Id
	private Long id;
	private String identifier;
	private String name;
	private String targets;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTargets() {
		return targets;
	}
	public void setTargets(String targets) {
		this.targets = targets;
	}
}
