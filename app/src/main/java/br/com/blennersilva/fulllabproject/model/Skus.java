package br.com.blennersilva.fulllabproject.model;

import java.util.ArrayList;

/**
 * Created by blennersilva on 13/01/18.
 */

public class Skus {
    private String Id;
    private String Name;
    private String Order;
    private ArrayList<Sellers> sellersArrayList;
    private ArrayList<Images> imagesArrayList;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getOrder() {
        return Order;
    }

    public void setOrder(String order) {
        Order = order;
    }

    public ArrayList<Sellers> getSellersArrayList() {
        return sellersArrayList;
    }

    public void setSellersArrayList(ArrayList<Sellers> sellersArrayList) {
        this.sellersArrayList = sellersArrayList;
    }

    public ArrayList<Images> getImagesArrayList() {
        return imagesArrayList;
    }

    public void setImagesArrayList(ArrayList<Images> imagesArrayList) {
        this.imagesArrayList = imagesArrayList;
    }
}
