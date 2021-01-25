package com.example.palmasturist.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.palmasturist.LembreteActivity;
import com.example.palmasturist.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        SliderLayout sliderShow = (SliderLayout) root.findViewById(R.id.slider);

        TextSliderView textSliderView = new TextSliderView(container.getContext());
        textSliderView.image(R.drawable.img1);
        sliderShow.addSlider(textSliderView);

        TextSliderView textSliderView2 = new TextSliderView(container.getContext());
        textSliderView2.image(R.drawable.img2);
        sliderShow.addSlider(textSliderView2);

        TextSliderView textSliderView3 = new TextSliderView(container.getContext());
        textSliderView3.image(R.drawable.img3);
        sliderShow.addSlider(textSliderView3);

        TextSliderView textSliderView4 = new TextSliderView(container.getContext());
        textSliderView4.image(R.drawable.img4);
        sliderShow.addSlider(textSliderView4);

        FloatingActionButton actionLembrete = root.findViewById(R.id.floatingLembrete);
        actionLembrete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), LembreteActivity.class);
                startActivity(i);
            }
        });
        return root;
    }
}