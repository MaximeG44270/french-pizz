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
                                .requestMatchers("/home").permitAll()
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/user-connected").permitAll()
                                .requestMatchers("/menu").permitAll()
                                .requestMatchers("/admin").permitAll()
                                .requestMatchers("/delivery").permitAll()
                                .requestMatchers("/link").permitAll()
                                .requestMatchers("/logout").permitAll()
                                .requestMatchers("/add-user").permitAll()
                                //toutes les requêtes commençant par vendor (ex: celles liées aux CSS et JS de uikit) sont autorisées
                                //ainsi SpringSecurity ne bloque plus le chargement d éléments stockés dans vendor
                                .requestMatchers("/vendor/**").permitAll()
                                .requestMatchers("/style/**").permitAll()
                                .requestMatchers("/image/**").permitAll()
                                //Pour toutes les autres requêtes qui ne sont pas explicitement déclarées ci-dessus,
                                //  alors on peut soit tout autoriser après authentification via .anyRequest().authenticated()
                                // ou alors plutôt les rejettées par défaut via:
                                .anyRequest().denyAll()
                        //ATTENTION: cette dernière requête anyRequest() doit être la dernière instruction!

                        // remarque : hasRole("ADMIN") est utilisable en lieu et place de hasAuthority("ROLE_ADMIN")
                        //idem avec hasAnyRole("EMPLOYE", "FORMATEUR", "ADMIN")) à la place de hasAnyAuthority("ROLE_EMPLOYE", "ROLE_FORMATEUR", "ROLE_ADMIN")
                );

        //Permet de configurer la page avec l'url "/login" pour qu'elle renvoie sur la page login fournie par défaut par Spring Security
        //et non notre page "/V2/login-page-v2.html"
        //http.formLogin(Customizer.withDefaults());
        //ATTENTION: quand on customize une COnnexion personnalisée, la page de DECOnnexion par défaut devra également etre obligatoirement personalisée
        //Permet ici de conserver en url "/login" le getMapping personnalisé présent dans DemoController renvoyant vers "/login-page.html"
        http.formLogin(form -> form.loginPage("/login")
                // permet le retour vers la page url  "" en cas de succès de connexion
                .defaultSuccessUrl("/user-connected"));

        HeaderWriterLogoutHandler clearSiteData = new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(ClearSiteDataHeaderWriter.Directive.ALL));

        //Permet ici d'instaurer que l'appel de l'url "/logout" provoquera une redirection et un nettoyage total de tous les éléments de cache mémoire , cookies etc
        //déposes sur le client
        http.logout((logout) ->
                logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                        // permet le retour vers la page url  "/login" en cas de succès de déconnexion
                        .logoutSuccessUrl("/login?logout")
                        .addLogoutHandler(clearSiteData)
        );

        return http.build();
    }

}
