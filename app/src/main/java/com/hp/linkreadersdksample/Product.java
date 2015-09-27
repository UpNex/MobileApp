package com.hp.linkreadersdksample;


/**
 * Created by ramnivasindani on 9/26/15.
 */

public class Product {

    private String productId;
    private String productType;
    private String calories;
    private String fat;
    private String cholestrol;
    private String sugar;
    private String protein;
    private String vitamins;
    private String ingredients;
    private String productName;
    private int imageResourceId;

    private int serves;

    public int getServes(){
        return this.serves;
    }

    public void setServes(int serves){
        this.serves = serves;
    }

    public int getImageResourceId(){
        return this.imageResourceId;
    }

    private void setImageResourceId(int imageResourceId){
        this.imageResourceId = imageResourceId;
    }
    public String getProductName(){
        return productName;
    }
    public void setProductName(String productName){
        this.productName = productName;
    }
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }





    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getFat() {
        return fat;
    }

    public void setFat(String fat) {
        this.fat = fat;
    }

    public String getCholestrol() {
        return cholestrol;
    }

    public void setCholestrol(String cholestrol) {
        this.cholestrol = cholestrol;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getProtein() {
        return protein;
    }

    public void setProtein(String protein) {
        this.protein = protein;
    }

    public String getVitamins() {
        return vitamins;
    }

    public void setVitamins(String vitamins) {
        this.vitamins = vitamins;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public Product(String productId, String productName, String productType, String calories, String fat, String cholestrol, String sugar,
                 String protein,   String vitamins, String ingredients, int imageResourceId, int serves){
        this.productId = productId;
        this.productName  = productName;
         this.productType = productType;
         this.calories = calories;
         this.fat = fat;
         this.cholestrol = cholestrol;
         this.sugar = sugar;
         this.protein = protein;
         this.vitamins = vitamins;
         this.ingredients = ingredients;
        this.imageResourceId = imageResourceId;
        this.serves = serves;

    }
    public Product(){

    }



}
