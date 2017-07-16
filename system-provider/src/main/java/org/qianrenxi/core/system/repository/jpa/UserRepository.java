package org.qianrenxi.core.system.repository.jpa;

import org.qianrenxi.core.common.repository.SupportRepository;
import org.qianrenxi.core.system.enity.User;

public interface UserRepository extends SupportRepository<User, Long> {

	User findByUsername(String username);
}
