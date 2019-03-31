package com.example.khaledsabry.Client.Functions;

import android.widget.Toast;

import com.example.khaledsabry.Client.MainActivity;

import es.dmoral.toasty.Toasty;

public class Toasts {


    public static void error(String msg)
    {
        Toasty.error(MainActivity.getActivity().getApplicationContext(),msg, Toast.LENGTH_SHORT,true).show();
    }


    public static void success(String msg)
    {
        Toasty.success(MainActivity.getActivity().getApplicationContext(),msg, Toast.LENGTH_SHORT,true).show();
    }

    public static void info(String msg)
    {
        Toasty.info(MainActivity.getActivity().getApplicationContext(),msg, Toast.LENGTH_SHORT,true).show();
    }

    public static void warning(String msg)
    {
        Toasty.warning(MainActivity.getActivity().getApplicationContext(),msg, Toast.LENGTH_SHORT,true).show();
    }

    public static void normal(String msg)
    {
        Toasty.error(MainActivity.getActivity().getApplicationContext(),msg, Toast.LENGTH_SHORT,true).show();
    }
}
