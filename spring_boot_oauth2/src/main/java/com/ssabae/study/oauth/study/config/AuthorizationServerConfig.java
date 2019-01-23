package com.ssabae.study.oauth.study.config;

import com.ssabae.study.oauth.study.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    /*
     * inMemory가 아닌 DB를 이용하여 저장하여야한다
     * 여러가지 APP들을 등록 할 수 있는 방법을 찾아야한다.
     */

    @Autowired
    TokenStore tokenStore;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Override
    public void configure(ClientDetailsServiceConfigurer configure) throws Exception {
        configure
                .inMemory()
                .withClient("ssabae-client")
                .secret(passwordEncoder.encode("ssabae-password"))
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
                .scopes("read", "write", "trust")
                .accessTokenValiditySeconds(1*60*60)
                .refreshTokenValiditySeconds(6*60*60);

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userService)
        ;
     }
}
