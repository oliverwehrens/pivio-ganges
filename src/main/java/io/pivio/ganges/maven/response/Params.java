
package io.pivio.ganges.maven.response;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "fl",
    "sort",
    "indent",
    "q",
    "core",
    "wt",
    "rows",
    "version"
})
public class Params {

    @JsonProperty("fl")
    private String fl;
    @JsonProperty("sort")
    private String sort;
    @JsonProperty("indent")
    private String indent;
    @JsonProperty("q")
    private String q;
    @JsonProperty("core")
    private String core;
    @JsonProperty("wt")
    private String wt;
    @JsonProperty("rows")
    private String rows;
    @JsonProperty("version")
    private String version;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("fl")
    public String getFl() {
        return fl;
    }

    @JsonProperty("fl")
    public void setFl(String fl) {
        this.fl = fl;
    }

    @JsonProperty("sort")
    public String getSort() {
        return sort;
    }

    @JsonProperty("sort")
    public void setSort(String sort) {
        this.sort = sort;
    }

    @JsonProperty("indent")
    public String getIndent() {
        return indent;
    }

    @JsonProperty("indent")
    public void setIndent(String indent) {
        this.indent = indent;
    }

    @JsonProperty("q")
    public String getQ() {
        return q;
    }

    @JsonProperty("q")
    public void setQ(String q) {
        this.q = q;
    }

    @JsonProperty("core")
    public String getCore() {
        return core;
    }

    @JsonProperty("core")
    public void setCore(String core) {
        this.core = core;
    }

    @JsonProperty("wt")
    public String getWt() {
        return wt;
    }

    @JsonProperty("wt")
    public void setWt(String wt) {
        this.wt = wt;
    }

    @JsonProperty("rows")
    public String getRows() {
        return rows;
    }

    @JsonProperty("rows")
    public void setRows(String rows) {
        this.rows = rows;
    }

    @JsonProperty("version")
    public String getVersion() {
        return version;
    }

    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
