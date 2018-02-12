package in.creativelizard.ccontacts.util;

import in.creativelizard.ccontacts.data.DataBaseHelper;

/**
 * Created by siddhartha on 12/2/18.
 */

public class ConstantClass {
    private static final String CONTENT_AUTHORITY = "in.creativelizard.ccontacts";
    public static final String  BASE_URL = "content://" + CONTENT_AUTHORITY;
    public static final String PATH_CONTACT = DataBaseHelper.TABLE_NAME;

}
