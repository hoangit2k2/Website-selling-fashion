package edu.hoang;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import edu.hoang.service.AuthService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class AuthConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired private AuthService authService;
	
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authService);
	}
	// phân quyền sử dụng và hình thức đăng nhập
		@Override
		protected void configure(HttpSecurity http) throws Exception{
			//CSRF, CORS
			http.csrf().disable().cors().disable();
			//Phân quyền sử dụng
			http.authorizeRequests()
         	.antMatchers("/security/**", "/assets/**", "/product/list").permitAll()
		 	.antMatchers("/page/director/**").hasAnyRole("DIRE")
		 	.antMatchers("/page/staff/**").hasAnyRole("STAF")
		 	.antMatchers("/cart/view/**","/order/list/**","/order/checkout/**").hasAnyRole("STAF","CUST","DIRE")
			.antMatchers("/admin/home/index").hasAnyRole("DIRE","STAF");
			 http.exceptionHandling().accessDeniedPage("/login/error/role");
			 http // form login
			 	.formLogin()
			 		.loginPage("/security/login/form")
					.loginProcessingUrl("/security/login") // default [/login] => process the submitted credential
					.defaultSuccessUrl("/login/success", true)
					.failureForwardUrl("/login/failed"); // login failed

		         http// logout
				.logout()
					.logoutUrl("/security/logoff") // default [/logout]
					.logoutSuccessUrl("/product/list");
		     
		     	http.oauth2Login()
				.loginPage("/auth/login/form")
				.defaultSuccessUrl("/oauth2/login/success", true)
				.failureUrl("/auth/login/error")
				.authorizationEndpoint()
				.baseUri("/oauth2/authorization");
		
		}
}
