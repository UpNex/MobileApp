package com.hp.linkreadersdksample;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ramnivasindani on 9/26/15.
 */
public class User {

    private static User user = new User();
    private String userName;
    private String userPhone;
    private int userAge;
    private Set<String> allergies = new HashSet<String>();
    private Set<String> foodPreferences = new HashSet<String>();

    private User(){
    }


    public static User getUserInstance(){
        if(null== user){
            user = new User();
        }
        return user;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    public Set<String> getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergy) {
        allergies.add(allergy);
    }

    public Set<String> getFoodPreferences() {
        return foodPreferences;
    }

    public void setFoodPreferences(String foodPreference) {
        foodPreferences.add(foodPreference);
    }
}
