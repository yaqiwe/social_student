package entity;

import lombok.Data;

import java.util.List;

/**
 * @Author yaqiwe
 * @Date 2020/3/15 15:19
 * @Version 1.0
 * 分页数据格式
 */
@Data
public class PageResoult <T> {

    private Long total;

    private List<T> rows;

    public PageResoult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public PageResoult() {
    }
}
