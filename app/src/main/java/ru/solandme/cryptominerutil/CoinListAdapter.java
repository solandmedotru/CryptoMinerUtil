package ru.solandme.cryptominerutil;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ru.solandme.cryptominerutil.pojo.Algo;
import ru.solandme.cryptominerutil.pojo.Coin;

public class CoinListAdapter extends RecyclerView.Adapter<CoinListAdapter.ViewHolder> {
    private List<Coin> coins;
    private List<Algo> algos;


    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView coinName;
        private TextView coinTag;
        private TextView algorithm;
        private TextView hashrate;
        private TextView profitBtcByDay;
        private TextView profitMoneyByDay;
        private ImageView coinImage;

        ViewHolder(View itemView) {
            super(itemView);
            coinName = itemView.findViewById(R.id.coin_name);
            coinTag = itemView.findViewById(R.id.coin_tag);
            algorithm = itemView.findViewById(R.id.algorithm);
            hashrate = itemView.findViewById(R.id.hashrate);
            profitBtcByDay = itemView.findViewById(R.id.profit_btc_day);
            profitMoneyByDay = itemView.findViewById(R.id.profit_money_day);
            coinImage = itemView.findViewById(R.id.coin_image);

        }
    }

    public CoinListAdapter(List<Coin> coins, List<Algo> algos) {
        this.coins = coins;
        this.algos = algos;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.coin_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.coinName.setText(coins.get(position).getName());
        holder.coinTag.setText(coins.get(position).getTag());
        holder.algorithm.setText(coins.get(position).getAlgo());
        holder.hashrate.setText(getHashrateByAlgo(coins.get(position).getAlgo()));
        holder.coinName.setText(coins.get(position).getName());
        holder.coinName.setText(coins.get(position).getName());
        holder.coinImage.setImageResource(R.drawable.bitcoin);

    }

    private String getHashrateByAlgo(String algorithm) {
        String hashrate = "n/a";
        for (Algo a : algos) {
            if (a.getName().equals(algorithm)) hashrate = a.getHashrate().toString();
        }
        return hashrate;
    }

    @Override
    public int getItemCount() {
        return coins.size();
    }


}
