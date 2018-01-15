package br.com.blennersilva.fulllabproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.blennersilva.fulllabproject.R;
import br.com.blennersilva.fulllabproject.model.SubCategory;

/**
 * Created by blennersilva on 14/01/18.
 */

public class SubCategoryAdapter extends RecyclerView.Adapter<SubCategoryAdapter.ViewHolder> {
    private ArrayList<SubCategory> subCategoryArrayList;
    private Context context;

    public SubCategoryAdapter(Context context, ArrayList<SubCategory> subCategoryArrayList) {
        this.subCategoryArrayList = subCategoryArrayList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_category_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SubCategory subCategory = subCategoryArrayList.get(position);
        holder.tvsubcategoryName.setText(subCategory.getName());
    }

    @Override
    public int getItemCount() {
        return subCategoryArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvsubcategoryName;

        public ViewHolder(View itemView) {
            super(itemView);

            tvsubcategoryName = itemView.findViewById(R.id.subCategoryName);
        }
    }
}
