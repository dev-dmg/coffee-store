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

    private Boolean keyStore, keyCoffee, keyCart, keySetting;

    private ImageView imgStore,imgCoffee,imgCart,imgSetting;

    private LinearLayout lineSetting, lineCart,lineCoffee,lineStore;
    private LinearLayout store,coffee,cart,setting;
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
                keyCart = false;
                keySetting = false;

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
                keyCart = false;
                keySetting = false;

                changeButtons();

                Coffee coffee = new Coffee();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.containerMain, coffee);
                ft.commit();
            }
        });

        //eat bnt
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                keyStore = false;
                keyCoffee = false;
                keyCart = true;
                keySetting = false;

                changeButtons();

                Eat eat = new Eat();
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.containerMain, eat);
                ft.commit();
            }
        });

        //about bnt
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                keyStore = false;
                keyCoffee = false;
                keyCart = false;
                keySetting = true;

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
            imgCart.setImageResource(R.drawable.ic_cart_off);
            imgSetting.setImageResource(R.drawable.ic_settings_off);

            lineStore.setVisibility(View.VISIBLE);
            lineCoffee.setVisibility(View.GONE);
            lineCart.setVisibility(View.GONE);
            lineSetting.setVisibility(View.GONE);

            lineStore.startAnimation(up);

            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            long milliseconds = 50;
            vibrator.vibrate(milliseconds);

        }

        if (keyCoffee){

            imgStore.setImageResource(R.drawable.ic_store_off);
            imgCoffee.setImageResource(R.drawable.ic_coffee_on);
            imgCart.setImageResource(R.drawable.ic_cart_off);
            imgSetting.setImageResource(R.drawable.ic_settings_off);

            lineStore.setVisibility(View.GONE);
            lineCoffee.setVisibility(View.VISIBLE);
            lineCart.setVisibility(View.GONE);
            lineSetting.setVisibility(View.GONE);

            lineCoffee.startAnimation(up);

            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            long milliseconds = 50;
            vibrator.vibrate(milliseconds);

        }

        if (keyCart){

            imgStore.setImageResource(R.drawable.ic_store_off);
            imgCoffee.setImageResource(R.drawable.ic_coffee_off);
            imgCart.setImageResource(R.drawable.ic_cart_on);
            imgSetting.setImageResource(R.drawable.ic_settings_off);

            lineStore.setVisibility(View.GONE);
            lineCoffee.setVisibility(View.GONE);
            lineCart.setVisibility(View.VISIBLE);
            lineSetting.setVisibility(View.GONE);

            lineCart.startAnimation(up);

            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            long milliseconds = 50;
            vibrator.vibrate(milliseconds);

        }

        if (keySetting){

            imgStore.setImageResource(R.drawable.ic_store_off);
            imgCoffee.setImageResource(R.drawable.ic_coffee_off);
            imgCart.setImageResource(R.drawable.ic_cart_off);
            imgSetting.setImageResource(R.drawable.ic_settings_on);

            lineStore.setVisibility(View.GONE);
            lineCoffee.setVisibility(View.GONE);
            lineCart.setVisibility(View.GONE);
            lineSetting.setVisibility(View.VISIBLE);

            lineSetting.startAnimation(up);

            Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            long milliseconds = 50;
            vibrator.vibrate(milliseconds);

        }

    }

    /** components **/
    private void getComponent() {

        imgStore = findViewById(R.id.imgStore);
        imgCoffee = findViewById(R.id.imgCoffee);
        imgCart = findViewById(R.id.imgCart);
        imgSetting = findViewById(R.id.imgSetting);

        lineSetting = findViewById(R.id.lineSetting);
        lineCart = findViewById(R.id.lineCart);
        lineCoffee = findViewById(R.id.lineCoffee);
        lineStore = findViewById(R.id.lineStore);

        store = findViewById(R.id.store);
        coffee = findViewById(R.id.coffee);
        cart = findViewById(R.id.cart);
        setting = findViewById(R.id.setting);

        containerMain = findViewById(R.id.containerMain);
//        navTop = findViewById(R.id.navTop);

    }
}