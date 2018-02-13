package in.creativelizard.ccontacts.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.net.URI;
import java.util.HashMap;

import javax.xml.datatype.DatatypeConstants;

import in.creativelizard.ccontacts.util.ConstantClass;

/**
 * Created by siddhartha on 12/2/18.
 */

/**
 * {@link ContentProvider} for CContact app.
 */
public class CContactProvider extends ContentProvider {

    /** Database helper object */
    private DatabaseHandler databaseHandler;
    static final UriMatcher uriMatcher;
    public static final String name = DataBaseHelper.COLUMN_NAME;
    public static final String number = DataBaseHelper.COLUMN_NUMBER;
    public static final String idHidden = DataBaseHelper.COLUMN_IS_HIDDEN;
    public static final String tagColorCode = DataBaseHelper.COLUMN_TAG_COLOR_CODE;
    public static final String getDateTime = DataBaseHelper.COLUMLN_CREATON_DATE;
    public static final String id = "id";
    private static final int uriCode = 1;
    private static HashMap<String, String> values;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(ConstantClass.CONTENT_AUTHORITY,ConstantClass.PATH_CONTACT,uriCode);
    }
    @Override
    public boolean onCreate() {
        databaseHandler = new DatabaseHandler(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(ConstantClass.PATH_CONTACT);

        switch (uriMatcher.match(uri)){
            case uriCode:
                queryBuilder.setProjectionMap(values);
                break;
                default:
                    throw new IllegalArgumentException("Unknown URI "+uri);
        }
        Cursor cursor = queryBuilder.query(databaseHandler.getDatabase(),projection,selection,selectionArgs,null,null,sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(),uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case uriCode:
                return ConstantClass.CONTENT_AUTHORITY+"/"+ConstantClass.PATH_CONTACT;
            default:
                throw new IllegalArgumentException("Unknown URI "+uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        Log.e("message",contentValues.toString());
        SQLiteDatabase db = databaseHandler.getWritableDatabase();
        long rowId = db.insert(DataBaseHelper.TABLE_NAME,null,contentValues);

        if(rowId>0){
            Uri _uri = ContentUris.withAppendedId(ConstantClass.CONTENT_URI,rowId);
            getContext().getContentResolver().notifyChange(_uri,null);
            return _uri;
        }else {

            Toast.makeText(getContext(), "Insert failed!", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {

        int rowsDeleted = 0;
        switch (uriMatcher.match(uri)){
            case uriCode:
                rowsDeleted = databaseHandler.getDatabase().delete(ConstantClass.PATH_CONTACT,s,strings);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI "+uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return rowsDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        int rowsUpdated = 0;
        switch (uriMatcher.match(uri)){
            case uriCode:
                rowsUpdated = databaseHandler.getDatabase().update(ConstantClass.PATH_CONTACT,contentValues,s,strings);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI "+uri);
        }
        getContext().getContentResolver().notifyChange(uri,null);
        return rowsUpdated;
    }
}
