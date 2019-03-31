package com.example.khaledsabry.Client.Functions;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;

public class Functions {



    public static void closeDrawerLayout(final DrawerLayout drawerLayout)
    {
        android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(drawerLayout != null)
                {
                    if(drawerLayout.isDrawerOpen(GravityCompat.END))
                        drawerLayout.closeDrawer(GravityCompat.END, true);
                    else if(drawerLayout.isDrawerOpen(GravityCompat.START))
                        drawerLayout.closeDrawer(GravityCompat.START, true);
                }
            }
        },500);

    }

    public static String removeQoutes(String name)
    {
        return (String) name.subSequence(1,name.length()-1);
    }

}
