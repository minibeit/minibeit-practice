package com.miniprac;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miniprac.config.RestDocsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;


@AutoConfigureRestDocs
@Import(RestDocsConfig.class)
public abstract class MvcTest {
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;
}
