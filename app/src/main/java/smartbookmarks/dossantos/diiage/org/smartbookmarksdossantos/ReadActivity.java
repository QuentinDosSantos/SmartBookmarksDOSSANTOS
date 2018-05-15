package smartbookmarks.dossantos.diiage.org.smartbookmarksdossantos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Comment;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ReadActivity extends AppCompatActivity {

    /**
     * ViewHolder d'une adresse
     */
    private static class CommentsViewHolder{
        public TextView txtBookTitle;
        public TextView txtComment;
        public TextView txtDateComment;
        public TextView txtPageNumber;

        public CommentsViewHolder(TextView txtBookTitle, TextView txtComment, TextView txtDateComment, TextView txtPageNumber) {
            this.txtBookTitle = txtBookTitle;
            this.txtComment = txtComment;
            this.txtDateComment = txtDateComment;
            this.txtPageNumber = txtPageNumber;
        }
    }

    private static class CommentsAdapter extends BaseAdapter{

        ArrayList<smartbookmarks.dossantos.diiage.org.smartbookmarksdossantos.Comment> comments;
        Activity context;

        public CommentsAdapter(Activity context, ArrayList<smartbookmarks.dossantos.diiage.org.smartbookmarksdossantos.Comment> comments){
            this.comments = comments;
            this.context = context;
        }

        @Override
        public int getCount() {
            return comments.size();
        }

        @Override
        public Object getItem(int position) {
            return comments.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //Déclaration de la vue qui sera retournée dans les deux cas
            View view;
            //Déclaration du ViewHolder qui permet de mémoriser les calculs de findViewById()
            CommentsViewHolder commentsViewHolder;

            //Si convertView n'est pas null, alors une vue est recyclée
            if(convertView != null){
                //On récupère alors directement cette vue
                view = convertView;
                //On récupère le viewHolder précédemment attaché à la vue
                commentsViewHolder = (CommentsViewHolder)view.getTag();
            } else {
                //Si convertView est null, il faut créer la vue, on fait donc appel au LayoutInflater
                LayoutInflater layoutInflater = this.context.getLayoutInflater();
                //On charge le layout d'un item adresse.
                // Le paramètre "root" est recommandé pour éviter les fuites mémoires, on lui passe parent,
                // tandis que le paramètre "attachToRoot" doit passé à false dans ce cas, l'attachement
                // de la vue étant géré par la ListView après l'appel à cette méthode.
                view = layoutInflater.inflate(R.layout.item_book, parent, false);

                //Une fois le layout chargé, on récupère des référence vers les sous-vues
                //à paramétrer du layout
                TextView txtBookTitle = view.findViewById(R.id.txtBookTitle);
                TextView txtComment = view.findViewById(R.id.txtComment);
                TextView txtDateComment = view.findViewById(R.id.txtDateComment);
                TextView txtPageNumber = view.findViewById(R.id.txtPageNumber);
                TextView txtStats = view.findViewById(R.id.txtStats);

                //On regroupe ces références dans un ViewHolder ...
                commentsViewHolder = new CommentsViewHolder(
                        txtBookTitle,
                        txtComment,
                        txtDateComment,
                        txtPageNumber
                );
                //... qu'on rattache à la vue pour une réutilisation future
                view.setTag(commentsViewHolder);
            }

            //Récupération de l'objet Adresse à la position en cours
            smartbookmarks.dossantos.diiage.org.smartbookmarksdossantos.Comment comment = comments.get(position);

            //Affectation des valeurs
            commentsViewHolder.txtBookTitle.setText(comment.getBook().getTitle());
            commentsViewHolder.txtComment.setText(comment.getComment());
            commentsViewHolder.txtDateComment.setText(comment.getDate());
            commentsViewHolder.txtPageNumber.setText(comment.getPage());

            //On retourne la vue ainsi générée ou recyclée
            return view;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);


        ArrayList<smartbookmarks.dossantos.diiage.org.smartbookmarksdossantos.Comment> comments = new ArrayList<>();

        ListView lvComments = findViewById(R.id.lvComments);

        //TODO get objects

        CommentsAdapter adapter = new CommentsAdapter(this,comments);
        lvComments.setAdapter(adapter);
    }
}
