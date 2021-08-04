package com.miniprac;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miniprac.config.RestDocsConfig;
import com.miniprac.config.WebMvcConfig;
import com.miniprac.security.token.JwtAuthEntryPoint;
import com.miniprac.security.token.JwtProps;
import com.miniprac.security.token.TokenProvider;
import com.miniprac.security.userdetails.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;


@AutoConfigureRestDocs
@Import({
        WebMvcConfig.class,
        TokenProvider.class,
        JwtProps.class,
        RestDocsConfig.class,
})
public abstract class MvcTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
    @MockBean
    protected CustomUserDetailsService customUserDetailsService;
    @MockBean
    protected JwtAuthEntryPoint jwtAuthEntryPoint;
}
