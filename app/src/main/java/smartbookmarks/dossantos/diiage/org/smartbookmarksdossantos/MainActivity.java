package smartbookmarks.dossantos.diiage.org.smartbookmarksdossantos;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnComments;
    Button btnAddComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnComments = (Button)findViewById(R.id.btnComments);
        btnAddComment = (Button)findViewById(R.id.btnAddComment);


        btnComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReadActivity.class);
                startActivity(intent);
            }
        });

        btnAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, WriteActivity.class);
                startActivity(intent);
            }
        });

        DatabaseAdapter maCollecDb = new DatabaseAdapter(this);
        SQLiteDatabase db = maCollecDb.getWritableDatabase();

        BookAdapter bookAdapter = new BookAdapter(db);
        CommentAdapter commentAdapter = new CommentAdapter(db);
        TextView txtStats = findViewById(R.id.txtStats);
        txtStats.setText("Il y a " + bookAdapter.getCount() + " livre(s), " + commentAdapter.getCount() + " commentaire(s), et une moyenne de " + (commentAdapter.getCount() / bookAdapter.getCount()) +"  commentaire(s) par livre");
    }
}
