package smartbookmarks.dossantos.diiage.org.smartbookmarksdossantos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Quentin on 05/04/2018.
 */

public class SpinnerBooksAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Book> data;

    LayoutInflater layoutInflater;

    ViewSpinnerHolder holder;

    public SpinnerBooksAdapter(ArrayList<Book> data, Context context) {
        super();
        this.data = data;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public int getCount(){
        return data.size();
    }

    public Book getItem(int position){
        return data.get(position);
    }

    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if(convertView == null)
        {
            vi = layoutInflater.inflate(R.layout.spinner_row, null);
            holder = new ViewSpinnerHolder((TextView) vi.findViewById(R.id.tvTitle));
            vi.setTag(holder);
        }
        else
        {
            holder = (ViewSpinnerHolder)vi.getTag();
        }

        Book book = getItem(position);

        if (book != null) {
            holder.tvTitle.setText(book.getTitle());
        }

        return vi;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {

        View vi = convertView;
        if(convertView == null)
        {
            vi = layoutInflater.inflate(R.layout.spinner_row, null);
            holder = new ViewSpinnerHolder((TextView) vi.findViewById(R.id.tvTitle));
            vi.setTag(holder);
        }
        else
        {
            holder = (ViewSpinnerHolder)vi.getTag();
        }

        Book book = getItem(position);

        if (book != null) {
            holder.tvTitle.setText(book.getTitle());
        }

        return vi;
    }
}

class ViewSpinnerHolder{
    public TextView tvTitle;

    public ViewSpinnerHolder(TextView tvTitle){
        this.tvTitle = tvTitle;
    }
}