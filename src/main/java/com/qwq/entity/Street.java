package com.qwq.entity;

import com.qwq.entity.District;
import com.qwq.entity.House;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 街道类
 * @Author: QWQ
 * @Date: 2019.11.01 16:54:14
 * @Version: 1.0
 */
@Entity
@Table(name="T_Street")
public class Street {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_Street")
    @SequenceGenerator( name = "SEQ_Street",sequenceName = "SEQ_Street_ID")
    Integer streetId;
    @Column(name = "streetName",length = 50)
    String streetName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "districtId")
    District districtId;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "streetId")
    Set<House> houses = new HashSet<>();
    
    public Street(){
        
    }

    public Street( Integer streetId,String streetName,District districtId ) {
        this.streetId = streetId;
        this.streetName = streetName;
        this.districtId = districtId;
    }

    public Integer getStreetId() {
        return streetId;
    }

    public void setStreetId( Integer streetId ) {
        this.streetId = streetId;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName( String streetName ) {
        this.streetName = streetName;
    }

    public District getDistrictId() {
        return districtId;
    }

    public void setDistrictId( District districtId ) {
        this.districtId = districtId;
    }

    public Set<House> getHouses() {
        return houses;
    }

    public void setHouses( Set<House> houses ) {
        this.houses = houses;
    }
}
