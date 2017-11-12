package providersanalyzer.alexejantonov.com;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class QueryActivity extends AppCompatActivity {

	public static final String EXTRA_URI = QueryActivity.class.getSimpleName() + ".uri";

	private TextView authorityView;
	private EditText projectionEdit;
	private EditText selectionEdit;
	private EditText selectionArgsEdit;
	private Button querySubmit;

	public static Intent newIntent(Context context, String uri) {
		Intent intent = new Intent(context, QueryActivity.class);
		intent.putExtra(EXTRA_URI, uri);
		return intent;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_query);

		authorityView = findViewById(R.id.authorityView);
		projectionEdit = findViewById(R.id.projectionEdit);
		selectionEdit = findViewById(R.id.selectionEdit);
		selectionArgsEdit = findViewById(R.id.selectionArgsEdit);
		querySubmit = findViewById(R.id.queryButton);

		String uri = getIntent().getStringExtra(EXTRA_URI);
		authorityView.setText(uri);
	}
}
