package Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.mcard.MainActivity;
import com.example.mcard.R;

import static com.example.mcard.MainActivity.score_click;


public class Fragment_left_menu extends Fragment implements View.OnTouchListener
{
    private View view;
    private LinearLayout LL_menu;
    private AppCompatButton btn_profile, btn_zvon, btn_history, btn_options;

    private Fragment_history_operation fragment_history_operation;
    private Fragment_notification fragment_notification;
    private Fragment_profile fragment_profile;
    private Fragment_settings fragment_settings;

    private Menu_Listener menu_listener;

    private FragmentTransaction fragmentTransaction;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

       this.view = inflater.inflate(R.layout.fragment_left_menu, container, false);

       fragment_history_operation = new Fragment_history_operation();
       fragment_notification = new Fragment_notification();
       fragment_profile = new Fragment_profile();
       fragment_settings = new Fragment_settings();

       btn_profile = view.findViewById(R.id.profile);
       btn_zvon = view.findViewById(R.id.zvon);
       btn_history = view.findViewById(R.id.history);
       btn_options = view.findViewById(R.id.options);


        this.fragmentTransaction = Fragment_left_menu.this.getFragmentManager().beginTransaction().setCustomAnimations(R.anim.menu_to_right, R.anim.menu_to_left);

        this.menu_listener = new Menu_Listener();

       btn_profile.setOnClickListener(menu_listener);
       btn_history.setOnClickListener(menu_listener);
       btn_options.setOnClickListener(menu_listener);
       btn_zvon.setOnClickListener(menu_listener);

       this.LL_menu = view.findViewById(R.id.LL_menu);
       this.LL_menu.setOnTouchListener(this);

       return this.view;

    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                score_click = 0;
                getFragmentManager().beginTransaction().setCustomAnimations(R.anim.menu_for_right, R.anim.menu_for_left).remove(this).commit();
        }
        return true;
    }

   private class Menu_Listener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {

            getFragmentManager().beginTransaction().setCustomAnimations(R.anim.menu_for_right, R.anim.menu_for_left).remove(Fragment_left_menu.this).commit();

           switch (v.getId())
           {
            case R.id.history:
                fragment_history_operation = new Fragment_history_operation();
                fragmentTransaction.replace(R.id.help, fragment_history_operation).commit();
                break;

               case R.id.profile:
                   fragment_profile = new Fragment_profile();
                   fragmentTransaction.replace(R.id.help, fragment_profile).commit();
                   break;

               case R.id.zvon:
                   fragment_notification = new Fragment_notification();
                   fragmentTransaction.replace(R.id.help, fragment_notification).commit();
                   break;

               case R.id.options:
                   fragment_settings = new Fragment_settings();
                   fragmentTransaction.replace(R.id.help, fragment_settings).commit();
                   break;
            }
           }
        }
    }
