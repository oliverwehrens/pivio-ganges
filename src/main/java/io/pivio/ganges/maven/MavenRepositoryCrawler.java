package io.pivio.ganges.maven;

import io.pivio.ganges.maven.response.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class MavenRepositoryCrawler {

    private final RestTemplate restTemplate;

    @Autowired
    public MavenRepositoryCrawler(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Cacheable("Info")
    Info getInformation(String group, String name) {
        UriComponents uriComponents =
                UriComponentsBuilder.fromUriString("http://search.maven.org/solrsearch/select?q={query}&core=gav&rows=200&wt=json").build()
                        .expand("g:" + group + "+AND+a:" + name + "");
        ResponseEntity<Info> forEntity = restTemplate.getForEntity(uriComponents.toUri(), Info.class);
        return forEntity.getBody();
    }
}