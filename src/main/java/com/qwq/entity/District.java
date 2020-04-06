package com.qwq.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 区县表类
 * @Author: QWQ
 * @Date: 2019.11.01 16:47:52
 * @Version: 1.0
 */

@Entity
@Table( name = "T_District" )
public class District {
    @Id
    @GeneratedValue( strategy = GenerationType.SEQUENCE, generator = "SEQ_District" )
    @SequenceGenerator( name = "SEQ_District", sequenceName = "SEQ_District_ID" )
    private Integer districtId;
    @Column( name = "districtName", length = 50 )
    private String districtName;
    @OneToMany( fetch = FetchType.LAZY, mappedBy = "districtId" )
    Set<Street> streets = new HashSet<>();

    public District() {

    }

    public District( Integer districtId,String districtName ) {
        this.districtId = districtId;
        this.districtName = districtName;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId( Integer districtId ) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName( String districtName ) {
        this.districtName = districtName;
    }

    public Set<Street> getStreets() {
        return streets;
    }

    public void setStreets( Set<Street> streets ) {
        this.streets = streets;
    }
}
