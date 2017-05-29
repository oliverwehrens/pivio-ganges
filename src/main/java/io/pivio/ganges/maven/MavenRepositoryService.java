package io.pivio.ganges.maven;

import io.pivio.ganges.maven.response.Doc;
import io.pivio.ganges.maven.response.Info;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
class MavenRepositoryService {

    private MavenRepositoryCrawler mavenRepositoryCrawler;

    MavenRepositoryService(MavenRepositoryCrawler mavenRepositoryCrawler) {
        this.mavenRepositoryCrawler = mavenRepositoryCrawler;
    }

    Optional<Result> getVersion(String groupId, String name, String version) {
        Info information = mavenRepositoryCrawler.getInformation(groupId, name);
        Doc document = information.getResponse().getDocs().stream().filter(doc -> doc.getV().equalsIgnoreCase(version)).findFirst().orElse(null);
        if (document != null) {
            Date releaseDate = new Date();
            releaseDate.setTime(document.getTimestamp());
            Long newestVersionTimestamp = 0L;
            String latestVersion = "";

            Date latestVersionReleaseDate = new Date();
            latestVersionReleaseDate.setTime(document.getTimestamp());

            int versionsBetweenLatestAndAsked = 0;

            for (Doc doc : information.getResponse().getDocs()) {
                if (document.getTimestamp() < doc.getTimestamp()) {
                    versionsBetweenLatestAndAsked++;
                }
                if (doc.getTimestamp() > newestVersionTimestamp) {
                    newestVersionTimestamp = doc.getTimestamp();
                    latestVersion = doc.getV();
                    latestVersionReleaseDate.setTime(doc.getTimestamp());
                }
            }


            Result result = new Result(groupId, name, version, Result.TYPE_MAVEN, releaseDate, latestVersion, latestVersionReleaseDate, versionsBetweenLatestAndAsked);
            return Optional.of(result);
        }
        return Optional.empty();
    }
}
