package io.pivio.ganges.maven;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.mock.mockito.*;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(MavenController.class)
public class MavenControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MavenRepositoryService mavenRepositoryService;

    @Test
    public void testGetResponse() throws Exception {
        Result value = new Result("group", "name", "1", Result.TYPE_MAVEN, new Date(0), "2");
        given(this.mavenRepositoryService.getVersion("group", "name", "1"))
                .willReturn(Optional.of(value));
        ObjectMapper o = new ObjectMapper();
        this.mvc.perform(get("/maven/group/name/1").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isOk()).andExpect(content().string(o.writeValueAsString(value)));
    }

    @Test
    public void testNotFound() throws Exception {
        given(this.mavenRepositoryService.getVersion("group", "name", "1"))
                .willReturn(Optional.empty());
        this.mvc.perform(get("/maven/group/name/1").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().isNotFound());
    }
}

