package com.example.appmarket.ui.list;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appmarket.AppInformation;
import com.example.appmarket.MainActivity;
import com.example.appmarket.R;
import com.example.appmarket.ResourceManager;
import com.example.appmarket.ui.appcontainer.AppPageFragment;
import com.example.appmarket.ui.home.HomeFragment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppListFragment extends Fragment {

    ArrayList<AppInformation> mAppList;

    Integer mContainer;

    public AppListFragment(ArrayList<AppInformation> appList, Integer container) {
        mAppList = appList;
        mContainer = container;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_app_list, container, false);

        RecyclerView rv = view.findViewById(R.id.app_list_recycler_view);

        MyAdapter adapter = new MyAdapter(mAppList);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3);


        rv.setLayoutManager(gridLayoutManager);
        rv.setAdapter(adapter);


        return view;
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        ArrayList<AppInformation> mAppList;


        public MyAdapter(ArrayList<AppInformation> appList) {
            mAppList = appList;
        }


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_small_button, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            holder.logo.setImageResource(mAppList.get(position).mLogoID);

            holder.logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager manager = getFragmentManager();
                    AppPageFragment appPageFragment = new AppPageFragment(mAppList.get(position));
                    manager.beginTransaction().replace(mContainer, appPageFragment).addToBackStack(null).commit();
                }
            });
            holder.name.setText(mAppList.get(position).mName);
        }

        @Override
        public int getItemCount() {
            return mAppList.size();
        }
    }
    private class MyViewHolder extends RecyclerView.ViewHolder{

        public ImageView logo;
        public TextView name;

        public MyViewHolder(View itemView) {
            super(itemView);
            logo = (ImageView)itemView.findViewById(R.id.app_logo);
            name = (TextView)itemView.findViewById(R.id.app_name);
        }
    }

}

