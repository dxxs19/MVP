package com.wei.mvp.datasource.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

import java.util.List;
import org.greenrobot.greendao.DaoException;
import com.wei.mvp.datasource.greendao.DaoSession;
import com.wei.mvp.datasource.greendao.EmployeeDao;
import com.wei.mvp.datasource.greendao.CompanyDao;

// @Entity 它会把当前类映射成默认以类名为表名的数据库表，每一个Entity对象对应着数据库表中的一行记录。
@Entity
public class Company {

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    @Property(nameInDb = "COMPANY_NAME")
    private String name;

    private String address;

    @Transient
    private int tempUsageCount;

    @ToMany(referencedJoinProperty = "companyId")
    private List<Employee> employees;

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 458770942)
    private transient CompanyDao myDao;

    @Generated(hash = 79655001)
    public Company(Long id, @NotNull String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    @Generated(hash = 1096856789)
    public Company() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1618489193)
    public List<Employee> getEmployees() {
        if (employees == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            EmployeeDao targetDao = daoSession.getEmployeeDao();
            List<Employee> employeesNew = targetDao._queryCompany_Employees(id);
            synchronized (this) {
                if (employees == null) {
                    employees = employeesNew;
                }
            }
        }
        return employees;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1253254391)
    public synchronized void resetEmployees() {
        employees = null;
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
    @Generated(hash = 1533027800)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCompanyDao() : null;
    }
    
}
