package org.qianrenxi.core.system.security;

import java.io.Serializable;

import org.qianrenxi.core.system.enity.Site;
import org.qianrenxi.core.system.enity.User;

public class UserToken implements Serializable {
	private static final long serialVersionUID = -1887595591691506217L;
	
	private User user;
	private Site currentSite;
	
	public UserToken(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Site getCurrentSite() {
		return currentSite;
	}
	public void setCurrentSite(Site currentSite) {
		this.currentSite = currentSite;
	}
	
	public String getUsername() {
		return user.getUsername();
	}
}
