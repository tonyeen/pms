package org.qianrenxi.core.system.setting.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.qianrenxi.core.system.enity.Auditable;

/**
 * 系统数据字典属性表
 * 国际化配置通用key为<code>system.setting.dict.item.</code>
 * 
 * @author jiawei
 *
 */
@Entity
@Table(name="sys_dict_item")
public class DictItem extends Auditable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	/**
	 * 数据字典属性名称，找不到国际化信息时默认显示
	 */
	@Column(nullable = false)
	private String name;
	@Column()
	private String value;
	/**
	 * 数据字典属性唯一标识
	 */
	@Column(nullable = false,unique = true)
	private String keyName;
	@ManyToOne
	@JoinColumn(name = "dict_group_id")
	private DictGroup dictGroup;
	
	
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
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getKeyName() {
		return keyName;
	}
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	public DictGroup getDictGroup() {
		return dictGroup;
	}
	public void setDictGroup(DictGroup dictGroup) {
		this.dictGroup = dictGroup;
	}
	
	
}
