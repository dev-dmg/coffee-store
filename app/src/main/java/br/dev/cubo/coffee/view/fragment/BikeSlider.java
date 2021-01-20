package br.dev.cubo.coffee.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import br.dev.cubo.coffee.R;

/**
 *
 *
 * @company CUBO
 * @site www.cubo.dev.br
 * @phone +55 11 9-7727-8055
 * @department development/support and design UI & UX
 *
 * @author D.M.G.
 * @since create at 2021-17-01
 *
 *
 */

public class BikeSlider extends Fragment {

    private View v;
    private ImageView img;
    private LinearLayout txt;

    private Animation mov,txtAnim;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.slide_bike, container, false);

        img = v.findViewById(R.id.imgBike);
        txt = v.findViewById(R.id.txtBike);

        getAnime();

        img.startAnimation(mov);
        txt.startAnimation(txtAnim);

        return v;
    }

    @SuppressLint("ResourceType")
    private void getAnime() {

         mov = AnimationUtils.loadAnimation(getContext(), R.animator.bike);
         txtAnim = AnimationUtils.loadAnimation(getContext(), R.animator.txt);
    }
}