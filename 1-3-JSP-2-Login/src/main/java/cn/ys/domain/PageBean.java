package cn.ys.domain;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * 分页对象
 */
@Data
@ToString
public class PageBean<T> {
    private int totalCount; // 总记录数
    private int totalPage ; // 总页码
    private List<T> list ; // 每页的数据
    private int currentPage ; //当前页码
    private int rows;//每页显示的记录数
}
