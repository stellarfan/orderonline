package com.chao.bysj.po;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chao on 2017/5/2.
 */
@Entity
@Table(name="bs_order")
public class Order {
    @Id
    private String id;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.REFRESH},optional = false)
    @JoinColumn(name="user_id")
    private User user;

    private String orderTime;

    private BigDecimal total;

    private String state;

    private String type;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name="address")
    private Address address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", orderTime='" + orderTime + '\'' +
                ", total=" + total +
                ", state='" + state + '\'' +
                ", type='" + type + '\'' +
                ", orderItems=" + orderItems +
                ", address=" + address +
                '}';
    }
}
