package smartbookmarks.dossantos.diiage.org.smartbookmarksdossantos;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;

public class WriteActivity extends AppCompatActivity
{
    ArrayList<Book> books;
    SpinnerBooksAdapter customAdapter;
    Spinner spBooks;
    Book book;
    Button btnSubmit;
    EditText editComment;
    EditText txtPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        book = new Book();
        spBooks = findViewById(R.id.spnBooks);
        btnSubmit = findViewById(R.id.btnValidComment);
        editComment = findViewById(R.id.txtComment);
        txtPage = findViewById(R.id.txtPage);

        DatabaseAdapter maCollecDb = new DatabaseAdapter(this);
        SQLiteDatabase db = maCollecDb.getWritableDatabase();
        final BookAdapter ba = new BookAdapter(db);
        final CommentAdapter ca = new CommentAdapter(db);
        books = ba.getAll();

        customAdapter = new SpinnerBooksAdapter(books, this);
        spBooks.setAdapter(customAdapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                book = (Book)spBooks.getSelectedItem();
                ca.insert(book.getId(), editComment.getText().toString(), Integer.parseInt(txtPage.getText().toString()));
                Intent intent = new Intent(WriteActivity.this, ReadActivity.class);
                startActivity(intent);
            }
        });

    }
}
