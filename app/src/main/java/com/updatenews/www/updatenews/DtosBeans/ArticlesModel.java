package com.updatenews.www.updatenews.DtosBeans;

import android.os.Parcel;
import android.os.Parcelable;


public class ArticlesModel implements Parcelable {
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;


    public ArticlesModel(Parcel in) {
        author = in.readString();
        title = in.readString();
        description = in.readString();
        url = in.readString();
        urlToImage = in.readString();
        publishedAt = in.readString();
    }

    public static final Creator<ArticlesModel> CREATOR = new Creator<ArticlesModel>() {
        @Override
        public ArticlesModel createFromParcel(Parcel in) {
            return new ArticlesModel(in);
        }

        @Override
        public ArticlesModel[] newArray(int size) {
            return new ArticlesModel[size];
        }
    };

    public ArticlesModel() {

    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public void setUrlToImage(String urlToImage) {
        this.urlToImage = urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(author);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(url);
        parcel.writeString(urlToImage);
        parcel.writeString(publishedAt);
    }
}
