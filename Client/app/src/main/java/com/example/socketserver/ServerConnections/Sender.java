package com.example.socketserver.ServerConnections;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.format.Formatter;

import com.example.socketserver.MainActivity;

import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;

@RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
public class Sender extends AsyncTask<JSONObject, Void, Void> {

    Socket socket;
    String getIp() {
        Context context = MainActivity.getActivity().getApplicationContext();

        if (context != null) {

            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            WifiManager wm = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

            WifiInfo connectionInfo = wm.getConnectionInfo();
            int ipAddress = connectionInfo.getIpAddress();
            if (ipAddress == 0) {
                return "";
            }

            String ipString = Formatter.formatIpAddress(ipAddress);
            return ipString;
        }
        return "";
    }

    @Override
    protected Void doInBackground(JSONObject... jsonObjects) {
        String msg = jsonObjects[0].toString();
        try
        {
            //String ip = getIp();
            socket = new Socket(Server.getInstance().getServerIp(), Integer.parseInt(Server.getInstance().getServerPort()));



            //Date date = new Date(Calendar.DATE);
            //date.getTime();

            DataOutputStream DOS = new DataOutputStream(socket.getOutputStream());
            DOS.writeUTF(msg);
            socket.close();



        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
