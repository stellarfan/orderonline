package com.chao.bysj.po;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by chao on 2017/3/23.
 */
@Entity
@Table(name="bs_user")
public class User implements Serializable {
    /**
     *主键ID
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     *账号
     */
    @NotNull
    private String account;

    /**
     *真实姓名
     */
    @NotNull
    private String username;

    /**
     * 密码
     */
    @NotNull
    private String password;

    /**
     * 邮箱
     */
    @NotNull
    private String mail;

    /**
     * 电话
     */
    @Column(unique = true)
    private String phone;

    /**
     * 收货地址
     */
    @OneToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private List<Address> addressId = new ArrayList<Address>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<Order>();

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Address> getAddressId() {
        return addressId;
    }

    public void setAddressId(List<Address> addressId) {
        this.addressId = addressId;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                ", addressId=" + addressId +
                '}';
    }
}
