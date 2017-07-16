package org.qianrenxi.core.system.enity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Repairable extends Auditable implements org.qianrenxi.core.common.entity.Repairable {
	private static final long serialVersionUID = 7125062208353024592L;

	private Boolean isDeleted = false;

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}