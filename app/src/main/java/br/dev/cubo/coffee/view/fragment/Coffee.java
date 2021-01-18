package br.dev.cubo.coffee.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.dev.cubo.coffee.R;
import br.dev.cubo.coffee.adapter.ListProduct;
import br.dev.cubo.coffee.model.Produto;

public class Coffee extends Fragment {

    private View v;
    private ListView list;
    private String name, desc;
    private double money, qtdValue, all;

    private LinearLayout cardChoice,addCart,close;
    private TextView valueAll,valueMoney,descProd,nameProd;
    private EditText qtd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.coffee, container, false);

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

        String value = qtd.getText().toString();
        qtdValue = Double.parseDouble(value);

        //amount of values
        amount();

        String allm = String.valueOf(all);
        valueAll.setText("R$ " + allm);

        //get btn
        getButton();

    }

    /** get btn **/
    private void getButton() {

        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                list.setVisibility(View.VISIBLE);
                cardChoice.setVisibility(View.GONE);

            }
        });
    }

    /** amount **/
    private void amount() {

        all = qtdValue * money;

    }

    /**Any example of returning a list of Products.
     * @return list with all products*/
    private List<Produto> allProduct() {
        return new ArrayList<>(Arrays.asList(
                new Produto("Glace", "aaaaaaaaa", 7.92),
                new Produto("Gelado", "aaaaaaaaa", 14.32),
                new Produto("Latte", "aaaaaaaaa", 9.85),
                new Produto("Americano", "aaaaaaaaa", 5.15),
                new Produto("Irish", "aaaaaaaaa", 4.35),
                new Produto("Cappuccino", "aaaaaaaaa", 10.21),
                new Produto("Espresso", "aaaaaaaaa", 2.42),
                new Produto("Especial", "aaaaaaaaa", 7.11),
                new Produto("Levar", "aaaaaaaaa", 6.12),
                new Produto("Pacote", "aaaaaaaaa", 22.64)
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
    }
}