package com.chao.bysj.repository;

import com.chao.bysj.po.Address;
import com.chao.bysj.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by chao on 2017/4/28.
 */
public interface AddressRepository extends JpaRepository<Address,Integer> {

}
