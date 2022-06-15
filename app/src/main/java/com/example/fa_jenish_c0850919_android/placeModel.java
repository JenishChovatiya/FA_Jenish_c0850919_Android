package com.example.fa_jenish_c0850919_android;

public class placeModel
{

    //Creating variables for holding the data
    String placeName;
    int numberIncrementer;


    //Creating Constructor
    public placeModel(String placeName, int numberIncrementer)
    {
        this.placeName = placeName;
        this.numberIncrementer = numberIncrementer;
    }



    //creating getter and setter
    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public int getNumberIncrementer() {
        return numberIncrementer;
    }

    public void setNumberIncrementer(int numberIncrementer) {
        this.numberIncrementer = numberIncrementer;
    }



}
