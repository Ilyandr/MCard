package Fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.mcard.MainActivity;
import com.example.mcard.R;

import java.util.Objects;


public class Fragment_history_operation extends Fragment implements View.OnTouchListener
{
    private  View view;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        this.view = inflater.inflate(R.layout.fragment_history_operation, container, false);
        FrameLayout frameLayout = view.findViewById(R.id.Main);
        frameLayout.setOnTouchListener(this::onTouch);




        return this.view;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        switch (event.getAction()) { case MotionEvent.ACTION_DOWN:  getFragmentManager().beginTransaction().setCustomAnimations(R.anim.menu_for_right, R.anim.menu_for_left).remove(this).commit(); }
        return true;
    }
}