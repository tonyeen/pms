package org.qianrenxi.pms.rest;

import org.qianrenxi.pms.entity.Project;
import org.qianrenxi.pms.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects")
public class ProjectApiController {
	
	@Autowired
	private ProjectService projectService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public Page<Project> allProjects(Project project, Pageable pageable) {
		Page<Project> projects = projectService.findAll(project, pageable);
		return projects;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Project getOne(@PathVariable("id") Long id) {
		Project project = projectService.findOne(id);
		return project;
	}
	
	@RequestMapping(value="", method= RequestMethod.PUT)
	public Project create(Project project) {
		project = projectService.save(project);
		return project;
	}
	
	@RequestMapping(value="/{id}", method= RequestMethod.POST)
	public Project update(@PathVariable("id") Long id, Project project) {
		project.setId(id);
		project = projectService.save(project);
		return project;
	}
	
	@RequestMapping(value="", method= RequestMethod.DELETE)
	public void update(@RequestParam("ids")Long[] ids) {
		projectService.delete(ids);
	}
}
