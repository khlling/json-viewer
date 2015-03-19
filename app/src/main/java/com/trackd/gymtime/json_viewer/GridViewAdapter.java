package com.trackd.gymtime.json_viewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.trackd.gymtime.json_viewer.json.ProductsDataModel;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kev on 18/03/2015.
 */
public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.ViewHolder>{

    private ProductsDataModel productsDataModel;
    private Context context;
    private LayoutInflater inflate;
    private ImageView img;
    private List<Bitmap> bitmapList = new ArrayList<>();


    public GridViewAdapter(Context context, ProductsDataModel productsDataModel){
        this.context=context;
        inflate = LayoutInflater.from(context);
        this.productsDataModel = productsDataModel;
    }

    @Override
    public GridViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflate.inflate(R.layout.item_view,viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        new LoadImage(this).execute(productsDataModel.getImageLocations());

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GridViewAdapter.ViewHolder viewHolder, int i) {

    if(!bitmapList.isEmpty()){
        viewHolder.imageView.setImageBitmap(bitmapList.get(i));
    }

    }

    @Override
    public int getItemCount() {
        return productsDataModel.getImageLocations().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView imageView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    public void updateView(List<Bitmap> bitmapList) {
        this.bitmapList = bitmapList;
        this.notifyDataSetChanged();
    }

    /**
     * AsynTack for loading product pics. It should be quick but if not it might cause ANR.
     * This is not the best way to load pictures from urls, I would probably use picasso
     */
    private class LoadImage extends AsyncTask<List<String>, String, List<Bitmap>> {
        private GridViewAdapter gridViewAdapter;
        public LoadImage(GridViewAdapter gridViewAdapter){
            this.gridViewAdapter= gridViewAdapter;
        }

        private Bitmap bitmap;
        protected List<Bitmap> doInBackground(List<String>... args) {
        List<Bitmap> bitmapList =  new ArrayList<Bitmap>();
            for (int i = 0; i < args[0].size(); i++) {
                try {
                    bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0].get(i)).getContent());
                    bitmapList.add(bitmap);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
            return bitmapList;
        }
        protected void onPostExecute(List<Bitmap> bitmapList) {
            if(bitmapList != null){
                gridViewAdapter.updateView(bitmapList);
            }else{
                Toast.makeText(context, "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
