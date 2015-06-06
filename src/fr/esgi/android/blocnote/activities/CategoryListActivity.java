package fr.esgi.android.blocnote.activities;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import fr.esgi.android.blocnote.R;
import fr.esgi.android.blocnote.adapters.CategoryListAdapter;
import fr.esgi.android.blocnote.datas.MyDatabaseHelper;


public class CategoryListActivity extends ListActivity {
	private Context context;
	private Button AddCategoryButton;
	private Button categorySearchButton;
	private EditText categorySearchInput;
	private CategoryListAdapter categoryListA;
	private Intent AddCategoryIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category_list);
		context=this;
		setTitle(R.string.list_category_Screen_Name);
		AddCategoryButton = (Button) findViewById(R.id.add_new_caterory);
		AddCategoryButton.setText(R.string.add_Category_Button);

		AddCategoryButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AddCategoryIntent = new Intent(
						CategoryListActivity.this,
						AddCategoryActivity.class);
				startActivityForResult(AddCategoryIntent,2);
				
			}
		});
		categorySearchButton = (Button) findViewById(R.id.categoryBtnSearch);
		categorySearchButton.setText(R.string.search_Button);
		categorySearchInput = (EditText) findViewById(R.id.categorySearchInput);
		categorySearchInput.setHint(R.string.search_Input_Category);
		
		SearchAllCategory();
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
	/*Search all category*/
	
	protected void SearchAllCategory() {
		categoryListA = new CategoryListAdapter(CategoryListActivity.this,
				MyDatabaseHelper.getInstance(context).getAllCategories());
		setListAdapter(categoryListA);
		
	}

}
