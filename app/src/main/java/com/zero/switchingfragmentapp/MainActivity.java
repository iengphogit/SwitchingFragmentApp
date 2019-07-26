package com.zero.switchingfragmentapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    final String ONE_FRAG_TAG = "oneFragTag";
    final String TWO_FRAG_TAG = "twoFragTag";
    boolean check;
    FragmentManager fragmentManager;

//    OneFragment oneFragment = new OneFragment();
//    TwoFragment twoFragment = new TwoFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

//        replaceFragment(oneFragment, ONE_FRAG_TAG);
//        replaceFragment(twoFragment, TWO_FRAG_TAG);

        final Button button = findViewById(R.id.switcher_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment(check ? TWO_FRAG_TAG : ONE_FRAG_TAG);
                button.setText(check ? "TWO FRAG" : "ONE FRAG");
                check = !check;
            }
        });
    }


    private void replaceFragment(Fragment fragment, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container_layout, fragment, tag);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void switchFragment(String selectedFlag) {

        if (fragmentManager == null) return;

        switch (selectedFlag) {
            case ONE_FRAG_TAG:

                if (fragmentManager.findFragmentByTag(ONE_FRAG_TAG) != null) {
                    fragmentManager.beginTransaction().show(Objects.requireNonNull(fragmentManager.findFragmentByTag(ONE_FRAG_TAG))).commit();
                } else {
                    fragmentManager.beginTransaction().add(R.id.fragment_container_layout, new OneFragment(), ONE_FRAG_TAG).commit();
                }

                if (fragmentManager.findFragmentByTag(TWO_FRAG_TAG) != null) {
                    fragmentManager.beginTransaction().hide(Objects.requireNonNull(fragmentManager.findFragmentByTag(TWO_FRAG_TAG))).commit();
                }
                break;

            case TWO_FRAG_TAG:

                if (fragmentManager.findFragmentByTag(TWO_FRAG_TAG) != null) {
                    fragmentManager.beginTransaction().show(Objects.requireNonNull(fragmentManager.findFragmentByTag(TWO_FRAG_TAG))).commit();
                } else {
                    fragmentManager.beginTransaction().add(R.id.fragment_container_layout, new TwoFragment(), TWO_FRAG_TAG).commit();
                }

                if (fragmentManager.findFragmentByTag(ONE_FRAG_TAG) != null) {
                    fragmentManager.beginTransaction().hide(Objects.requireNonNull(fragmentManager.findFragmentByTag(ONE_FRAG_TAG))).commit();
                }

                break;
        }
    }
}
