package com.example.ForumBE.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "The response object given when requests are made to the service")
@Builder
public class TopicResponse {
    @ApiModelProperty("the topics returned from the request")
    private ArrayList<Topic> topic;
    @ApiModelProperty("An error message will display here if the request fails")
    private String errors;
    @ApiModelProperty("The resulting outcome of the request")
    private int status;
}
