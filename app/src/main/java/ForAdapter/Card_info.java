package ForAdapter;

import android.net.Uri;
import android.widget.ImageView;

public class Card_info
{
    private Uri image;
    private String info;
    private ImageView image_1;

    public Card_info(Uri image, String info) { this.image = image; this.info = info; }

    public Uri getImage() { return image; }

    public void setImage(Uri image) {
        this.image = image;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) { this.info = info; }
}
