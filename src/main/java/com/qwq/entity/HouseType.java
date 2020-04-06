package com.qwq.entity;

import com.qwq.entity.House;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 房屋类型类
 * @Author: QWQ
 * @Date: 2019.11.01 16:27:13
 * @Version: 1.0
 */
@Entity
@Table(name="T_HouseType")
public class HouseType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ_UserType")
    @SequenceGenerator( name = "SEQ_UserType",sequenceName = "SEQ_UserType_ID")
    private Integer houseTypeId;
    @Column(name = "houseTypeName",length = 50)
    private String houseTypeName;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "typeId")
    private Set<House> houses = new HashSet<>();
    
    public HouseType(){
        
    }

    public HouseType( Integer houseTypeId,String houseTypeName,Set<House> houses ) {
        this.houseTypeId = houseTypeId;
        this.houseTypeName = houseTypeName;
        this.houses = houses;
    }

    public Integer getHouseTypeId() {
        return houseTypeId;
    }

    public void setHouseTypeId( Integer houseTypeId ) {
        this.houseTypeId = houseTypeId;
    }

    public String getHouseTypeName() {
        return houseTypeName;
    }

    public void setHouseTypeName( String houseTypeName ) {
        this.houseTypeName = houseTypeName;
    }

    public Set<House> getHouses() {
        return houses;
    }

    public void setHouses( Set<House> houses ) {
        this.houses = houses;
    }
}
