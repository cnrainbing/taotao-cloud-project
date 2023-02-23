package com.taotao.cloud.auth.biz.authentication.face;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

public class FaceAuthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

	private final Object principal;

	/**
	 * 此构造函数用来初始化未授信凭据.
	 *
	 * @param principal the principal
	 */
	public FaceAuthenticationToken(Object principal) {
		super(null);
		this.principal = principal;
		setAuthenticated(false);
	}

	/**
	 * 此构造函数用来初始化授信凭据.
	 *
	 * @param principal   the principal
	 * @param authorities the authorities
	 */
	public FaceAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		// must use super, as we override
		super.setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return this.principal;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		if (isAuthenticated) {
			throw new IllegalArgumentException(
				"Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
		}

		super.setAuthenticated(false);
	}

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
	}
}