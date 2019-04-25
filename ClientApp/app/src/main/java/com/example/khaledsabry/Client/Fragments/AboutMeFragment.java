package com.example.khaledsabry.Client.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khaledsabry.Client.R;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

public class AboutMeFragment extends Fragment {

    public static AboutMeFragment newInstance() {
        AboutMeFragment fragment = new AboutMeFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        AboutView view = AboutBuilder.with(getContext())
                .setPhoto(R.mipmap.profile_picture)
                .setCover(R.mipmap.profile_cover)
                .setName("Khaled Sabry Ibrahim")
                .setSubTitle("Computer Engineer")
                .setBrief("reach me if you want to get sea of ideas :)")
                .setAppIcon(R.drawable.app_image)
                .setAppName(R.string.app_name)
                .addGooglePlayStoreLink("8002078663318221363")
                .addGitHubLink("khaledsabry97")
                .addLinkedInLink("khaled-sabry-12263614a")
                .addEmailLink("khaledsab1997@gmail.com")
                .addFacebookLink("https://www.facebook.com/khaledsabry")
                .addFiveStarsAction()
                .setVersionNameAsAppSubTitle()
                .addShareAction(R.string.app_name)
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(true).setBackgroundColor(R.color.blue_dark)
                .build();

        return view;
    }

}
