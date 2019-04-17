package cn.ys.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User {
    private int id;
    private String name;
    private String gender;
    private int age;
    private String address;
    private String qq;
    private String email;

    private String username;
    private String password;
}
