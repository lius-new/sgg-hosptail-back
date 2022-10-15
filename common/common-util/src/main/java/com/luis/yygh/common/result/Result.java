package com.luis.yygh.common.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import jdk.dynalink.beans.StaticClass;
import lombok.Data;

@Data
@ApiModel(value = "全局统一返回结果")
public class Result<T> {

    @ApiModelProperty(value="返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private T data;

    public Result(){

    }

    /**
     * 根据传入的数据来创建结果类
     */
    protected static  <T> Result<T> build(T data){
        Result<T> tResult = new Result<>();
        if (data != null)
            tResult.setData(data);
        return tResult;
    }

    /**
     * 根据传入的数据和枚举类型来创建结果类
     */
    public static <T> Result<T> build(T body,ResultEnum resultEnum){
        Result<T> build = build(body);
        build.setCode(resultEnum.getCode());
        build.setMessage(resultEnum.getMessage());
        return build;
    }

    /**
     * 结果类的类型是由build中的T决定的
     * 泛型方法：泛型方法修饰符后的<T>的类型可以是参数决定, 也可以是方法体中有同样的泛型标识
     */
    public static <T> Result<T> build(Integer code,String message){
        Result<T> result = build(null);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    public static <T> Result<T> ok(){
        return Result.ok(null);
    }

    /**
     * 操作成功
     */
    public static <T> Result<T> ok(T data){
        return build(data,ResultEnum.SUCCESS);
    }

    public static <T> Result<T> fail(){
        return Result.fail(null);
    }

    /**
     * 操作失败
     */
    public static <T> Result<T> fail(T data){
        return build(data,ResultEnum.FAIL);
    }

    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }
    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }

    /**
     * 结果是否成功
     */
    public boolean isOk(){
        return this.getCode().intValue() == ResultEnum.SUCCESS.getCode().intValue();
    }
}
