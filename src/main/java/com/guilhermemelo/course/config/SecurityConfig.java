package com.guilhermemelo.course.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment environment;

    private static final String[] PUBLIC_MATCHERS = { // Vetor para definir os caminhos que sao publicos
            "/h2-console/**"
    };

    private static final String[] PUBLIC_MATCHERS_GET = { // Vetor para definir os caminhos que sao publicos apenas para leitura
            "/produtos/**",
            "/categorias/**"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        if (Arrays.asList(environment.getActiveProfiles()).contains("test")){
            http.headers().frameOptions().disable(); //Se o nosso profile ativo for 'test', ele vai permitir no acesso
            //ao banco de dados
        }

        http.cors().and().csrf().disable();
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll() //Permite todos os caminhos que tiverem nesse vetor
                // que do tipo de request GET
                .antMatchers(PUBLIC_MATCHERS).permitAll() //Permite todos os caminhos que tiverem nesse vetor
                .anyRequest().authenticated(); //Se for qualquer outro caminho, vai precisar de autorizacao
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);// Para o nosso backend nao criar
        //sesao de usuario
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues()); //to definindo
        // um bean, para dar acesso aos meus endpoints por multiplas fontes com as configuracoes basicas
        return source;
    }

    @Bean //BCryptPasswordEncoder Para encodar a senha passada pelo Cliente
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
