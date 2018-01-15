package br.com.blennersilva.fulllabproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.blennersilva.fulllabproject.R;
import br.com.blennersilva.fulllabproject.SubCategoryActivity;
import br.com.blennersilva.fulllabproject.model.Category;

/**
 * Created by blennersilva on 14/01/18.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private ArrayList<Category> categoryArrayList;
    private Context context;
    private Category category;

    public CategoryAdapter(Context context, ArrayList<Category> categoryArrayList) {
        this.categoryArrayList = categoryArrayList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        category = categoryArrayList.get(position);

        holder.tvCategoryName.setText(category.getName());
    }

    @Override
    public int getItemCount() {
        return categoryArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvCategoryName;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tvCategoryName = itemView.findViewById(R.id.categoryName);
        }

        @Override
        public void onClick(View view) {
            int pos = getAdapterPosition();
            context.startActivity(SubCategoryActivity.newIntent(context, categoryArrayList.get(pos).getSubCategoryArrayList()));
        }
    }
}
