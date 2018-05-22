package com.example.android.rummy500scorepad;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {
    private final PlayerClickHandler mClickHandler;
    private List<String[]> allPlayers;

    public interface PlayerClickHandler {
        void onClick(int buttonId);
    }

    public PlayerAdapter(PlayerClickHandler handler) {
        this.mClickHandler = handler;
    }

    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.player_row, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewHolder holder, int position) {
        holder.bind(allPlayers.get(position));
    }

    @Override
    public int getItemCount() {
        if (allPlayers == null) return 0;

        return allPlayers.size();
    }

    public void setData(List<String[]> data) {
        this.allPlayers = data;
        notifyDataSetChanged();
    }


    /**
     * View holder class for each player row
     */
    class PlayerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView score, playerName;
        Button plusFive, plusTen, plusFifteen, minusFive;

        public PlayerViewHolder(View itemView) {
            super(itemView);

            score = itemView.findViewById(R.id.player_score);
            playerName = itemView.findViewById(R.id.player_name);
            plusFive = itemView.findViewById(R.id.plus_five);
            plusTen = itemView.findViewById(R.id.plus_ten);
            plusFifteen = itemView.findViewById(R.id.plus_fifteen);
            minusFive = itemView.findViewById(R.id.minus_five);

            playerName.setOnClickListener(this);
            plusFive.setOnClickListener(this);
            plusTen.setOnClickListener(this);
            plusFifteen.setOnClickListener(this);
            minusFive.setOnClickListener(this);
        }

        public void bind(String[] player) {
            playerName.setText(player[0]);
            score.setText(player[1]);
        }

        @Override
        public void onClick(View v) {
            int increment = 0;

            switch (v.getId()) {
                case R.id.player_name:
                    Log.d("ViewHolder", "Click on name button detected");
                    break;
                case R.id.plus_five:
                    increment = 5;
                    break;
                case R.id.minus_five:
                    increment = -5;
                    break;
                case R.id.plus_ten:
                    increment = 10;
                    break;
                case R.id.plus_fifteen:
                    increment = 15;
                    break;
            }

            if(increment != 0) {
                int position = getAdapterPosition();
                int val = Integer.valueOf(allPlayers.get(position)[1]);
                val += increment;
                allPlayers.get(position)[1] = String.valueOf(val);
                Log.d("ViewHolder", "new value " + val);
                notifyDataSetChanged();
            }
        }
    }
}
