package com.example.aleixgm.userprofile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ProfileActivity extends AppCompatActivity {

    private Profile profile;
    private Gson gson = new Gson();
    private TextView name;
    private TextView handle;
    private TextView followers;
    private TextView following;
    private TextView about;
    private ImageView background;
    private ImageView imgProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.textName);
        handle = findViewById(R.id.textHandle);
        followers = findViewById(R.id.textFollowers);
        following = findViewById(R.id.textFollowing);
        about = findViewById(R.id.textAbout);
        background = findViewById(R.id.imageBg);
        imgProfile = findViewById(R.id.imageProfile);

        try {
            InputStream stream = getAssets().open("ProfileInfo.json");
            InputStreamReader reader = new InputStreamReader((stream));
            profile = gson.fromJson(reader, Profile.class);
            ProfileInfo();
        }

        catch (IOException e){
            Toast.makeText(ProfileActivity.this,"ERROR",Toast.LENGTH_SHORT).show();
        }


    }

    private void ProfileInfo() {
        String aux = profile.getName()+profile.getLastname();
        name.setText(aux);
        handle.setText(profile.getHandle());
        followers.setText(profile.getFollowers());
        following.setText(profile.getFollowing());
        about.setText(profile.getAbout());

        Glide.with(ProfileActivity.this).load("file:///android_asset/ProfileBackground.jpg")
               .into (background);

        Glide.with(ProfileActivity.this).load("file:///android_asset/ProfileImage.jpg")
                .apply(RequestOptions.circleCropTransform()).into (imgProfile);
    }
}
