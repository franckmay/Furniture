package com.shashank.platform.furnitureecommerceappui.data.contrats;
import android.provider.BaseColumns;

public class CategorieContract {

    public static final String TABLE_NAME = "department";

    public static final class CategorieEntry implements BaseColumns{
        //DepartmentEntry class for deifning column names of Department table
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_CATEGORIE_NAME = "name";
        public static final String COLUMN_CATEGORIE_DESCRIPTION = "description" ;

    }
}