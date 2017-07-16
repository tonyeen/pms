package org.qianrenxi.pms.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.qianrenxi.core.system.enity.Repairable;
import org.qianrenxi.core.system.enity.User;

@Entity
@Table(name="pms_product")
public class Product extends Repairable {
	private static final long serialVersionUID = 990525242084577517L;
	
	public enum ProductType {
		NORMAL,
		MULTI_BRANCH,
		MULTI_PLATFORM;
	}
	
	public enum ProductStatus {
		ACTIVE,
		CLOSED
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String name;
	private String code;
	private String description;
	@Enumerated(EnumType.STRING)
	private ProductType type;
	@Enumerated(EnumType.STRING)
	private ProductStatus status;
	
	@ManyToOne
	@JoinColumn(name="product_leader_id")
	private User productLeader;
	@ManyToOne
	@JoinColumn(name="test_leader_id")
	private User testLeader;
	@ManyToOne
	@JoinColumn(name="publish_leader_id")
	private User publishLeader;
	
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ProductStatus getStatus() {
		return status;
	}
	public void setStatus(ProductStatus status) {
		this.status = status;
	}
	public ProductType getType() {
		return type;
	}
	public void setType(ProductType type) {
		this.type = type;
	}
	public User getProductLeader() {
		return productLeader;
	}
	public void setProductLeader(User productLeader) {
		this.productLeader = productLeader;
	}
	public User getTestLeader() {
		return testLeader;
	}
	public void setTestLeader(User testLeader) {
		this.testLeader = testLeader;
	}
	public User getPublishLeader() {
		return publishLeader;
	}
	public void setPublishLeader(User publishLeader) {
		this.publishLeader = publishLeader;
	}
}
