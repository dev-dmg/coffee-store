package br.dev.cubo.coffee.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.coffee, container, false);

        list = v.findViewById(R.id.listProduct);
        List<Produto> produtos = allProduct();

        ListProduct listProduct = new ListProduct(produtos, getActivity());
        list.setAdapter(listProduct);

        return v;
    }

    /**
     * Exemplo qualquer de devolução de uma lista de cursos.
     * Para esse exemplo será considerado um hard coded.
     *
     * @return lista com todos os cursos
     */
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
}