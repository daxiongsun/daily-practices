package com.ngsky.microservice.simple.consumer.bean;

import java.math.BigDecimal;

/**
 * <dl>
 * <dt>User</dt>
 * <dd>Description:</dd>
 * <dd>CreateDate: 11/11/2018 10:36 AM</dd>
 * </dl>
 *
 * @author daxiong
 */
public class User {
    private Long id;
    private String username;
    private String name;
    private Integer age;
    private BigDecimal balance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
