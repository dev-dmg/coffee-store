package br.dev.cubo.coffee.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.dev.cubo.coffee.R;
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

public class ListProduct extends BaseAdapter {

    private final List<Produto> produtos;
    private final Activity activity;

    public ListProduct(List<Produto> produtos, Activity activity) {
        this.produtos = produtos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return produtos.size();
    }

    @Override
    public Object getItem(int position) {
        return produtos.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View v = activity.getLayoutInflater().inflate(R.layout.item_product, viewGroup, false);

        Produto produto = produtos.get(position);
        TextView name = v.findViewById(R.id.name);
        TextView desc = v.findViewById(R.id.desc);
        TextView value = v.findViewById(R.id.value);
        ImageView img = v.findViewById(R.id.img);


        name.setText(produto.getNome());
        desc.setText(produto.getDesc());

        double money = produto.getValor();
        String m = String.valueOf(money);

        value.setText("R$ " + m);

        if (produto.getNome().equals("Glace")){
            img.setImageResource(R.drawable.ic_glace);

        }else if (produto.getNome().equals("Latte")){
            img.setImageResource(R.drawable.ic_latte);

        }else if (produto.getNome().equals("Americano")){
            img.setImageResource(R.drawable.ic_american);

        }else if (produto.getNome().equals("Irish")){
            img.setImageResource(R.drawable.ic_irish);

        }else if (produto.getNome().equals("Cappuccino")){
            img.setImageResource(R.drawable.ic_cappuccino);

        }else if (produto.getNome().equals("Espresso")){
            img.setImageResource(R.drawable.ic_expresso);

        }else if (produto.getNome().equals("Especial")){
            img.setImageResource(R.drawable.ic_home);

        }else if (produto.getNome().equals("Levar")){
            img.setImageResource(R.drawable.ic_way);

        }else if (produto.getNome().equals("Gelado")){
            img.setImageResource(R.drawable.ic_ice);

        }else if (produto.getNome().equals("Pacote")){
            img.setImageResource(R.drawable.ic_package);
        }

        return v;
    }
}
