package com.example.socialnetworkdemo;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.function.Predicate;

public class DetailActivity extends AppCompatActivity {
    TextView username,login,time,date,retweet1,like, group,message;
    ImageView image,avatar,likeButton,verify;
    ImageButton goBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        final int avatarIntent=intent.getIntExtra("avatar",0);
        final String usernameIntent = intent.getStringExtra("username");
        final String loginIntent=intent.getStringExtra("login");
        final String timeIntent=intent.getStringExtra("time");
        final int imageIntent=intent.getIntExtra("image",1);
        final String dateIntent = intent.getStringExtra("date");
        Log.e("retweet",String.valueOf(intent.getIntExtra("retweet",0)));
        final String retweetIntent= String.valueOf(intent.getIntExtra("retweet",0));
        final String likeIntent=String.valueOf(intent.getIntExtra("like",0));
        final String groupIntent=intent.getStringExtra("group");
        final int index = intent.getIntExtra("index",0);
        final int verifyIntent=intent.getIntExtra("verify",0);

        likeButton = findViewById(R.id.likeButton);
        if (MainActivity.list.get(index).isFavourite){
            likeButton.setTag(R.drawable.heart_icon);
            likeButton.setImageResource(R.drawable.heart_icon);
        }else{
            likeButton.setTag(1);
        }
        final String messageIntent=intent.getStringExtra("message");

        likeButton.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if((Integer)likeButton.getTag() != R.drawable.heart_icon) {
                    likeButton.setImageResource(R.drawable.heart_icon);
                    likeButton.setTag(R.drawable.heart_icon);
                    like.setText(String.valueOf(Integer.parseInt(like.getText().toString())+1));
                    MainActivity.list.get(index).likes += 1;
                    MainActivity.list.get(index).isFavourite=true;
                    MainActivity.favList.add(new User(avatarIntent,usernameIntent,Integer.parseInt(retweetIntent),verifyIntent,loginIntent,timeIntent,messageIntent,imageIntent,10,Integer.parseInt(like.getText().toString()),dateIntent,groupIntent,true));
                    Log.e("ARRAY",MainActivity.favList.toString());
                }else {
                    likeButton.setImageResource(R.drawable.icons8_love_24);
                    likeButton.setTag(1);
                    //MainActivity.favList.remove(new User(avatarIntent,usernameIntent,Integer.parseInt(retweetIntent),verifyIntent,loginIntent,timeIntent,messageIntent,imageIntent,10,Integer.parseInt(like.getText().toString()),dateIntent,groupIntent,true));
                    MainActivity.favList.removeIf(new Predicate<User>() {
                        @Override
                        public boolean test(User favouritelistItem) {
                            return favouritelistItem.name.equals(usernameIntent);
                        }
                    });
                    like.setText(String.valueOf(Integer.parseInt(like.getText().toString())-1));
                    Log.e("ARRAY2",MainActivity.favList.toString());
                    MainActivity.list.get(index).likes -= 1;
                    MainActivity.list.get(index).isFavourite=false;
                }
            }
        });
        username = findViewById(R.id.userName);
        username.setText(usernameIntent);
        login=findViewById(R.id.userLogin);
        login.setText(loginIntent);
        time=findViewById(R.id.time);
        time.setText(timeIntent);
        goBack=findViewById(R.id.back);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        image=findViewById(R.id.userImage);
        avatar=findViewById(R.id.avatarList);
        avatar.setImageResource(avatarIntent);

        if (imageIntent==0){
            image.setVisibility(View.GONE);
        }else{
            image.setVisibility(View.VISIBLE);
            image.setImageResource(imageIntent);
        }
        date=findViewById(R.id.date);
        date.setText(dateIntent);
        retweet1=findViewById(R.id.tweetsContain);
        Log.e("retweet1",retweet1+"");
        retweet1.setText(String.valueOf(retweetIntent));
        like=findViewById(R.id.likeContain);
        like.setText(likeIntent);

        group=findViewById(R.id.group);
        group.setText(groupIntent);
        verify=findViewById(R.id.verify);
        if (verifyIntent!=0) {
            verify.setImageResource(verifyIntent);
        }
        message=findViewById(R.id.userMessageDetail);
        message.setText(messageIntent);



    }
}
