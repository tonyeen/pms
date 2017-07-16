package org.qianrenxi.core.system.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.qianrenxi.core.system.enity.Site;
import org.qianrenxi.core.system.enity.User;
import org.qianrenxi.core.system.repository.jpa.SiteRepository;
import org.qianrenxi.core.system.repository.jpa.UserRepository;

// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration(classes = { PmsApplication.class, SystemConfig.class })
//// @TestPropertySource(locations = { "classpath:application.properties" })
// @Transactional
public class SiteRepositoryTest {

//	@Autowired
	SiteRepository siteRepository;
//	@Autowired
	UserRepository userRepository;

//	@Test
	public void testAdd() {
		Site site = new Site();
		site.setName("Root Site");

		siteRepository.save(site);
		
		User user = new User();
		user.setUsername("system");
		user.setSite(site);
		userRepository.save(user);

		assertTrue(site.getId() != null && site.getId() > 0);
		assertNotNull(site.getCreatedBy());
	}

}
