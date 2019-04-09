package kr.hs.dgsw.realmemoapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class MemoItemViewHolder extends RecyclerView.ViewHolder {

    TextView textViewTitle;
    TextView textViewUpdated;

    public MemoItemViewHolder(@NonNull View itemView) {
        super(itemView);

        textViewTitle = itemView.findViewById(R.id.textViewTitle);
        textViewUpdated = itemView.findViewById(R.id.textViewUpdated);
    }
}
