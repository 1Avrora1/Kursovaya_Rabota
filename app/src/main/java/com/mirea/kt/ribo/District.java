package com.mirea.kt.ribo;

public class District {
    private String Acronym;
    private String Name;
    private String Avatar;

    public District(String acronym, String name, String avatar) {
        Acronym = acronym;
        Name = name;
        Avatar = avatar;
    }

    public String getAcronym() {
        return Acronym;
    }

    public void setAcronym(String acronym) {
        Acronym = acronym;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAvatar() {
        return Avatar;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }
}
