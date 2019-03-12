package com.chao.bysj.po;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Blob;


/**
 * Created by chao on 2017/4/12.
 */
@Entity
@Table(name="bs_product")
public class Product {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * /产品名称
     */
    @NotNull
    private String productName;

    /**
     * 产品价格
     */
    @NotNull
    private BigDecimal price;

    /**
     * 产品图片
     */
    private String picture;

    /**
     * 产品描述
     */
    private String description;

    /**
     * 折扣
     */
    private double discount;

    /**
     * 所属菜系
     */
    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},optional = false)
    @JoinColumn(name="menu_id")
    private Menu menuCode;

    public Product() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Menu getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(Menu menuCode) {
        this.menuCode = menuCode;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", picture='" + picture + '\'' +
                ", description='" + description + '\'' +
                ", discount=" + discount +
                ", menuCode=" + menuCode +
                '}';
    }
}
