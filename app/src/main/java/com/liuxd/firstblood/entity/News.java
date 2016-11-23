package com.liuxd.firstblood.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Liuxd on 2016/11/21 16:04.
 * 新闻
 */

public class News {
    @SerializedName("stat")
    private String state;
    private List<NewsBody> data;

    public class NewsBody {
        private String title;
        private String date;
        @SerializedName("author_name")
        private String authorName;
        @SerializedName("thumbnail_pic_s")
        private String thumbPic1;
        @SerializedName("thumbnail_pic_s02")
        private String thumbPic2;
        @SerializedName("thumbnail_pic_s03")
        private String thumbPic3;
        private String url;
        @SerializedName("uniquekey")
        private String key;
        private String type;
        @SerializedName("realtype")
        private String realType;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getAuthorName() {
            return authorName;
        }

        public void setAuthorName(String authorName) {
            this.authorName = authorName;
        }

        public String getThumbPic1() {
            return thumbPic1;
        }

        public void setThumbPic1(String thumbPic1) {
            this.thumbPic1 = thumbPic1;
        }

        public String getThumbPic2() {
            return thumbPic2;
        }

        public void setThumbPic2(String thumbPic2) {
            this.thumbPic2 = thumbPic2;
        }

        public String getThumbPic3() {
            return thumbPic3;
        }

        public void setThumbPic3(String thumbPic3) {
            this.thumbPic3 = thumbPic3;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRealType() {
            return realType;
        }

        public void setRealType(String realType) {
            this.realType = realType;
        }
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<NewsBody> getData() {
        return data;
    }

    public void setData(List<NewsBody> data) {
        this.data = data;
    }
}
