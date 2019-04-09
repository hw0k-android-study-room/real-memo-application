package kr.hs.dgsw.realmemoapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.Date;
import java.util.List;

public class MemoAdapter extends RecyclerView.Adapter<MemoItemViewHolder> {

    private List<Memo> memoList;
    private ItemClickListener listener;

    public MemoAdapter(List<Memo> memoList, ItemClickListener listener) {
        this.memoList = memoList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MemoItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MemoItemViewHolder(
                LayoutInflater
                        .from(viewGroup.getContext())
                        .inflate(R.layout.item_memo, viewGroup, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull MemoItemViewHolder viewHolder, int i) {
        Memo memo = memoList.get(i);
        final int index = i;

        viewHolder.textViewTitle.setText(memo.getTitle());
        viewHolder.textViewUpdated.setText(new Date(memo.getUpdated()).toString());

        viewHolder.itemView.setOnClickListener(view -> listener.onItemClick(view, index));
    }



    @Override
    public int getItemCount() {
        return memoList.size();
    }
}
