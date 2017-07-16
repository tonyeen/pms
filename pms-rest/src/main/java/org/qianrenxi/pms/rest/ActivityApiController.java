package org.qianrenxi.pms.rest;

import org.qianrenxi.pms.entity.Activity;
import org.qianrenxi.pms.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activities")
public class ActivityApiController {
	
	@Autowired
	private ActivityService activityService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public Page<Activity> allActivities(Activity activity, Pageable pageable) {
		Page<Activity> activities = activityService.findAll(activity, pageable);
		return activities;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Activity getOne(@PathVariable("id") Long id) {
		Activity activity = activityService.findOne(id);
		return activity;
	}
	
	@RequestMapping(value="", method= RequestMethod.PUT)
	public Activity create(Activity activity) {
		activity = activityService.save(activity);
		return activity;
	}
	
	@RequestMapping(value="/{id}", method= RequestMethod.POST)
	public Activity update(@PathVariable("id") Long id, Activity activity) {
		activity.setId(id);
		activity = activityService.save(activity);
		return activity;
	}
	
	@RequestMapping(value="", method= RequestMethod.DELETE)
	public void update(@RequestParam("ids")Long[] ids) {
		activityService.delete(ids);
	}
}
