package com.example.palmasturist.ui.praiaDoCaju;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.palmasturist.R;
import com.example.palmasturist.ui.pracaDosGirassois.SlideshowViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PraiaCajuFragment extends Fragment implements OnMapReadyCallback {

    private SlideshowViewModel slideshowViewModel;
    private GoogleMap mMap;
    private static final CameraPosition PALMAS_CAMERA = new CameraPosition.Builder()
            .target(new LatLng(-10.186, -48.336)).zoom(0).bearing(0).tilt(70).build();
    private String link ="https://goo.gl/maps/2bFTkk9dM7iPQ6DE7";
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =new ViewModelProvider(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_praia_caju, container, false);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        SliderLayout sliderShow = (SliderLayout) root.findViewById(R.id.slider);
        mapFragment.getMapAsync(this);

        TextSliderView textSliderView = new TextSliderView(container.getContext());
        textSliderView.image(R.drawable.caju1);
        sliderShow.addSlider(textSliderView);

        TextSliderView textSliderView2 = new TextSliderView(container.getContext());
        textSliderView2.image(R.drawable.caju2);
        sliderShow.addSlider(textSliderView2);

        TextSliderView textSliderView3 = new TextSliderView(container.getContext());
        textSliderView3.image(R.drawable.caju3);
        sliderShow.addSlider(textSliderView3);


        FloatingActionButton actionButton = root.findViewById(R.id.buttonLocationCaju);
        FloatingActionButton actionWhatsApp = root.findViewById(R.id.floatingWhatsCaju);
        FloatingActionButton actionEmail = root.findViewById(R.id.floatingEmailCaju);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(actionWhatsApp.getVisibility() == View.GONE){
                    actionEmail.setVisibility(View.VISIBLE);
                    actionWhatsApp.setVisibility(View.VISIBLE);
                }else{
                    actionEmail.setVisibility(View.GONE);
                    actionWhatsApp.setVisibility(View.GONE);
                }
            }
        });
        actionWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = link;
                Intent intent = null;
                intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,text);
                intent.setType("text/plain");
                intent.setPackage("com.whatsapp");
                startActivity(intent);
            }
        });
        actionEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                String text = link;
                intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,text);
                intent.setType("text/plain");
                intent.setPackage("com.google.android.gm");
                startActivity(intent);
            }
        });
        return root;


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(PALMAS_CAMERA));

        LatLng local = new LatLng(-10.265352492926375, -48.363846180613066);
        mMap.addMarker(new MarkerOptions().position(local).title("Praça do Cajú"));

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (mMap != null) {
                mMap.setMyLocationEnabled(true);
            }
        }

    }
}