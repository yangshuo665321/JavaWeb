package cn.ys.domain;

import lombok.Data;
import lombok.ToString;

/**
 * 用户的实体类
 */
@Data
@ToString
public class User {

    private int id;
    private String username;
    private String password;

}
