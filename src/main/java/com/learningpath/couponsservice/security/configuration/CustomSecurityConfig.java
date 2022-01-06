package com.learningpath.couponsservice.security.configuration;

import com.learningpath.couponsservice.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();
        httpSecurity.authorizeRequests()
                .mvcMatchers(HttpMethod.GET,"/couponapi/coupons/{code:^[A-Za-z0-9]*$}",
                        "/couponapi/coupons","/index","/showCoupons").hasAnyRole("USER","ADMIN")
                .mvcMatchers(HttpMethod.GET,"/createCoupon","/createCouponResponse").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST,"/couponDetails").hasAnyRole("ADMIN","USER")
                .mvcMatchers(HttpMethod.POST,"/couponapi/coupons","/saveCoupon").hasRole("ADMIN")
                .mvcMatchers("/","/login","/logout","/registerUser","/showReg").permitAll()
                .anyRequest().denyAll().and().logout().logoutSuccessUrl("/");

        httpSecurity.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
