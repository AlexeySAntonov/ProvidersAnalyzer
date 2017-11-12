package providersanalyzer.alexejantonov.com;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

	private ProviderInfo[] providers;
	private List<ProviderInfo> providersList = new ArrayList<>();

	private RecyclerView recyclerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		recyclerView = findViewById(R.id.recyclerView);
		recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
		recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
		recyclerView.setAdapter(new ProvidersAdapter(getProviders(), this::launchQueryActivity));

		getSupportActionBar().setTitle("Доступные провайдеры");
	}

	private void launchQueryActivity(String contentUri) {
		Intent intent = QueryActivity.newIntent(this, contentUri);
		startActivity(intent);
	}

	private List<ProviderInfo> getProviders() {
		List<PackageInfo> packageInfoList = getPackageManager().getInstalledPackages(PackageManager.GET_PROVIDERS);
		for (int i = 0; i < packageInfoList.size(); i++) {
			providers = packageInfoList.get(i).providers;
			if (providers != null) {
				providersList.addAll(Arrays.asList(providers));
			}
		}
		Toast.makeText(this, "Providers count: " + providersList.size(), Toast.LENGTH_LONG).show();
		return providersList;
	}
}
