package Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mcard.Card_get_work;
import com.example.mcard.MainActivity;
import com.example.mcard.R;


public class Fragment_info_card extends Fragment implements View.OnTouchListener
{
    private View view;
    private AppCompatButton back;
    private ImageView image_click_card;
    private String uri;


    @SuppressLint("ClickableViewAccessibility")
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_info_card, container, false);

        this.back = view.findViewById(R.id.back);
        this.image_click_card = view.findViewById(R.id.get_card_image);

        int get_position = getArguments().getInt("position");
        Log.d("NUM", "NUMBER: " + String.valueOf(get_position));  // FOR TEST (TRUE)

        Card_get_work card_get_work = new Card_get_work();
        this.uri = card_get_work.get_image_card(get_position);

        image_click_card.setImageURI(Uri.parse(this.uri));

        back.setOnTouchListener(this);

        return this.view;
    }


    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                getActivity().getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.menu_for_right, R.anim.menu_for_left).remove(this).commit();
        }
        return true;
    }
}

