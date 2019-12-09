package spittr.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import spittr.pojo.Spitter;
import spittr.repository.SpitterRepository;

public class SpittrAuthorizingRealm extends AuthorizingRealm {

	


	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		String userName = (String) principals.getPrimaryPrincipal();
		System.out.println("------doGetAuthorizationInfo userName=" + userName);

		

		if ("admin".equals(userName)) {

			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

			info.addRole(userName);
			
			return info;
		}

		return null;
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		String userName = (String) token.getPrincipal();
		System.out.println("------doGetAuthenticationInfo userName=" + userName);

		

		if ("admin".equals(userName)) {

			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo("admin", "admin", getName());

		 
			//info.setCredentials("admin");
			 
			return info;
		}

		return null;
		
	}

	@Override
	public void setName(String name) {
		// TODO Auto-generated method stub
		super.setName("abc");
	}

}
