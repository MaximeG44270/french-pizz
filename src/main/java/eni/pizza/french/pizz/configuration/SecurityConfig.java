package eni.pizza.french.pizz.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery("SELECT email, mot_de_passe, 1 FROM utilisateur WHERE email=?");
        userDetailsManager.setAuthoritiesByUsernameQuery("SELECT utilisateur.email, role.libelle \n" +
                "FROM utilisateur" +
                " INNER JOIN role_utilisateur ON role_utilisateur.UTILISATEUR_id_utilisateur = utilisateur.id_utilisateur" +
                " INNER JOIN role ON role_utilisateur.ROLE_id_role = ROLE.id_role" +
                " WHERE email=?");

        return userDetailsManager;
    }

    @Bean
    public SecurityFilterChain web(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests((authorize) -> authorize

                                .requestMatchers(HttpMethod.GET,"/add-produit/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/add-produit/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/add-user/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/add-user/**").permitAll()
                                .requestMatchers(HttpMethod.GET,"/login/**").permitAll()
                                .requestMatchers(HttpMethod.POST,"/login/**").permitAll()
                                .requestMatchers("add-produit/**").permitAll()
                                .requestMatchers("/add-user/**").permitAll()
                                .requestMatchers("/admin").permitAll()
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/list-commandes").permitAll()
                                .requestMatchers("/home").permitAll()
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/user-connected").permitAll()
                                .requestMatchers("/menu").permitAll()
                                .requestMatchers("/admin").permitAll()
                                .requestMatchers("/delivery").permitAll()
                                .requestMatchers("/link").permitAll()
                                .requestMatchers("/logout").permitAll()
                                .requestMatchers("/add-user").permitAll()
                                .requestMatchers("/commande-detail").permitAll()
                                .requestMatchers("/commande-detail/**").permitAll()
                                .requestMatchers("/vendor/**").permitAll()
                                .requestMatchers("/style/**").permitAll()
                                .requestMatchers("/image/**").permitAll()

                                .anyRequest().denyAll()
                );

        http.formLogin(form -> form.loginPage("/login")
                // permet le retour vers la page url  "" en cas de succès de connexion
                .defaultSuccessUrl("/user-connected"));

        HeaderWriterLogoutHandler clearSiteData = new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(ClearSiteDataHeaderWriter.Directive.ALL));

        http.logout((logout) ->
                logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                        .logoutSuccessUrl("/login?logout")
                        .addLogoutHandler(clearSiteData)
        );
        return http.build();
    }
}
