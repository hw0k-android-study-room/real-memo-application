package kr.hs.dgsw.realmemoapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MemoActivity extends AppCompatActivity {

    private EditText editTextTitle;
    private EditText editTextContent;
    private Button buttonDelete;
    private Button buttonSave;

    private String mode;
    private Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextContent = findViewById(R.id.editTextContent);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonSave = findViewById(R.id.buttonSave);

        Intent comingIntent = getIntent();
        mode = comingIntent.getStringExtra("mode");

        if (mode.equals("create")) {
            buttonDelete.setEnabled(false);
        }

        if (mode.equals("update")) {
            id = comingIntent.getLongExtra("id", 0L);
            editTextTitle.setText(comingIntent.getStringExtra("title"));
            editTextContent.setText(comingIntent.getStringExtra("content"));
        }

        buttonSave.setOnClickListener(view -> {
            String title = editTextTitle.getEditableText().toString().trim();
            String content = editTextContent.getEditableText().toString().trim();

            if (title.equals("") || content.equals("")) {
                Toast.makeText(this, "제목이랑 내용을 한 글자라도 넣어주세요 ㅠㅠ", Toast.LENGTH_SHORT).show();
                return;
            }

            if (mode.equals("create")) {
                Intent goingIntent = new Intent();
                goingIntent.putExtra("title", title);
                goingIntent.putExtra("content", content);
                setResult(Activity.RESULT_OK, goingIntent);
                finish();
            }

            if (mode.equals("update")) {
                Intent goingIntent = new Intent();
                goingIntent.putExtra("id", id);
                goingIntent.putExtra("title", title);
                goingIntent.putExtra("content", content);
                setResult(Activity.RESULT_OK, goingIntent);
                finish();
            }
        });

        buttonDelete.setOnClickListener(view -> {
            Intent goingIntent = new Intent();
            goingIntent.putExtra("id", id);
            setResult(IntentReq.REQ_DELETE, goingIntent);
            finish();
        });
    }
}
