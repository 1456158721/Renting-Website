package com.qwq.entity;

import com.qwq.entity.User;

import javax.persistence.*;
import java.util.Date;

/**
 *   房屋类
 *   @Author: QWQ
 *   @Date: 2019.11.01 14:30:00
 *   @Version: 1.0
 */
@Entity
@Table(name="T_House")
public class House {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_House")
    @SequenceGenerator(name="SEQ_House", sequenceName="SEQ_House_Id")
    private Integer houseId;
    @Column(name = "houseTitle",length = 50)
    private String houseTitle;
    @Column(name = "houseDescription",length = 2000)
    private String houseDescription;
    @Column(name = "housePrice",length = 6)
    private Double housePrice;
    @Column(name = "housePubDate")
    private Date housePubDate;
    @Column(name = "houseFloorAge",length = 4)
    private Double houseFloorAge;
    @Column(name="houseContact",length = 100)
    private String houseContact;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "houseFromUserId")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "houseTypeId")
    private HouseType typeId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "houseStreetId")
    private Street streetId;

    public House(){

    }

    public House( Integer houseId,String houseTitle,String houseDescription,Double housePrice,Date housePubDate,Double houseFloorAge,String houseContact,User user,HouseType typeId,Street streetId ) {
        this.houseId = houseId;
        this.houseTitle = houseTitle;
        this.houseDescription = houseDescription;
        this.housePrice = housePrice;
        this.housePubDate = housePubDate;
        this.houseFloorAge = houseFloorAge;
        this.houseContact = houseContact;
        this.user = user;
        this.typeId = typeId;
        this.streetId = streetId;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId( Integer houseId ) {
        this.houseId = houseId;
    }

    public String getHouseTitle() {
        return houseTitle;
    }

    public void setHouseTitle( String houseTitle ) {
        this.houseTitle = houseTitle;
    }

    public String getHouseDescription() {
        return houseDescription;
    }

    public void setHouseDescription( String houseDescription ) {
        this.houseDescription = houseDescription;
    }

    public Double getHousePrice() {
        return housePrice;
    }

    public void setHousePrice( Double housePrice ) {
        this.housePrice = housePrice;
    }

    public Date getHousePubDate() {
        return housePubDate;
    }

    public void setHousePubDate( Date housePubDate ) {
        this.housePubDate = housePubDate;
    }

    public Double getHouseFloorAge() {
        return houseFloorAge;
    }

    public void setHouseFloorAge( Double houseFloorAge ) {
        this.houseFloorAge = houseFloorAge;
    }

    public String getHouseContact() {
        return houseContact;
    }

    public void setHouseContact( String houseContact ) {
        this.houseContact = houseContact;
    }

    public User getUser() {
        return user;
    }

    public void setUser( User user ) {
        this.user = user;
    }

    public HouseType getTypeId() {
        return typeId;
    }

    public void setTypeId( HouseType typeId ) {
        this.typeId = typeId;
    }

    public Street getStreetId() {
        return streetId;
    }

    public void setStreetId( Street streetId ) {
        this.streetId = streetId;
    }
}
