package br.com.unic.lha.app.database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * The Class DatabaseOpenHelper.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {
	
	/** The Constant DB_VERSION. */
	private static final int DB_VERSION	= 1;
	
	/** The Constant DB_NAME. */
	private static final String DB_NAME	= "2beFit_database.db";
	
	/** The Constant DB_PATH. */
	private static final String DB_PATH = "/data/data/br.com.unic.lha/databases/";
	
	/** The my context. */
	private Context myContext;
	
	/** The m database. */
	private SQLiteDatabase mDatabase; 
	

	/**
	 * Instantiates a new database open helper.
	 *
	 * @param context the context
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public DatabaseOpenHelper(Context context) throws IOException {
		super(context, DB_NAME, null, DB_VERSION);
		
		this.myContext = context;
		boolean dbexist = checkdatabase();

		if (dbexist) {
			// Open the database
			String mypath = DB_PATH + DB_NAME;
			mDatabase = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);
		} else {
			createDB();
		}
	}

	/** {@inheritDoc} **/ 
	@Override
	public void onCreate(SQLiteDatabase db) {
		mDatabase = db;
	}

	/** {@inheritDoc} **/ 
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w("DataBase", "Upgrading database from version " + oldVersion + 
				" to " + newVersion + ", which will destroy all old data");

		onCreate(db);
	}

	/**
	 * Creates the db.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void createDB() throws IOException {
		boolean dbexist = checkdatabase();

		if (!dbexist) {
			this.getReadableDatabase();

			try {
				copydatabase();
			} catch (Exception e) {
				e.printStackTrace();
				throw new IOException("Error copying database!");
			}
		}
	}

	/**
	 * Checkdatabase.
	 *
	 * @return true, if successful
	 */
	private boolean checkdatabase() {
		boolean checkdb = false;
		
		try {
			String myPath = DB_PATH + DB_NAME;
			File dbfile = new File(myPath);
			checkdb = dbfile.exists();
		} catch (SQLiteException e) {
			Log.e("DataBase", "Database doesn't exist");
		}

		return checkdb;
	}

	/**
	 * Copydatabase.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void copydatabase() throws IOException {
		InputStream myinput = myContext.getAssets().open(DB_NAME);		// Open your local db as the input stream
		String outfilename = DB_PATH + DB_NAME;							// Path to the just created empty db
		OutputStream myoutput = new FileOutputStream(outfilename);		// Open the empty db as the output stream

		int length;
		byte[] buffer = new byte[myinput.available()];
		
		// transfer byte to inputfile to outputfile
		while ((length = myinput.read(buffer)) > 0) {
			myoutput.write(buffer, 0, length);
		}

		// Close the streams
		myoutput.flush();
		myinput.close();
		myoutput.close();
	}

	/** {@inheritDoc} **/ 
	public synchronized void close() {
		try{
			mDatabase.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		super.close();
	}

}