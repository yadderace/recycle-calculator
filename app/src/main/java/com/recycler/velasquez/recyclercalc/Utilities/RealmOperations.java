package com.recycler.velasquez.recyclercalc.Utilities;

import com.recycler.velasquez.recyclercalc.Models.Configuration;
import com.recycler.velasquez.recyclercalc.Models.ConfigurationDetail;
import com.recycler.velasquez.recyclercalc.Models.Product;
import com.recycler.velasquez.recyclercalc.Models.Rate;
import com.recycler.velasquez.recyclercalc.Models.RateProduct;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by yadder on 7/19/17.
 */

public class RealmOperations {

    private Realm realm;

    /**
     * Create instance for realm object
     */
    public RealmOperations(){
        realm = Realm.getDefaultInstance();
    }

    /**
     * Set the realm object
     * @param realm
     */
    public RealmOperations(Realm realm){
        this.realm = realm;
    }

    /**
     * Close the realm connection
     */
    public void closeRealm(){
        if(realm != null && !realm.isClosed())
            realm.close();
    }

    // ##############################################################################################
    // PRODUCT

    /**
     * Save or update a product
     * @param product
     */
    public void saveProduct(final Product product){

        //If not found the product create a new product
        if(getProduct(product.getRecId()) == null)
        {
            Number currentMaxId = realm.where(Product.class).max("recId");

            int nextId;
            if(currentMaxId == null)
                nextId = 1;
            else
                nextId = currentMaxId.intValue() + 1;

            product.setRecId(nextId);
        }

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(product);
            }
        });
    }

    /**
     * Get the product according the recId
     * @param productRecId
     * @return
     */
    public Product getProduct(int productRecId){
        Product product = null;
        Product realmProduct = realm.where(Product.class).equalTo("recId", productRecId).findFirst();
        if(realmProduct != null){
            product = realm.copyFromRealm(realmProduct);
        }
        return product;
    }

    /**
     * Find the registered products in the realm database
     * @return
     */
    public ArrayList<Product> getAllProducts(){
        List<Product> products = null;
        RealmResults<Product> realmProducts = realm.where(Product.class).findAll();
        if(realmProducts != null){
            products = realm.copyFromRealm(realmProducts);
        }
        return (ArrayList<Product>) products;
    }


    // ##############################################################################################
    // RATE

    /**
     * Save or update a rate
     * @param rate
     */
    public void saveRate(final Rate rate){

        //If not found the product create a new product
        if(getRate(rate.getRecId()) == null)
        {
            Number currentMaxId = realm.where(Rate.class).max("recId");

            int nextId;
            if(currentMaxId == null)
                nextId = 1;
            else
                nextId = currentMaxId.intValue() + 1;

            rate.setRecId(nextId);

            for(int idx = 0; idx < rate.getRateProducts().size(); idx++) {
                int recId = saveRateProduct(rate.getRateProducts().get(idx));
                rate.getRateProducts().get(idx).setRecId(recId);
            }
        }

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(rate);
            }
        });
    }

    /**
     * Get the rate according the recId
     * @param rateRecId
     * @return
     */
    public Rate getRate(int rateRecId){
        Rate rate = null;
        Rate realmRate = realm.where(Rate.class).equalTo("recId", rateRecId).findFirst();
        if(realmRate != null){
            rate = realm.copyFromRealm(realmRate);
        }
        return rate;
    }

    // ##############################################################################################
    // RATEPRODUCT

    /**
     * Save or update a rate product
     * @param rateProduct
     */
    public int saveRateProduct(final RateProduct rateProduct){

        //If not found the product create a new product
        if(getRateProduct(rateProduct.getRecId()) == null)
        {
            Number currentMaxId = realm.where(RateProduct.class).max("recId");

            int nextId;
            if(currentMaxId == null)
                nextId = 1;
            else
                nextId = currentMaxId.intValue() + 1;

            rateProduct.setRecId(nextId);
        }

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(rateProduct);
            }
        });

        return rateProduct.getRecId();
    }

    /**
     * Get the rate product according the recId
     * @param rateProductRecId
     * @return
     */
    public RateProduct getRateProduct(int rateProductRecId){
        RateProduct rateProduct = null;
        RateProduct realmRateProduct = realm.where(RateProduct.class).equalTo("recId", rateProductRecId).findFirst();
        if(realmRateProduct != null){
            rateProduct = realm.copyFromRealm(realmRateProduct);
        }
        return rateProduct;
    }

    // ##############################################################################################
    // CONFIGURATION

    public Configuration createNewConfiguration(){
        Configuration configuration = new Configuration();
        return configuration;
    }

    /**
     * Save or update a Configuration Object
     * @param configuration
     */
    public void saveConfiguration(final Configuration configuration){

        //If not found the product create a new product
        if(getConfiguration(configuration.getRecId()) == null)
        {
            Number currentMaxId = realm.where(Configuration.class).max("recId");

            int nextId;
            if(currentMaxId == null)
                nextId = 1;
            else
                nextId = currentMaxId.intValue() + 1;

            configuration.setRecId(nextId);
        }

        realm.executeTransaction(new Realm.Transaction(){
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(configuration);
            }
        });
    }

    /**
     * Find and return a configuration according the recId
     * @param configurationRecId
     * @return
     */
    public Configuration getConfiguration(int configurationRecId){
        Configuration configuration = null;
        Configuration realmConfiguration = realm.where(Configuration.class).equalTo("recId", configurationRecId).findFirst();
        if(realmConfiguration != null){
            configuration = realm.copyFromRealm(realmConfiguration);
        }
        return configuration;
    }

    /**
     * Return the current saved active configuration
     * @return
     */
    public Configuration getActiveConfiguration(){
        Configuration configuration = null;
        Configuration realmConfiguration = realm.where(Configuration.class).equalTo("active", true).findFirst();
        if(realmConfiguration != null){
            configuration = realm.copyFromRealm(realmConfiguration);
        }
        return configuration;
    }


    /**
     * Set all registered configurations active to false
     */
    public void setActiveConfigurationsToFalse(){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Configuration> configurations = realm.where(Configuration.class).findAll();
                for(Configuration configuration : configurations) {
                    configuration.setActive(false);
                }
            }
        });
    }

    // ##############################################################################################
    // CONFIGURATION DETAIL

    /**
     * Save or update a ConfigurationDetail Object
     * @param configurationDetail
     */
    public void saveConfigurationDetail(final ConfigurationDetail configurationDetail){

        //If not found the product create a new product
        if(getConfigurationDetail(configurationDetail.getRecId()) == null)
        {
            Number currentMaxId = realm.where(ConfigurationDetail.class).max("recId");

            int nextId;
            if(currentMaxId == null)
                nextId = 1;
            else
                nextId = currentMaxId.intValue() + 1;

            configurationDetail.setRecId(nextId);
        }

        realm.executeTransaction(new Realm.Transaction(){
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(configurationDetail);
            }
        });
    }


    public ConfigurationDetail getConfigurationDetail(int configurationDetailRecId){
        ConfigurationDetail configurationDetail = null;
        ConfigurationDetail realmConfigurationDetail = realm.where(ConfigurationDetail.class).equalTo("recId", configurationDetailRecId).findFirst();
        if(realmConfigurationDetail != null){
            configurationDetail = realm.copyFromRealm(realmConfigurationDetail);
        }
        return configurationDetail;
    }

    public ArrayList<ConfigurationDetail> getConfigurationDetailByConfiguration(int configurationRecId){
        ArrayList<ConfigurationDetail> list = new ArrayList<ConfigurationDetail>();
        ConfigurationDetail configurationDetail = null;
        RealmResults<ConfigurationDetail> realmConfigurationDetail = realm.where(ConfigurationDetail.class).equalTo("header.recId", configurationRecId).findAll();
        list.addAll(realm.copyFromRealm(realmConfigurationDetail));
        return list;
    }


}
