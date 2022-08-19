package ru.makcnm4ik.vk_news.vk;

import java.util.ArrayList;

public class VKWallEntity {
    public Response response;
}

class Attachment {
    public String type;
    public Photo photo;
    public Video video;
}

class Comments {
    public int can_post;
    public int count;
}

class Donut {
    public boolean is_donut;
}

class FirstFrame {
    public String url;
    public int width;
    public int height;
}

class Image {
    public String url;
    public int width;
    public int height;
    public int with_padding;
}

class Item {
    public int id;
    public int from_id;
    public int owner_id;
    public int date;
    public int marked_as_ads;
    public String post_type;
    public String text;
    public int is_pinned;
    public ArrayList<Attachment> attachments;
    public PostSource post_source;
    public Comments comments;
    public Likes likes;
    public Reposts reposts;
    public Views views;
    public Donut donut;
    public double short_text_rate;
    public int edited;
    public String hash;
}

class Likes {
    public int can_like;
    public int count;
    public int user_likes;
    public int can_publish;
}

class Photo {
    public int album_id;
    public int date;
    public int id;
    public int owner_id;
    public String access_key;
    public int post_id;
    public ArrayList<Size> sizes;
    public String text;
    public int user_id;
    public boolean has_tags;
}

class PostSource {
    public String type;
}

class Reposts {
    public int count;
    public int user_reposted;
}

class Response {
    public int count;
    public ArrayList<Item> items;
}

class Size {
    public int height;
    public String url;
    public String type;
    public int width;
}

class Video {
    public String access_key;
    public int can_comment;
    public int can_like;
    public int can_repost;
    public int can_subscribe;
    public int can_add_to_faves;
    public int can_add;
    public int comments;
    public int date;
    public String description;
    public int duration;
    public ArrayList<Image> image;
    public ArrayList<FirstFrame> first_frame;
    public int width;
    public int height;
    public int id;
    public int owner_id;
    public String title;
    public String track_code;
    public String type;
    public int views;
}

class Views {
    public int count;
}

