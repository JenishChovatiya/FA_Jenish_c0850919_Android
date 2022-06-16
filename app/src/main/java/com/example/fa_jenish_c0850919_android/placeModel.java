package com.example.fa_jenish_c0850919_android;

public class placeModel
{

    //Creating variables for holding the data
    int placeid;
    String placeName;
    int numberIncrementer;


    //Creating Constructor
    public placeModel(int placeid, String placeName, int numberIncrementer)
    {
        this.placeid = placeid;
        this.placeName = placeName;
        this.numberIncrementer = numberIncrementer;
    }



    //creating getter and setter

    public int getPlaceid()
    {
        return placeid;
    }

    public  void setPlaceid(int placeid)
    {
        this.placeid = placeid;
    }


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
