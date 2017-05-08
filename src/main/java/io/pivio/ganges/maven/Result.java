package io.pivio.ganges.maven;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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

    @JsonProperty("repository_type")
    private String repositoryType;

    @JsonProperty("release_date_timestamp")
    private Date releaseDate;

    @JsonProperty("latest_version")
    String latestVersion;

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

}
