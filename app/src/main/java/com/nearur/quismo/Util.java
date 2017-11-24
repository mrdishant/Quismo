package com.nearur.quismo;

import android.net.Uri;

/**
 * Created by mrdis on 11/22/2017.
 */

public class Util {

    public static String db="Quismo";
    public static String table="Daily";
    public static int dbversion=1;

    public static String date="Date";
    public static String wasted="Wasted";
    public static String saved="Saved";
    public static String number="Number";

    public static String query="create table Daily(\n" +
            "Date text," +
            "Saved int," +
            "Wasted int," +
            "Number int" +
            ")";

    public static Uri u=Uri.parse("content://rdbms.data/"+table);

}
