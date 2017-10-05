package ru.solandme.cryptominerutil;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.solandme.cryptominerutil.pojo.Algo;
import ru.solandme.cryptominerutil.pojo.Coin;

public class CoinListAdapter extends RecyclerView.Adapter<CoinListAdapter.CoinViewHolder> {

    public static class CoinViewHolder extends RecyclerView.ViewHolder {
        private TextView coinName;
        private TextView coinTag;
        private TextView algorithm;
        private TextView hashrate;
        private TextView profitBtcByDay;
        private TextView profitMoneyByDay;

        CoinViewHolder(View itemView) {
            super(itemView);
            coinName = itemView.findViewById(R.id.coin_name);
            coinTag = itemView.findViewById(R.id.coin_tag_field);
            algorithm = itemView.findViewById(R.id.algorithm);
            hashrate = itemView.findViewById(R.id.hashrate_field);
            profitBtcByDay = itemView.findViewById(R.id.profit_btc_day_field);
            profitMoneyByDay = itemView.findViewById(R.id.profit_money_day_field);
        }
    }

    private List<Coin> coins;
    private List<Algo> algos;

    CoinListAdapter(List<Coin> coins, List<Algo> algos) {
        this.coins = coins;
        this.algos = algos;
    }

    @Override
    public CoinViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.coin_row, parent, false);
        return new CoinViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CoinViewHolder holder, int position) {
        holder.coinName.setText(coins.get(position).getName());
        holder.coinTag.setText(coins.get(position).getTag());
        holder.algorithm.setText(coins.get(position).getAlgo());
        holder.hashrate.setText(getHashrateByAlgo(coins.get(position).getAlgo()));
        holder.profitBtcByDay.setText(coins.get(position).getDayBtc().toString());
        holder.profitMoneyByDay.setText(coins.get(position).getDayBtc()*4400 + ""); //TODO добавить расчет по текущему курсу
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

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }




}
