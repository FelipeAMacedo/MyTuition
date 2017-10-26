package com.example.felipemacedo.mytuition.model;

/**
 * Created by felipemacedo on 23/10/17.
 */

public class CurrentUser {
    public String id;
    public String username;
    public String email;
    public String senha;
    public int level;
    public int xp;
    private static CurrentUser currentUser = new CurrentUser();

    private CurrentUser() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public static CurrentUser getInstance() {
        return currentUser;
    }
}
