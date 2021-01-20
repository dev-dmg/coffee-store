package br.dev.cubo.coffee.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.dev.cubo.coffee.R;
import br.dev.cubo.coffee.adapter.ListProduct;
import br.dev.cubo.coffee.model.Produto;

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

public class Coffee extends Fragment {

    private View v;
    private ListView list;
    private String name,desc,smallCup,mediumCup,bigCup,paymtKey,showValue = "R$ 0.00";
    private double money, all, val;
    private boolean keySmall, keyMedium, keyBig;

    private LinearLayout cardChoice,addCart,close;
    private TextView valueAll,valueMoney,descProd,nameProd;
    private TextView txtSmall, txtMedium, txtBig;
    private ImageView imgSmall, imgMedium, imgBig;
    private LinearLayout small, medium, big;
    private NumberPicker qtd;
    int valuePk;

    public static final String CART = "value_buy";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.coffee, container, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.status));
        }

        getComponents();

        List<Produto> produtos = allProduct();

        ListProduct listProduct = new ListProduct(produtos, getActivity());
        list.setAdapter(listProduct);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                name = produtos.get(i).getNome();
                desc = produtos.get(i).getDesc();
                money = produtos.get(i).getValor();

                openBox();

            }
        });;

        return v;
    }

    /** quantity and payment **/
    private void openBox() {

        list.setVisibility(View.GONE);
        cardChoice.setVisibility(View.VISIBLE);

        @SuppressLint("ResourceType") Animation up = AnimationUtils.loadAnimation(getContext(), R.animator.down_in);
        cardChoice.startAnimation(up);

        String m = String.valueOf(money);
        valueMoney.setText("R$ " + m);

        descProd.setText(desc);
        nameProd.setText(name);

        //get amount product
        qtdProduct();

        //get btn
        getButton();

        //get size cup
        getSize();

    }

    /** change size cup **/
    private void getSize() {

        small.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                keySmall = true;
                keyMedium = false;
                keyBig = false;

                // change config
                changeCup();
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                keySmall = false;
                keyMedium = true;
                keyBig = false;

                // change config
                changeCup();
            }
        });

        big.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                keySmall = false;
                keyMedium = false;
                keyBig = true;

                // change config
                changeCup();
            }
        });


    }

    /** change size cup **/
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void changeCup() {

        if (keySmall){

            small.setBackgroundResource(R.drawable.size_card_on);
            txtSmall.setTextColor(getResources().getColor(R.color.card));
            imgSmall.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.card)));

            medium.setBackgroundResource(R.drawable.size_card);
            txtMedium.setTextColor(getResources().getColor(R.color.card_off));
            imgMedium.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.card_off)));

            big.setBackgroundResource(R.drawable.size_card);
            txtBig.setTextColor(getResources().getColor(R.color.card_off));
            imgBig.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.card_off)));

            smallCup = "300ml";

        }

        if (keyMedium){

            small.setBackgroundResource(R.drawable.size_card);
            txtSmall.setTextColor(getResources().getColor(R.color.card_off));
            imgSmall.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.card_off)));

            medium.setBackgroundResource(R.drawable.size_card_on);
            txtMedium.setTextColor(getResources().getColor(R.color.card));
            imgMedium.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.card)));

            big.setBackgroundResource(R.drawable.size_card);
            txtBig.setTextColor(getResources().getColor(R.color.card_off));
            imgBig.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.card_off)));

            mediumCup = "500ml";

        }

        if (keyBig){

            small.setBackgroundResource(R.drawable.size_card);
            txtSmall.setTextColor(getResources().getColor(R.color.card_off));
            imgSmall.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.card_off)));

            medium.setBackgroundResource(R.drawable.size_card);
            txtMedium.setTextColor(getResources().getColor(R.color.card_off));
            imgMedium.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.card_off)));

            big.setBackgroundResource(R.drawable.size_card_on);
            txtBig.setTextColor(getResources().getColor(R.color.card));
            imgBig.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.card)));

            bigCup = "700ml";
        }

    }

    /** change amount product **/
    private void qtdProduct() {

        qtd.setMaxValue(20);
        qtd.setMinValue(0);

        qtd.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {

                valuePk = qtd.getValue();

                //amount of values
                amount();
            }
        });


    }

    /** get btn **/
    private void getButton() {

        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (smallCup == "300ml" || mediumCup == "500ml" || bigCup == "700ml"){

                    if (showValue == "R$ 0.00"){

                        Toast.makeText(getActivity(),"DEFINA A QUANTIDADE", Toast.LENGTH_LONG).show();

                    }else {

                        SharedPreferences sharedPreferences = getContext().getSharedPreferences(CART, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("allValue_key", String.valueOf(showValue));
                        editor.putString("name_key", String.valueOf(name));
                        editor.putString("qtd_key", String.valueOf(val));
                        editor.putString("paymt_key", (paymtKey = "true"));

                        if (keySmall){
                            editor.putString("small_key", (smallCup));
                        }else {
                            editor.putString("small_key", (smallCup = "0ml" ));
                        }

                        if (keyMedium){
                            editor.putString("medium_key", (mediumCup));
                        }else {
                            editor.putString("medium_key", (mediumCup = "0ml" ));
                        }

                        if (keyBig){
                            editor.putString("big_key", (bigCup));
                        }else {
                            editor.putString("big_key", (bigCup = "0ml" ));
                        }

                        editor.apply();

                        Cart cart = new Cart();
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .replace(R.id.containerMain, cart, "cart")
                                .commit();

                    }

                }else {

                    Toast.makeText(getActivity(),"DEFINA O TAMANHO", Toast.LENGTH_LONG).show();

                }

            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                list.setVisibility(View.VISIBLE);
                cardChoice.setVisibility(View.GONE);
                valueAll.setText("R$ 0.00");
                showValue = "R$ 0.00";
                qtd.setValue(0);

                smallCup = null;
                mediumCup = null;
                bigCup = null;

                small.setBackgroundResource(R.drawable.size_card);
                txtSmall.setTextColor(getResources().getColor(R.color.card_off));
                imgSmall.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.card_off)));

                medium.setBackgroundResource(R.drawable.size_card);
                txtMedium.setTextColor(getResources().getColor(R.color.card_off));
                imgMedium.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.card_off)));

                big.setBackgroundResource(R.drawable.size_card);
                txtBig.setTextColor(getResources().getColor(R.color.card_off));
                imgBig.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.card_off)));

            }
        });
    }

    /** amount **/
    private void amount() {

        val = Double.parseDouble(String.valueOf(valuePk));
        all = val * money;

        showValue = String.valueOf(all);
        valueAll.setText("R$ " + showValue);

    }

    /**Any example of returning a list of Products.
     * @return list with all products*/
    private List<Produto> allProduct() {
        return new ArrayList<>(Arrays.asList(
                new Produto("Glace", "leite condensado", 7.92),
                new Produto("Gelado", "bolas de sorvete", 14.32),
                new Produto("Latte", "cobertura de sorvete", 9.85),
                new Produto("Americano", "filtrado com papel ", 5.15),
                new Produto("Irish", "açúcar mascavo", 4.35),
                new Produto("Cappuccino", "chocolate em pó", 10.21),
                new Produto("Expresso", "café solúvel", 2.42),
                new Produto("Especial", "solúvel com sorvete", 7.11),
                new Produto("Levar", "café solúvel", 6.12),
                new Produto("Pacote", "orfeu intenso", 22.64)
        ));
    }

    /** get components **/
    private void getComponents() {

        list = v.findViewById(R.id.listProduct);

        cardChoice = v.findViewById(R.id.cardChoice);
        addCart = v.findViewById(R.id.addCart);
        close = v.findViewById(R.id.close);

        valueAll = v.findViewById(R.id.valueAll);
        valueMoney = v.findViewById(R.id.valueMoney);
        descProd = v.findViewById(R.id.descProd);
        nameProd = v.findViewById(R.id.nameProd);

        qtd = v.findViewById(R.id.qtd);

        txtSmall = v.findViewById(R.id.txtSmall);
        txtMedium = v.findViewById(R.id.txtMedium);
        txtBig = v.findViewById(R.id.txtBig);

        imgSmall = v.findViewById(R.id.imgSmall);
        imgMedium = v.findViewById(R.id.imgMedium);
        imgBig = v.findViewById(R.id.imgBig);

        small = v.findViewById(R.id.small);
        medium = v.findViewById(R.id.medium);
        big = v.findViewById(R.id.big);

    }
}