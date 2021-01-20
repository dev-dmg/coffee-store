package br.dev.cubo.coffee.view.fragment;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
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

public class Cart extends Fragment {

    private View v;
    private ImageView imgCard;
    private TextView valueAllPay, size, nameProduct, qtdProduct;
    private LinearLayout addPay, cancel, content, header, pay, cancelPay, paymtDone;
    private ConstraintLayout choicePay, payDone;
    private String showValue, name, qtd, bigCup, mediumCup, smallCup, paymtKey;

    private LinearLayout credt, debt, qrCode;
    private boolean keyCredt, keyDebt, keyQrCode;

    private ScrollView scrollView;

    public static final String CART = "value_buy";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.cart, container, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.brown_light));
        }

        getComponent();
        getButton();

        @SuppressLint("ResourceType") Animation up = AnimationUtils.loadAnimation(getContext(), R.animator.splash_up);
        imgCard.startAnimation(up);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(CART, Context.MODE_PRIVATE);
        name = sharedPreferences.getString("name_key", null);
        showValue = sharedPreferences.getString("allValue_key", null);
        qtd = sharedPreferences.getString("qtd_key", null);
        smallCup = sharedPreferences.getString("small_key", null);
        mediumCup = sharedPreferences.getString("medium_key", null);
        bigCup = sharedPreferences.getString("big_key", null);
        paymtKey = sharedPreferences.getString("paymt_key", null);

        if (name != null) nameProduct.setText(name);
        if (showValue != null) valueAllPay.setText("R$ " + showValue);
        if (qtd != null) qtdProduct.setText(qtd);

        if (bigCup == "700ml") size.setText(bigCup);
        if (mediumCup == "500ml") size.setText(mediumCup);
        if (smallCup == "300ml") size.setText(smallCup);

        return v;
    }

    /**get btn**/
    private void getButton() {

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ObjectAnimator objectAnimator = ObjectAnimator.ofInt(scrollView, "scrollY", 500, 0).setDuration(800);
                objectAnimator.start();

                valueAllPay.setText("R$ 0.00");
                size.setText("----------");
                nameProduct.setText("----------");
                qtdProduct.setText("0");

                paymtKey = "";
                cleanSharedPreferences();

            }
        });

        addPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (paymtKey == "true") {

                    getMode();
                    content.setVisibility(View.GONE);

                    header.setVisibility(View.VISIBLE);
                    choicePay.setVisibility(View.VISIBLE);

                } else {

                    Toast.makeText(getActivity(), "Falha ao carregar os dados. Selecione o produto novamente!", Toast.LENGTH_LONG).show();
                }

            }
        });


        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (keyQrCode || keyDebt || keyCredt) {

                    content.setVisibility(View.GONE);
                    choicePay.setVisibility(View.GONE);

                    header.setVisibility(View.VISIBLE);
                    payDone.setVisibility(View.VISIBLE);

                    valueAllPay.setText("R$ 0.00");
                    size.setText("----------");
                    nameProduct.setText("----------");
                    qtdProduct.setText("0");

                    cleanSharedPreferences();


                } else {

                    Toast.makeText(getActivity(), "Selecione uma forma de pagamento", Toast.LENGTH_LONG).show();
                }

            }
        });


        cancelPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                choicePay.setVisibility(View.GONE);
                payDone.setVisibility(View.GONE);

                header.setVisibility(View.VISIBLE);
                content.setVisibility(View.VISIBLE);

                valueAllPay.setText("R$ 0.00");
                size.setText("----------");
                nameProduct.setText("----------");
                qtdProduct.setText("0");

                paymtKey = "";
                cleanSharedPreferences();

            }
        });

        paymtDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                choicePay.setVisibility(View.GONE);
                payDone.setVisibility(View.GONE);

                header.setVisibility(View.VISIBLE);
                content.setVisibility(View.VISIBLE);

                Coffee coffee = new Coffee();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.containerMain, coffee, "cart")
                        .commit();

            }
        });

    }

    /**paymt**/
    private void getMode() {

        credt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                keyCredt = true;
                keyDebt = false;
                keyQrCode = false;

                // change config
                changeMode();
            }
        });

        debt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                keyCredt = false;
                keyDebt = true;
                keyQrCode = false;

                // change config
                changeMode();
            }
        });

        qrCode.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {

                keyCredt = false;
                keyDebt = false;
                keyQrCode = true;

                // change config
                changeMode();
            }
        });


    }

    /**change paymt**/
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void changeMode() {

        if (keyCredt) {

            credt.setBackgroundResource(R.drawable.card_pay_on);
            debt.setBackgroundResource(R.drawable.card_pay);
            qrCode.setBackgroundResource(R.drawable.card_pay);

        }

        if (keyDebt) {

            credt.setBackgroundResource(R.drawable.card_pay);
            debt.setBackgroundResource(R.drawable.card_pay_on);
            qrCode.setBackgroundResource(R.drawable.card_pay);

        }

        if (keyQrCode) {

            credt.setBackgroundResource(R.drawable.card_pay);
            debt.setBackgroundResource(R.drawable.card_pay);
            qrCode.setBackgroundResource(R.drawable.card_pay_on);

        }

    }

    /**components**/
    private void getComponent() {

        valueAllPay = v.findViewById(R.id.valueAllPay);
        size = v.findViewById(R.id.size);
        nameProduct = v.findViewById(R.id.nameProduct);
        qtdProduct = v.findViewById(R.id.qtdProduct);

        addPay = v.findViewById(R.id.addPay);
        cancel = v.findViewById(R.id.cancell);
        content = v.findViewById(R.id.content);
        header = v.findViewById(R.id.header);

        credt = v.findViewById(R.id.credt);
        debt = v.findViewById(R.id.debt);
        qrCode = v.findViewById(R.id.qrCode);

        choicePay = v.findViewById(R.id.choicePay);
        payDone = v.findViewById(R.id.payDone);
        pay = v.findViewById(R.id.pay);
        cancelPay = v.findViewById(R.id.cancelPay);
        paymtDone = v.findViewById(R.id.paymtDone);

        imgCard = v.findViewById(R.id.imgCard);

        scrollView = v.findViewById(R.id.scrollView);
    }

    private void cleanSharedPreferences() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(CART, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    @Override
    public void onDestroy() {
        cleanSharedPreferences();
        super.onDestroy();
    }

    @Override
    public void onStop() {
        cleanSharedPreferences();
        super.onStop();
    }
}
