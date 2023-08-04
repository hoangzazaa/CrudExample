package vn.vnpt.stc.ol.apigw.api.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.vnpt.stc.ol.apigw.constants.Status;
import vn.vnpt.stc.ol.apigw.exception.BaseException;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "Common Api Response", description = "Common Api Response")
public class ApiResponse<T> implements Serializable {
    /**
     * 状态码
     */
    @ApiModelProperty(value = "return code", required = true)
    private Integer code;

    /**
     * 返回内容
     */
    @ApiModelProperty(value = "return content", required = true)
    private String message;

    /**
     * 返回数据
     */
    @ApiModelProperty(value = "return data", required = true)
    private T data;

    /**
     * 构造一个自定义的API返回
     *
     * @param code    状态码
     * @param message 返回内容
     * @param data    返回数据
     * @return ApiResponse
     */
    public static <T> ApiResponse of(Integer code, String message, T data) {
        return new ApiResponse(code, message, data);
    }

    /**
     * 构造一个成功且带数据的API返回
     *
     * @param data 返回数据
     * @return ApiResponse
     */
    public static <T> ApiResponse ofSuccess(T data) {
        return ofStatus(Status.OK, data);
    }

    /**
     * 构造一个成功且自定义消息的API返回
     *
     * @param message 返回内容
     * @return ApiResponse
     */
    public static ApiResponse ofMessage(String message) {
        return of(Status.OK.getCode(), message, null);
    }

    /**
     * 构造一个有状态的API返回
     *
     * @param status 状态 {@link Status}
     * @return ApiResponse
     */
    public static ApiResponse ofStatus(Status status) {
        return ofStatus(status, null);
    }

    /**
     * 构造一个有状态且带数据的API返回
     *
     * @param status 状态 {@link Status}
     * @param data   返回数据
     * @return ApiResponse
     */
    public static <T> ApiResponse ofStatus(Status status, T data) {
        return of(status.getCode(), status.getMessage(), data);
    }

    /**
     * 构造一个异常且带数据的API返回
     *
     * @param t    异常
     * @param data 返回数据
     * @param <T>  {@link BaseException} 的子类
     * @return ApiResponse
     */
    public static <T extends BaseException> ApiResponse ofException(T t, Object data) {
        return of(t.getCode(), t.getMessage(), data);
    }

    /**
     * 构造一个异常且带数据的API返回
     *
     * @param t   异常
     * @param <T> {@link BaseException} 的子类
     * @return ApiResponse
     */
    public static <T extends BaseException> ApiResponse ofException(T t) {
        return ofException(t, null);
    }
}
