package com.shashank.platform.furnitureecommerceappui.data.dbHelpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.shashank.platform.furnitureecommerceappui.data.contrats.CategorieContract;
import com.shashank.platform.furnitureecommerceappui.data.contrats.ProduitContract;
import com.shashank.platform.furnitureecommerceappui.data.contrats.ProduitContract.ProduitEntry;
import com.shashank.platform.furnitureecommerceappui.data.contrats.UserContract;
import com.shashank.platform.furnitureecommerceappui.data.contrats.UserContract.UserEntry;

public class FurnitureDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "furniture.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;


    public FurnitureDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Create a String that contains the SQL statement to create the user table
        String SQL_CREATE_USER_TABLE = "CREATE TABLE " + UserContract.TABLE_NAME + "("
                + UserContract.UserEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + UserContract.UserEntry.COLUMN_USER_NAME + " VARCHAR(255) NOT NULL, "
                + UserContract.UserEntry.COLUMN_USER_EMAIL + "  VARCHAR(255) ,"
                + UserContract.UserEntry.COLUMN_USER_PASS + "  VARCHAR(20) ,"
                + UserContract.UserEntry.COLUMN_USER_PHONE + "  VARCHAR(20) "
                + ");";

        String SQL_CREATE_CATEGORY_TABLE = "CREATE TABLE " + CategorieContract.TABLE_NAME + "("
                + CategorieContract.CategorieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CategorieContract.CategorieEntry.COLUMN_CATEGORIE_NAME + " VARCHAR(255) NOT NULL UNIQUE, "
                + CategorieContract.CategorieEntry.COLUMN_CATEGORIE_DESCRIPTION + "  VARCHAR(300) "
                + ");";

        String SQL_CREATE_PRODUCT_TABLE = "CREATE TABLE " + ProduitContract.TABLE_NAME + "("
                + ProduitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + ProduitEntry.COLUMN_PRODUIT_NAME + " VARCHAR(70) NOT NULL, "
                + ProduitEntry.COLUMN_PRODUIT_CATEGORIE_ID + " INTEGER NOT NULL,"
                + ProduitEntry.COLUMN_PRODUIT_PRIX + " VARCHAR(50) NOT NULL,"
                + ProduitEntry.COLUMN_PRODUIT_NOTES + " VARCHAR(255),"
                + ProduitEntry.COLUMN_PRODUIT_PHOTO + " VARCHAR(255), "
                + ProduitEntry.COLUMN_PRODUIT_DESCRIPTION + " VARCHAR(1024), "
                + "FOREIGN KEY(" + ProduitEntry.COLUMN_PRODUIT_CATEGORIE_ID + ") REFERENCES " + CategorieContract.TABLE_NAME + "(" + CategorieContract.CategorieEntry._ID + ")"
                + ");";


        db.execSQL(SQL_CREATE_USER_TABLE);
        db.execSQL(SQL_CREATE_CATEGORY_TABLE);
        db.execSQL(SQL_CREATE_PRODUCT_TABLE);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
        // DATABASE_VERSION ++;
    }



    public Cursor getUser(String em, String pwd) {
        SQLiteDatabase db = this.getReadableDatabase(); //get readable instance of the db
        //specify the columns to be read
        String[] columns = {
                UserEntry._ID,
                UserEntry.COLUMN_USER_NAME,
                UserEntry.COLUMN_USER_EMAIL,
                UserEntry.COLUMN_USER_PASS,
                UserEntry.COLUMN_USER_PHONE,
        };
        String selection = UserEntry.COLUMN_USER_EMAIL + " =?"; //where statement
        String selectionArgs[] = {em};

        return db.query(UserContract.TABLE_NAME, columns, selection, selectionArgs, null, null, null);//don't forget to close the cursor after usage

    }

    public boolean checkUser(String email, String password) {
        // array of columns to fetch
        String[] columns = {
                UserEntry._ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = UserEntry.COLUMN_USER_EMAIL + " = ?" + " AND " + UserEntry.COLUMN_USER_PASS + " = ?";
        // selection arguments
        String[] selectionArgs = {email, password};
        // query user table with conditions

        Cursor cursor = db.query(UserContract.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean checkUser(String email) {
        // array of columns to fetch
        String[] columns = {
                UserEntry._ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = UserEntry.COLUMN_USER_EMAIL + " = ?";
        // selection argument
        String[] selectionArgs = {email};
        // query user table with condition

        Cursor cursor = db.query(UserContract.TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }

    public boolean addUser(String user_name, String user_email, String user_pass, String user_phone) {

        SQLiteDatabase db = this.getWritableDatabase(); //gets writeable instance of database
        ContentValues cv = new ContentValues(); //used for inserting an entry

        if (user_name != null && !user_name.isEmpty() && !user_name.trim().isEmpty()) //checks if field is provided if not it is not added in the query
            cv.put(UserEntry.COLUMN_USER_NAME, user_name);
        if (user_email != null && !user_email.isEmpty() && !user_email.trim().isEmpty()) // to be edited
            cv.put(UserEntry.COLUMN_USER_EMAIL, user_email);
        if (user_pass != null && !user_pass.isEmpty() && !user_pass.trim().isEmpty())
            cv.put(UserEntry.COLUMN_USER_PASS, user_pass);

        // no need to check for null as it is required to be provided
        cv.put(UserEntry.COLUMN_USER_PHONE, user_phone);
        long flag = db.insert(UserContract.TABLE_NAME, null, cv); //returns a flag to indicate succes of insertion

        return flag != -1; //-1 if insert fails

    }


    //
//        public Cursor getEmployeesOfTask(long task_id) {
//            SQLiteDatabase db = this.getReadableDatabase();
//            String select = "SELECT * ";
//            String from = " FROM employee_task inner join " + EmployeeContract.TABLE_NAME + " on employee_task."
//                    + EmployeeContract.TABLE_NAME + ProduitEntry._ID + " = " + EmployeeContract.TABLE_NAME + "." + ProduitEntry._ID;
//            String where = " WHERE employee_task." + TaskContract.TABLE_NAME + TaskEntry._ID + " = ?";
//            String query = select + from + where;
//            String selectionArgs[] = {String.valueOf(task_id)};
//            //cursor is a table containing the rows returned form the query
//            Cursor c = db.rawQuery(query, selectionArgs);
//            return c;
//        }
//
//
//        public Cursor getTasksOfDepartment(long department_id) {
//            //gets tasks of a specific deparrtment
//            SQLiteDatabase db = this.getReadableDatabase(); //get readable instance of the db
//            String select = "SELECT DISTINCT " +
//                    TaskContract.TABLE_NAME + "." + TaskEntry.COLUMN_TASK_NAME + " AS TaskName , " +
//                    EmployeeContract.TABLE_NAME + "." + ProduitEntry.COLUMN_EMPLOYEE_NAME + " , " +
//                    //TaskContract.TABLE_NAME+"."+ TaskEntry.COLUMN_TASK_DESCRIPTION +" , "+
//                    //TaskContract.TABLE_NAME+"."+TaskEntry.COLUMN_TASK_DEADLINE +" , "+
//                    //TaskContract.TABLE_NAME+"."+TaskEntry.COLUMN_TASK_DATE +" , "+
//                    TaskContract.TABLE_NAME + "." + TaskEntry.COLUMN_TASK_EVALUATION + " AS Evaluation , " +
//                    TaskContract.TABLE_NAME + "." + TaskEntry.COLUMN_TASK_COMPLETED + " , " +
//                    TaskContract.TABLE_NAME + TaskEntry._ID + " , " +
//                    EmployeeContract.TABLE_NAME + "." + ProduitEntry._ID + " , " +
//                    EmployeeContract.TABLE_NAME + "." + ProduitEntry.COLUMN_EMPLOYEE_DEPARTMENT_ID + " , " +
//                    CategorieContract.TABLE_NAME + "." + CategorieEntry._ID + " , " +
//                    "employee_task." + EmployeeContract.TABLE_NAME + ProduitEntry._ID + " , " +
//                    "employee_task." + TaskContract.TABLE_NAME + TaskEntry._ID;
//
//
//            String from = " from " + CategorieContract.TABLE_NAME + " INNER JOIN " + EmployeeContract.TABLE_NAME
//                    + " ON " + EmployeeContract.TABLE_NAME + "." + ProduitEntry.COLUMN_EMPLOYEE_DEPARTMENT_ID + " = "
//                    + CategorieContract.TABLE_NAME + "." + CategorieEntry._ID
//                    + " INNER JOIN " + " employee_task "
//                    + " ON " + EmployeeContract.TABLE_NAME + "." + ProduitEntry._ID + " = "
//                    + " employee_task." + EmployeeContract.TABLE_NAME + ProduitEntry._ID
//                    + " INNER JOIN " + TaskContract.TABLE_NAME
//                    + " ON " + TaskContract.TABLE_NAME + "." + TaskEntry._ID + " = "
//                    + " employee_task." + TaskContract.TABLE_NAME + TaskEntry._ID;
//            String where = " WHERE " + CategorieContract.TABLE_NAME + "." + CategorieEntry._ID + " = " + String.valueOf(department_id);
//            String group = " GROUP BY " + TaskContract.TABLE_NAME + "." + TaskEntry._ID + " ";
//            String query = select + from + where + group;
//            return db.rawQuery(query, null); //don't forget to close the cursor after usage
//
//        }
//
//
//        public Cursor getTasksOfEmployee(long employee_id) {
//            //gets tasks of a specific employee
//            SQLiteDatabase db = this.getReadableDatabase(); //get readable instance of the db
//
//            String select = "SELECT DISTINCT " +
//                    TaskContract.TABLE_NAME + "." + TaskEntry.COLUMN_TASK_NAME + " AS TaskName  , " +
//                    EmployeeContract.TABLE_NAME + "." + ProduitEntry.COLUMN_EMPLOYEE_NAME + " , " +
//                    TaskContract.TABLE_NAME + "." + TaskEntry.COLUMN_TASK_DESCRIPTION + " , " +
//                    TaskContract.TABLE_NAME + "." + TaskEntry.COLUMN_TASK_DEADLINE + " , " +
//                    TaskContract.TABLE_NAME + "." + TaskEntry.COLUMN_TASK_DATE + " , " +
//                    TaskContract.TABLE_NAME + "." + TaskEntry.COLUMN_TASK_EVALUATION + " AS Evaluation , " +
//                    TaskContract.TABLE_NAME + "." + TaskEntry.COLUMN_TASK_COMPLETED + " AS Completed , " +
//                    TaskContract.TABLE_NAME + TaskEntry._ID + " , " +
//                    EmployeeContract.TABLE_NAME + "." + ProduitEntry._ID + " , " +
//                    EmployeeContract.TABLE_NAME + "." + ProduitEntry.COLUMN_EMPLOYEE_DEPARTMENT_ID + " , " +
//                    CategorieContract.TABLE_NAME + "." + CategorieEntry._ID + " , " +
//                    "employee_task." + EmployeeContract.TABLE_NAME + ProduitEntry._ID + " , " +
//                    "employee_task." + TaskContract.TABLE_NAME + TaskEntry._ID;
//
//
//            String from = " from " + CategorieContract.TABLE_NAME + " INNER JOIN " + EmployeeContract.TABLE_NAME
//                    + " ON " + EmployeeContract.TABLE_NAME + "." + ProduitEntry.COLUMN_EMPLOYEE_DEPARTMENT_ID + " = "
//                    + CategorieContract.TABLE_NAME + "." + CategorieEntry._ID
//                    + " INNER JOIN " + " employee_task "
//                    + " ON " + EmployeeContract.TABLE_NAME + "." + ProduitEntry._ID + " = "
//                    + " employee_task." + EmployeeContract.TABLE_NAME + ProduitEntry._ID
//                    + " INNER JOIN " + TaskContract.TABLE_NAME
//                    + " ON " + TaskContract.TABLE_NAME + "." + TaskEntry._ID + " = "
//                    + " employee_task." + TaskContract.TABLE_NAME + TaskEntry._ID;
//
//            String where = " WHERE " + EmployeeContract.TABLE_NAME + "." + ProduitEntry._ID + " = " + String.valueOf(employee_id);
//            String group = " GROUP BY " + TaskContract.TABLE_NAME + "." + TaskEntry._ID + " ";
//            String query = select + from + where + group;
//
//
//            return db.rawQuery(query, null); //don't forget to close the cursor after usage
//
//
//        }
//
//
//        public Cursor getAllTasksCursor() {
//            //gets all tasks
//            SQLiteDatabase db = this.getReadableDatabase(); //get readable instance of the db
//
//            //specify the columns to be read
//            String[] columns = {
//                    TaskEntry._ID,
//                    TaskEntry.COLUMN_TASK_NAME,
//                    TaskEntry.COLUMN_TASK_DESCRIPTION,
//                    TaskEntry.COLUMN_TASK_DEADLINE,
//                    TaskEntry.COLUMN_TASK_DATE,
//                    TaskEntry.COLUMN_TASK_EVALUATION,
//                    TaskEntry.COLUMN_TASK_COMPLETED
//            };
//
//            //cursor is a table containing the rows returned form the query
//            return db.query(TaskContract.TABLE_NAME, columns, null, null, null, null, null); //don't forget to close the cursor after usage
//
//        }
//
//        public Cursor getSpecifiTaskCursor(long task_id) {
//
//            //gets specific task by its id
//            SQLiteDatabase db = this.getReadableDatabase(); //get readable instance of the db
//
//            //specify the columns to be read
//            String[] columns = {
//                    TaskEntry._ID,
//                    TaskEntry.COLUMN_TASK_NAME,
//                    TaskEntry.COLUMN_TASK_DESCRIPTION,
//                    TaskEntry.COLUMN_TASK_DEADLINE,
//                    TaskEntry.COLUMN_TASK_DATE,
//                    TaskEntry.COLUMN_TASK_EVALUATION,
//                    TaskEntry.COLUMN_TASK_COMPLETED
//            };
//
//            //where statement to filter quere
//            String selection = TaskEntry._ID + " =?"; //where TaskEntry._ID=task_id
//            String selectionArgs[] = {String.valueOf(task_id)};
//
//
//            //cursor is a table containing the rows returned form the query
//            return db.query(TaskContract.TABLE_NAME, columns, selection, selectionArgs, null, null, null); //don't forget to close the cursor after usage
//
//        }
//
//        public Cursor getAllDepartments() {
//            //gets all departments
//            SQLiteDatabase db = this.getReadableDatabase(); //get readable instance of the db
//
//            //specify the columns to be read
//            String[] columns = {
//                    CategorieEntry._ID,
//                    CategorieEntry.COLUMN_DEPARTMENT_NAME,
//                    CategorieEntry.COLUMN_DEPARTMENT_DESCRIPTION
//            };
//
//            String orderBy = CategorieEntry.COLUMN_DEPARTMENT_NAME + " ASC "; //order by statement
//
//            //cursor is a table containing the rows returned form the query
//            return db.query(CategorieContract.TABLE_NAME, columns, null, null, null, null, orderBy); //don't forget to close the cursor after usage
//
//        }
//
//        public Cursor getDepartment(Long departmentId) {
//            SQLiteDatabase db = this.getReadableDatabase(); //get readable instance of the db
//            //specify the columns to be read
//            String[] columns = {
//                    CategorieEntry._ID,
//                    CategorieEntry.COLUMN_DEPARTMENT_NAME,
//                    CategorieEntry.COLUMN_DEPARTMENT_DESCRIPTION
//            };
//            String selection = CategorieEntry._ID + " =?"; //where statement
//            String selectionArgs[] = {String.valueOf(departmentId)};
//
//            return db.query(CategorieContract.TABLE_NAME, columns, selection, selectionArgs, null, null, null);//don't forget to close the cursor after usage
//
//        }
//

//
//        public Cursor getEmployee(Long employeeId) {
//            SQLiteDatabase db = this.getReadableDatabase(); //get readable instance of the db
//            //specify the columns to be read
//            String[] columns = {
//                    ProduitEntry._ID,
//                    ProduitEntry.COLUMN_EMPLOYEE_NAME,
//                    ProduitEntry.COLUMN_EMPLOYEE_PHONE,
//                    ProduitEntry.COLUMN_EMPLOYEE_BIRTHDATE,
//                    ProduitEntry.COLUMN_EMPLOYEE_EMAIL,
//                    ProduitEntry.COLUMN_EMPLOYEE_JOB,
//                    ProduitEntry.COLUMN_EMPLOYEE_PHOTO,
//                    ProduitEntry.COLUMN_EMPLOYEE_NOTES
//            };
//            String selection = ProduitEntry._ID + " =?"; //where statement
//            String selectionArgs[] = {String.valueOf(employeeId)};
//
//            return db.query(EmployeeContract.TABLE_NAME, columns, selection, selectionArgs, null, null, null);//don't forget to close the cursor after usage
//
//        }
//
//        public Cursor getEmployessOfDepartment(long department_id) {
//            //gets all employees of a given department
//            SQLiteDatabase db = this.getReadableDatabase(); //get readable instance of the db
//
//            //specify the columns to be read
//            String[] columns = {
//                    ProduitEntry._ID,
//                    ProduitEntry.COLUMN_EMPLOYEE_NAME,
//                    ProduitEntry.COLUMN_EMPLOYEE_JOB,
//                    ProduitEntry.COLUMN_EMPLOYEE_PHOTO
//
//            };
////department_id is the right one
//            String selection = ProduitEntry.COLUMN_EMPLOYEE_DEPARTMENT_ID + " =?"; //where statement
//            String selectionArgs[] = {String.valueOf(department_id)};
//            String orderBy = ProduitEntry.COLUMN_EMPLOYEE_NAME + " ASC";
//
//
//            //cursor is a table containing the rows returned form the query
//            //don't forget to close the cursor after usage
//
//            return db.query(EmployeeContract.TABLE_NAME, columns, selection, selectionArgs, null, null, orderBy);
//        }
//
//
//        public boolean addDepartment(String department_name, String department_description) {
//            SQLiteDatabase db = this.getWritableDatabase(); //gets writeable instance of database
//            ContentValues cv = new ContentValues(); //used for inserting an entry
//
//
//            cv.put(TaskEntry.COLUMN_TASK_NAME, department_name);
//
//            if (department_description != null && !department_description.isEmpty() && !department_description.trim().isEmpty()) // to be edited
//                cv.put(TaskEntry.COLUMN_TASK_DESCRIPTION, department_description);
//
//            long flag = db.insert(CategorieContract.TABLE_NAME, null, cv); //reutrns a flag to indicate succes of insertion
//
//            return flag != -1; //-1 if insert fails
//        }
//
//
//        public boolean addEmployee(String employee_name, String employee_birthdate, long department_id, String employee_job, String employee_email, String employee_phone, String employee_photo) {
//            //adds an employee entry to employee table
//
//            SQLiteDatabase db = this.getWritableDatabase(); //gets writeable instance of database
//            ContentValues cv = new ContentValues(); //used for inserting an entry
//
//
//            // no need to check for null as it is required to be provided
//            cv.put(ProduitEntry.COLUMN_EMPLOYEE_NAME, employee_name);
//            cv.put(ProduitEntry.COLUMN_EMPLOYEE_BIRTHDATE, employee_birthdate);
//            cv.put(ProduitEntry.COLUMN_EMPLOYEE_DEPARTMENT_ID, department_id);
//            cv.put(ProduitEntry.COLUMN_EMPLOYEE_JOB, employee_job);
//
//            if (employee_email != null && !employee_email.isEmpty() && !employee_email.trim().isEmpty()) //checks if field is provided if not it is not added in the query
//                cv.put(ProduitEntry.COLUMN_EMPLOYEE_EMAIL, employee_email);
//            if (employee_phone != null && !employee_phone.isEmpty() && !employee_phone.trim().isEmpty()) // to be edited
//                cv.put(ProduitEntry.COLUMN_EMPLOYEE_PHONE, employee_phone);
//            if (employee_photo != null && !employee_photo.isEmpty() && !employee_photo.trim().isEmpty())
//                cv.put(ProduitEntry.COLUMN_EMPLOYEE_PHOTO, employee_photo);
//
//
//            long flag = db.insert(EmployeeContract.TABLE_NAME, null, cv); //reutrns a flag to indicate succes of insertion
//
//            return flag != -1; //-1 if insert fails
//
//        }
//


//        public Long addTask(Task task) {
//            //adds task to db
//            SQLiteDatabase db = this.getWritableDatabase(); //gets writeable instance of database
//            ContentValues cv = new ContentValues(); //used for inserting an entry
//
//            cv.put(TaskEntry.COLUMN_TASK_NAME, task.getTaskName());
//            cv.put(TaskEntry.COLUMN_TASK_EVALUATION, task.getEvaluation());
//
//            if (task.getTaskDetails() != null && !task.getTaskDetails().isEmpty() && !task.getTaskDetails().trim().isEmpty())
//                cv.put(TaskEntry.COLUMN_TASK_DESCRIPTION, task.getTaskDetails());
//
//            if (task.getTaskDeadline() != null && !task.getTaskDeadline().isEmpty() && !task.getTaskDeadline().trim().isEmpty())
//                cv.put(TaskEntry.COLUMN_TASK_DEADLINE, task.getTaskDeadline());
//            cv.put(TaskEntry.COLUMN_TASK_COMPLETED, task.isDone());
//            cv.put(TaskEntry.COLUMN_TASK_DATE, task.getTaskDate());
//            long task_id = db.insert(TaskContract.TABLE_NAME, null, cv); //reutrns a flag to indicate succes of insertion
//
//
//            cv = new ContentValues();
//            ArrayList<Long> emplyee_ids = task.getEmployees_id();
//            if (emplyee_ids != null) {
//                for (long emp_id : emplyee_ids) {
//                    cv.put(EmployeeContract.TABLE_NAME + ProduitEntry._ID, emp_id);
//                    cv.put(TaskContract.TABLE_NAME + TaskEntry._ID, task_id);
//                    long flag = db.insert("employee_task", null, cv); //reutrns a flag to indicate succes of insertion
//                }
//            }
//            return task_id;
//        }
//
//        public boolean deleteEmployee(long employee_id) {
//            SQLiteDatabase db = this.getWritableDatabase(); //gets writeable instance of database
//
//            db.delete("employee_task", EmployeeContract.TABLE_NAME + ProduitEntry._ID + "=" + employee_id, null);
//            int flag = db.delete(EmployeeContract.TABLE_NAME, ProduitEntry._ID + "=" + employee_id, null);
//
//            //resets autoincrement
//            Cursor c = db.rawQuery("SELECT MAX(" + ProduitEntry._ID + ") from " + EmployeeContract.TABLE_NAME, null);
//            c.moveToFirst();
//            long max_id = c.getLong(0);
//            db.execSQL("UPDATE SQLITE_SEQUENCE SET seq = " + String.valueOf(max_id + 1) + " WHERE name ='  " + EmployeeContract.TABLE_NAME + " ' ");
//            c.close();
//
//
//            return flag > 0;
//
//        }
//
//        public boolean deleteTask(long task_id) {
//            SQLiteDatabase db = this.getWritableDatabase(); //gets writeable instance of database
//            db.delete("employee_task", TaskContract.TABLE_NAME + TaskEntry._ID + "=" + task_id, null);
//            int flag = db.delete(TaskContract.TABLE_NAME, TaskEntry._ID + "=" + task_id, null);
//            //resets autoincrement
//            Cursor c = db.rawQuery("SELECT MAX(" + TaskEntry._ID + ") from " + TaskContract.TABLE_NAME, null);
//            c.moveToFirst();
//            long max_id = c.getLong(0);
//            db.execSQL("UPDATE SQLITE_SEQUENCE SET seq = " + String.valueOf(max_id + 1) + " WHERE name ='  " + TaskContract.TABLE_NAME + " ' ");
//            c.close();
//            return flag > 0;
//
//        }
//
//
//        public boolean deleteDepartment(long department_id) {
//            SQLiteDatabase db = this.getWritableDatabase(); //gets writeable instance of database
//
//            Cursor c = getEmployessOfDepartment(department_id);
//            while (c.moveToNext()) {
//                db.delete(EmployeeContract.TABLE_NAME, ProduitEntry._ID + "  = " + c.getString(0), null);
//            }
//            c.close();
//
//            //resets autoincrement for employess table
//            Cursor c2 = db.rawQuery("SELECT MAX(" + ProduitEntry._ID + ") from " + EmployeeContract.TABLE_NAME, null);
//            c2.moveToFirst();
//            long max_id = c2.getLong(0);
//            db.execSQL("UPDATE SQLITE_SEQUENCE SET seq = " + String.valueOf(max_id + 1) + " WHERE name ='  " + EmployeeContract.TABLE_NAME + " ' ");
//            c2.close();
//
//            db.delete(EmployeeContract.TABLE_NAME, CategorieEntry._ID + "=" + department_id, null);
//            int flag = db.delete(CategorieContract.TABLE_NAME, CategorieEntry._ID + "=" + department_id, null);
//
//            //resets autoincrement
//            Cursor c3 = db.rawQuery("SELECT MAX(" + CategorieEntry._ID + ") from " + CategorieContract.TABLE_NAME, null);
//            c3.moveToFirst();
//            max_id = c3.getLong(0);
//            db.execSQL("UPDATE SQLITE_SEQUENCE SET seq = " + String.valueOf(max_id + 1) + " WHERE name ='  " + CategorieContract.TABLE_NAME + " ' ");
//            c3.close();
//
//            return flag > 0;
//        }
//
//        public boolean deleteAllDepartments() {
//            SQLiteDatabase db = this.getWritableDatabase();
//
//            int f2 = db.delete("employee_task", null, null);
//            int f1 = db.delete(TaskContract.TABLE_NAME, null, null);
//            int f3 = db.delete(EmployeeContract.TABLE_NAME, null, null);
//            int f4 = db.delete(CategorieContract.TABLE_NAME, null, null);
//
//            db.execSQL("UPDATE SQLITE_SEQUENCE SET seq = " + String.valueOf(0) + " WHERE name ='  " + EmployeeContract.TABLE_NAME + " ' ");
//            db.execSQL("UPDATE SQLITE_SEQUENCE SET seq = " + String.valueOf(0) + " WHERE name ='  " + TaskContract.TABLE_NAME + " ' ");
//            db.execSQL("UPDATE SQLITE_SEQUENCE SET seq = " + String.valueOf(0) + " WHERE name ='  " + CategorieContract.TABLE_NAME + " ' ");
//            db.execSQL("UPDATE SQLITE_SEQUENCE SET seq = " + String.valueOf(0) + " WHERE name ='  " + " employee_task " + " ' ");
//
//
//            return f1 > 0 && f2 > 0 && f3 > 0 && f4 > 0;
//        }
//
//        public boolean deleteAllTasks() {
//            SQLiteDatabase db = this.getWritableDatabase();
//
//            int f2 = db.delete("employee_task", null, null);
//            int f1 = db.delete(TaskContract.TABLE_NAME, null, null);
//            db.execSQL("UPDATE SQLITE_SEQUENCE SET seq = " + String.valueOf(0) + " WHERE name ='  " + " employee_task " + " ' ");
//            db.execSQL("UPDATE SQLITE_SEQUENCE SET seq = " + String.valueOf(0) + " WHERE name ='  " + TaskContract.TABLE_NAME + " ' ");
//
//
//            return f1 > 0 && f2 > 0;
//        }
//
//        public boolean updateDepartment(DepartmentItem departmentItem) {
//            SQLiteDatabase db = this.getWritableDatabase();
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(CategorieEntry._ID, departmentItem.id);
//            contentValues.put(CategorieEntry.COLUMN_DEPARTMENT_NAME, departmentItem.name);
//            contentValues.put(CategorieEntry.COLUMN_DEPARTMENT_DESCRIPTION, departmentItem.details);
//            db.update(CategorieContract.TABLE_NAME, contentValues, CategorieEntry._ID + "=" + departmentItem.id, null);
//            return true;
//        }
//
//        public boolean updateTask(Task task) {
//            int task_id = (int) task.getId();
//            ArrayList<Long> employee_ids = task.getEmployees_id();
//            SQLiteDatabase db = this.getWritableDatabase();
//            SQLiteDatabase db_ = this.getReadableDatabase();
//            ContentValues cv = new ContentValues();
//            ArrayList<Long> list_of_current_ids = new ArrayList<>();
//            ArrayList<Long> list1 = new ArrayList<>();
//            ArrayList<Long> list2 = new ArrayList<>();
//            cv.put(TaskEntry.COLUMN_TASK_NAME, task.getTaskName());
//            cv.put(TaskEntry.COLUMN_TASK_EVALUATION, task.getEvaluation());
//            cv.put(TaskEntry.COLUMN_TASK_DESCRIPTION, task.getTaskDetails());
//            cv.put(TaskEntry.COLUMN_TASK_DEADLINE, task.getTaskDeadline());
//            db.update(TaskContract.TABLE_NAME, cv, TaskEntry._ID + "=" + task_id, null);
//
//
//            Cursor c = db_.rawQuery("SELECT " + EmployeeContract.TABLE_NAME + ProduitEntry._ID + " from employee_task where " + TaskContract.TABLE_NAME + TaskEntry._ID + "= " + task_id, null);
//            if (c.moveToFirst()) {
//                while (!c.isAfterLast()) {
//                    String data = c.getString(c.getColumnIndex(EmployeeContract.TABLE_NAME + ProduitEntry._ID));
//                    list_of_current_ids.add(Long.parseLong(data));
//                    c.moveToNext();
//                }
//            }
//            c.close();
//
//            list1.addAll(employee_ids);
//            list2.addAll(list_of_current_ids);
//            list1.removeAll(list_of_current_ids);
//            list2.removeAll(employee_ids);
//
//            if (list2.size() > 0) {
//                for (long emp_id : list2) {
//                    long flag = db.delete("employee_task", EmployeeContract.TABLE_NAME + ProduitEntry._ID + "=" + emp_id, null);
//                    if (flag == -1) return false;
//                }
//            }
//
//            if (list1.size() > 0) {
//                cv = new ContentValues();
//                for (long emp_id : list1) {
//                    cv.put(EmployeeContract.TABLE_NAME + ProduitEntry._ID, emp_id);
//                    cv.put(TaskContract.TABLE_NAME + TaskEntry._ID, task_id);
//                    long flag = db.insert("employee_task", null, cv); //reutrns a flag to indicate succes of insertion
//                    if (flag == -1) return flag == -1;
//                }
//            }
//
//            return true;
//
//        }
//
//        public boolean updateTaskEvaluation(Long task_id, boolean task_completed, int task_evaluation) {
//            SQLiteDatabase db = this.getWritableDatabase();
//            ContentValues cv = new ContentValues();
//            cv.put(TaskEntry.COLUMN_TASK_COMPLETED, task_completed);
//            cv.put(TaskEntry.COLUMN_TASK_EVALUATION, task_evaluation);
//            return db.update(TaskContract.TABLE_NAME, cv, TaskEntry._ID + "=" + task_id, null) > 0;
//        }
//
//        public boolean updateEmployee(long id, ContentValues values) {
//            SQLiteDatabase db = this.getWritableDatabase();
//            String selection = ProduitEntry._ID + " =?"; //where statement
//            String selectionArgs[] = {String.valueOf(id)};
//            return db.update(EmployeeContract.TABLE_NAME, values, selection, selectionArgs) > 0;
//        }
//

}
