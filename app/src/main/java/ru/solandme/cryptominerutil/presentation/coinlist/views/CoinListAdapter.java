package ru.solandme.cryptominerutil.presentation.coinlist.views;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import ru.solandme.cryptominerutil.R;
import ru.solandme.cryptominerutil.business.pojo.Algo;
import ru.solandme.cryptominerutil.business.pojo.Coin;

public class CoinListAdapter extends RecyclerView.Adapter<CoinListAdapter.CoinViewHolder> {
    private static int currentPosition = 0;

    public static class CoinViewHolder extends RecyclerView.ViewHolder {
        private TextView coinName;
        private TextView coinTag;
        private TextView algorithm;
        private TextView hashrate;
        private TextView profitBtcByDay;
        private TextView profitMoneyByDay;
        private TextView difNow;
        private TextView difByDay;
        private TextView difWard;
        private ImageView coinImage;


        private ConstraintLayout constraintLayout;
        private ConstraintLayout container;

        CoinViewHolder(View itemView) {
            super(itemView);
            coinName = itemView.findViewById(R.id.coin_name);
            coinTag = itemView.findViewById(R.id.coin_tag);
            algorithm = itemView.findViewById(R.id.algorithm);
            hashrate = itemView.findViewById(R.id.hashrate);
            profitBtcByDay = itemView.findViewById(R.id.profit_btc_day_field);
            profitMoneyByDay = itemView.findViewById(R.id.profit_money_day_field);
            difNow = itemView.findViewById(R.id.difficulty_now);
            difByDay = itemView.findViewById(R.id.difficulty_day);
            difWard = itemView.findViewById(R.id.dif_ward);
            coinImage = itemView.findViewById(R.id.coin_image);

            constraintLayout = itemView.findViewById(R.id.expandLayout);
            container = itemView.findViewById(R.id.container);
        }
    }

    private List<Coin> coins;
    private HashMap<String, Algo> algos;
    private Context context;

    CoinListAdapter(Context context, List<Coin> coins, HashMap<String, Algo> algos) {
        this.coins = coins;
        this.algos = algos;
        this.context = context;
    }

    @Override
    public CoinViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.coin_row, parent, false);
        return new CoinViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CoinViewHolder holder, final int position) {
        Coin coin = coins.get(position);
        holder.coinName.setText(coin.getName());
        holder.coinTag.setText(coin.getTag());
        holder.algorithm.setText(coin.getAlgo());
        holder.hashrate.setText(getHashrateByAlgo(coin.getAlgo()).toString());
        holder.profitBtcByDay.setText(String.format("%.8f", coin.getDayBtc() * getHashrateByAlgo(coin.getAlgo())));
        holder.profitMoneyByDay.setText(String.format("%.2f", coin.getDayBtc() * 4400)); //TODO добавить расчет по текущему курсу
        if (coin.getDifficultyNow() != null && coin.getDifficultyByDay() != null) {
            holder.difNow.setText(coin.getDifficultyNow().toString());
            holder.difByDay.setText(coin.getDifficultyByDay().toString());
        } else {
            holder.difNow.setText("n/a");
            holder.difByDay.setText("n/a");
        }


        Picasso.with(context)
                .load("https://files.coinmarketcap.com/static/img/coins/32x32/" + coin.getName().toLowerCase().replace(" ", "-") + ".png")
                .placeholder(R.drawable.ic_monet)
                .into(holder.coinImage);


        holder.constraintLayout.setVisibility(View.GONE);

        if (currentPosition == position) {

            if (holder.constraintLayout.getVisibility() == View.VISIBLE) {
                holder.constraintLayout.setVisibility(View.GONE);
                holder.constraintLayout.animate().translationY(holder.constraintLayout.getHeight()).setDuration(2000);
                ;
            } else {
                holder.constraintLayout.setVisibility(View.VISIBLE);
                holder.constraintLayout.animate().translationY(0).setDuration(2000);
                ;
            }


        }

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPosition = position;
                notifyDataSetChanged();
            }
        });
    }

    private Long getHashrateByAlgo(String algorithm) {
        return algos.get(algorithm.toLowerCase().replace(" ", "_")).getHashrate();
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
