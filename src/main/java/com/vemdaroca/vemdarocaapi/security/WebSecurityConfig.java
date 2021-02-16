package com.vemdaroca.vemdarocaapi.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ImplementsUserDetailsService userDetailsService;

	private static final String[] AUTH_WHITELIST = { "/v2/api-docs", "/swagger-resources", "/swagger-resources/**",
			"/configuration/ui", "/configuration/security", "/swagger-ui.html", "/webjars/**", "/h2-console/**" };

	private static final String[] ADMIN_ACCESS = { "/pedido", "/pedido/**", "/produto", "/produto/all", "/itempedido",
			"/itempedido/all" };

	private static final String[] USER_GET_ACCESS = { "/produto/allActive" };
	private static final String[] USER_POST_ACCESS = { "/itempedido/createAll" };

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors().and().csrf().disable().authorizeRequests().antMatchers(AUTH_WHITELIST).permitAll()
				.antMatchers(HttpMethod.POST, "/cliente").permitAll().antMatchers(HttpMethod.OPTIONS, "/cliente")
				.permitAll().antMatchers(HttpMethod.POST, "/login").permitAll().antMatchers(HttpMethod.POST, "/email")
				.permitAll().antMatchers(HttpMethod.OPTIONS, "/email").permitAll()
				.antMatchers(HttpMethod.GET, ADMIN_ACCESS).hasRole("ADMIN").antMatchers(HttpMethod.GET, USER_GET_ACCESS)
				.hasAnyRole("ADMIN", "USER").antMatchers(HttpMethod.POST, ADMIN_ACCESS).hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, USER_POST_ACCESS).hasAnyRole("ADMIN", "USER")
				.antMatchers(HttpMethod.PUT, ADMIN_ACCESS).hasRole("ADMIN").antMatchers(HttpMethod.DELETE, ADMIN_ACCESS)
				.hasRole("ADMIN").and().authorizeRequests().anyRequest().authenticated().and()

				// filtra requisições de login

				.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
						UsernamePasswordAuthenticationFilter.class)

				// filtra outras requisições para verificar a presença do JWT no header
				.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// cria uma conta default
		auth.userDetailsService(userDetailsService).passwordEncoder(NoOpPasswordEncoder.getInstance());

	}

	@Override
	public void configure(WebSecurity web) throws Exception {

//		final String[] ADMIN_ACCESS = { "/pedido", "/pedido/**", "/produto", "/produto/**", "/itempedido",
//				"/itempedido/**" };

		web.ignoring().antMatchers(HttpMethod.POST, "/cliente").antMatchers(HttpMethod.POST, "/email");

	}

//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedOrigins(Arrays.asList("*"));
//		configuration.setAllowedHeaders(Arrays.asList("*"));
//		configuration.setAllowedMethods(Arrays.asList("*"));
//		configuration.addExposedHeader("Authorization");
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("*"));
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("DELETE");
		config.addExposedHeader("Authorization");

		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
