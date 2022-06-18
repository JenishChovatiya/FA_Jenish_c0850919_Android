package com.example.fa_jenish_c0850919_android;

public class placeModel
{

    //Creating variables for holding the data
    int placeid;
    String placeName,placeLatitude,placeLongitude;
    int numberIncrementer;


    //Creating Constructor
    public placeModel(int placeid, String placeName, int numberIncrementer, String placeLatitude, String placeLongitude)
    {
        this.placeid = placeid;
        this.placeName = placeName;
        this.numberIncrementer = numberIncrementer;
        this.placeLatitude = placeLatitude;
        this.placeLongitude = placeLongitude;
    }

    public placeModel() {

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


    public String getPlaceLatitude() {
        return placeLatitude;
    }

    public void setPlaceLatitude(String placeLatitude) {
        this.placeLatitude = placeLatitude;
    }

    public String getPlaceLongitude() {
        return placeLongitude;
    }

    public void setPlaceLongitude(String placeLongitude)
    {
        this.placeLongitude = placeLongitude;

    }



}
