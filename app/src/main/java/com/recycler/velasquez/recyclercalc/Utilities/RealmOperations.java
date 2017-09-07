package com.recycler.velasquez.recyclercalc.Utilities;

import com.recycler.velasquez.recyclercalc.Models.Configuration;
import com.recycler.velasquez.recyclercalc.Models.ConfigurationDetail;
import com.recycler.velasquez.recyclercalc.Models.Product;

import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;
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

    public static void initRealmDatabase(){
        RealmOperations realmOperations = new RealmOperations();

        Configuration configuration = new Configuration();
        configuration.setRecId(1);
        configuration.setCreatedDateTime(new Date());
        configuration.setActive(true);
        realmOperations.saveConfiguration(configuration);

        Product product = new Product();
        product.setRecId(1);
        product.setName("Aluminio");
        product.setDescription("");
        product.setCreatedDateTime(new Date());
        product.setIcon("ic_aluminum");
        realmOperations.saveProduct(product);

        ConfigurationDetail configurationDetail = new ConfigurationDetail();
        configurationDetail.setRecId(1);
        configurationDetail.setHeader(configuration);
        configurationDetail.setProduct(product);
        configurationDetail.setUnit("lb");
        configurationDetail.setUnitValue(1.25);
        realmOperations.saveConfigurationDetail(configurationDetail);

        product.setRecId(2);
        product.setName("Plastico");
        product.setDescription("");
        product.setCreatedDateTime(new Date());
        product.setIcon("ic_plastic");
        realmOperations.saveProduct(product);

        configurationDetail.setRecId(2);
        configurationDetail.setHeader(configuration);
        configurationDetail.setProduct(product);
        configurationDetail.setUnit("lb");
        configurationDetail.setUnitValue(1.25);
        realmOperations.saveConfigurationDetail(configurationDetail);

        product.setRecId(3);
        product.setName("Vidrio");
        product.setDescription("");
        product.setCreatedDateTime(new Date());
        product.setIcon("ic_glass");
        realmOperations.saveProduct(product);

        configurationDetail.setRecId(3);
        configurationDetail.setHeader(configuration);
        configurationDetail.setProduct(product);
        configurationDetail.setUnit("lb");
        configurationDetail.setUnitValue(1.25);
        realmOperations.saveConfigurationDetail(configurationDetail);

        product.setRecId(4);
        product.setName("Plastico Duro");
        product.setDescription("");
        product.setCreatedDateTime(new Date());
        product.setIcon("ic_hard_plastic");
        realmOperations.saveProduct(product);

        configurationDetail.setRecId(4);
        configurationDetail.setHeader(configuration);
        configurationDetail.setProduct(product);
        configurationDetail.setUnit("lb");
        configurationDetail.setUnitValue(1.25);
        realmOperations.saveConfigurationDetail(configurationDetail);

        realmOperations.closeRealm();

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

    /**
     * Find and return a configuration according the recId
     * @param configurationRecId
     * @return
     */
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
