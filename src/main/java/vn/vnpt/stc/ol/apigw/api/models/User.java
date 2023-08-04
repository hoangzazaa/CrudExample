package vn.vnpt.stc.ol.apigw.api.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "User", description = "User Entity")
public class User {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "id", required = true)
    private Integer id;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "name", required = true)
    private String name;
    /**
     * 工作岗位
     */
    @ApiModelProperty(value = "job", required = true)
    private String job;

}
