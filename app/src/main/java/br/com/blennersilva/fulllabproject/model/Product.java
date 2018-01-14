package br.com.blennersilva.fulllabproject.model;

import java.util.ArrayList;

/**
 * Created by blennersilva on 13/01/18.
 */

public class Product {
    private boolean Availability;
    private ArrayList<Skus> skusArrayList;

    public boolean isAvailability() {
        return Availability;
    }

    public void setAvailability(boolean availability) {
        Availability = availability;
    }

    public ArrayList<Skus> getSkusArrayList() {
        return skusArrayList;
    }

    public void setSkusArrayList(ArrayList<Skus> skusArrayList) {
        this.skusArrayList = skusArrayList;
    }

}
