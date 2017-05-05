package io.pivio.ganges.maven;

import io.pivio.ganges.maven.response.Info;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

@RunWith(SpringRunner.class)
@RestClientTest(MavenRepositoryCrawler.class)
public class MavenRepositoryCrawlerTest {

    @Autowired
    private MockRestServiceServer server;

    @Autowired
    private MavenRepositoryCrawler mavenRepositoryCrawler;

    @Test
    public void getInformation() throws Exception {
        String expectedResponse = new String(Files.readAllBytes(Paths.get("src/test/resources/maven-junit-response.json")));
        server.expect(requestTo("http://search.maven.org/solrsearch/select?q=g:junit+AND+a:junit&core=gav&rows=200&wt=json"))
                .andExpect(method(GET)).andRespond(MockRestResponseCreators.withStatus(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(expectedResponse));

        Info information = mavenRepositoryCrawler.getInformation("junit", "junit");
        assertThat(information.getResponse().getDocs()).hasSize(24);
    }

}