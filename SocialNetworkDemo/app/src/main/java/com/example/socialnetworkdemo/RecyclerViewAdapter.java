package com.example.socialnetworkdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.function.Predicate;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<User> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private Context mContext;

    // data is passed into the constructor
    RecyclerViewAdapter(Context context, List<User> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //String animal = mData.get(position);
        //holder.myTextView.setText(animal);
        holder.avatarList.setImageResource(mData.get(position).avatarList);
        holder.username.setText(mData.get(position).name);
        if(mData.get(position).isFavourite){
            holder.likeIcon.setTag(R.drawable.heart_icon);
            holder.likeIcon.setImageResource(R.drawable.heart_icon);
        }else {
            holder.likeIcon.setTag(1);
        }
        holder.userlogin.setText(mData.get(position).login);
        holder.time.setText(mData.get(position).lastActive);
        holder.message.setText(mData.get(position).message);
        holder.commentContain.setText(mData.get(position).comments+"");
        holder.retweet.setText(mData.get(position).retweet+"");
        holder.likeContain.setText(mData.get(position).likes+"");
        if(mData.get(position).image != 0){
            holder.userImage.setVisibility(View.VISIBLE);
            //holder.userImage.setImageResource(mData.get(position).image);
            holder.userImage.setBackgroundResource(mData.get(position).image);

        }else{
            holder.userImage.setVisibility(View.GONE);
        }
        if (mData.get(position).verify!=0){
            holder.verify.setVisibility(View.VISIBLE);
            holder.verify.setImageResource(mData.get(position).verify);
        }
        else{
            holder.verify.setVisibility(View.GONE);
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView username,userlogin,time,message,commentContain,retweet,likeContain, date,group;
        ImageView avatarList,userImage, likeIcon,verify;


        ViewHolder(View itemView) {
            super(itemView);
            //myTextView = itemView.findViewById(R.id.tvAnimalName);
            likeIcon=itemView.findViewById(R.id.likeIcon);
            avatarList = itemView.findViewById(R.id.avatarList);
            username = itemView.findViewById(R.id.userName);
            verify=itemView.findViewById(R.id.verify);
            userlogin = itemView.findViewById(R.id.userLogin);
            time = itemView.findViewById(R.id.time);
            message = itemView.findViewById(R.id.userMessage);
            commentContain = itemView.findViewById(R.id.commentContain);
            retweet = itemView.findViewById(R.id.retweet);
            likeContain = itemView.findViewById(R.id.likeContain);
            userImage = itemView.findViewById(R.id.userImage);
            date=itemView.findViewById(R.id.date);
            group=itemView.findViewById(R.id.group);

            likeIcon.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {
                    if(likeIcon.getTag() == null || (Integer)likeIcon.getTag() != R.drawable.heart_icon) {
                        likeIcon.setImageResource(R.drawable.heart_icon);
                        likeIcon.setTag(R.drawable.heart_icon);
                        likeContain.setText(String.valueOf(Integer.parseInt(likeContain.getText().toString())+1));
                        MainActivity.list.get(getAdapterPosition()).isFavourite=true;

                        MainActivity.favList.add(new User(mData.get(getAdapterPosition()).avatarList,mData.get(getAdapterPosition()).name,
                                Integer.parseInt(String.valueOf(mData.get(getAdapterPosition()).retweet)),mData.get(getAdapterPosition()).verify,
                                mData.get(getAdapterPosition()).login,mData.get(getAdapterPosition()).lastActive,mData.get(getAdapterPosition()).message,
                                mData.get(getAdapterPosition()).image,10,Integer.parseInt(likeContain.getText().toString()),
                                mData.get(getAdapterPosition()).date,mData.get(getAdapterPosition()).group,true));
                    }
                    else {
                        likeIcon.setImageResource(R.drawable.icons8_love_24);
                        likeIcon.setTag(1);
                        //MainActivity.favList.remove(new User(avatarIntent,usernameIntent,Integer.parseInt(retweetIntent),verifyIntent,loginIntent,timeIntent,messageIntent,imageIntent,10,Integer.parseInt(like.getText().toString()),dateIntent,groupIntent,true));
                        MainActivity.favList.removeIf(new Predicate<User>() {
                            @Override
                            public boolean test(User favouritelistItem) {
                                return favouritelistItem.name.equals(mData.get(getAdapterPosition()).name);
                            }
                        });
                        likeContain.setText(String.valueOf(Integer.parseInt(likeContain.getText().toString())-1));
                        Log.e("ARRAY2",MainActivity.favList.toString());
                        MainActivity.list.get(getAdapterPosition()).isFavourite=false;
                    }

                }
            });
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Log.e("YA",getAdapterPosition()+"");
                    Intent intent = new Intent(mContext,DetailActivity.class);
                    intent.putExtra("username",mData.get(getAdapterPosition()).name);
                    intent.putExtra("login",mData.get(getAdapterPosition()).login);
                    intent.putExtra("message",mData.get(getAdapterPosition()).message);
                    intent.putExtra("image",mData.get(getAdapterPosition()).image);
                    intent.putExtra("avatar",mData.get(getAdapterPosition()).avatarList);
                    intent.putExtra("time",mData.get(getAdapterPosition()).lastActive);
                    intent.putExtra("like",mData.get(getAdapterPosition()).likes);
                    intent.putExtra("retweet",mData.get(getAdapterPosition()).retweet);
                    intent.putExtra("date",mData.get(getAdapterPosition()).date);
                    intent.putExtra("group",mData.get(getAdapterPosition()).group);
                    intent.putExtra("index",getAdapterPosition());
                    intent.putExtra("verify",mData.get(getAdapterPosition()).verify);

                    mContext.startActivity(intent);
                }
            });


        }


        @Override
        public void onClick(View view) {
            Log.e("YA","TOJE ZDES");
            if (mClickListener != null){
                mClickListener.onItemClick(view, getAdapterPosition());

            }
        }
    }


    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
