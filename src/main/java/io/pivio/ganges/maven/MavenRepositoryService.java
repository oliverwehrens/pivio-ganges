package io.pivio.ganges.maven;

import io.pivio.ganges.maven.response.Doc;
import io.pivio.ganges.maven.response.Info;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class MavenRepositoryService {

    private MavenRepositoryCrawler mavenRepositoryCrawler;

    public MavenRepositoryService(MavenRepositoryCrawler mavenRepositoryCrawler) {
        this.mavenRepositoryCrawler = mavenRepositoryCrawler;
    }

    Optional<Result> getVersion(String groupId, String name, String version) {
        Info information = mavenRepositoryCrawler.getInformation(groupId, name);
        Doc document = information.getResponse().getDocs().stream().filter(doc -> doc.getV().equalsIgnoreCase(version)).findFirst().orElse(null);
        if (document != null) {
            Date releaseDate = new Date();
            releaseDate.setTime(document.getTimestamp());
            return Optional.of(new Result(Result.TYPE_MAVEN, releaseDate));
        }
        return Optional.empty();
    }
}
