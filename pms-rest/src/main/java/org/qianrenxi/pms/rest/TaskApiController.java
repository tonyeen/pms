package org.qianrenxi.pms.rest;

import org.qianrenxi.pms.entity.Task;
import org.qianrenxi.pms.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tasks")
public class TaskApiController {
	
	@Autowired
	private TaskService taskService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public Page<Task> allTasks(Task task, Pageable pageable) {
		Page<Task> tasks = taskService.findAll(task, pageable);
		return tasks;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Task getOne(@PathVariable("id") Long id) {
		Task task = taskService.findOne(id);
		return task;
	}
	
	@RequestMapping(value="", method= RequestMethod.PUT)
	public Task create(Task task) {
		task = taskService.save(task);
		return task;
	}
	
	@RequestMapping(value="/{id}", method= RequestMethod.POST)
	public Task update(@PathVariable("id") Long id, Task task) {
		task.setId(id);
		task = taskService.save(task);
		return task;
	}
	
	@RequestMapping(value="", method= RequestMethod.DELETE)
	public void update(@RequestParam("ids")Long[] ids) {
		taskService.delete(ids);
	}
}
