package org.qianrenxi.pms.rest;

import org.qianrenxi.pms.entity.Plan;
import org.qianrenxi.pms.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/plans")
public class PlanApiController {
	
	@Autowired
	private PlanService planService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public Page<Plan> allPlans(Plan plan, Pageable pageable) {
		Page<Plan> plans = planService.findAll(plan, pageable);
		return plans;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Plan getOne(@PathVariable("id") Long id) {
		Plan plan = planService.findOne(id);
		return plan;
	}
	
	@RequestMapping(value="", method= RequestMethod.PUT)
	public Plan create(Plan plan) {
		plan = planService.save(plan);
		return plan;
	}
	
	@RequestMapping(value="/{id}", method= RequestMethod.POST)
	public Plan update(@PathVariable("id") Long id, Plan plan) {
		plan.setId(id);
		plan = planService.save(plan);
		return plan;
	}
	
	@RequestMapping(value="", method= RequestMethod.DELETE)
	public void update(@RequestParam("ids")Long[] ids) {
		planService.delete(ids);
	}
}
