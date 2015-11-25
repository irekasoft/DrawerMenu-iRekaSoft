package com.irekasoft.slidebarmenu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by hijazi on 24/11/15.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    String TITLES[] = {"Main Page","Apps","Services",
            "About","Learn","Developer",
            "Facebook","Twitter","Call",
            "SMS","SMS","SMS"};
    int ICONS[] = {R.drawable.ic_user,R.drawable.ic_user,R.drawable.ic_user,
            R.drawable.ic_user,R.drawable.ic_user,R.drawable.ic_user,
            R.drawable.ic_user,R.drawable.ic_user,R.drawable.ic_user,
            R.drawable.ic_user,R.drawable.ic_user,R.drawable.ic_user};

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private String mNavTitles[];
    private int mIcons[];

    private String name;
    private int profile;
    private String email;

    Context ctx;

    MyRecyclerViewAdapter(Context context){
        this.ctx = context;
        mNavTitles = this.TITLES;
        mIcons = this.ICONS;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        int holderid;
        TextView textView;
        ImageView imageView;
        ImageView profile;
        TextView Name;
        TextView email;
        Context ctx_viewHolder;

        public ViewHolder(View itemView, int ViewType, Context context) {
            super(itemView);

            ctx_viewHolder = context;
            itemView.setClickable(true);
            itemView.setOnClickListener(this);

            if (ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.rowText);
                imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
                holderid = 1;
            }
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(ctx_viewHolder,"The Item Clicked is: "+getPosition(), Toast.LENGTH_SHORT).show();

        }
        // * view holder is used to store the inflated views in order to recycle them
    }


    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
            ViewHolder vhItem = new ViewHolder(v,viewType, ctx);
            return vhItem;

        } else if (viewType == TYPE_HEADER) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header,parent,false);
            ViewHolder vhHeader = new ViewHolder(v,viewType, ctx);
            return vhHeader;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewAdapter.ViewHolder holder, int position) {
        if(holder.holderid ==1) {
            holder.textView.setText(mNavTitles[position - 1]);
            holder.imageView.setImageResource(mIcons[position -1]);
        }
        else{
        }
    }

    @Override
    public int getItemCount() {
        return mNavTitles.length + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

}