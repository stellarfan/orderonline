package com.chao.bysj.po;

import java.math.BigDecimal;

/**
 * Created by chao on 2017/5/2.
 */
public class Item {
    private Integer count;
    private Product product;

    public Item() {
    }

    public Item(Integer count, Product product) {
        this.count = count;
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getSubtotal() {
        return product.getPrice().multiply(new BigDecimal(count+""));
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
