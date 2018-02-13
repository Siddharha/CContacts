package in.creativelizard.ccontacts.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import in.creativelizard.ccontacts.items.ContactItem;
import in.creativelizard.ccontacts.util.CContactUtil;

import static in.creativelizard.ccontacts.data.DataBaseHelper.COLUMLN_CREATON_DATE;
import static in.creativelizard.ccontacts.data.DataBaseHelper.COLUMN_IS_HIDDEN;
import static in.creativelizard.ccontacts.data.DataBaseHelper.COLUMN_IS_PASSWORD_PROTECTED;
import static in.creativelizard.ccontacts.data.DataBaseHelper.COLUMN_NAME;
import static in.creativelizard.ccontacts.data.DataBaseHelper.COLUMN_NUMBER;
import static in.creativelizard.ccontacts.data.DataBaseHelper.COLUMN_TAG_COLOR_CODE;

/**
 * Created by siddhartha on 12/2/18.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    public DatabaseHandler(Context context) {
        super(context, DataBaseHelper.DATABASE_NAME, null, DataBaseHelper.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + DataBaseHelper.TABLE_NAME + "("
                + DataBaseHelper.COLUMN_ID + " INTEGER PRIMARY KEY autoincrement," + DataBaseHelper.COLUMN_NAME + " TEXT,"
                + DataBaseHelper.COLUMN_NUMBER + " TEXT, " + COLUMN_IS_HIDDEN +
                " TEXT, " + COLUMN_IS_PASSWORD_PROTECTED + "TEXT, " +
                COLUMLN_CREATON_DATE + " datetime  DEFAULT CURRENT_TIMESTAMP, " +
                DataBaseHelper.COLUMN_UPDATE_DATE + "datetime DEFAULT CURRENT_TIMESTAMP, " +
                COLUMN_TAG_COLOR_CODE + " TEXT" + ")";
      //  Log.d("TAG", CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_CONTACTS_TABLE);
        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DataBaseHelper.TABLE_NAME);
        onCreate(db);

    }

    public SQLiteDatabase getDatabase(){
        return db;
    }

    public long insertContact(String name, String number) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_NUMBER, number);
        values.put(COLUMN_IS_HIDDEN, "N");
        values.put(COLUMN_IS_PASSWORD_PROTECTED, "N");
        values.put(COLUMN_TAG_COLOR_CODE, "#00000000");
        values.put(COLUMLN_CREATON_DATE, CContactUtil.getDateTime());
        long i = db.insert(DataBaseHelper.TABLE_NAME, null, values);
        db.close();
        return i;
    }

    public ArrayList<ContactItem> getAllContactList() {
        ArrayList<ContactItem> contactItems = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + DataBaseHelper.TABLE_NAME + " ORDER BY " + COLUMLN_CREATON_DATE + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                ContactItem contactItem = new ContactItem();
                contactItem.setId(cursor.getInt(0));
                contactItem.setName(cursor.getString(1));
                contactItem.setNumber(cursor.getString(2));
                contactItem.setDateTime(cursor.getString(5));
                contactItems.add(contactItem);
            } while (cursor.moveToNext());
        }
        db.close();
        return contactItems;
    }
}
