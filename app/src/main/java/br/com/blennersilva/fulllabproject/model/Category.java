package br.com.blennersilva.fulllabproject.model;

import java.util.ArrayList;

/**
 * Created by blennersilva on 14/01/18.
 */

public class Category {
    private int id;
    private String name;
    private ArrayList<SubCategory> subCategoryArrayList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<SubCategory> getSubCategoryArrayList() {
        return subCategoryArrayList;
    }

    public void setSubCategoryArrayList(ArrayList<SubCategory> subCategoryArrayList) {
        this.subCategoryArrayList = subCategoryArrayList;
    }
}
