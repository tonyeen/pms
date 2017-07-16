package org.qianrenxi.core.system.enity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 授权 等同于 老产品 user_role
 * @author tony
 *
 */
@Entity
@Table(name="sys_permission")
public class Permission extends Auditable{

	private static final long serialVersionUID = -4045959585232525765L;
	
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
	@JoinColumn(name="logic_group_id")
	private LogicGroup logicGroup;
	@ManyToOne
	@JoinColumn(name="role_id")
	private Role role;
	@ManyToOne
	@JoinColumn(name="privilege_id")
	private Privilege privilege;
	
	// TODO: Target and target id
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserGroup getUserGroup() {
		return userGroup;
	}
	public void setUserGroup(UserGroup userGroup) {
		this.userGroup = userGroup;
	}
	public LogicGroup getLogicGroup() {
		return logicGroup;
	}
	public void setLogicGroup(LogicGroup logicGroup) {
		this.logicGroup = logicGroup;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Privilege getPrivilege() {
		return privilege;
	}
	public void setPrivilege(Privilege privilege) {
		this.privilege = privilege;
	}

}
