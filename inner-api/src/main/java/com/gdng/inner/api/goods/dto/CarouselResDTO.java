package com.gdng.inner.api.goods.dto;

/**
 * @Auther: guocheng
 * @CreateDate: 2022/7/5 17:21
 * @Description:
 * @Version: 1.0.0
 */
public class CarouselResDTO {

    private Integer no;
    private String productName;
    private String productImg;
    private String productCode;

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }
}
