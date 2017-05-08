package io.pivio.ganges.maven;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {

    @Test
    public void testConstructorMapping() throws IOException {
        Result result = new Result("group", "name", "version", "repo", new Date(0), "latest");
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(result);
        assertThat(json).isEqualTo("{\"repository_type\":\"repo\",\"group_id\":\"group\",\"name\":\"name\",\"version\":\"version\",\"latest_version\":\"latest\",\"release_date\":\"Thu Jan 01 01:00:00 CET 1970\",\"release_date_pretty\":\"5 decades ago\",\"release_date_timestamp\":0}");
    }

}