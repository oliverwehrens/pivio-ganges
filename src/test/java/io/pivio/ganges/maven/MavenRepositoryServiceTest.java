package io.pivio.ganges.maven;

import io.pivio.ganges.maven.response.Doc;
import io.pivio.ganges.maven.response.Info;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MavenRepositoryServiceTest {

    private MavenRepositoryService mavenRepositoryService;
    private MavenRepositoryCrawler mavenRepositoryCrawlerMock;

    @Before
    public void setup() {
        mavenRepositoryCrawlerMock = mock(MavenRepositoryCrawler.class);
        mavenRepositoryService = new MavenRepositoryService(mavenRepositoryCrawlerMock);
    }

    @Test
    public void testEmptyResponse() throws Exception {
        Info info = new Info();
        when(mavenRepositoryCrawlerMock.getInformation("groupId", "name")).thenReturn(info);
        Optional<Result> version = mavenRepositoryService.getVersion("groupId", "name", "1");
        assertThat(version.isPresent()).isFalse();
    }

    @Test
    public void testGetLatestVersion() throws Exception {
        Info info = new Info();
        Doc askedVersion = createDoc("group", "name", "1", 1000L);
        Doc midVersion = createDoc("group", "name", "2", 2000L);
        Doc newerVersion = createDoc("group", "name", "3", 3000L);
        info.getResponse().getDocs().add(askedVersion);
        info.getResponse().getDocs().add(midVersion);
        info.getResponse().getDocs().add(newerVersion);
        when(mavenRepositoryCrawlerMock.getInformation("groupId", "name")).thenReturn(info);
        Optional<Result> version = mavenRepositoryService.getVersion("groupId", "name", "1");
        assertThat(version.get().latestVersion).isEqualTo("3");
    }

    private Doc createDoc(String group, String name, String version, long timestamp) {
        Doc askedVersion = new Doc();
        askedVersion.setV(version);
        askedVersion.setG(group);
        askedVersion.setA(name);
        askedVersion.setTimestamp(timestamp);
        return askedVersion;
    }

}