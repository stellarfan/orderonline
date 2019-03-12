package com.chao.bysj.po;

/**
 * Created by chao on 2017/5/17.
 */
public class OrderQuery {
    private String id;
    private String user;
    private String phone;
    private String data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "OrderQuery{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", phone='" + phone + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
