package br.dev.cubo.coffee.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import br.dev.cubo.coffee.R;
import br.dev.cubo.coffee.adapter.SliderAdapter;
import br.dev.cubo.coffee.view.fragment.BoySlide;
import br.dev.cubo.coffee.view.fragment.GrilSlide;
import br.dev.cubo.coffee.view.fragment.StoreSlide;

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

public class Onboarding extends AppCompatActivity {

    private ViewPager slider;
    private PagerAdapter pagerAdapter;
    private LinearLayout dotsSlider;
    private TextView[] dots;
    private LinearLayout notAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.mov_in, R.anim.mov_out);
        setContentView(R.layout.onboarding);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        slider = findViewById(R.id.vpSlider);
        dotsSlider = findViewById(R.id.dotsSlider);
        notAccount = findViewById(R.id.notAccount);

        getButton();

        addDots(0);
        slider.addOnPageChangeListener(viewListener);

        List<Fragment> list = new ArrayList<>();
        list.add(new StoreSlide());
        list.add(new BoySlide());
        list.add(new GrilSlide());

        pagerAdapter = new SliderAdapter(getSupportFragmentManager(), list);
        slider.setAdapter(pagerAdapter);
    }

    /** action button **/
    private void getButton() {

        notAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent open = new Intent(Onboarding.this, MainActivity.class);
                startActivity(open);
                finish();
            }
        });
    }

    /** method confg dots **/
    public void addDots(int position){

        dots = new TextView[3];
        dotsSlider.removeAllViews();

        for (int i = 0; i < dots.length; i++){

            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.brown_day));

            dotsSlider.addView(dots[i]);
        }

        if (dots.length > 0){

            dots[position].setTextColor(getResources().getColor(R.color.brown_dark));
        }

    }

    /** method listener ViewPager **/
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDots(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}