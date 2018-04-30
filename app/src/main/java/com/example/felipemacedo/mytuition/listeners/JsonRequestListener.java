package com.example.felipemacedo.mytuition.listeners;

public interface JsonRequestListener<T> {

    void onSuccess(T response);
    void onError(T response);
}