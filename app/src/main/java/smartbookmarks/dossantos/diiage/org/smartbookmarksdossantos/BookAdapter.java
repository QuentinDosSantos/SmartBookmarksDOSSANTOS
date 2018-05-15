package smartbookmarks.dossantos.diiage.org.smartbookmarksdossantos;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Quentin on 05/04/2018.
 */

public class BookAdapter {
    public static final String TABLE = "books";

    private SQLiteDatabase db = null;

    public BookAdapter(SQLiteDatabase db) {
        this.db = db;
    }

    public ArrayList<Book> getAll(){
        Cursor cursor = db.query(TABLE, //table
                new String[]{"id", "title", "author", "genre"}, //colonnes
                null, //selection (where)
                null, //paramètres de sélection
                null, //group
                null, //having
                "title ASC", //orderby
                null); //limit

        ArrayList<Book> books = new ArrayList<Book>();
        books = this.fillBookList(cursor);

        return books;
    }

    public Book getById(long id){
        Cursor cursor = db.query(TABLE, //table
                new String[]{"id", "title", "author", "genre"}, //colonnes
                "id LIKE ?", //selection (where)
                new String[]{String.valueOf(id)}, //paramètres de sélection
                null, //order
                null, //group
                null); //limit

        Book book = new Book();

        if (cursor.moveToFirst()){
            book.setId(cursor.getLong(0));
            book.setTitle(cursor.getString(1));
            book.setAuthor(cursor.getString(2));
            book.setGenre(cursor.getString(3));
        }

        cursor.close();

        return book;
    }

    public long insert(String title, String author, String genre){
        long id = -1;
        try {
            ContentValues contentValue = new ContentValues();
            contentValue.put("title", title);
            contentValue.put("author", author);
            contentValue.put("genre", genre);
            id = db.insert(TABLE, null, contentValue);

            return id;
        }
        catch(Exception ex){
            return id;
        }
    }

    public long getCount(){
        long count  = DatabaseUtils.queryNumEntries(db, TABLE);
        return count;
    }

    public long getCommentsCount(long bookId){
        long result = -1;
        String query = "SELECT count(comments.id) AS commentCount " +
                "FROM comments " +
                "WHERE bookId = " + bookId + " ;";

        Cursor cursor = db.rawQuery(query, new String [] {});

        if (cursor.moveToFirst()) {
            result = cursor.getLong(0);
        }

        return result;
    }

    private ArrayList<Book> fillBookList(Cursor cursor){
        ArrayList<Book> books = new ArrayList<Book>();
        while (cursor.moveToNext()){
            Book book = new Book();
            int idColumnIndex = cursor.getColumnIndex("id");
            int titleIdColumnIndex = cursor.getColumnIndex("title");
            int authorColumnIndex = cursor.getColumnIndex("author");
            int genreColumnIndex = cursor.getColumnIndex("genre");

            book.setId(cursor.getLong(idColumnIndex));
            book.setTitle(cursor.getString(titleIdColumnIndex));
            book.setAuthor(cursor.getString(authorColumnIndex));
            book.setGenre(cursor.getString(genreColumnIndex));
            book.setCommentsCount(this.getCommentsCount(cursor.getLong(idColumnIndex)));

            books.add(book);
        }

        return books;
    }
}