package providersanalyzer.alexejantonov.com;

import android.content.pm.ProviderInfo;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ProvidersAdapter extends RecyclerView.Adapter<ProvidersAdapter.ProviderHolder> {

	private List<ProviderInfo> providers;
	private OnProviderClickListener listener;

	public ProvidersAdapter(List<ProviderInfo> providers, OnProviderClickListener listener) {
		this.providers = providers;
		this.listener = listener;
	}

	@Override
	public ProviderHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.provider_item, parent, false);
		return new ProviderHolder(view);
	}

	@Override
	public void onBindViewHolder(ProviderHolder holder, int position) {
		holder.bindProvider(providers.get(position));
	}

	@Override
	public int getItemCount() {
		return providers.size();
	}

	class ProviderHolder extends RecyclerView.ViewHolder {

		TextView authorityView;
		TextView readPermissionView;
		TextView writePermissionView;

		public ProviderHolder(View itemView) {
			super(itemView);

			authorityView = itemView.findViewById(R.id.authorityView);
			readPermissionView = itemView.findViewById(R.id.readPermissionView);
			writePermissionView = itemView.findViewById(R.id.writePermissionView);

			itemView.setOnClickListener(view -> listener.onClick(
					String.format(
							itemView
									.getContext()
									.getString(R.string.authority), providers.get(getAdapterPosition()).authority
					)
					)
			);
		}

		public void bindProvider(ProviderInfo provider) {
			authorityView.setText(String.format(itemView.getContext().getString(R.string.authority), provider.authority));
			readPermissionView.setText(String.format(itemView.getContext().getString(R.string.readPermission), provider.readPermission));
			writePermissionView.setText(String.format(itemView.getContext().getString(R.string.writePermission), provider.writePermission));
		}
	}

	interface OnProviderClickListener {
		void onClick(String contentUri);
	}
}
