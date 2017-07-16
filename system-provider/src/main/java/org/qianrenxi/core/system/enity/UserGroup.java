package org.qianrenxi.core.system.enity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;
import org.qianrenxi.core.system.annotation.SystemResource;

/**
 * 组织
 * @author tony
 *
 */
@Entity
@Table(name="sys_user_group")
@SystemResource(name = "user_group")
public class UserGroup extends Repairable {
	private static final long serialVersionUID = 8466543128145505442L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	@ManyToOne
	@JoinColumn(name = "site_id")
	private Site site;
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private UserGroup parent;
	
	// private String path;
	// private String pathName;
	@Formula("(select count(cug.id) from sys_user_group cug "
			+ "where cug.is_deleted = 0 and cug.parent_id = id)")
	private Long childrenCount;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
	public UserGroup getParent() {
		return parent;
	}
	public void setParent(UserGroup parent) {
		this.parent = parent;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Long getChildrenCount() {
		return childrenCount;
	}
	public void setChildrenCount(Long childrenCount) {
		this.childrenCount = childrenCount;
	}
}
