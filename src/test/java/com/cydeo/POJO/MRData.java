package com.cydeo.POJO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class MRData {

    private String limit;
    private String total;
    private String offset;
    @JsonProperty("StatusTable")
    private StatusTable statusTable;



}
