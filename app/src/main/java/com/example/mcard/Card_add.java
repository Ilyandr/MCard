package com.example.mcard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import SQliteChange.DataBase_for_card;

public class Card_add extends AppCompatActivity {

    private static String TAG;
    private int  MY_CAMERA_PERMISSION_REQUEST;
    private int MY_CAMERA_REQUEST;
    private int CAMERA_REQUEST_CODE;

    private File outFile;

    private DataBase_for_card work_data_base;
    private SQLiteDatabase database_card;

    private ImageView imageView;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_add);

        requestPermissions();

        //work_data_base = new DataBase_for_card(this);

        imageView = (ImageView) findViewById(R.id.camera);
        btn = (Button) findViewById(R.id.btn_true);

        this.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (btn.getText() == "Готово")
                {
                    //database_card = work_data_base.getWritableDatabase();
                    //ContentValues contentValues = new ContentValues();
                    // contentValues.put(work_data_base.table_name, name);
                    //contentValues.put(work_data_base.table_name, data);
                    //contentValues.put(DataBase_for_card.card_info, String.valueOf(imageView));
                    //database_card.insert(DataBase_for_card.table_name, null, contentValues);
                    //организовать работу бд - загрузку и выгрузку данных по карте + сделать toast при переходе и разобраться в коде.

                    int random_number1 = (int) (Math.random() * 1101010101);
                    ImageView image = (ImageView) findViewById(R.id.camera);

                    Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
                    saveReceivedImage(bitmap, Integer.toString(random_number1));

                   // Toast.makeText(Card_add.this, outFile.toString() , Toast.LENGTH_SHORT).show();

                   // String give_uri = outFile.toString();

                    Intent go_to = new Intent(Card_add.this, MainActivity.class);
                    startActivity(go_to);
                    finish();
                }

                else {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, CAMERA_REQUEST_CODE);
                    }
            }
        });}


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MY_CAMERA_REQUEST)
        {
            if (resultCode == RESULT_OK)
            {
                if (data != null)
                {
                    Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                    imageView.setImageBitmap(bitmap);

                    this.btn.setText("Готово");
                }
            }
        }
    }

    private void requestPermissions() { ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_REQUEST); }

    private void saveReceivedImage(Bitmap image, String imageName)
    {
        try {
            File path = new File( getFilesDir(), "MCardData" + File.separator + "Images");
            if(!path.exists()) { path.mkdirs(); }

            outFile = new File(path, imageName + ".jpeg");
            FileOutputStream outputStream = new FileOutputStream(outFile);

            image.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
           Toast.makeText(Card_add.this, "Данные обновлены.", Toast.LENGTH_LONG).show();

            imageView = (ImageView) findViewById(R.id.camera);

            outputStream.flush();
            outputStream.close();
        }
        catch (FileNotFoundException e) { Log.e(TAG, "FILE NOT FOUND: ", e); }
        catch (IOException e) { Log.e(TAG, "ERROR SAVING: ", e); }
    }
}

