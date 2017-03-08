package br.com.unic.lha.app.component.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * The Class ExampleListAdapter.
 */
public class ExampleListAdapter extends BaseAdapter {
	
	/** The objects. */
	private final List<Object> objects = new ArrayList<Object>();
	
	/** The inflater. */
	private final LayoutInflater inflater;
	
	/**
	 * Instantiates a new example list adapter.
	 *
	 * @param context the context
	 */
	public ExampleListAdapter(final Context context) {
		this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	
	/** {@inheritDoc} **/ 
	@Override
	public int getCount() {
		return objects.size();
	}

	/** {@inheritDoc} **/ 
	@Override
	public Object getItem(final int position) {
		// TODO Auto-generated method stub
		return objects.get(position);
	}

	/** {@inheritDoc} **/ 
	@Override
	public long getItemId(final int position) {
		// TODO Auto-generated method stub
		return position;
	}

	/** {@inheritDoc} **/ 
	@Override
	public View getView(final int position, final View convertView, final ViewGroup parent) {
		// TODO Auto-generated method stub
		
		//Inflar o layout da linha
		final View view = inflater.inflate(null, null);
		
		//fazer o bind dos componentes e preenche-los com os dados do 
		final Object obj = getItem(position);
		
		//retornar view criada e preenchida para ser montada na lista;
		return view;
	}

}
