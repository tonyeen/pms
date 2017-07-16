package org.qianrenxi.pms.rest;

import org.qianrenxi.pms.entity.Requirement;
import org.qianrenxi.pms.service.RequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/requirements")
public class RequirementApiController {
	
	@Autowired
	private RequirementService requirementService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public Page<Requirement> allRequirements(Requirement requirement, Pageable pageable) {
		Page<Requirement> requirements = requirementService.findAll(requirement, pageable);
		return requirements;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Requirement getOne(@PathVariable("id") Long id) {
		Requirement requirement = requirementService.findOne(id);
		return requirement;
	}
	
	@RequestMapping(value="", method= RequestMethod.PUT)
	public Requirement create(Requirement requirement) {
		requirement = requirementService.save(requirement);
		return requirement;
	}
	
	@RequestMapping(value="/{id}", method= RequestMethod.POST)
	public Requirement update(@PathVariable("id") Long id, Requirement requirement) {
		requirement.setId(id);
		requirement = requirementService.save(requirement);
		return requirement;
	}
	
	@RequestMapping(value="", method= RequestMethod.DELETE)
	public void update(@RequestParam("ids")Long[] ids) {
		requirementService.delete(ids);
	}
}
