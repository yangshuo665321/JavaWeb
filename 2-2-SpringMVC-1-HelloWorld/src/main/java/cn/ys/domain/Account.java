package cn.ys.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
@ToString
public class Account implements Serializable{

    private String username;
    private String password;
    private Double money;

//    private User user;

    private List<User> list;
    private Map<String, User> map;

}
