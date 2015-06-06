package fr.esgi.android.blocnote.datas;

import java.util.LinkedList;
import java.util.List;

import fr.esgi.android.blocnote.models.Category;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper{
	private static  MyDatabaseHelper instance;
	private static Context ctx;

	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "Notes.db";

	private static final String TABLE_CATEGORIES = "categories";
	private static final String KEY_ID_CATEGORY = "id";
	private static final String KEY_NAME_CATEGORY = "name";
	private static final String[] CATEGORIES_COLUMNS = { KEY_ID_CATEGORY,
		KEY_NAME_CATEGORY };


	public MyDatabaseHelper(Context context) {
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
		ctx = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_CATEGORIES_TABLE = "CREATE TABLE " + TABLE_CATEGORIES
				+ " ( " + KEY_ID_CATEGORY
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME_CATEGORY
				+ " TEXT )";
		db.execSQL(CREATE_CATEGORIES_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS categories");
		this.onCreate(db);

	}
	public static MyDatabaseHelper getInstance(Context context)
	{	
		if (instance == null)
		{
			synchronized (MyDatabaseHelper.class) 
			{
				instance = new MyDatabaseHelper(context);
			}
		}

		return instance;
	}


	public List<Category> getAllCategories() {
		List<Category> categories = new LinkedList<Category>();

		String query = "SELECT  * FROM " + TABLE_CATEGORIES +" ORDER BY name ";

		SQLiteDatabase db = getInstance(ctx).getWritableDatabase();

		Cursor cursor = db.rawQuery(query, null);

		Category category = null;
		if (cursor.moveToFirst()) {
			do {
				category = new Category();
				category.setId(Integer.parseInt(cursor.getString(0)));
				category.setName(cursor.getString(1));

				categories.add(category);
			} while (cursor.moveToNext());
		}

		return categories;
	}

	public int updateCategory(Category category) {
		SQLiteDatabase db = getInstance(ctx).getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put("name", category.getName());

		int i = db.update(TABLE_CATEGORIES, values, KEY_ID_CATEGORY + " = ?",
				new String[] { String.valueOf(category.getId()) });

		db.close();

		return i;

	}

	public void addCategory(Category category) {
		SQLiteDatabase db = getInstance(ctx).getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME_CATEGORY, category.getName());

		db.insert(TABLE_CATEGORIES, null, values);

		db.close();

	}

}
