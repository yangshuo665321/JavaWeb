package cn.ys.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 自定义异常类
 */
@Data
@AllArgsConstructor
public class SysException extends Exception{

    // 存储提示信息的
    private String message;

}
