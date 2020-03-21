package com.example.socialnetworkdemo;

public class User {
    String name,login,lastActive,message, date,group;
    int avatarList,image,comments,likes,verify, retweet;
    boolean isFavourite;

    public User(int avatarList,String name,int retweet,int verify, String login, String lastActive, String message, int image, int comments, int likes,String date,String group, boolean isFavourite) {
        this.name = name;
        this.avatarList = avatarList;
        this.verify=verify;
        this.login = login;
        this.lastActive = lastActive;
        this.message = message;
        this.retweet=retweet;
        this.image = image;
        this.comments = comments;
        this.likes = likes;
        this.date=date;
        this.group=group;
        this.isFavourite = isFavourite;
    }
}
