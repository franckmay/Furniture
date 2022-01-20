package com.shashank.platform.furnitureecommerceappui.data.contrats;

import android.provider.BaseColumns;

public class ProduitContract {

    public static final String TABLE_NAME = "employee" ;

    public static final class ProduitEntry implements BaseColumns{
        //ProduitEntry class for deifning column names of Produit table

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_PRODUIT_NAME = "name";
        public static final String COLUMN_PRODUIT_DESCRIPTION = "description";
        public static final String COLUMN_PRODUIT_PRIX = "price";
        public static final String COLUMN_PRODUIT_CATEGORIE_ID = "category_id";
        public static final String COLUMN_PRODUIT_PHOTO = "photo";
        public static final String COLUMN_PRODUIT_NOTES = "notes";
    }
}
