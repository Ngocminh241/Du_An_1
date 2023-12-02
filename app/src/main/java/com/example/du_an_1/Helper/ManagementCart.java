package com.example.du_an_1.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.du_an_1.DTO.Food;
import com.example.du_an_1.Database.DbHelper;
import com.example.du_an_1.Interface.ChangeNumberItemsListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;

public class ManagementCart {
    private Context context;
    private SharedPreferences preferences;
    DbHelper dbHelper;
    SQLiteDatabase db;

    public ManagementCart(Context context) {
        this.context = context;
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
//    public void insertFood(FoodDomain item){
//        ArrayList<FoodDomain> listFood = getListCart();
//        boolean exisAlready = false;
//        int n = 0;
//        for (int i = 0; i < listFood.size(); i++){
//            if (listFood.get(i).getTitle().equals(item.getTitle())){
//                exisAlready = true;
//                n = i;
//                break;
//            }
//        }
//        if (exisAlready){
//            listFood.get(n).setNumberInCart(item.getNumberInCart());
//        } else {
//            listFood.add(item);
//        }
//        tinyDB.putListObject("CartList", listFood);
//        Toast.makeText(context, "Added to your cart", Toast.LENGTH_SHORT).show();
//    }
    public void insertFood_2(Food item){
        ArrayList<Food> listFood = getListCart_2();
        boolean exisAlready = false;
        int n = 0;
        for (int i = 0; i < listFood.size(); i++){
            if (listFood.get(i).getTenFood().equals(item.getTenFood())){
                exisAlready = true;
                n = i;
                break;
            }
        }
        if (exisAlready){
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        } else {
            listFood.add(item);
        }
        putListObjec_2("CartList2", listFood);
        Toast.makeText(context, "Added to your cart", Toast.LENGTH_SHORT).show();
    }

    public void putListObjec_2(String key, ArrayList<Food> playerList){
        checkForNullKey(key);
        Gson gson = new Gson();
        ArrayList<String> objStrings = new ArrayList<String>();
        for(Food player: playerList){
            objStrings.add(gson.toJson(player));
        }
        putListString(key, objStrings);
    }
    public void putListString(String key, ArrayList<String> stringList) {
        checkForNullKey(key);
        String[] myStringList = stringList.toArray(new String[stringList.size()]);
        preferences.edit().putString(key, TextUtils.join("‚‗‚", myStringList)).apply();
    }
    private void checkForNullKey(String key){
        if (key == null){
            throw new NullPointerException();
        }
    }
    private void checkForNullValue(String value){
        if (value == null){
            throw new NullPointerException();
        }
    }
    public ArrayList<Food> getListObject_2(String key){
        Gson gson = new Gson();

        ArrayList<String> objStrings = getListString(key);
        ArrayList<Food> playerList =  new ArrayList<Food>();

        for(String jObjString : objStrings){
            Food player  = gson.fromJson(jObjString,  Food.class);
            playerList.add(player);
        }
        return playerList;
    }
    public ArrayList<String> getListString(String key) {
        return new ArrayList<String>(Arrays.asList(TextUtils.split(preferences.getString(key, ""), "‚‗‚")));
    }
    public ArrayList<Boolean> getListBoolean(String key) {
        ArrayList<String> myList = getListString(key);
        ArrayList<Boolean> newList = new ArrayList<Boolean>();

        for (String item : myList) {
            if (item.equals("true")) {
                newList.add(true);
            } else {
                newList.add(false);
            }
        }

        return newList;
    }


//    public ArrayList<FoodDomain> getListCart(){
//        return tinyDB.getListObject("CartList");
//    }

    public ArrayList<Food> getListCart_2(){
        return getListObject_2("CartList2");
    }

//    public void plusNumberFood(ArrayList<FoodDomain>listFood, int position, ChangeNumberItemsListener changeNumberItemsListener){
//        listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart()+1);
//        tinyDB.putListObject("CartList", listFood);
//        changeNumberItemsListener.changed();
//    }
//
//
//    public void minusNumberFood(ArrayList<FoodDomain>listFood, int position, ChangeNumberItemsListener changeNumberItemsListener){
//        if (listFood.get(position).getNumberInCart()==1){
//            listFood.remove(position);
//        }else {
//            listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart()-1);
//        }
//        tinyDB.putListObject("CartList", listFood);
//        changeNumberItemsListener.changed();
//    }
    public void plusNumberFood_2(ArrayList<Food>listFood2, int position, ChangeNumberItemsListener changeNumberItemsListener){
        listFood2.get(position).setNumberInCart(listFood2.get(position).getNumberInCart()+1);
        putListObjec_2("CartList2", listFood2);
        changeNumberItemsListener.changed();
    }


    public void minusNumberFood_2(ArrayList<Food>listFood2, int position, ChangeNumberItemsListener changeNumberItemsListener){
        if (listFood2.get(position).getNumberInCart()==1){
            listFood2.remove(position);
        }else {
            listFood2.get(position).setNumberInCart(listFood2.get(position).getNumberInCart()-1);
        }
        putListObjec_2("CartList2", listFood2);
        changeNumberItemsListener.changed();
    }


//    public Double gettotalFee(){
//        ArrayList<FoodDomain> listfood = getListCart();
//        double fee = 0;
//        for (int i = 0; i < listfood.size(); i++){
//            fee = fee + (listfood.get(i).getFee() * listfood.get(i).getNumberInCart());
//        }
//        return fee;
//    }
    public Double gettotalFee_2(){
        ArrayList<Food> listfood = getListCart_2();
        double fee = 0;
        for (int i = 0; i < listfood.size(); i++){
            fee = fee + (listfood.get(i).getGiaFood() * listfood.get(i).getNumberInCart());
        }
        return fee;
    }
}
