package com.example.bridesmaids.config;

import com.example.bridesmaids.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import org.codehaus.jettison.json.JSONObject;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .authorizeRequests()
                .antMatchers("/api/v1/user/register"
                        ,"/api/v1/Category/Category"
                        ,"/api/v1/product/byVendorId","/api/v1/product/getByCategory/{categoryId}","/api/v1/product/getBySubCategory/{subCategoryId}"
                        ,"/api/v1/product/byProduct","/api/v1/timeSlot/byProduct"
                ).permitAll()
                .antMatchers("/api/v1/product/get","/api/v1/picture/get","/api/v1/timeSlot/get","/api/v1/user/get","/api/v1/user/delete/{id}",
                        "/api/v1/user/notApproved", "/api/v1/user/isApproved"
                        ,"/api/v1/request/get","/api/v1/request/getAllByStatus/{status}"

                ).hasAuthority("ADMIN")
                .antMatchers("/api/v1/product/byVendorId","/api/v1/request/add"
                        ,"/api/v1/request/getAllByUserId"
                        ,"/api/v1/request/getAllByUserIdAndStatus/{status}"

                ).hasAuthority("CUSTOMER")
                .antMatchers("/api/v1/product/add",
                "/api/v1/product/delete/{id}",
                "/api/v1/product/update/{id}",
               "/api/v1/product/myProducts","/api/v1/picture/add",
              "/api/v1/picture/delete/{id}",
               "/api/v1/picture/update/{id}","/api/v1/timeSlot/add",
               " /api/v1/timeSlot/delete/{id}",
               " /api/v1/timeSlot/update/{id}",
                        "/api/v1/request/getAllByVendorId",
                        "/api/v1/request/getAllByVendorIdAndAndStatus/{status}"
                        ,"/api/v1/request/changeRequestStatus/{requestId}/{status}"
                ).hasAuthority("VENDOR")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/auth/logout/**").invalidateHttpSession(true).deleteCookies("JSESSIONID")
                .and()
                .httpBasic().authenticationEntryPoint(entryPoint());;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "OPTIONS"));
        configuration.addAllowedMethod(HttpMethod.TRACE);
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public AuthenticationEntryPoint entryPoint() {
        return new BasicAuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest request, HttpServletResponse response,
                                 AuthenticationException authException) throws IOException {
                JSONObject jsonObject = new JSONObject();
                try {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.setContentType("application/json");
                    jsonObject.put("message", authException.getMessage());
                    response.getWriter()
                            .println(jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterPropertiesSet() {
                setRealmName("Contact-Keeper");
                super.afterPropertiesSet();
            }
        };
    }

}