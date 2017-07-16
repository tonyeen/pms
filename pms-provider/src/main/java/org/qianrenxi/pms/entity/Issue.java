package org.qianrenxi.pms.entity;

import java.util.Date;

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
@Table(name = "pms_issue")
public class Issue extends Repairable {
	private static final long serialVersionUID = 8767012619506269902L;
	
	/*public enum IssueType {
		
	}*/

	public enum IssueStatus {
		ACTIVE,
		RESOLVED,
		CLOSED
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String description;
	
	// @Enumerated(EnumType.STRING)
	private String type;
	private Integer severity;
	private Integer priority;
	
	@Enumerated(EnumType.STRING)
	private IssueStatus status;
	private Boolean affirmed;
	
	@ManyToOne
	private User assignedTo;
	private Date deadline;
	
	private String keywords;
	private String platform;
	private String browser;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	@ManyToOne
	@JoinColumn(name="module_id")
	private Module module;
	@ManyToOne
	@JoinColumn(name="plan_id")
	private Plan plan;
	
	@ManyToOne
	@JoinColumn(name="project_id")
	private Project project;
	@ManyToOne
	@JoinColumn(name="requirement_id")
	private Requirement requirement;
	@ManyToOne
	@JoinColumn(name="task_id")
	private Task task;
	// private Build effectBuild;
	
	@ManyToOne
	private User resolvedBy;
	private Date resolvedDate;
	// private Build resolvedBuild;
	private String resolution;
	private String remark;
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getSeverity() {
		return severity;
	}
	public void setSeverity(Integer severity) {
		this.severity = severity;
	}
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public IssueStatus getStatus() {
		return status;
	}
	public void setStatus(IssueStatus status) {
		this.status = status;
	}
	public Boolean getAffirmed() {
		return affirmed;
	}
	public void setAffirmed(Boolean affirmed) {
		this.affirmed = affirmed;
	}
	public User getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Module getModule() {
		return module;
	}
	public void setModule(Module module) {
		this.module = module;
	}
	public Plan getPlan() {
		return plan;
	}
	public void setPlan(Plan plan) {
		this.plan = plan;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Requirement getRequirement() {
		return requirement;
	}
	public void setRequirement(Requirement requirement) {
		this.requirement = requirement;
	}
	public Task getTask() {
		return task;
	}
	public void setTask(Task task) {
		this.task = task;
	}
	public User getResolvedBy() {
		return resolvedBy;
	}
	public void setResolvedBy(User resolvedBy) {
		this.resolvedBy = resolvedBy;
	}
	public Date getResolvedDate() {
		return resolvedDate;
	}
	public void setResolvedDate(Date resolvedDate) {
		this.resolvedDate = resolvedDate;
	}
	public String getResolution() {
		return resolution;
	}
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
