package br.dev.cubo.coffee.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

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

public class SplashScreen extends AppCompatActivity {

    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        hideStatusBarAndNavigationBar();

        @SuppressLint("WrongViewCast") Drawable bkg = findViewById(R.id.splash).getBackground();
        bkg.setAlpha(90);

        logo = findViewById(R.id.logo);
        @SuppressLint("ResourceType") Animation aniMovIn = AnimationUtils.loadAnimation(getApplicationContext(),R.animator.intro);
        logo.startAnimation(aniMovIn);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getActivity();
            }

        }, 4000);
    }

    /** open Activity **/
    private void getActivity() {
        Intent open = new Intent(SplashScreen.this, Onboarding.class);
        startActivity(open);
        finish();
    }

    /** hide StatusBar and NavigationBar **/
    private void hideStatusBarAndNavigationBar() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            View mDecorView = getWindow().getDecorView();
            mDecorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                            | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                            | View.SYSTEM_UI_FLAG_IMMERSIVE
            );
        }
    }

}