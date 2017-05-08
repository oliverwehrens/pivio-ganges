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
        "repositoryType",
        "groupId",
        "name",
        "version",
        "latestVersion",
        "releaseDateAsString",
        "prettyTime",
        "releaseDate"
})

public class Result {

    static volatile String TYPE_MAVEN = "maven";
    @JsonProperty("latest_version")
    String latestVersion;
    @JsonProperty("repository_type")
    private String repositoryType;
    @JsonProperty("release_date_timestamp")
    private Date releaseDate;
    @JsonProperty("group_id")
    private String groupId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("version")
    private String version;


    public Result() {
    }

    Result(String groupId, String name, String version, String repositoryType, Date releaseDate, String latestVersion) {
        this.groupId = groupId;
        this.name = name;
        this.repositoryType = repositoryType;
        this.releaseDate = releaseDate;
        this.version = version;
        this.latestVersion = latestVersion;
    }

    @JsonProperty("release_date")
    @JsonIgnoreProperties
    public String getReleaseDateAsString() {
        return releaseDate.toString();
    }

    @JsonProperty("release_date_pretty")
    public String getPrettyTime() {
        return new PrettyTime().format(releaseDate);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Result result = (Result) o;

        return new EqualsBuilder()
                .append(latestVersion, result.latestVersion)
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
                .append(repositoryType)
                .append(releaseDate)
                .append(groupId)
                .append(name)
                .append(version)
                .toHashCode();
    }
}
