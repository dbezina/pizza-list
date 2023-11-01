package com.bezina.pizza.project.pizzalist.security;


import com.bezina.pizza.project.pizzalist.DAO.UserRepository;
import com.bezina.pizza.project.pizzalist.entity.User;
import com.bezina.pizza.project.pizzalist.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepo) {
        return username -> {
            User user = userRepo.findByUsername(username);
            if (user != null) return user;
            throw new UsernameNotFoundException("User " + username + " not found");
        };
    }


    /*  @Bean
      public UserDetailsService userDetailService(PasswordEncoder passwordEncoder) {
       List<UserDetails> userList = new ArrayList<>();
          userList.add(new User("user1",passwordEncoder.encode("password"),
                  Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
          userList.add(new User("user2",passwordEncoder.encode("password"),
                  Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
          return new InMemoryUserDetailsManager(userList);
      }*/
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

  /*  @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/design", "/orders/**").hasRole("USER")
                // Other authorization rules
                .and()
                .formLogin()
                .and()
                .userDetailsService(customUserDetailsService);
    }*/


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        //     HeaderWriterLogoutHandler clearSiteData = new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter());

        return http
                .userDetailsService(customUserDetailsService)
                .authorizeHttpRequests((authorize) -> authorize
                                //     .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()

                                .requestMatchers("/design", "/orders/**")

                                //   .hasAnyRole("USER","ADMIN")
                                .hasAuthority("ROLE_USER")
                                //   .authenticated()

                                .requestMatchers("/static/**").permitAll() // Permit access to static resources

                                .requestMatchers("/all")
                                .hasAuthority("ROLE_ADMIN")
                                //   .hasAnyRole("ADMIN")

                                .requestMatchers("/register", "/home")
                                .permitAll()


                                .anyRequest()
                                .authenticated()
                        // .denyAll()
                )

                /*    .authorizeHttpRequests((authorize) -> authorize
                            .dispatcherTypeMatchers(DispatcherType.FORWARD, DispatcherType.ERROR).permitAll()
                            .requestMatchers("/login").permitAll()
                            .anyRequest().denyAll()
                    )*/

                .formLogin(form -> form
                        .loginPage("/login")
                        .failureForwardUrl("/home")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/design", true)
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .permitAll()
                )
                .logout((logout) -> logout
                        .logoutUrl("/logout")// URL for logout
                        .logoutSuccessUrl("/home")// Redirect after logout
                        //   .addLogoutHandler(clearSiteData)
                        .invalidateHttpSession(true) // Invalidate the HTTP session
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .oauth2Login(httpSecurityOAuth2LoginConfigurer ->
                        httpSecurityOAuth2LoginConfigurer
                                .loginPage("/login")
                                .defaultSuccessUrl("/design", true)
                                .permitAll()
                )
                .build();

      /*  http
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults());
        return http.build();*/
    }
}
