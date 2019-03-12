package com.chao.bysj.service;

import com.chao.bysj.po.*;
import com.chao.bysj.repository.OrderItemRepository;
import com.chao.bysj.repository.OrderRepository;
import com.chao.bysj.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by chao on 2017/5/4.
 */
@Service
public class OrderManageService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderItemRepository orderItemRepository;
    @Autowired
    UserRepository userRepository;

    /**
     * 生成订单
     * @param model
     * @param request
     * @return
     */
    @Transactional
    public String  addOrder(Model model, HttpServletRequest request,Address address){
        LinkedHashMap<Integer,Item> shopCar = ( LinkedHashMap<Integer,Item>)request.getSession().getAttribute("shopCar");
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        String date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        order.setOrderTime(date);
        order.setState("未付款");
        order.setType("在线支付");
        User user = (User)request.getSession().getAttribute("user");
        order.setUser(user);
        order.setTotal(CarManageService.getTotal(shopCar));
        List<OrderItem> orderItemList = new ArrayList<OrderItem>();
        for(Item item : shopCar.values()){
            OrderItem oi = new OrderItem();
            oi.setId(UUID.randomUUID().toString());
            oi.setCount(item.getCount());
            oi.setProduct(item.getProduct());
            oi.setSubtotal(item.getSubtotal());
            oi.setOrder(order);
            orderItemList.add(oi);
            orderItemRepository.save(oi);
        }
        order.setOrderItems(orderItemList);
        List<Address> addressList = userRepository.findOne(user.getId()).getAddressId();
       if(address == null){
           if(addressList != null || addressList.size()>0){
               address = addressList.get(0);
           }
       }
        order.setAddress(address);
        orderRepository.save(order);
        model.addAttribute("order",order);
        model.addAttribute("addressList",addressList);
        return "order";
    }

    public String changeOrderAddress(Model model, HttpServletRequest request,Address address,String orderId){
        User user = (User)request.getSession().getAttribute("user");
        List<Address> addressList = userRepository.findOne(user.getId()).getAddressId();
        Order order = orderRepository.findOne(orderId);
        order.setAddress(address);
        orderRepository.save(order);
        model.addAttribute("order",order);
        model.addAttribute("addressList",addressList);
        return "order";
    }
    /**
     * 修改订单（地址，支付方式）
     * @param order
     * @param pay
     * @param model
     * @return
     */
    public String updateOrder(Order order,String pay, Model model, HttpServletRequest request){
        LinkedHashMap<Integer,Item> shopCar = ( LinkedHashMap<Integer,Item>)request.getSession().getAttribute("shopCar");
        Order _order = orderRepository.findOne(order.getId());
        _order.setType(pay);
        orderRepository.save(_order);
        shopCar.clear();
        if(pay.equals("在线支付")){
            model.addAttribute("order",_order);
            return "pay";
        }else{
            return "success";
        }
    }


    /**
     * 支付
     * @param id
     * @return
     */
    public String pay(String id){
        //TODO 支付接口
        Order order = orderRepository.findOne(id);
        order.setState("已付款");
        orderRepository.save(order);
        return "success";
    }

    /**
     * 商家查询全部订单
     */
    public String findAllOrder(Model model){
        List<Order> orderList = orderRepository.findAll();
        model.addAttribute("orderList",orderList);
        return "/admin/order";
    }

    public String findOrderByPage(Model model, Integer page, Integer size, OrderQuery orderQuery){
        Pageable pageable = new PageRequest(page,size,null);
        Page<Order> data = orderRepository.findAll(new Specification<Order>() {
             @Override
             public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                 List<Predicate> list = new ArrayList<Predicate>();
                 if (null != orderQuery.getId() && !"".equals(orderQuery.getId())) {
                     list.add(criteriaBuilder.equal(root.get("id").as(String.class), orderQuery.getId()));
                 }
                 Predicate[] p = new Predicate[list.size()];
                 return criteriaBuilder.and(list.toArray(p));
             }
        },pageable);
        model.addAttribute("orderList",data);
        return "/admin/order";
    }

    /**
     * 用户查询自己的全部订单
     */
    public String myOrder(Model model,HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");
        List<Order> orderList = orderRepository.findByUser(user);
        model.addAttribute("orderList",orderList);
        return "orderList";
    }

    /**
     * 用户按状态查询订单
     */
    public String myOrderState(Model model,HttpServletRequest request,String state){
        User user = (User)request.getSession().getAttribute("user");
        List<Order> orderList = orderRepository.findByUserAndState(user,state);
        model.addAttribute("orderList",orderList);
        return "orderList";
    }

    /**
     * 删除订单
     * @param model
     * @param request
     * @param id
     * @return
     */
    public String delMyOrder(Model model,HttpServletRequest request,String id){
        orderRepository.delete(id);
        return myOrder(model,request);
    }

    public String delOrder(Model model,String id){
        orderRepository.delete(id);
        return findAllOrder(model);
    }


}
