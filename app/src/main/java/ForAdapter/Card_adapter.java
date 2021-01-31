package ForAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mcard.R;

import java.util.ArrayList;

public class Card_adapter extends ArrayAdapter<Card_info>
{
   private Context data_card;
   private Integer card_res;

    public Card_adapter(@NonNull Context context, int resource, @NonNull ArrayList<Card_info> objects)
    {
        super(context, resource, objects);
        this.data_card = context;
        this.card_res = resource;
    }

    @SuppressLint("ViewHolder") @NonNull @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(data_card);
        convertView = layoutInflater.inflate(card_res, parent, false);

        ImageView photo_card = convertView.findViewById(R.id.photo);
        //TextView information_for_card = convertView.findViewById(R.id.card_info);

        Uri uri = getItem(position).getImage();
        photo_card.setImageURI(uri);
        //information_for_card.setText(getItem(position).getInfo());

        return convertView;
    }



}
