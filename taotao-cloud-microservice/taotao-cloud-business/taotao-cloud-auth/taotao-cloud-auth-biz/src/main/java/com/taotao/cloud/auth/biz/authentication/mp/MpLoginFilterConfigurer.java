package com.taotao.cloud.auth.biz.authentication.mp;

import com.taotao.cloud.auth.biz.authentication.AbstractLoginFilterConfigurer;
import com.taotao.cloud.auth.biz.authentication.LoginFilterSecurityConfigurer;
import com.taotao.cloud.auth.biz.authentication.mp.service.MpUserDetailsService;
import com.taotao.cloud.auth.biz.jwt.JwtTokenGenerator;
import com.taotao.cloud.auth.biz.models.LoginAuthenticationSuccessHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.HttpSecurityBuilder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;

public class MpLoginFilterConfigurer<H extends HttpSecurityBuilder<H>> extends
	AbstractLoginFilterConfigurer<H, MpLoginFilterConfigurer<H>, MpAuthenticationFilter, LoginFilterSecurityConfigurer<H>> {

	private MpUserDetailsService mpUserDetailsService;

	private JwtTokenGenerator jwtTokenGenerator;

	public MpLoginFilterConfigurer(LoginFilterSecurityConfigurer<H> securityConfigurer) {
		super(securityConfigurer, new MpAuthenticationFilter(), "/login/mp");
	}

	public MpLoginFilterConfigurer<H> mpUserDetailsService(
		MpUserDetailsService mpUserDetailsService) {
		this.mpUserDetailsService = mpUserDetailsService;
		return this;
	}

	public MpLoginFilterConfigurer<H> jwtTokenGenerator(JwtTokenGenerator jwtTokenGenerator) {
		this.jwtTokenGenerator = jwtTokenGenerator;
		return this;
	}

	@Override
	protected RequestMatcher createLoginProcessingUrlMatcher(String loginProcessingUrl) {
		return new AntPathRequestMatcher(loginProcessingUrl, "POST");
	}

	@Override
	protected AuthenticationProvider authenticationProvider(H http) {
		ApplicationContext applicationContext = http.getSharedObject(ApplicationContext.class);

		MpUserDetailsService mpUserDetailsService =
			this.mpUserDetailsService != null ? this.mpUserDetailsService
				: getBeanOrNull(applicationContext, MpUserDetailsService.class);
		Assert.notNull(mpUserDetailsService, "mpUserDetailsService is required");

		return new MpAuthenticationProvider(mpUserDetailsService);
	}

	@Override
	protected AuthenticationSuccessHandler defaultSuccessHandler(H http) {
		if (this.jwtTokenGenerator == null) {
			ApplicationContext applicationContext = http.getSharedObject(ApplicationContext.class);
			jwtTokenGenerator = getBeanOrNull(applicationContext, JwtTokenGenerator.class);
		}
		Assert.notNull(jwtTokenGenerator, "jwtTokenGenerator is required");
		return new LoginAuthenticationSuccessHandler(jwtTokenGenerator);
	}
}