package org.example.fleetflow.model;

public class Child extends Chauffeur{

    public Child(String permisType) {
        super(permisType);
    }


    @Override
    public String getPermisType(){
        return "hello"+super.getPermisType();
    }

}
