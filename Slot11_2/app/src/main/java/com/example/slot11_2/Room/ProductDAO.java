package com.example.slot11_2.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDAO {
    @Insert
    long insert(Products products);

    @Update
    void update(Products products);

    @Query("DELETE FROM products WHERE id = :id")
    void delete(int id);

    @Query("SELECT * FROM products")
    List<Products> getAllProduct();
}
