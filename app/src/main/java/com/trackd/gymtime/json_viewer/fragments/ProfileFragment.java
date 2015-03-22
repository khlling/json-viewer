package com.trackd.gymtime.json_viewer.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.trackd.gymtime.json_viewer.R;
import com.trackd.gymtime.json_viewer.json.HeaderJSONHandler;

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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        HeaderJSONHandler headerJSONHandler = new HeaderJSONHandler(getActivity());
        img = (ImageView) view.findViewById(R.id.profile_pic);

        //Using picasso for easy thread management and image handling - recommend this library a lot
        Picasso.with(getActivity())
                .load(headerJSONHandler.getHeader().getImageLocation())
                .error(R.drawable.ic_launcher)
                .into(img);

        name =(TextView) view.findViewById(R.id.textView_name);
        name.setText(headerJSONHandler.getHeader().getName());

        following =(TextView) view.findViewById(R.id.textView_following);
        following.setText(Integer.toString(headerJSONHandler.getHeader().getFollowing()));
        followers =(TextView) view.findViewById(R.id.textView_followers);
        followers.setText(Integer.toString(headerJSONHandler.getHeader().getFollowers()));

    }

    //Pass back fragment to MainActivity, factory implementation
    public static Fragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        fragment.setRetainInstance(true);
        return fragment;
    }
}
