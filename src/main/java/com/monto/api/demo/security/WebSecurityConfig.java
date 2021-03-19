package com.monto.api.demo.security;


import com.monto.api.demo.security.jwt.JwtAuthEntryPoint;
import com.monto.api.demo.security.jwt.JwtAuthTokenFilter;
import com.monto.api.demo.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
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
        http.
                cors().and().csrf().disable().
                authorizeRequests()
                .antMatchers("/android/**").permitAll()
                .antMatchers("/meds/**").permitAll()
                .antMatchers("/student/**").permitAll()
                .antMatchers("/Attendance/**").permitAll()
                .antMatchers("/incident/**").permitAll()
                .antMatchers("/newsfeed/**").permitAll()
                .antMatchers("/profileimage/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/absent-report/**").permitAll()
                .antMatchers("/androidlogin/**").permitAll()
                .antMatchers("/admin/**").permitAll()
                .antMatchers("/parent/**").permitAll()
                .antMatchers("/teacherinfo/**").permitAll()
                .antMatchers("/messages/**").permitAll()
                .antMatchers("/notification/**").permitAll()
                .antMatchers("/badges/**").permitAll()
                .antMatchers("/moods/**").permitAll()
                .antMatchers("/class/**").permitAll()
                .antMatchers("/homework/**").permitAll()
                .anyRequest().authenticated()
                .and()
//               .httpBasic();
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
