package com.luis.yygh.common.exception;

import com.luis.yygh.common.result.ResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 自定义异常类, 该异常需要通过throw 手动抛出
 */
@Data
@ApiModel("自定义全局异常类")
public class YyghException extends RuntimeException {

    @ApiModelProperty("异常状态码")
    private Integer code;

    public YyghException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public YyghException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    @Override
    public String toString() {
        return "YyghException{" +
                "code=" + code +
                '}';
    }
}
