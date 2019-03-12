package com.chao.bysj.po;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chao on 2017/4/10.
 */
@Entity
@Table(name="bs_menu")
public class Menu {
    /**
     * 主键id
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 菜系名称
     */
    @NotNull
    private String menu;

    /**
     * 数字表示
     */
    @NotNull
    private Integer code;

    /**
     * 产品集合
     */
    @OneToMany(mappedBy = "menuCode",cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<Product>();

    public Menu() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", menu='" + menu + '\'' +
                ", code=" + code +
                ", products=" + products +
                '}';
    }
}
