package fr.esgi.android.blocnote.adapters;

import java.util.List;

import fr.esgi.android.blocnote.R;
import fr.esgi.android.blocnote.models.Category;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CategoryListAdapter extends BaseAdapter {

	private Context context;
	private List<Category> categories;
	private LayoutInflater inflater;

	public CategoryListAdapter(Context context,
			List<Category> categories) {
		this.context=context;
		this.categories=categories;
		this.inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return categories.size();
	}

	@Override
	public Object getItem(int position) {
		return categories.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return categories.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Category cat = (Category) getItem(position);

		convertView = inflater.inflate(R.layout.list_category,null);

		TextView tagName = (TextView) convertView.findViewById(R.id.catTextView);
		tagName.setText(cat.getName()); 


		return convertView;
	}

}
