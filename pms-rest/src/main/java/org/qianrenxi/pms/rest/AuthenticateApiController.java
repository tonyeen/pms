package org.qianrenxi.pms.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/authenticate")
public class AuthenticateApiController {

	/**
	 * 登录/认证
	 * 
	 * 
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public void authenticate() {
		
	}

}
