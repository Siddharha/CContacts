package in.creativelizard.ccontacts.util;

import android.net.Uri;

import in.creativelizard.ccontacts.data.DataBaseHelper;

/**
 * Created by siddhartha on 12/2/18.
 */

public class ConstantClass {
    private static final String CONTENT_AUTHORITY = "in.creativelizard.ccontacts";
    public static final Uri  BASE_URL = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_CONTACT = DataBaseHelper.TABLE_NAME;
    public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_URL, PATH_CONTACT);
}
