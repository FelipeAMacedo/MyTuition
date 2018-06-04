package com.felipemacedo.mytuition.model;

/**
 * Created by felipemacedo on 23/10/17.
 */

public class CurrentUser extends Usuario {
    public String id;

    private static CurrentUser currentUser = new CurrentUser();

    private CurrentUser() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public static CurrentUser getInstance() {
        return currentUser;
    }
}
