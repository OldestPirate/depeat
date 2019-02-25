package com.example.myapplication2.ui.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication2.R;
import com.example.myapplication2.datamodels.Product;
import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter {

    private LayoutInflater inflater;
    private ArrayList<Product> data;
    private OnQuanityChangedListener onQuanityChangedListener;

    public ProductAdapter(Context context) {
        this.data = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    public interface OnQuanityChangedListener {
        void onChange(float price);
    }

    public OnQuanityChangedListener getOnQuanityChangedListener() {
        return onQuanityChangedListener;
    }

    public void setOnQuanityChangedListener(OnQuanityChangedListener onQuanityChangedListener) {
        this.onQuanityChangedListener = onQuanityChangedListener;
    }

    public ArrayList<Product> getData() {
        return data;
    }

    public void setData(ArrayList<Product> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = inflater.inflate(R.layout.item_product, viewGroup, false);
        return new ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Product product = data.get(i);
        ProductViewHolder vh = (ProductViewHolder) viewHolder;
        vh.productName.setText(product.getName());
        vh.productPrice.setText(String.valueOf(product.getPrice()));
        vh.productQty.setText(String.valueOf(product.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView productName, productPrice, productQty;
        public Button addBtn, removeBtn;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.item_name);
            productPrice = itemView.findViewById(R.id.item_price);
            productQty = itemView.findViewById(R.id.item_picker).findViewById(R.id.quantity_tv);
            addBtn = itemView.findViewById(R.id.item_picker).findViewById(R.id.add_btn);
            removeBtn = itemView.findViewById(R.id.item_picker).findViewById(R.id.remove_btn);
            addBtn.setOnClickListener(this);
            removeBtn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Product product = data.get(getAdapterPosition());
            if (view.getId() == R.id.add_btn) {
                product.increaseQuantity();
                notifyItemChanged(getAdapterPosition());
                onQuanityChangedListener.onChange(product.getPrice());
            } else if (view.getId() == R.id.remove_btn) {
                if (product.getQuantity() == 0) return;
                product.decreaseQuantity();
                onQuanityChangedListener.onChange(product.getPrice() * -1);
            }
            notifyItemChanged(getAdapterPosition());
        }
    }
}
