package com.recycler.velasquez.recyclercalc.Models;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by yadder on 11/25/17.
 */

public class RateProduct extends RealmObject {
    private int         recId;
    private Date        creationDate;
    private Date        modificationDate;
    private Product     product;
    private double      price;

    public int getRecId() {
        return recId;
    }

    public void setRecId(int recId) {
        this.recId = recId;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
