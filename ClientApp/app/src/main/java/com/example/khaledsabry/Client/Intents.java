package com.example.khaledsabry.Client;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

//create chooser function is important so that if there is no any activity to serve the intent the app doesn't crash
public class Intents {
    private static final Intents ourInstance = new Intents();

    public static Intents getInstance() {
        return ourInstance;
    }

    private Intents() {
    }


    //to share the magnet link with your friend on any app
    public void shareMagnetLink(String magnet)
    {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,magnet);
        MainActivity.getActivity().startActivity(Intent.createChooser(intent, "Send to"));

    }

    //pass the magnet link to download through a torrent app
    public void downloadMagnetLink(String magnet)
    {
        Intent intent1 = new Intent(Intent.ACTION_VIEW);
        intent1.addCategory(Intent.CATEGORY_DEFAULT);
        intent1.setType("application/x-bittorrent");
        intent1.setData(Uri.parse(magnet));
        Intent intent = generateTorrentIntent(MainActivity.getActivity().getApplicationContext(), intent1);

        MainActivity.getActivity().startActivity(Intent.createChooser(intent, "send to"));
    }

    /**
     * search for the apps that downloads the torrent magnet
     *
     * @param context the context of the app
     * @param intent  the intent that specify which app we look for and send with it the data
     * @return an intent to call it with founded apps
     */
    private Intent generateTorrentIntent(Context context, Intent intent) {
        final PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> resolveInfo = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveInfo.size() > 0) {
            List<Intent> targetedShareIntents = new ArrayList<Intent>();
            for (ResolveInfo r : resolveInfo) {
                Intent progIntent = (Intent) intent.clone();
                String packageName = r.activityInfo.packageName;

                progIntent.setPackage(packageName);
                if (r.activityInfo.packageName.toLowerCase().contains("torrent"))
                    targetedShareIntents.add(progIntent);

            }
            if (targetedShareIntents.size() > 0) {
                Intent chooserIntent = Intent.createChooser(targetedShareIntents.remove(0),
                        "Select app to download");

                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,
                        targetedShareIntents.toArray(new Parcelable[]{}));

                return chooserIntent;
            }
        }
        return null;
    }

}
