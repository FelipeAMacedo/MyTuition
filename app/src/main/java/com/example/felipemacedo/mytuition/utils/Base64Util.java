package com.example.felipemacedo.mytuition.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.util.Base64;

public class Base64Util {

    public static Drawable base64ToDrawable(Resources res, String imagem) {
        byte[] decodedString = Base64.getDecoder().decode(imagem);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return new BitmapDrawable(res, decodedByte);
    }
}
