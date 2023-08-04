package vn.vnpt.stc.ol.apigw.api.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Command", description = "Command")
public class Command {
    @ApiModelProperty(value = "id", required = true)
    private long id;
    @ApiModelProperty(value = "url", required = true)
    private String url;
    @ApiModelProperty(value = "name", required = true)
    private String name;
    @ApiModelProperty(value = "method", required = true)
    private String method;
    @ApiModelProperty(value = "created", required = true)
    private long created;
    @ApiModelProperty(value = "updated", required = true)
    private long updated;

}
