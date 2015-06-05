package fr.esgi.android.blocnote.activities;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import fr.esgi.android.blocnote.R;


public class CategoryListActivity extends ListActivity {
	private Context context;
	private Button categoryButton;
	private Button categorySearchButton;
	private EditText categorySearchInput;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category_list);
		context=this;
		setTitle(R.string.category_Screen_Name);
		categoryButton = (Button) findViewById(R.id.add_new_caterory);
		categoryButton.setText(R.string.add_Categorie_Button);

		categoryButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
		categorySearchButton = (Button) findViewById(R.id.categoryBtnSearch);
		categorySearchButton.setText(R.string.search_Button);
		categorySearchInput = (EditText) findViewById(R.id.categorySearchInput);
		categorySearchInput.setHint(R.string.search_Input_Category);
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
