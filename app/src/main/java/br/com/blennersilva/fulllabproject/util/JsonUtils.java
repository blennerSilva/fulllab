package br.com.blennersilva.fulllabproject.util;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.com.blennersilva.fulllabproject.model.Images;
import br.com.blennersilva.fulllabproject.model.Product;
import br.com.blennersilva.fulllabproject.model.Sellers;
import br.com.blennersilva.fulllabproject.model.Skus;

/**
 * Created by blennersilva on 13/01/18.
 */

public class JsonUtils {
    private static ArrayList<Skus> skusArrayList = new ArrayList<>();
    private static ArrayList<Sellers> sellersArrayList = new ArrayList<>();
    private static ArrayList<Images> imagesArrayList = new ArrayList<>();

    public static ArrayList<Product> parseProductsFromJason(JSONObject response) throws JSONException {
        Product product = new Product();
        ArrayList<Product> productArrayList = new ArrayList<>();

        JSONArray jsonArray = response.getJSONArray("Products");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            product.setAvailability(jsonObject.getBoolean("Availability"));
            product.setSkusArrayList(parseSkuFromJson(jsonObject));

            productArrayList.add(product);
        }
        return productArrayList;
    }

    private static ArrayList<Skus> parseSkuFromJson(JSONObject response) throws JSONException {
        JSONArray jsonArray = response.getJSONArray("Skus");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            Skus skus = new Skus();
            skus.setId(jsonObject.getString("Id"));
            Log.d("SKU ID", jsonObject.getString("Id"));
            skus.setName(jsonObject.getString("Name"));
            Log.d("SKU NAME", jsonObject.getString("Name"));
            skus.setOrder(jsonObject.getString("Order"));
            Log.d("SKU ORDER", jsonObject.getString("Order"));

            skus.setSellersArrayList(parseSellersFromjson(jsonObject));
            skus.setImagesArrayList(parseImagesFromjson(jsonObject));

            skusArrayList.add(skus);
        }

        return skusArrayList;
    }

    private static ArrayList<Sellers> parseSellersFromjson(JSONObject response) throws JSONException {


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

    private static ArrayList<Images> parseImagesFromjson(JSONObject response) throws JSONException {
        JSONArray jsonArray = response.getJSONArray("Images");

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);

            Images images = new Images();
            images.setImageUrl(jsonObject.getString("ImageUrl"));

            imagesArrayList.add(images);

        }
        return imagesArrayList;
    }
}
