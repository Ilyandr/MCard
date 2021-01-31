package SQliteChange;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase_for_card extends SQLiteOpenHelper
{

    public static final int version_sql = 1;
    public static final String name_sql = "save_card";
    public static final String table_name = "my_card";

    public static final String card_id = "_id";
    public static final String card_info = "information";
    public static final String card_data_add = "data_add";


    public DataBase_for_card(@Nullable Context context) {
        super(context, name_sql, null, version_sql);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
       db.execSQL("create table " + table_name + "(" + card_id + " integer primary key," + card_info + " text," + card_data_add + " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("drop table if exists " + table_name);
        onCreate(db);
    }
}
