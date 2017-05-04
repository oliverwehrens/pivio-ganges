package io.pivio.ganges.maven;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ocpsoft.prettytime.PrettyTime;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {

    public static volatile String TYPE_MAVEN = "maven";

    @JsonProperty("repository_type")
    public String repositoryType;

    @JsonProperty("release_date_timestamp")
    public Date releaseDate;

    public Result(String repositoryType, Date releaseDate) {
        this.repositoryType = repositoryType;
        this.releaseDate = releaseDate;
    }

    @JsonProperty("release_date")
    public String getReleaseDateAsString() {
        return releaseDate.toString();
    }

    @JsonProperty("release_date_pretty")
    public String getPrettyTime() {
        return new PrettyTime().format(releaseDate).toString();
    }

}
