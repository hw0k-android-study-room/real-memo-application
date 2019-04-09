package kr.hs.dgsw.realmemoapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MemoRepository memoRepository;
    private MemoAdapter memoAdapter;
    private List<Memo> memoList;

    private RecyclerView recyclerViewMemo;
    private FloatingActionButton buttonCreate;

    private ItemClickListener itemClickListener = (view, i) -> {
        Memo memo = memoList.get(i);
        Intent intent = new Intent(this, MemoActivity.class);
        intent.putExtra("mode", "update");
        intent.putExtra("id", memo.getId());
        intent.putExtra("title", memo.getTitle());
        intent.putExtra("content", memo.getContent());
        startActivityForResult(intent, IntentReq.REQ_UPDATE);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        memoRepository = new MemoRepository(this, "memodb", null, 1);

        memoList = memoRepository.getAll();
        memoAdapter = new MemoAdapter(memoList, itemClickListener);

        recyclerViewMemo = findViewById(R.id.recyclerViewMemo);
        recyclerViewMemo.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMemo.setAdapter(memoAdapter);

        buttonCreate = findViewById(R.id.buttonCreate);
        buttonCreate.setOnClickListener(view -> {
            Intent intent = new Intent(this, MemoActivity.class);
            intent.putExtra("mode", "create");
            startActivityForResult(intent, IntentReq.REQ_CREATE);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == IntentReq.REQ_CREATE) {
                String title = data.getStringExtra("title");
                String content = data.getStringExtra("content");
                memoRepository.insert(new Memo(title, content));
                memoList = memoRepository.getAll();
                memoAdapter.notifyDataSetChanged();
            }

            else if (requestCode == IntentReq.REQ_UPDATE) {
                Long id = data.getLongExtra("id", 0L);
                String title = data.getStringExtra("title");
                String content = data.getStringExtra("content");
                memoRepository.update(new Memo(id, title, content));
                memoList = memoRepository.getAll();
                memoAdapter.notifyDataSetChanged();
            }
        }
        else if (resultCode == IntentReq.REQ_DELETE) {
            if (requestCode == IntentReq.REQ_UPDATE) {
                Long id = data.getLongExtra("id", 0L);
                memoRepository.delete(id);
                memoList = memoRepository.getAll();
                memoAdapter.notifyDataSetChanged();
            }
        }
    }
}
