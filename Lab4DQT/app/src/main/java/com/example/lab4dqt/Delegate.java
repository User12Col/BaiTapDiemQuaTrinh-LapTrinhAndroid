package com.example.lab4dqt;

import java.io.Serializable;
import java.util.ArrayList;

public class Delegate implements Serializable {
    private String name;
    private String nameRole;
    private String party;
    private String address;
    private String phone;
    private String email;
    private String website;
    private String img;
    private ArrayList<SocialMedia> socialMediaList;

    public Delegate(){

    }

    public Delegate(String name, String nameRole, String party, String address, String phone, String email, String website, String img, ArrayList<SocialMedia> socialMediaList) {
        this.name = name;
        this.nameRole = nameRole;
        this.party = party;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.website = website;
        this.img = img;
        this.socialMediaList = socialMediaList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public ArrayList<SocialMedia> getSocialMediaList() {
        return socialMediaList;
    }

    public void setSocialMediaList(ArrayList<SocialMedia> socialMediaList) {
        this.socialMediaList = socialMediaList;
    }
}
