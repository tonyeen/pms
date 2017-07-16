package org.qianrenxi.core.system.setting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.qianrenxi.core.system.enity.Auditable;

/**
 * 系统数据字典分组表
 * 国际化配置通用key为<code>system.setting.dict.group.</code>
 * 
 * @author jiawei
 *
 */
@Entity
@Table(name="sys_dict_group")
public class DictGroup extends Auditable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	/**
	 * 数据字典分组名称
	 */
	@Column(nullable = false)
	private String name;
	/**
	 * 数据字典分组唯一标识
	 */
	@Column(nullable = false,unique = true)
	private String identifier;
	/**
	 * 数据字典是否可见，决定是否在系统中显示
	 */
	private boolean visible;
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
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
