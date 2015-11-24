package com.m5b10.sqlite;

/**
 * Created by Kenpachi on 10/21/2015.
 */
public class Products {

    private int _id;
    private String _productname;

    public Products(){

    }   //empty constructor

    public Products(String productname) {   //passing in productName
        this._productname = productname;    //store passed in productName into var _productName
    }   //constructor that accepts String param

    //ALT+INSERT - insert Setters to give info to int _id and String _productName
    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_productname(String _productname) {
        this._productname = _productname;
    }

    //ALT+INSERT - Getters to retrieve info from variables
    public int get_id() {
        return _id;
    }

    public String get_productname() {
        return _productname;
    }
}
