package com.example.mcard;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;

import ForAdapter.Card_info;

public class Card_get_work extends AppCompatActivity
{
    private ArrayList<Card_info> card_list;
    private final String path = "/data/user/0/com.example.mcard/files/MCardData/Images/";
    private String uri_click_image;

    public ArrayList<Card_info> start_program ()
    {
        try {
            this.card_list = new ArrayList<>();
            Log.d("Files", "Path: " + path);

            File directory = new File(path);
            File[] files = directory.listFiles();

            Log.d("Files", "Size: " + files.length);

            for (int i = 0; i < files.length; i++)
            {
                this.card_list.add(new Card_info(Uri.parse(path + files[i].getName().toString()), ""));
                Log.d("Files", "FileName:" + files[i].getName());
            }
        }
        catch (NullPointerException error) { System.err.print("GET ERROR FOR READ DIRECTORY: " + error.getMessage()); }

        return this.card_list;
    }


    public String get_image_card(int position)
    {
        try {
            this.card_list = new ArrayList<>();
            Log.d("Files", "Path: " + path);

            File directory = new File(path);
            File[] files = directory.listFiles();

             this.uri_click_image = path + files[position].getName();
             Log.d("LOG", "GET_ROAD_FOR_POSITION:" + this.uri_click_image);
        }
        catch (Exception error) { System.err.print("DONT HAVE IMAGE: " + error.getMessage()); }

        return this.uri_click_image;
    }
}
