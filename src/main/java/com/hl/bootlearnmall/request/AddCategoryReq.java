package com.hl.bootlearnmall.request;


import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 将前端传过来的请求参数封装到一个类里，不然一串参数写起来不美观也不方便
 */
public class AddCategoryReq {

    @Size(max = 5,min = 2)
    @NotNull
    private String name;
    @NotNull
    @Max(3)
    private Integer type;
    @NotNull
    private Integer parentId;
    @NotNull
    private Integer orderNum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }
}
