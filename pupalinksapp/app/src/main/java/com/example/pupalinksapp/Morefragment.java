package com.example.pupalinksapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.Objects;

import static android.app.Activity.RESULT_OK;

public class Morefragment extends Fragment {
    CircularImageView circularImageView;
    RelativeLayout user_account,user_details;
    private  static  final  int PICK_IMAGE=100;
    Uri imageuri;
    EditText name,email,phone_no,password;
    TextView savename,saveemail,savephoneno;
    Button save;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    String user_name,user_email,userphone_no,user_password;

    @SuppressLint({"ResourceAsColor", "CommitPrefEdits"})
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        preferences = Objects.requireNonNull(this.getActivity()).getSharedPreferences("userdat", Context.MODE_PRIVATE);
        editor = preferences.edit();
        assert container != null;
        @SuppressLint("InflateParams")
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.more_fragment, null);
        circularImageView = view.findViewById(R.id.circularimage);
        save = view.findViewById(R.id.save);
        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        phone_no = view.findViewById(R.id.phone);
        password = view.findViewById(R.id.password);
        user_account = view.findViewById(R.id.user_account);
        user_details = view.findViewById(R.id.user_details);
        savename = view.findViewById(R.id.username);
        saveemail = view.findViewById(R.id.useremail);
        savephoneno = view.findViewById(R.id.userphone);

        user_name = name.getText().toString();
        user_email = email.getText().toString();
        userphone_no = phone_no.getText().toString();
        user_password = password.getText().toString();

        circularImageView.setBorderColor(getResources().getColor(R.color.colorWhite));
        circularImageView.setBorderWidth(10);
// Add Shadow with default param
        circularImageView.addShadow();
// or with custom param
        circularImageView.setShadowRadius(15);
        circularImageView.setShadowColor(R.color.colorPrimary);
        circularImageView.setBackgroundColor(R.color.colorPrimaryDark);
        circularImageView.setShadowGravity(CircularImageView.ShadowGravity.CENTER);

        circularImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }


        });

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                editor.putString("UserName",user_name);
                editor.putString("UserEmail",user_email);
                editor.putString("UserPhone_no",userphone_no);
                editor.putString("UserPassword",user_password);
                editor.putString("UserImage", String.valueOf(imageuri));
                editor.apply();
                user_account.setVisibility(View.GONE);
                user_details.setVisibility(View.VISIBLE);
                savename.setText(name.getText().toString());
                saveemail.setText(email.getText().toString());
                savephoneno.setText(phone_no.getText().toString());


            }
        });

        imageAd();

        return view;
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_IMAGE);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageuri = data.getData();
            circularImageView.setImageURI(imageuri);

        }
    }

    private void imageAd() {

    }

}
