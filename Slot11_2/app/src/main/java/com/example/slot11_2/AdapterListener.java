package com.example.slot11_2;

import com.example.slot11_2.Room.Products;


public interface AdapterListener {

    void OnUpdate(Products products);
    void OnDelete(int id, int pos);
}
