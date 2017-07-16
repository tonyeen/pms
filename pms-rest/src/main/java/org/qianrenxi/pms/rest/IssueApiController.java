package org.qianrenxi.pms.rest;

import org.qianrenxi.pms.entity.Issue;
import org.qianrenxi.pms.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/issues")
public class IssueApiController {
	
	@Autowired
	private IssueService issueService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public Page<Issue> allIssues(Issue issue, Pageable pageable) {
		Page<Issue> issues = issueService.findAll(issue, pageable);
		return issues;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Issue getOne(@PathVariable("id") Long id) {
		Issue issue = issueService.findOne(id);
		return issue;
	}
	
	@RequestMapping(value="", method= RequestMethod.PUT)
	public Issue create(Issue issue) {
		issue = issueService.save(issue);
		return issue;
	}
	
	@RequestMapping(value="/{id}", method= RequestMethod.POST)
	public Issue update(@PathVariable("id") Long id, Issue issue) {
		issue.setId(id);
		issue = issueService.save(issue);
		return issue;
	}
	
	@RequestMapping(value="", method= RequestMethod.DELETE)
	public void update(@RequestParam("ids")Long[] ids) {
		issueService.delete(ids);
	}
}
