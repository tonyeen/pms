package org.qianrenxi.core.system.enity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="sys_term")
public class Term extends Auditable {
	private static final long serialVersionUID = -1553180691737032493L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name="site_id")
	private Site site;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	@ManyToOne
	@JoinColumn(name="user_group_id")
	private UserGroup userGroup;
	@ManyToOne
	@JoinColumn(name="logicGroup")
	private LogicGroup logicGroup;
	@ManyToOne
	@JoinColumn(name="condition_id")
	private Condition condition;
}
