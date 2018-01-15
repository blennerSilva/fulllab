package br.com.blennersilva.fulllabproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.awesomedialog.blennersilva.awesomedialoglibrary.AwesomeProgressDialog;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import br.com.blennersilva.fulllabproject.adapter.CategoryAdapter;
import br.com.blennersilva.fulllabproject.model.Category;
import br.com.blennersilva.fulllabproject.util.JsonUtils;
import cz.msebera.android.httpclient.Header;

public class CategoryActivity extends AppCompatActivity {
    RecyclerView rvCategory;
    ArrayList<Category> categoryArrayList;
    CategoryAdapter categoryAdapter;
    AwesomeProgressDialog awesomeProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        categoryArrayList = new ArrayList<>();

        rvCategory = findViewById(R.id.rvCategory);

        awesomeProgressDialog = new AwesomeProgressDialog(this).setColoredCircle(R.color.dialogErrorBackgroundColor);

        awesomeProgressDialog.show();

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvCategory.setLayoutManager(mLayoutManager);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(rvCategory.getContext(), DividerItemDecoration.HORIZONTAL);
        rvCategory.addItemDecoration(mDividerItemDecoration);
        rvCategory.setItemAnimator(new DefaultItemAnimator());

        requestCategory();
    }


    private void requestCategory() {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get("https://desafio.mobfiq.com.br/StorePreference/CategoryTree", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                try {
                    categoryArrayList = JsonUtils.parseCategoryFromJson(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                categoryAdapter = new CategoryAdapter(CategoryActivity.this, categoryArrayList);
                rvCategory.setAdapter(categoryAdapter);
                awesomeProgressDialog.hide();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(CategoryActivity.this, "Falha na requisicao", Toast.LENGTH_SHORT).show();
                awesomeProgressDialog.hide();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
