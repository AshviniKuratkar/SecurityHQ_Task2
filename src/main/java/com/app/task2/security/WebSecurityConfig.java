package com.app.task2.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.task2.security.jwt.AuthEntryPointJwt;
import com.app.task2.security.jwt.AuthTokenFilter;
import com.app.task2.security.services.UserDetailsServiceImpl;

@PropertySource("application.properties")
@Configuration
@EnableAutoConfiguration
@EnableWebSecurity//allows Spring to find and automatically apply the class to the global Web Security.
@EnableGlobalMethodSecurity(
     //securedEnabled = true,
    //jsr250Enabled = true,
 prePostEnabled = true)//provides AOP security on methods. It enables @PreAuthorize, @PostAuthorize
//WebSecurityConfigurerAdapter is a convenience class that allows 
//customization to both WebSecurity and HttpSecurity. We can extend WebSecurityConfigurerAdapter
//multiple times (in distinct objects) to replicate the behavior of having multiple http elements
public class WebSecurityConfig extends WebSecurityConfigurerAdapter 
{
  @Autowired
  UserDetailsServiceImpl userDetailsService;
  
  
  @Autowired
  private AuthEntryPointJwt unauthorizedHandler;

  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter() {
    return new AuthTokenFilter();
  }
  
  //override the configure(HttpSecurity http) method from WebSecurityConfigurerAdapter interface. It tells Spring Security how we configure CORS and CSRF, 
  //when we want to require all users to be authenticated or not, which filter (AuthTokenFilter)

  @Override
  public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
    authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http = http.cors().and().csrf().disable();
     http.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
      .authorizeRequests().antMatchers("/api/auth/**").permitAll()
      .antMatchers(HttpMethod.GET,"/api/admin/**").hasRole("ADMIN")
      .antMatchers(HttpMethod.POST,"/api/admin/**").hasRole("ADMIN")
      .antMatchers(HttpMethod.PUT,"/api/admin/**").hasRole("ADMIN")
      .antMatchers(HttpMethod.DELETE,"/api/admin/**").hasRole("ADMIN")
      .antMatchers(HttpMethod.GET,"/api/user/**").hasAnyRole("ADMIN","USER")
      .antMatchers(HttpMethod.POST,"/api/user/**").hasAnyRole("ADMIN","USER")
      .antMatchers(HttpMethod.POST,"/api/user/editUserDetails/{id}/**").access("@userSecurity.hasUserId(authentication,#id)")
      .antMatchers(HttpMethod.GET,"/api/user/viewProfile/{id}/**").access("@userSecurity.hasUserId(authentication,#id)")
      .antMatchers("/api/test/**").permitAll()
      .antMatchers("/**").permitAll()
      .antMatchers("/", "/login**", "/error**")
      .permitAll().anyRequest().authenticated()
      .and().logout().logoutUrl("/logout");
     
    http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
  }
  

 
}
  
