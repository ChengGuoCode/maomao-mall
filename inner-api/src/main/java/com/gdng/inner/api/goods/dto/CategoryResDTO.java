package com.gdng.inner.api.goods.dto;

import java.util.List;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/6 14:22
 * @Description:
 * @Version: 1.0.0
 */
public class CategoryResDTO {

    private String categoryCode;
    private String name;
    private String pic;
    private List<CategoryResDTO> sonCategoryList;

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public List<CategoryResDTO> getSonCategoryList() {
        return sonCategoryList;
    }

    public void setSonCategoryList(List<CategoryResDTO> sonCategoryList) {
        this.sonCategoryList = sonCategoryList;
    }
}
