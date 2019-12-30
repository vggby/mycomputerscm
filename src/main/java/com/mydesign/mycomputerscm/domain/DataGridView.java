package com.mydesign.mycomputerscm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//使用后添加一个构造函数，该构造函数含有所有已声明字段属性参数
@AllArgsConstructor
//使用后创建一个无参构造函数
@NoArgsConstructor
public class DataGridView {
    private Integer code=0;
    private String msg="";
    private Long count=0L;
    private Object data;
    public DataGridView(Long count, Object data) {
        super();
        this.count = count;
        this.data = data;
    }
    public DataGridView(Object data) {
        super();
        this.data = data;
    }
}
