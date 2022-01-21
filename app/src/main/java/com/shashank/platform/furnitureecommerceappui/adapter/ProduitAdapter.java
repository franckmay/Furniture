package com.shashank.platform.furnitureecommerceappui.adapter;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.RelativeLayout;
        import android.widget.TextView;
        import android.widget.Toast;

        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;


        import com.shashank.platform.furnitureecommerceappui.Models.Produit;
        import com.shashank.platform.furnitureecommerceappui.R;
        import com.shashank.platform.furnitureecommerceappui.viewHolder.ProduitViewHolder;

        import java.util.ArrayList;

public class ProduitAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Produit> productArrayList;
    private ProduitAdapterStatus status;

    public ProduitAdapter(Context context, ArrayList<Produit> productArrayList, ProduitAdapterStatus status) {
        this.context = context;
        this.productArrayList = productArrayList;
        this.status = status;
    }

    public interface ProduitAdapterStatus {
        void DataRefresh(boolean isRefresh);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProduitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        setProduitName(((ProduitViewHolder) holder).product_name, productArrayList.get(position).getProduit_name());
        setProduitId(((ProduitViewHolder) holder).product_id, productArrayList.get(position).getProduit_id());
        setCategorieId(((ProduitViewHolder) holder).product_id, productArrayList.get(position).getProduit_id());
        setProduitImage(((ProduitViewHolder) holder).product_image, productArrayList.get(position).getProduit_image());
        setProduitPrice(((ProduitViewHolder) holder).product_price, productArrayList.get(position).getProduit_price());
        setOnClickItem(((ProduitViewHolder) holder).product_parent, productArrayList.get(position).getProduit_id());
        setOnClickCancel(((ProduitViewHolder) holder).product_cancel, position);
    }

    @Override
    public int getItemCount() {
        return productArrayList.size();
    }

    private void setProduitName(TextView textView, String text) {
        textView.setText(text);
    }

    private void setProduitId(TextView textView, int id) {
        textView.setText(context.getString(R.string.product_id) + " " + id);
    }

 private void setCategorieId(TextView textView, int id) {
        textView.setText(context.getString(R.string.product_id) + " " + id);
    }

    private void setProduitImage(ImageView imageView, int imageId) {
        imageView.setBackgroundResource(imageId);
    }

    private void setProduitPrice(TextView textView, float price) {
        textView.setText(context.getString(R.string.currency) + "" + price);
    }

    private void setOnClickItem(RelativeLayout relativeLayout, final int id) {
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Item click: " + id, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setOnClickCancel(RelativeLayout relativeLayout, final int position) {
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productArrayList.remove(position);
                notifyDataSetChanged();
                status.DataRefresh(true);
            }
        });
    }
}
