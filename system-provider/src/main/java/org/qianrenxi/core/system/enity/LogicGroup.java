package org.qianrenxi.core.system.enity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.qianrenxi.core.system.annotation.SystemResource;

@Entity
@Table(name="sys_logic_group")
@SystemResource(name = "logic_group")
public class LogicGroup extends Repairable {
	private static final long serialVersionUID = 581947956147157215L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	@ManyToOne
	@JoinColumn(name="site_id")
	private Site site;
	@OneToOne
	@JoinColumn(name="condition_id")
	private Condition condition;
	
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
	public Condition getCondition() {
		return condition;
	}
	public void setCondition(Condition condition) {
		this.condition = condition;
	}
}
