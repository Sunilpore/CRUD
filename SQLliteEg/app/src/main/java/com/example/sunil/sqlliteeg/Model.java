package com.example.sunil.sqlliteeg;

/**
 * Created by Sunil on 10/31/2017.
 */

public class Model {

    public String id;  //For Update method call.Bcz it required 'ID' as a int argument
    public String name;
    public String newage;
    public int age;
    public boolean selected;



    public Model(String id){
        this.id = id;
    }

    public Model(String name, int age) {
        this.name = name;
        this.age = age;
    }

    /*public Model(String name, int age, boolean selected) {
        this.name = name;
        this.age = age;
        this.selected = selected;
    }*/



   /* public Model(String name, String newage, boolean selected) {
        this.name = name;
        this.newage = newage;
        this.selected = selected;
    }*/

    public Model(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Model(String id, String name, String newage, boolean selected) {
            this.id=id;
            this.name=name;
            this.newage=newage;
            this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNewage() {
        return newage;
    }

    public void setNewage(String newage) {
        this.newage = newage;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}