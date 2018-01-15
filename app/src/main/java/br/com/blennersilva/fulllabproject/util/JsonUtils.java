package br.com.blennersilva.fulllabproject.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.com.blennersilva.fulllabproject.model.Category;
import br.com.blennersilva.fulllabproject.model.Images;
import br.com.blennersilva.fulllabproject.model.Product;
import br.com.blennersilva.fulllabproject.model.Sellers;
import br.com.blennersilva.fulllabproject.model.Skus;
import br.com.blennersilva.fulllabproject.model.SubCategory;

/**
 * Created by blennersilva on 13/01/18.
 */

public class JsonUtils {

    public static ArrayList<Product> parseProductsFromJason(JSONObject response) throws JSONException {
        ArrayList<Product> productArrayList = new ArrayList<>();
        JSONArray jsonArray = response.getJSONArray("Products");

        for (int i = 0; i < jsonArray.length(); i++) {
            ArrayList<Skus> skusArrayList = new ArrayList<>();
            ArrayList<Sellers> sellersArrayList = new ArrayList<>();
            ArrayList<Images> imagesArrayList = new ArrayList<>();

            Product product = new Product();
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            product.setAvailability(jsonObject.getBoolean("Availability"));
            product.setSkusArrayList(parseSkuFromJson(jsonObject, skusArrayList, sellersArrayList, imagesArrayList));

            productArrayList.add(product);
        }

        return productArrayList;
    }

    private static ArrayList<Skus> parseSkuFromJson(JSONObject response, ArrayList<Skus> skusArrayList, ArrayList<Sellers> sellersArrayList, ArrayList<Images> imagesArrayList) throws JSONException {
        JSONArray jsonArray = response.getJSONArray("Skus");
        Skus skus = new Skus();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            skus.setId(jsonObject.getString("Id"));
            Log.d("SKU ID", jsonObject.getString("Id"));
            skus.setName(jsonObject.getString("Name"));
            Log.d("SKU NAME", jsonObject.getString("Name"));
            skus.setOrder(jsonObject.getString("Order"));
            Log.d("SKU ORDER", jsonObject.getString("Order"));

            skus.setSellersArrayList(parseSellersFromjson(jsonObject, sellersArrayList));
            skus.setImagesArrayList(parseImagesFromjson(jsonObject, imagesArrayList));

            skusArrayList.add(skus);
        }

        return skusArrayList;
    }

    private static ArrayList<Sellers> parseSellersFromjson(JSONObject response, ArrayList<Sellers> sellersArrayList) throws JSONException {
        JSONArray jsonArray = response.getJSONArray("Sellers");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            Sellers sellers = new Sellers();
            sellers.setId(jsonObject.getString("Id"));
            sellers.setName(jsonObject.getString("Name"));
            sellers.setQuantity(jsonObject.getInt("Quantity"));
            sellers.setPrice(jsonObject.getInt("Price"));
            sellers.setListPrice(jsonObject.getInt("ListPrice"));
            sellers.setCount(jsonObject.getJSONObject("BestInstallment").getInt("Count"));
            sellers.setValue(jsonObject.getJSONObject("BestInstallment").getInt("Value"));
            sellers.setTotal(jsonObject.getJSONObject("BestInstallment").getInt("Total"));
            sellers.setRate(jsonObject.getJSONObject("BestInstallment").getInt("Rate"));

            sellersArrayList.add(sellers);
        }
        return sellersArrayList;
    }

    private static ArrayList<Images> parseImagesFromjson(JSONObject response, ArrayList<Images> imagesArrayList) throws JSONException {
        JSONArray jsonArray = response.getJSONArray("Images");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            Images images = new Images();
            images.setImageUrl(jsonObject.getString("ImageUrl"));

            imagesArrayList.add(images);

        }
        return imagesArrayList;
    }

    public static ArrayList<Category> parseCategoryFromJson(JSONObject response) throws JSONException {
        ArrayList<Category> categoryArrayList = new ArrayList<>();

        JSONArray jsonArray = response.getJSONArray("Categories");

        for (int i = 0; i < jsonArray.length(); i++) {
            ArrayList<SubCategory> subCategoryArrayList = new ArrayList<>();
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            Category category = new Category();
            category.setId(jsonObject.getInt("Id"));
            category.setName(jsonObject.getString("Name"));
            category.setSubCategoryArrayList(parseSubcategoryFromJson(jsonObject, subCategoryArrayList));

            categoryArrayList.add(category);

        }

        return categoryArrayList;
    }

    private static ArrayList<SubCategory> parseSubcategoryFromJson(JSONObject responde, ArrayList<SubCategory> subCategoryArrayList) throws JSONException {
        JSONArray jsonArray = responde.getJSONArray("SubCategories");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            SubCategory subCategory = new SubCategory();

            subCategory.setId(jsonObject.getInt("Id"));
            subCategory.setName(jsonObject.getString("Name"));

            subCategoryArrayList.add(subCategory);
        }

        return subCategoryArrayList;
    }
}
