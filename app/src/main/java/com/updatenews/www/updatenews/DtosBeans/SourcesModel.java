package com.updatenews.www.updatenews.DtosBeans;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by Seema Giri on 9/25/2017.
 */

public class SourcesModel implements Parcelable {
    private UrlsToLogos urlsToLogos;
    private String[] sortBysAvailable;
    private String id;
    private String name;
    private String description;
    private String url;
    private String category;
    private String language;
    private String country;

    protected SourcesModel(Parcel in) {
        sortBysAvailable = in.createStringArray();
        id = in.readString();
        name = in.readString();
        description = in.readString();
        url = in.readString();
        category = in.readString();
        language = in.readString();
        country = in.readString();
    }

    public static final Creator<SourcesModel> CREATOR = new Creator<SourcesModel>() {
        @Override
        public SourcesModel createFromParcel(Parcel in) {
            return new SourcesModel(in);
        }

        @Override
        public SourcesModel[] newArray(int size) {
            return new SourcesModel[size];
        }
    };

    public String[] getSortBysAvailable() {
        return sortBysAvailable;
    }

    public void setSortBysAvailable(String[] sortBysAvailable) {
        this.sortBysAvailable = sortBysAvailable;
    }

    public UrlsToLogos getUrlsToLogos() {
        return urlsToLogos;
    }

    public void setUrlsToLogos(UrlsToLogos urlsToLogos) {
        this.urlsToLogos = urlsToLogos;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(sortBysAvailable);
        parcel.writeString(id);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(url);
        parcel.writeString(category);
        parcel.writeString(language);
        parcel.writeString(country);
    }
}
