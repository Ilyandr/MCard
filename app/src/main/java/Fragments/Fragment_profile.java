package Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.mcard.R;


public class Fragment_profile extends Fragment implements View.OnTouchListener
{
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        this.view = inflater.inflate(R.layout.fragment_profile, container, false);



        return this.view;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}