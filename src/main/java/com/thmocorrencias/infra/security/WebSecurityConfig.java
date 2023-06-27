package com.thmocorrencias.infra.security;

import com.thmocorrencias.infra.security.jwt.JwtFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.headers().frameOptions().disable();
            http.cors().and().csrf().disable()
                    .addFilterAfter(new JwtFilter(), UsernamePasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers("/swagger-ui/**","/v3/api-docs/**").permitAll()
                    .antMatchers("/public/**").permitAll()
                    .antMatchers("/imagens/**").permitAll()
                    //.antMatchers("/TOS").permitAll()

                    .antMatchers(HttpMethod.DELETE,"/usuarios/{id}").hasAnyRole("MANAGER")
                    .antMatchers(HttpMethod.POST,"/usuarios/cadastro").permitAll()
                    .antMatchers(HttpMethod.GET,"/usuarios/list").hasAnyRole("MANAGER")
                    .antMatchers(HttpMethod.GET,"/usuarios/{id}").hasAnyRole("MANAGER")
                    .antMatchers(HttpMethod.PUT,"/usuarios").hasAnyRole("MANAGER","USER")

                    .antMatchers(HttpMethod.DELETE,"ocorrencias/{id}").hasAnyRole("MANAGER")
                    .antMatchers(HttpMethod.GET,"ocorrencias/{id}").permitAll()
                    .antMatchers(HttpMethod.POST,"/ocorrencias/cadastro").hasAnyRole("USER")
                    .antMatchers(HttpMethod.GET,"/ocorrencias/list").permitAll()
                    .antMatchers(HttpMethod.PUT,"/ocorrencias").hasAnyRole("USER")
                    .antMatchers(HttpMethod.GET,"/upimagem/view-from-disk/{id}").permitAll()

                    .antMatchers(HttpMethod.GET,"/usuariosManager/cadastro").hasAnyRole("MANAGER")

                    .anyRequest().authenticated()
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }
        @Bean
        public CorsConfigurationSource corsConfigurationSource() {
            CorsConfiguration configuration = new CorsConfiguration();
            configuration.setAllowedOrigins(Arrays.asList("*"));
            configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE"));
            configuration.addAllowedOrigin("*");
            configuration.addAllowedHeader("*");
            configuration.addAllowedMethod("*");
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", configuration);
            return source;
        }

        @Bean
        public BCryptPasswordEncoder encoder(){
            return new BCryptPasswordEncoder();
        }
}
