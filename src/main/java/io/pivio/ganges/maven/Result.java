package io.pivio.ganges.maven;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder({
        "repository_type",
        "group_id",
        "name",
        "version",
        "release_date_timestamp",
        "release_date",
        "release_date_pretty",
        "latest_version",
        "latest_version_release_date_timestamp",
        "latest_version_release_date",
        "latest_version_release_date_pretty",
        "versions_to_latest_version"
})

public class Result {

    static volatile String TYPE_MAVEN = "maven";
    @JsonProperty("latest_version")
    String latestVersion;
    @JsonProperty("latest_version_release_date_timestamp")
    private Date latestVersionReleaseDate;
    @JsonProperty("repository_type")
    private String repositoryType;
    @JsonProperty("release_date_timestamp")
    private Date releaseDate;
    @JsonProperty("group_id")
    private String groupId;
    @JsonProperty("versions_to_latest_version")
    int versionsToLatestVersion;
    @JsonProperty("name")
    private String name;
    @JsonProperty("version")
    private String version;


    public Result() {
    }

    Result(String groupId, String name, String version, String repositoryType, Date releaseDate, String latestVersion, Date latestVersionReleaseDate, int versionsToLatestVersion) {
        this.groupId = groupId;
        this.name = name;
        this.repositoryType = repositoryType;
        this.releaseDate = releaseDate;
        this.version = version;
        this.latestVersion = latestVersion;
        this.latestVersionReleaseDate = latestVersionReleaseDate;
        this.versionsToLatestVersion = versionsToLatestVersion;
    }

    @JsonProperty("release_date")
    @JsonIgnoreProperties
    public String getReleaseDateAsString() {
        return releaseDate.toString();
    }

    @JsonProperty("release_date_pretty")
    public String getReleaseDatePretty() {
        return new PrettyTime().format(releaseDate);
    }

    @JsonProperty("latest_version_release_date")
    public String getLatestVersionReleaseDateAsString() {
        return latestVersionReleaseDate.toString();
    }

    @JsonProperty("latest_version_release_date_pretty")
    public String getLatestVersionReleaseDatePretty() {
        return new PrettyTime().format(latestVersionReleaseDate);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Result result = (Result) o;

        return new EqualsBuilder()
                .append(versionsToLatestVersion, result.versionsToLatestVersion)
                .append(latestVersion, result.latestVersion)
                .append(latestVersionReleaseDate, result.latestVersionReleaseDate)
                .append(repositoryType, result.repositoryType)
                .append(releaseDate, result.releaseDate)
                .append(groupId, result.groupId)
                .append(name, result.name)
                .append(version, result.version)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(latestVersion)
                .append(latestVersionReleaseDate)
                .append(repositoryType)
                .append(releaseDate)
                .append(groupId)
                .append(versionsToLatestVersion)
                .append(name)
                .append(version)
                .toHashCode();
    }
}
