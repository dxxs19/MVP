package com.wei.mvp.datasource.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class IDCard
{
    @Id(autoincrement = true)
    private Long idCardId;

    @NotNull
    private String code;

    @Generated(hash = 1096351846)
    public IDCard(Long idCardId, @NotNull String code) {
        this.idCardId = idCardId;
        this.code = code;
    }

    @Generated(hash = 1276747893)
    public IDCard() {
    }

    public Long getIdCardId() {
        return this.idCardId;
    }

    public void setIdCardId(Long idCardId) {
        this.idCardId = idCardId;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "IDCard{" +
                "idCardId=" + idCardId +
                ", code='" + code + '\'' +
                '}';
    }
}
