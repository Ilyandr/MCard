package com.example.mcard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import ForAdapter.Card_adapter;
import ForAdapter.Card_info;
import Fragments.Fragment_history_operation;
import Fragments.Fragment_info_card;
import Fragments.Fragment_left_menu;
import Fragments.Fragment_notification;
import Fragments.Fragment_profile;
import Fragments.Fragment_settings;
import SQliteChange.DataBase_for_card;
import UserLocation.Geolocation_listener;

public class MainActivity extends AppCompatActivity {

    private TextView main_text;
    private Button btn_menu, btn_add, btn_search, btn_geolocation;
    private ListView list_item;
    private LinearLayout main_linear;

    private ArrayList<Card_info> card_list;
    private Card_adapter card_adapter;
    private Bundle give_position;

    private DataBase_for_card work_data_base;
    private SQLiteDatabase database_card;

    private Fragment_left_menu frag2;
    private Fragment_info_card frag1;

    private Fragment_history_operation frag3;
    private Fragment_notification frag4;
    private Fragment_profile frag5;
    private Fragment_settings frag6;

    private Animation anim_for_menu_btn;
    private Animation anim_for_plus_btn;
    private Animation anim_for_text;
    private Animation anim_menu_back_btn;

    public static int score_click = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frag3 = new Fragment_history_operation();
        frag4 = new Fragment_notification();
        frag5 = new Fragment_profile();
        frag6 = new Fragment_settings();
        frag2 = new Fragment_left_menu();

        main_text = (TextView) findViewById(R.id.main_text);
        btn_menu = (Button) findViewById(R.id.menu);
        btn_add = (Button) findViewById(R.id.add);
        btn_search = (Button) findViewById(R.id.btn_search);
        btn_geolocation = (Button) findViewById(R.id.btn_geolocation);
        list_item = (ListView) findViewById(R.id.list_item);
        main_linear = (LinearLayout) findViewById(R.id.main_linear);

        anim_for_menu_btn = AnimationUtils.loadAnimation(MainActivity.this, R.anim.to_button_animation_magic);
        anim_for_plus_btn = AnimationUtils.loadAnimation(MainActivity.this, R.anim.to_button_add_magic);
        anim_for_text = AnimationUtils.loadAnimation(MainActivity.this, R.anim.to_text_light);
        anim_menu_back_btn = AnimationUtils.loadAnimation(MainActivity.this, R.anim.back_button_magic);

        main_text.setAnimation(anim_for_text);
        list_item.setAnimation(anim_for_text);

        //work_data_base = new DataBase_for_card(this); БД
        //database_card = work_data_base.getReadableDatabase();
        //@SuppressLint("Recycle") Cursor cursor = database_card.query(DataBase_for_card.table_name, null, null, null, null, null, null);
        // data_for_adapter();
        //start_program_get_data();

          Card_get_work card_get_work = new Card_get_work();
          card_adapter = new Card_adapter(MainActivity.this, R.layout.get_photo_data, card_get_work.start_program());

          list_item.setAdapter(card_adapter);
          list_item.setOnItemClickListener(this::onItemClick);

          Two_button_click click = new Two_button_click();
          btn_search.setOnClickListener(click);
          btn_geolocation.setOnClickListener(click);

        Geolocation_listener.permissions(MainActivity.this);

        btn_menu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try {
                    if (score_click % 2 == 0) {
                        v.startAnimation(anim_for_menu_btn);
                        FragmentTransaction open_fragment_info = getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.menu_to_right, R.anim.menu_to_left);
                        open_fragment_info.replace(R.id.help, frag2).commit();
                    } else {
                        v.startAnimation(anim_menu_back_btn);
                        FragmentTransaction open_fragment_info = getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.menu_for_right, R.anim.menu_for_left);
                        open_fragment_info.remove(frag2).commit();
                    }

                    score_click++;
                } catch (IllegalStateException exception) { Log.w("WARNING", "FAST CLICK: " + exception.getMessage()); }
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                v.startAnimation(anim_for_plus_btn);

                Intent intent = new Intent(MainActivity.this, Card_add.class);
                onBackPressed();

                startActivity(intent);
            }
        });
    }

    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        FragmentTransaction open_fragment_info = getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.menu_to_right, R.anim.menu_to_left);
        this.frag1 = new Fragment_info_card();

        this.give_position = new Bundle();
        this.give_position.putInt("position", position);
        frag1.setArguments(this.give_position);

        open_fragment_info.replace(R.id.main_linear, frag1);
        open_fragment_info.commit();
    }

    @Override
    public void onBackPressed()
    {
        score_click = 0;

        try { getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.menu_for_right, R.anim.menu_for_left).remove(this.frag1).commit(); }
        catch (Exception exception) { System.err.println( "WARNING DELETE FRAGMENT 1: " + exception.getMessage()); }

        try { getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.menu_for_right, R.anim.menu_for_left).remove(this.frag2).commit(); }
        catch (Exception exception) { System.err.println( "WARNING DELETE FRAGMENT 2: " + exception.getMessage()); }

        try { getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.menu_for_right, R.anim.menu_for_left).remove(this.frag3).commit(); }
        catch (Exception exception) { System.err.println( "WARNING DELETE FRAGMENT 3: " + exception.getMessage()); }

        try { getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.menu_for_right, R.anim.menu_for_left).remove(this.frag4).commit(); }
        catch (Exception exception) { System.err.println( "WARNING DELETE FRAGMENT 4: " + exception.getMessage()); }

        try { getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.menu_for_right, R.anim.menu_for_left).remove(this.frag5).commit(); }
        catch (Exception exception) { System.err.println( "WARNING DELETE FRAGMENT 5: " + exception.getMessage()); }

        try { getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.menu_for_right, R.anim.menu_for_left).remove(frag6).commit(); }
        catch (Exception exception) { System.err.println( "WARNING DELETE FRAGMENT 6: " + exception.getMessage()); }
    }

    private class Two_button_click implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            v.startAnimation(anim_for_plus_btn);

            switch (v.getId())
            {
                case R.id.btn_search:

                    break;
                case R.id.btn_geolocation:
                    try {
                        Geolocation_listener.SetUpLocationListener(MainActivity.this);
                        Toast.makeText(MainActivity.this,"Координаты X,Y: " +  Geolocation_listener.get_location_XY(), Toast.LENGTH_SHORT).show();
                    } catch (NullPointerException e) { Toast.makeText(MainActivity.this, "Что-то пошло не так...", Toast.LENGTH_LONG).show();}
                    break;
            }
        }
    }
}

