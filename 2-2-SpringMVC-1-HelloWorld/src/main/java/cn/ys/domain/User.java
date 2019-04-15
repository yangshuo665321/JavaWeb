package cn.ys.domain;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class User implements Serializable{

    private String uname;
    private Integer age;

    private Date date;
}
