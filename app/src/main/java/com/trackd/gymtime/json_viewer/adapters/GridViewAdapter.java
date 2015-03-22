package com.trackd.gymtime.json_viewer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.trackd.gymtime.json_viewer.R;
import com.trackd.gymtime.json_viewer.json.ProductsJSONHandler;

/**
 * Created by Kev on 18/03/2015.
 */
public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.ViewHolder> {

    private ProductsJSONHandler productsJSONHandler;
    private Context context;
    private LayoutInflater inflate;


    public GridViewAdapter(Context context, ProductsJSONHandler productsJSONHandler) {
        this.context = context;
        inflate = LayoutInflater.from(context);
        this.productsJSONHandler = productsJSONHandler;
    }

    @Override
    public GridViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflate.inflate(R.layout.item_view, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        //new LoadImage(this).execute(productsDataModel.getImageLocations());

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GridViewAdapter.ViewHolder viewHolder, int i) {

        //Using picasso for easy thread management and image handling - recommend this library a lot

        Picasso.with(context)
                .load(productsJSONHandler.getProduct().getImageLocations().get(i))
               .error(R.drawable.ic_launcher)
                .into(viewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return productsJSONHandler.getProduct().getImageLocations().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
