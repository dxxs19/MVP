package com.wei.mvp.datasource.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.ToOne;
import org.greenrobot.greendao.DaoException;
import com.wei.mvp.datasource.greendao.DaoSession;
import com.wei.mvp.datasource.greendao.IDCardDao;
import com.wei.mvp.datasource.greendao.EmployeeDao;

@Entity
public class Employee {
    @Id(autoincrement = true)
    private Long eid;
    @Property(nameInDb = "EMPLOYEE_NAME")
    private String name;
    private int age;
    private String sex;
    private Long companyId;
    private Long idCardId;
    @ToOne(joinProperty = "idCardId")
    private IDCard idCard;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 694547204)
    private transient EmployeeDao myDao;
    @Generated(hash = 602561657)
    private transient Long idCard__resolvedKey;

    @Generated(hash = 691310262)
    public Employee(Long eid, String name, int age, String sex, Long companyId, Long idCardId) {
        this.eid = eid;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.companyId = companyId;
        this.idCardId = idCardId;
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
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1868177483)
    public IDCard getIdCard() {
        Long __key = this.idCardId;
        if (idCard__resolvedKey == null || !idCard__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            IDCardDao targetDao = daoSession.getIDCardDao();
            IDCard idCardNew = targetDao.load(__key);
            synchronized (this) {
                idCard = idCardNew;
                idCard__resolvedKey = __key;
            }
        }
        return idCard;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 394403598)
    public void setIdCard(IDCard idCard) {
        synchronized (this) {
            this.idCard = idCard;
            idCardId = idCard == null ? null : idCard.getIdCardId();
            idCard__resolvedKey = idCardId;
        }
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }
    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 671679171)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getEmployeeDao() : null;
    }
    public Long getIdCardId() {
        return this.idCardId;
    }
    public void setIdCardId(Long idCardId) {
        this.idCardId = idCardId;
    }
}
