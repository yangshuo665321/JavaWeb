package cn.ys.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 账户的实体类
 */
@Data
@ToString
public class Account implements Serializable {

    private Integer id;
    private String name;
    private Float money;
}
