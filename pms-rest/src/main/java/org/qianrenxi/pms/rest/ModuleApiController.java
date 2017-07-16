package org.qianrenxi.pms.rest;

import org.qianrenxi.pms.entity.Module;
import org.qianrenxi.pms.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/modules")
public class ModuleApiController {
	
	@Autowired
	private ModuleService moduleService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public Page<Module> allModules(Module module, Pageable pageable) {
		Page<Module> modules = moduleService.findAll(module, pageable);
		return modules;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Module getOne(@PathVariable("id") Long id) {
		Module module = moduleService.findOne(id);
		return module;
	}
	
	@RequestMapping(value="", method= RequestMethod.PUT)
	public Module create(Module module) {
		module = moduleService.save(module);
		return module;
	}
	
	@RequestMapping(value="/{id}", method= RequestMethod.POST)
	public Module update(@PathVariable("id") Long id, Module module) {
		module.setId(id);
		module = moduleService.save(module);
		return module;
	}
	
	@RequestMapping(value="", method= RequestMethod.DELETE)
	public void update(@RequestParam("ids")Long[] ids) {
		moduleService.delete(ids);
	}
}
