package com.uekiwg.ngchat.util;

import com.google.firebase.database.DatabaseError;

public class Str {
    public static String toString(String caller, DatabaseError e) {
        return caller + " -------------\n"
            + "error  :" + e.getClass().getName() + "\n"
            + "code   :" + e.getCode() + "\n"
            + "message:" + e.getMessage() + "\n"
            + "details:" + e.getDetails() + "\n"
            + "---------------------";
    }
}
