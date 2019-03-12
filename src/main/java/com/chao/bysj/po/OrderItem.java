package com.chao.bysj.po;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by chao on 2017/5/3.
 */
@Entity
@Table(name="bs_orderitem")
public class OrderItem {
    @Id
    private String id;

    @OneToOne
    @JoinColumn(name="product_id")
    private Product product;

    private Integer count;

    private BigDecimal subtotal;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},optional = false)
    @JoinColumn(name="order_id")
    private Order order;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
