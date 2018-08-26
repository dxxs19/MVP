package com.wei.mvp.datasource.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Employee {
    @Id(autoincrement = true)
    private Long eid;
    @Property(nameInDb = "EMPLOYEE_NAME")
    private String name;
    private int age;
    private String sex;
    private Long companyId;
    @Generated(hash = 1399051761)
    public Employee(Long eid, String name, int age, String sex, Long companyId) {
        this.eid = eid;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.companyId = companyId;
    }
    @Generated(hash = 202356944)
    public Employee() {
    }
    public Long getEid() {
        return this.eid;
    }
    public void setEid(Long eid) {
        this.eid = eid;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getSex() {
        return this.sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public Long getCompanyId() {
        return this.companyId;
    }
    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eid=" + eid +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", companyId=" + companyId +
                '}';
    }
}
