package com.recycler.velasquez.recyclercalc.Models;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yadder on 7/17/17.
 */

public class ConfigurationDetail extends RealmObject {

    @PrimaryKey
    private int recId;

    private Configuration header;

    private Product product;

    private double unitValue;

    private String unit;

    private Date createdDateTime;

    public int getRecId() {
        return recId;
    }

    public void setRecId(int recId) {
        this.recId = recId;
    }

    public Configuration getHeader() {
        return header;
    }

    public void setHeader(Configuration header) {
        this.header = header;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getUnitValue() {
        return unitValue;
    }

    public void setUnitValue(double unitValue) {
        this.unitValue = unitValue;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(Date createdDateTime) {
        this.createdDateTime = createdDateTime;
    }
}
