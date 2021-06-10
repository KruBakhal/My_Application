
package com.example.myapplication.PersonalData.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TranslatorAdsId {

    @SerializedName("banner")
    @Expose
    private String banner;
    @SerializedName("interstitial")
    @Expose
    private String interstitial;

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getInterstitial() {
        return interstitial;
    }

    public void setInterstitial(String interstitial) {
        this.interstitial = interstitial;
    }

    @Override
    public String toString() {
        return "TranslatorAdsId{" +
                "banner='" + banner + '\'' +
                ", interstitial='" + interstitial + '\'' +
                '}';
    }
}
