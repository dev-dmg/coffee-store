package br.dev.cubo.coffee.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import br.dev.cubo.coffee.R;
import br.dev.cubo.coffee.view.fragment.About;
import br.dev.cubo.coffee.view.fragment.Coffee;
import br.dev.cubo.coffee.view.fragment.Eat;
import br.dev.cubo.coffee.view.fragment.Home;

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

public class MainActivity extends AppCompatActivity {

    private Boolean keyStore, keyCoffee, keyEat, keyAbout;

    private ImageView imgStore,imgCoffee,imgEat,imgAbout;

    private LinearLayout lineAbout, lineEat,lineCoffee,lineStore;
    private LinearLayout store,coffee,eat,about;
    private LinearLayout containerMain,navTop;

    private Animation up, down;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.mov_in, R.anim.mov_out);
        setContentView(R.layout.main);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        getComponent();
        getButtons();
        getAnim();

        Home home = new Home();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.containerMain, home);
        ft.commit();

    }

    /** anim **/
    @SuppressLint("ResourceType")
    private void getAnim() {

        up = AnimationUtils.loadAnimation(this, R.animator.down_in);
        down = AnimationUtils.loadAnimation(this, R.animator.down_out);

    }

    /** btn **/
    private void getButtons() {

        //store bnt
        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                keyStore = true;
                keyCoffee = false;
                keyEat = false;
                keyAbout = false;

                changeButtons();

                Home home = new Home();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.containerMain, home);
                ft.commit();
            }
        });

        //coffee bnt
        coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                keyStore = false;
                keyCoffee = true;
                keyEat = false;
                keyAbout = false;

                changeButtons();

                Coffee coffee = new Coffee();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.containerMain, coffee);
                ft.commit();
            }
        });

        //eat bnt
        eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                keyStore = false;
                keyCoffee = false;
                keyEat = true;
                keyAbout = false;

                changeButtons();

                Eat eat = new Eat();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.containerMain, eat);
                ft.commit();
            }
        });

        //about bnt
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                keyStore = false;
                keyCoffee = false;
                keyEat = false;
                keyAbout = true;

                changeButtons();

                About about = new About();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.containerMain, about);
                ft.commit();
            }
        });
    }

    /** change btn **/
    private void changeButtons(){

        if (keyStore){

            imgStore.setImageResource(R.drawable.ic_store_on);
            imgCoffee.setImageResource(R.drawable.ic_coffee_off);
            imgEat.setImageResource(R.drawable.ic_eat_off);
            imgAbout.setImageResource(R.drawable.ic_about_off);

            lineStore.setVisibility(View.VISIBLE);
            lineCoffee.setVisibility(View.GONE);
            lineEat.setVisibility(View.GONE);
            lineAbout.setVisibility(View.GONE);

            lineStore.startAnimation(up);

            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            long milliseconds = 50;
            vibrator.vibrate(milliseconds);

        }

        if (keyCoffee){

            imgStore.setImageResource(R.drawable.ic_store_off);
            imgCoffee.setImageResource(R.drawable.ic_coffee_on);
            imgEat.setImageResource(R.drawable.ic_eat_off);
            imgAbout.setImageResource(R.drawable.ic_about_off);

            lineStore.setVisibility(View.GONE);
            lineCoffee.setVisibility(View.VISIBLE);
            lineEat.setVisibility(View.GONE);
            lineAbout.setVisibility(View.GONE);

            lineCoffee.startAnimation(up);

            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            long milliseconds = 50;
            vibrator.vibrate(milliseconds);

        }

        if (keyEat){

            imgStore.setImageResource(R.drawable.ic_store_off);
            imgCoffee.setImageResource(R.drawable.ic_coffee_off);
            imgEat.setImageResource(R.drawable.ic_eat_on);
            imgAbout.setImageResource(R.drawable.ic_about_off);

            lineStore.setVisibility(View.GONE);
            lineCoffee.setVisibility(View.GONE);
            lineEat.setVisibility(View.VISIBLE);
            lineAbout.setVisibility(View.GONE);

            lineEat.startAnimation(up);

            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            long milliseconds = 50;
            vibrator.vibrate(milliseconds);

        }

        if (keyAbout){

            imgStore.setImageResource(R.drawable.ic_store_off);
            imgCoffee.setImageResource(R.drawable.ic_coffee_off);
            imgEat.setImageResource(R.drawable.ic_eat_off);
            imgAbout.setImageResource(R.drawable.ic_about_on);

            lineStore.setVisibility(View.GONE);
            lineCoffee.setVisibility(View.GONE);
            lineEat.setVisibility(View.GONE);
            lineAbout.setVisibility(View.VISIBLE);

            lineAbout.startAnimation(up);

            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            long milliseconds = 50;
            vibrator.vibrate(milliseconds);

        }

    }

    /** components **/
    private void getComponent() {

        imgStore = findViewById(R.id.imgStore);
        imgCoffee = findViewById(R.id.imgCoffee);
        imgEat = findViewById(R.id.imgEat);
        imgAbout = findViewById(R.id.imgAbout);

        lineAbout = findViewById(R.id.lineAbout);
        lineEat = findViewById(R.id.lineEat);
        lineCoffee = findViewById(R.id.lineCoffee);
        lineStore = findViewById(R.id.lineStore);

        store = findViewById(R.id.store);
        coffee = findViewById(R.id.coffee);
        eat = findViewById(R.id.eat);
        about = findViewById(R.id.about);

        containerMain = findViewById(R.id.containerMain);
//        navTop = findViewById(R.id.navTop);

    }
}