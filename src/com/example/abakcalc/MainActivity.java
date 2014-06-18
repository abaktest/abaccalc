package com.example.abakcalc;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null)
		{
			getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();
		if (id == R.id.action_settings)
		{
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment
	{
		
		public PlaceholderFragment()
		{
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			Button computeButton = (Button) rootView.findViewById(R.id.compute);
			final EditText exprField = (EditText) rootView.findViewById(R.id.editText);
			final TextView resultTV = (TextView) rootView.findViewById(R.id.result);
			computeButton.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					try
					{
						String[] infixOutput = ExpressionParser.splitString(exprField.getText().toString());
						for (String token : infixOutput) {  
				            Log.d("output", "infix: " + token + " ");
				        }  
						String[] rpnOutput = ExpressionParser.infixToRPN(infixOutput);  

				        for (String token : rpnOutput) {  
				            Log.d("output", "rpn: " + token + " ");
				        }  

				        Double result = ExpressionParser.RPNtoDouble( rpnOutput );
				        resultTV.setText(String.valueOf(result));
					} catch (Exception e)
					{
						e.printStackTrace();
						resultTV.setText("Expression error");
					}
					
				}
			});
			return rootView;
		}
	}

}
