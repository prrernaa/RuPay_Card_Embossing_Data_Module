package com.card.config;



import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.card.repo.UserRepository;
import com.card.service.impl.UserDetailsServiceImpl;



@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter
{
	@Autowired
	private UserRepository userRepository;
	@Bean
	public UserDetailsService userDetailsService()
	{
		return new UserDetailsServiceImpl();
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider daoAuthenticationProvider= new DaoAuthenticationProvider();
		 daoAuthenticationProvider.setUserDetailsService(this.userDetailsService());
		 daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		 
		 return daoAuthenticationProvider;
	}
	
//	COnfigure Method
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.authenticationProvider(authenticationProvider());
	}
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
					.antMatchers("/admin/**","/admin","/usermanager").hasRole("ADMIN")
					.antMatchers("/operation1/**","/operation1").hasRole("OPERATION1")
					.antMatchers("/operation2/**","/operation2").hasRole("OPERATION2")
					.antMatchers("/operation3/**","/operation3").hasRole("OPERATION3")
					.antMatchers("/**").permitAll()
					.and()
					.formLogin().loginPage("/login")
					.defaultSuccessUrl("/", true) // Redirect to default URL after login
		            .successHandler((request, response, authentication) -> {
		                // Get the authorities (roles) of the authenticated user
		                Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		                for (GrantedAuthority authority : authorities) {
		                    // Check if the user has the ADMIN role
		                    if (authority.getAuthority().equals("ROLE_ADMIN")) {
		                        // Redirect to admin page if the user is an admin
		                        response.sendRedirect("/admin");
		                        return;
		                    } else if (authority.getAuthority().equals("ROLE_OPERATION1")) {
		                        // Redirect to Operation1 page if the user has Operation1 role
		                        response.sendRedirect("/operation1");
		                        return;
		                    } else if (authority.getAuthority().equals("ROLE_OPERATION2")) {
		                        // Redirect to Operation1 page if the user has Operation1 role
		                        response.sendRedirect("/operation2");
		                        return;
		                    } else if (authority.getAuthority().equals("ROLE_OPERATION3")) {
		                        // Redirect to Operation1 page if the user has Operation1 role
		                        response.sendRedirect("/operation3");
		                        return;
		                    }
		                    // Add more conditions for other roles if needed
		                }
		                // If the user doesn't have any specific role, redirect to the default URL
		                response.sendRedirect("/");
		            }).and()
					.csrf().disable();
	}
}
	
	
	
//	@Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//                .anyRequest().permitAll() // Permit access to all URLs without authentication
//            .and()
//            .formLogin()
//                .loginPage("/login") // Specify the custom login page
//                .permitAll() // Allow access to the login page
//            .and()
//            .logout()
//                .logoutSuccessUrl("/login") // Redirect to login page after logout
//                .permitAll() // Allow access to the logout URL
//            .and()
//            .csrf().disable(); // Disable CSRF protection
//    }
	
//	@Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//            .dataSource(dataSource)
//            .usersByUsernameQuery("SELECT username, password FROM user_info WHERE username=?")
//            .authoritiesByUsernameQuery("SELECT username, user_role FROM user_info WHERE username=?")
//            .passwordEncoder(passwordEncoder());
//    }
	
//	 @Override
//	    protected void configure(HttpSecurity http) throws Exception {
//         http
//                 .authorizeRequests(requests -> requests
//                         .antMatchers("/login", "/Signup", "/loginauth").permitAll()
//                         .antMatchers("/admin", "/pendingSignups", "/reject", "/approve").hasRole("admin")
//                         .antMatchers("/Operation1", "/upload").hasRole("Operation 1 : download files")
//                         .antMatchers("/Operation2", "/operation2/form").hasRole("Operation 2 : BANKING PERSONALIZATION WORK ORDER")
//                         .antMatchers("/Operation3", "/operation3/upload", "/operation3/form").hasRole("Operation 3 : BOTH DOWNLOAD FILES & BANKING PERSONALIZATION WORK ORDER")
//                         .anyRequest().authenticated())
//                 .formLogin(login -> login
////                         .loginPage("/login")
//                         .defaultSuccessUrl("/login")
//                         .failureUrl("/login?error")
//                         .permitAll())
//                 .logout(logout -> logout
//                         .logoutSuccessUrl("/login?logout")
//                         .permitAll());
//	    }



