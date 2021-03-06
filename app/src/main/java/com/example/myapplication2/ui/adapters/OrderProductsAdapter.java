package com.example.myapplication2.ui.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.myapplication2.R;
import com.example.myapplication2.datamodels.Product;
import com.example.myapplication2.ui.activities.CheckoutActivity;
import java.util.ArrayList;

public class OrderProductsAdapter extends RecyclerView.Adapter<OrderProductsAdapter.OrderProductViewHolder>{

    private ArrayList<Product> dataSet;
    private Context context;
    private LayoutInflater inflater;
    private float miniumOrder;

    public  OrderProductsAdapter(Context context, ArrayList<Product> dataSet,float miniumOrder){
        this.dataSet = dataSet;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.miniumOrder = miniumOrder;
    }

    public OrderProductsAdapter(CheckoutActivity context, ArrayList<Product> products, float minimumOrder) {
    }

    public interface onItemRemovedListener{
        void onItemRemoved(float subtotal);
    }

    private onItemRemovedListener onItemRemovedListener;

    public OrderProductsAdapter.onItemRemovedListener getOnItemRemovedListener() {
        return onItemRemovedListener;
    }

    public void setOnItemRemovedListener(OrderProductsAdapter.onItemRemovedListener onItemRemovedListener) {
        this.onItemRemovedListener = onItemRemovedListener;
    }

    @NonNull
    @Override
    public OrderProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new OrderProductViewHolder(inflater.inflate(R.layout.item_order_product,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderProductViewHolder orderProductViewHolder, int i) {
        Product product = dataSet.get(i);
        orderProductViewHolder.productNameTv.setText(product.getName());
        orderProductViewHolder.quantityTv.setText(String.valueOf(product.getQuantity()));
        orderProductViewHolder.subtotalTv.setText(String.valueOf(product.getSubtotal()));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    private void removeItem(int index){
        dataSet.remove(index);
        notifyItemRemoved(index);
    }

    public class OrderProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView quantityTv,productNameTv,subtotalTv;
        public ImageButton removeBtn;

        public OrderProductViewHolder(@NonNull View itemView) {
            super(itemView);
            quantityTv = itemView.findViewById(R.id.quantity_tv);
            productNameTv = itemView.findViewById(R.id.product_name_tv);
            subtotalTv = itemView.findViewById(R.id.subtotal_tv);
            removeBtn = itemView.findViewById(R.id.remove_btn);
            removeBtn.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            AlertDialog.Builder removeAlert = new AlertDialog.Builder(context);
            removeAlert
                    .setTitle(R.string.be_careful)
                    .setMessage(R.string.remove_title)
                    .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            onItemRemovedListener.onItemRemoved(dataSet.get(getAdapterPosition()).getSubtotal());
                            removeItem(getAdapterPosition());
                        }
                    })
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    })
                    .create()
                    .show();
        }
    }
}
