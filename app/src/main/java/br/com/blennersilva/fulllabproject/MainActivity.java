package br.com.blennersilva.fulllabproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.claudiodegio.msv.MaterialSearchView;
import com.claudiodegio.msv.OnSearchViewListener;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.pushwoosh.Pushwoosh;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.com.blennersilva.fulllabproject.adapter.ProductAdapter;
import br.com.blennersilva.fulllabproject.model.Product;
import br.com.blennersilva.fulllabproject.util.JsonUtils;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    Product product;
    RecyclerView recyclerView;
    ProductAdapter productAdapter;
    ArrayList<Product> productArrayList;
    MaterialSearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        searchView = findViewById(R.id.sv);

        searchView.setOnSearchViewListener(new OnSearchViewListener() {
            @Override
            public void onSearchViewShown() {

            }

            @Override
            public void onSearchViewClosed() {

            }

            @Override
            public boolean onQueryTextSubmit(String s) {
                searchProductList(s);
                recyclerView.setAdapter(null);
                return false;
            }

            @Override
            public void onQueryTextChange(String s) {

            }
        });
        Pushwoosh.getInstance().registerForPushNotifications();
        recyclerView = findViewById(R.id.rvProducts);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        requestProductList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.manu, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    private void requestProductList() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("Query", " ");
        params.put("Offset", 0);
        params.put("Size", 10);
        asyncHttpClient.post("https://desafio.mobfiq.com.br/Search/Criteria", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    productArrayList = JsonUtils.parseProductsFromJason(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                productAdapter = new ProductAdapter(MainActivity.this, productArrayList);
                recyclerView.setAdapter(productAdapter);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    private void searchProductList(String query) {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("Query", query);
        params.put("Offset", 0);
        params.put("Size", 10);
        asyncHttpClient.post("https://desafio.mobfiq.com.br/Search/Criteria", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    productArrayList = JsonUtils.parseProductsFromJason(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                productAdapter = new ProductAdapter(MainActivity.this, productArrayList);
                recyclerView.setAdapter(productAdapter);
                productAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

}
