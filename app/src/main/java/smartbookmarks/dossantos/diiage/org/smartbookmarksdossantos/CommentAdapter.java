package smartbookmarks.dossantos.diiage.org.smartbookmarksdossantos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Quentin on 05/04/2018.
 */

public class CommentAdapter {
    public static final String TABLE = "comments";

    private SQLiteDatabase db = null;

    public CommentAdapter(SQLiteDatabase db) {
        this.db = db;
    }

    public ArrayList<Comment> getAll() {
        Cursor cursor = db.query(TABLE, //table
                new String[]{"id", "bookId", "comment", "date"}, //colonnes
                null, //selection (where)
                null, //paramètres de sélection
                null, //order
                null, //group
                null); //limit

        ArrayList<Comment> comments = new ArrayList<Comment>();
        comments = this.fillCommentList(cursor);

        return comments;
    }

    public long insert(long bookId, String comment, int page){
        long id = -1;
        String x = "";
        try {

            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
            String formattedDate = df.format(c.getTime());

            ContentValues contentValue = new ContentValues();
            contentValue.put("bookId", bookId);
            contentValue.put("comment", comment);
            contentValue.put("date", formattedDate);
            contentValue.put("page", page);
            id = db.insert(TABLE, null, contentValue);

            return id;
        }
        catch(Exception ex){
            x = ex.toString();
            return id;
        }
    }

    public long getCount(){
        long count  = DatabaseUtils.queryNumEntries(db, TABLE);
        return count;
    }

    private ArrayList<Comment> fillCommentList(Cursor cursor){
        ArrayList<Comment> comments = new ArrayList<Comment>();
        BookAdapter ba = new BookAdapter(db);
        while (cursor.moveToNext()){
            Comment comment = new Comment();
            int idColumnIndex = cursor.getColumnIndex("id");
            int bookIdColumnIndex = cursor.getColumnIndex("bookId");
            int commentColumnIndex = cursor.getColumnIndex("comment");
            int dateColumnIndex = cursor.getColumnIndex("date");

            comment.setId(cursor.getLong(idColumnIndex));
            comment.setBookId(cursor.getLong(bookIdColumnIndex));
            comment.setComment(cursor.getString(commentColumnIndex));
            comment.setDate(cursor.getString(dateColumnIndex));

            comment.setBook(ba.getById(comment.getBookId()));

            comments.add(comment);
        }

        return comments;
    }
}