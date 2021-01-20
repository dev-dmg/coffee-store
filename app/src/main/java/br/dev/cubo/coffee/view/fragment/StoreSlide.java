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

public class StoreSlide extends Fragment {

    private View v;
    private ImageView img, cloudL, cloudR;
    private LinearLayout txt;

    private Animation mov,txtAnim,cloudl,cloud;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.slide_store, container, false);

        img = v.findViewById(R.id.imgCoff);
        cloudL = v.findViewById(R.id.cloude_l);
        cloudR = v.findViewById(R.id.cloude_r);
        txt = v.findViewById(R.id.txtHome);

        getAnime();

        img.startAnimation(mov);
        txt.startAnimation(txtAnim);
        cloudL.startAnimation(cloudl);
        cloudR.startAnimation(cloud);

        return v;
    }

    @SuppressLint("ResourceType")
    private void getAnime() {

        mov = AnimationUtils.loadAnimation(getContext(), R.animator.splash_up);
        txtAnim = AnimationUtils.loadAnimation(getContext(), R.animator.txt);
        cloud = AnimationUtils.loadAnimation(getContext(), R.animator.cloud);
        cloudl = AnimationUtils.loadAnimation(getContext(), R.animator.cloud_l);
    }
}