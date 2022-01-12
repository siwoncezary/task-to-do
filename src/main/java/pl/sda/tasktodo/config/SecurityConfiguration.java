package pl.sda.tasktodo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import pl.sda.tasktodo.service.StudentDetailService;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final StudentDetailService service;

    public SecurityConfiguration(StudentDetailService service) {
        this.service = service;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(NoOpPasswordEncoder.getInstance());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                .and()
                .authorizeRequests()
                .antMatchers("/student","/student/**").hasRole("STUDENT")
                .antMatchers("/admin","/admin/**").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .and()
                .logout()
                .logoutSuccessUrl("/");
    }
}
