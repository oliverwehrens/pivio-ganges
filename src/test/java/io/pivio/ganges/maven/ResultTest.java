package io.pivio.ganges.maven;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultTest {

    @Test
    public void testConstructorMapping() throws IOException {
        Result result = new Result("group", "name", "version", "repo", new Date(0), "latest", new Date(1), 0);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(result);
        Result rebuild = objectMapper.readValue(json, Result.class);
        assertThat(rebuild).isEqualToComparingFieldByField(result);
    }

}