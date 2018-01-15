package br.com.blennersilva.fulllabproject.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import br.com.blennersilva.fulllabproject.R;
import br.com.blennersilva.fulllabproject.model.Product;

/**
 * Created by blennersilva on 13/01/18.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private ArrayList<Product> productArrayList;
    private Context context;

    public ProductAdapter(Context context, ArrayList<Product> productArrayList) {
        this.productArrayList = productArrayList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_cards, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = productArrayList.get(position);

        holder.tvProductName.setText(product.getSkusArrayList().get(0).getName());
        holder.tvProductPrice.setText("R$: " + String.valueOf(product.getSkusArrayList().get(0).getSellersArrayList().get(0).getPrice()));
        holder.tvProductInstallment.setText(String.valueOf(product.getSkusArrayList().get(0).getSellersArrayList().get(0).getCount()) + "x de " + String.valueOf(product.getSkusArrayList().get(0).getSellersArrayList().get(0).getValue()));
        Picasso.with(context)
                .load(product.getSkusArrayList().get(0).getImagesArrayList().get(0).getImageUrl())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView tvProductName;
        private TextView tvProductPrice;
        private TextView tvProductInstallment;

        public ViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.image);
            tvProductName = itemView.findViewById(R.id.productName);
            tvProductPrice = itemView.findViewById(R.id.price);
            tvProductInstallment = itemView.findViewById(R.id.instalment);
        }
    }

}
