package kr.hs.dgsw.realmemoapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MemoRepository extends SQLiteOpenHelper {

    private List<Memo> result;

    public MemoRepository(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        result = new ArrayList<>();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Memo (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT," +
                "content TEXT," +
                "updated INTEGER" +
        ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE Memo");
        onCreate(db);
    }

    private Memo addCursorDataToMemo(Cursor cursor) {
        Memo memo = new Memo();
        memo.setId(cursor.getLong(cursor.getColumnIndex("id")));
        memo.setTitle(cursor.getString(cursor.getColumnIndex("title")));
        memo.setContent(cursor.getString(cursor.getColumnIndex("content")));
        memo.setUpdated(cursor.getLong(cursor.getColumnIndex("updated")));

        return memo;
    }

    public List<Memo> getAll() {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query("memo", null, null, null, null, null, null);
        result.clear();

        while (cursor.moveToNext()) {
            Memo memo = addCursorDataToMemo(cursor);

            result.add(memo);
        }

        return result;
    }

    public Memo getOne(Long id) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query("memo", null, "id = ?", String.valueOf(id), null, null, null);

        while (cursor.moveToNext()) {
            Memo memo = addCursorDataToMemo(cursor);

            return memo;
        }

        return null;
    }

    public long insert(Memo memo) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", memo.getTitle());
        values.put("content", memo.getContent());
        values.put("updated", System.currentTimeMillis());

        return db.insert("memo", null, values);
    }

    public long delete(Memo memo) {
        SQLiteDatabase db = getWritableDatabase();

        return db.delete("memo", "id = ?", String.valueOf(memo.getId()));
    }

    public long delete(Long id) {
        SQLiteDatabase db = getWritableDatabase();

        return db.delete("memo", "id = ?", String.valueOf(id));
    }

    public long deleteAll() {
        SQLiteDatabase db = getWritableDatabase();

        return db.delete("memo", null, null);
    }

    public long update(Memo memo) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", memo.getTitle());
        values.put("content", memo.getContent());
        values.put("updated", System.currentTimeMillis());

        return db.update("memo", values, "id = ?", String.valueOf(memo.getId()));
    }
}
