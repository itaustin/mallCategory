package com.rbt.diamond.fragment;

import androidx.lifecycle.MutableLiveData;

public class ViewModel extends androidx.lifecycle.ViewModel {
    protected MutableLiveData<Integer> number;

    public MutableLiveData<Integer> getNumber() {
        if(number == null){
            number = new MutableLiveData<>();
            number.setValue(0);
        }
        return number;
    }

    public void setNumber() {
        if(number.getValue() != null){
            number.setValue(number.getValue()+1);
        } else {
            number.setValue(0);
        }
    }
}
