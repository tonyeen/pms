package org.qianrenxi.core.system.enity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.qianrenxi.core.system.annotation.SystemResource;

/**
 * 站点
 * @author tony
 *
 */
@Entity
@Table(name="sys_site")
@SystemResource(name = "site")
public class Site extends Repairable {
	private static final long serialVersionUID = 2360932493937263142L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 短名称，编码，只能是英文、数字和下划线组成
	 */
	private String shortName;
	/**
	 * 父级站点
	 */
	private String description;
	
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Site Parent;
	
	// private String path;// 1, 2, 3
	// private String pathName; // Root Site, Seconde Site, Third Site

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

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public Site getParent() {
		return Parent;
	}

	public void setParent(Site parent) {
		Parent = parent;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
