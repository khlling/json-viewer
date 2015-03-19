package com.trackd.gymtime.json_viewer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.trackd.gymtime.json_viewer.json.HeaderDataModel;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by Kev on 18/03/2015.
 */
public class ProfileFragment extends Fragment {

    private TextView name;
    private TextView following;
    private TextView followers;
    private ImageView img;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.d("JV", "dfsfws");
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        HeaderDataModel headerDataModel = new HeaderDataModel(getActivity());
        img = (ImageView) view.findViewById(R.id.profile_pic);
        new LoadImage().execute(headerDataModel.getImageLocation());
        name =(TextView) view.findViewById(R.id.textView_name);
        name.setText(headerDataModel.getName());

        following =(TextView) view.findViewById(R.id.textView_following);
        following.setText(Integer.toString(headerDataModel.getFollowing()));
        followers =(TextView) view.findViewById(R.id.textView_followers);
        followers.setText(Integer.toString(headerDataModel.getFollowers()));

    }

    //Pass back fragment to MainActivity
    public static Fragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        fragment.setRetainInstance(true);
        return fragment;
    }

    /**
     * AsynTack for loading profile pic. It should be quick but if not it might cause ANR.
     * This is not the best way to load pictures from urls, I would probably use picasso
     */
    private class LoadImage extends AsyncTask<String, String, Bitmap> {

        private Bitmap bitmap;
        protected Bitmap doInBackground(String... args) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(args[0]).getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        protected void onPostExecute(Bitmap image) {
            if(image != null){
                img.setImageBitmap(image);
            }else{
                Toast.makeText(getActivity(), "Image Does Not exist or Network Error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
