package br.com.blennersilva.fulllabproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

import br.com.blennersilva.fulllabproject.adapter.SubCategoryAdapter;
import br.com.blennersilva.fulllabproject.model.SubCategory;

public class SubCategoryActivity extends AppCompatActivity {
    private static final String EXTRA_PARAM_SUB_CATEGORY = "subCategory";
    private ArrayList<SubCategory> subCategoryArrayList;
    private RecyclerView rvSubCategory;
    private SubCategoryAdapter subCategoryAdapter;

    public static Intent newIntent(Context context, ArrayList<SubCategory> subCategories) {
        Intent i = new Intent(context, SubCategoryActivity.class);
        i.putExtra(EXTRA_PARAM_SUB_CATEGORY, subCategories);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_category);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        rvSubCategory = findViewById(R.id.rvSubcategory);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(rvSubCategory.getContext(), DividerItemDecoration.HORIZONTAL);
        rvSubCategory.addItemDecoration(mDividerItemDecoration);
        rvSubCategory.setLayoutManager(mLayoutManager);
        rvSubCategory.setItemAnimator(new DefaultItemAnimator());

        Intent i = getIntent();

        subCategoryArrayList = new ArrayList<>();

        subCategoryArrayList = i.getParcelableArrayListExtra(EXTRA_PARAM_SUB_CATEGORY);

        subCategoryAdapter = new SubCategoryAdapter(SubCategoryActivity.this, subCategoryArrayList);
        rvSubCategory.setAdapter(subCategoryAdapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

}
