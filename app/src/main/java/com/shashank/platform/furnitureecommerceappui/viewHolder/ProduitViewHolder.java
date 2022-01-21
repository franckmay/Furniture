package com.shashank.platform.furnitureecommerceappui.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shashank.platform.furnitureecommerceappui.R;


public class ProduitViewHolder extends RecyclerView.ViewHolder {
    public RelativeLayout product_parent, product_cancel;
    public ImageView product_image;
    public TextView product_name, product_price, product_id;

    public ProduitViewHolder(@NonNull View itemView) {

        super(itemView);
        product_parent = itemView.findViewById(R.id.product_parent);
        product_image = itemView.findViewById(R.id.product_image);
        product_name = itemView.findViewById(R.id.product_name);
        product_price = itemView.findViewById(R.id.product_price);
        product_id = itemView.findViewById(R.id.product_id);
        product_cancel = itemView.findViewById(R.id.product_cancel);
    }
}