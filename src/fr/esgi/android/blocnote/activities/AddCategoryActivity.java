package fr.esgi.android.blocnote.activities;

import fr.esgi.android.blocnote.R;
import fr.esgi.android.blocnote.datas.MyDatabaseHelper;
import fr.esgi.android.blocnote.models.Category;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddCategoryActivity extends Activity {
	
	private EditText categoryName;
	private boolean modifyFlag = false;
	private Context context;
	private Button addCategory;
	private int categoryIdList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_category_activity);
		context=this;
		setTitle(R.string.add_Category_Screen_Name);
		
		categoryName = (EditText) findViewById(R.id.addCategoryEditText);
		categoryName.setHint(R.string.input_Category_Hint);
		
		addCategory = (Button) findViewById(R.id.addCategoryButton);
		addCategory.setText(R.string.add_Category);

		if (this.getIntent().getStringExtra("categoryName") != null) {
			String categoryNameList = this.getIntent().
					getStringExtra("categoryName");
			categoryIdList = this.getIntent().getIntExtra("categoryId", 1);
			modifyFlag = this.getIntent().getBooleanExtra("modifyFlag", false);
			categoryName.setText(categoryNameList);
			
			addCategory.setText(R.string.modify_Button);
		}
		
		addCategory.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Category addCategory = new Category();
				addCategory.setName(categoryName.getText().toString());

				if (modifyFlag == true) {
					addCategory.setId(categoryIdList);
					
					MyDatabaseHelper.getInstance(context).updateCategory(addCategory);
				}
				else {
					
					MyDatabaseHelper.getInstance(context).addCategory(addCategory);
				}
				
				Intent intent = new Intent();
				setResult(2,intent);  
	            finish();

			}
		});


	}



}
