package fr.esgi.android.blocnote.activities;

import org.w3c.dom.ls.LSInput;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import fr.esgi.android.blocnote.R;
import fr.esgi.android.blocnote.adapters.CategoryListAdapter;
import fr.esgi.android.blocnote.datas.MyDatabaseHelper;
import fr.esgi.android.blocnote.models.Category;


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

		registerForContextMenu(getListView());

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


			}

			@Override
			public void afterTextChanged(Editable s) {


			}
		});
		SearchAllCategory();

	}


	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu_list_category, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {

		AdapterContextMenuInfo AcmInfo = 
				(AdapterContextMenuInfo) item.getMenuInfo();

		Category category = (Category) getListAdapter().getItem(AcmInfo.position);
		switch (item.getItemId()) {
		case R.id.modifyCategory:

			Intent modifyCategory = new Intent(
					ListCategoryActivity.this,
					AddCategoryActivity.class
					);
			modifyCategory.putExtra("categoryId", category.getId());
			modifyCategory.putExtra("categoryName", category.getName());
			modifyCategory.putExtra("categoryAction", true);
			startActivityForResult(modifyCategory, 2);
			return true;

		case R.id.deleteCategory:
			deleteCategory(category.getId());
			return true;

		default:

			return super.onContextItemSelected(item);

		}
	}

	private void deleteCategory(int id) {
		MyDatabaseHelper.getInstance(context).
		deleteCategory(id);

		categoryListA = new CategoryListAdapter(
				ListCategoryActivity.this,
				MyDatabaseHelper.getInstance(context).
				getAllCategories());

		setListAdapter(categoryListA);

	}


	protected void SearchAllCategory() {
		categoryListA = new CategoryListAdapter(ListCategoryActivity.this,
				MyDatabaseHelper.getInstance(context).getAllCategories());
		setListAdapter(categoryListA);

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Category category = (Category) l.getItemAtPosition(position);
		int categoryId = category.getId();
/*
		Intent i = new Intent(ListCategoryActivity.this, NoteListActivity.class);
		i.putExtra("categoryId", tagId);
		startActivityForResult(i, 2);
	*/
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
