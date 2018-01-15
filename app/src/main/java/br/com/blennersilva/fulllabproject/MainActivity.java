package br.com.blennersilva.fulllabproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.pushwoosh.Pushwoosh;

import org.json.JSONArray;
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
    SearchView searchView;
    TextView errorText;
    ImageView errorImg;
    Button errorBtn;
    AwesomeProgressDialog awesomeProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        errorText = findViewById(R.id.errorTxt);
        errorImg = findViewById(R.id.errorImg);
        errorBtn = findViewById(R.id.errorbutton);

        awesomeProgressDialog = new AwesomeProgressDialog(MainActivity.this).setColoredCircle(R.color.dialogErrorBackgroundColor);
        awesomeProgressDialog.show();

        errorBtn.setOnClickListener(view -> requestProductList());
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

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchProductList(query);
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.category:
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
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
                awesomeProgressDialog.hide();
                hideErro();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                showError();
                awesomeProgressDialog.hide();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                showError();
                awesomeProgressDialog.hide();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                showError();
                awesomeProgressDialog.hide();

            }
        });
    }

    private void hideErro() {
        errorBtn.setVisibility(View.GONE);
        errorImg.setVisibility(View.GONE);
        errorText.setVisibility(View.GONE);
    }

    private void showError() {
        errorText.setVisibility(View.VISIBLE);
        errorImg.setVisibility(View.VISIBLE);
        errorBtn.setVisibility(View.VISIBLE);
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
