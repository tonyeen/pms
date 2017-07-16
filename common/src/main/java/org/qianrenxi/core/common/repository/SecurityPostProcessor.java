package org.qianrenxi.core.common.repository;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.support.RepositoryProxyPostProcessor;

/**
 * @author gujun
 */
public class SecurityPostProcessor implements RepositoryProxyPostProcessor {

	@Override
	public void postProcess(ProxyFactory factory, RepositoryInformation repositoryInformation) {
		factory.addAdvice(SecurecyAdvice.instance);
	}
	static enum SecurecyAdvice implements MethodInterceptor {
		instance;
		@Override
		public Object invoke(MethodInvocation invocation) throws Throwable {
			System.out.println(">>>>>>>>>>>>>>>>>>>>>将要执行方法：" + invocation.getMethod().getName());
			
			//待调用的目标对象及参数列表
			Object[] args = invocation.getArguments();
			/*if (args !=null && args.length > 0) {
				for(int i = 0;i< args.length; i++){
					Object o = args[i];
					if( o instanceof Specification ){
						Specification<Object> specification = (Specification)o;
						o =  new Specification<Object>() {
							@Override
							public Predicate toPredicate(Root<Object> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
								Predicate predicate = specification.toPredicate(root, query, cb);
								predicate = cb.and(predicate, cb.isFalse(root.get("isDeleted")));
								return predicate;
							}
						};
						args[i] = o;
					}
				}
			}*/
			
			Object obj = invocation.proceed();
			System.out.println(invocation.getMethod().getName() + ">>>>>>>>>>>>>>>>>已经被执行");
			return obj;
		}
	}
}