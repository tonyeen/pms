package org.qianrenxi.core.system.enity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.ConstraintMode;
import javax.persistence.EntityListeners;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 标记对象为可审计的
 * 
 * 创建和更新时，JPA会自动注入修改人和修改时间等审计信息
 * 
 * @author Tony
 *
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable implements Serializable {
	private static final long serialVersionUID = -6832522029134793266L;

	@CreatedBy
	@ManyToOne
	@JoinColumn(name = "created_by", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
	protected User createdBy;
	@CreatedDate
	protected Date createdDate;
	@LastModifiedBy
	@ManyToOne
	@JoinColumn(name = "last_modified_by", foreignKey = @ForeignKey(name = "none", value = ConstraintMode.NO_CONSTRAINT))
	protected User lastModifiedBy;
	@LastModifiedDate
	protected Date lastModifiedDate;

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public User getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(User lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
}
