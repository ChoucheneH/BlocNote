package fr.esgi.android.blocnote.activities;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import fr.esgi.android.blocnote.R;
import fr.esgi.android.blocnote.adapters.CategoryListAdapter;
import fr.esgi.android.blocnote.datas.MyDatabaseHelper;


public class ListCategoryActivity extends ListActivity {
	private Context context;
	private Button AddCategoryButton;
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
						ListCategoryActivity.this,
						AddCategoryActivity.class);
				startActivityForResult(AddCategoryIntent,2);

			}
		});

		categorySearchInput = (EditText) findViewById(R.id.categorySearchInput);
		categorySearchInput.setHint(R.string.search_Input_Category);
		categorySearchInput.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				categorySearch(categorySearchInput.getText().toString());
				Toast.makeText(getApplicationContext(), "Les nombres d'enregistrement : "+categoryListA.getCount(), Toast.LENGTH_SHORT).show();

			}



			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
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
		categoryListA = new CategoryListAdapter(ListCategoryActivity.this,
				MyDatabaseHelper.getInstance(context).getAllCategories());
		setListAdapter(categoryListA);

	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode ==2){
			categoryListA = new CategoryListAdapter(
					ListCategoryActivity.this, 
					MyDatabaseHelper.getInstance(context)
					.getAllCategories());

			setListAdapter(categoryListA);
		}

	}
	private void categorySearch(String categoryName){
		categoryListA = new CategoryListAdapter(ListCategoryActivity.this, MyDatabaseHelper.getInstance(context).getCategoriesForSearch(categoryName));
		setListAdapter(categoryListA);
	}

}
