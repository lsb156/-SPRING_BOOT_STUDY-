package com.ssabae.demo.configure;

import com.ssabae.demo.accounts.Account;
import com.ssabae.demo.accounts.AccountRole;
import com.ssabae.demo.accounts.AccountService;
import com.ssabae.demo.common.AppProperties;
import com.ssabae.demo.common.BaseControllerTest;
import com.ssabae.demo.common.TestDescription;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthServerConfigTest extends BaseControllerTest {

    @Autowired
    AccountService accountService;

    @Autowired
    AppProperties appProperties;

    @Test
    @TestDescription("인증 토큰을 발급 받는 테스트")
    public void getAuthToken() throws Exception {

        Account ssabae = Account.builder()
                .email(appProperties.getUserUsername())
                .password(appProperties.getUserPassword())
                .roles(Set.of(AccountRole.ADMIN, AccountRole.USER))
                .build();
        this.accountService.saveAccount(ssabae);

        this.mockMvc.perform(post("/oauth/token")
                    .with(httpBasic(appProperties.getClientId(), appProperties.getClientSecret()))
                        .param("username", appProperties.getUserUsername())
                        .param("password", appProperties.getUserPassword())
                        .param("grant_type", "password")
                    )
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("access_token").exists());
    }
}