package org.qianrenxi.core.system.enity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="sys_condition")
public class Condition implements Serializable {
	private static final long serialVersionUID = -4805750702732196629L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name="site_id")
	private Site site;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
