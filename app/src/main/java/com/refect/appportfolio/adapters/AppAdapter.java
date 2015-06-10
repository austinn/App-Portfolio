package com.refect.appportfolio.adapters;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.refect.appportfolio.R;
import com.refect.appportfolio.listeners.OnRecyclerViewItemClickListener;
import com.refect.appportfolio.models.AppModel;


/**
 *
 * @author Austin
 */
public class AppAdapter extends RecyclerView.Adapter<AppAdapter.ViewHolder> implements View.OnClickListener {

	private List<AppModel> models;
	private OnRecyclerViewItemClickListener<AppModel> itemClickListener;
	private static Context mContext;
	private int lastPosition = -1;

	public AppAdapter(List<AppModel> models, Context context) {
		this.models = models;
		this.mContext = context;
	}

	public AppAdapter(Context context) {
		this.mContext = context;
		this.models = new ArrayList<>();
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_app, viewGroup, false);
		v.setOnClickListener(this);		
		return new ViewHolder(v);
	}

	@Override
	public void onBindViewHolder(ViewHolder viewHolder, int i) {
		final AppModel model = models.get(i);
		viewHolder.itemView.setTag(model);
		viewHolder.appName.setText(model.getName());
		viewHolder.appDescription.setText(model.getDescription());

		if(model.getResId() != -1) {
			viewHolder.appThumbnail.setImageResource(model.getResId());
		} else {
			viewHolder.appThumbnail.setBackgroundColor(model.getBackgroundColor());
		}
			
	}

	@Override
	public int getItemCount() {
		return models == null ? 0 : models.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		public TextView appName;
		public TextView appDescription;
		public ImageView appThumbnail;

		public ViewHolder(View itemView) {
			super(itemView);
			appName = (TextView) itemView.findViewById(R.id.tv_app_name);
			appDescription = (TextView) itemView.findViewById(R.id.tv_app_description);
			appThumbnail = (ImageView) itemView.findViewById(R.id.iv_app_thumbnail);
		}

	}

	public void add(AppModel item, int position) {
		models.add(position, item);
		notifyItemInserted(position);
	}

	public void remove(AppModel item) {
		int position = models.indexOf(item);
		models.remove(position);
		notifyItemRemoved(position);
	}

	public void setOnItemClickListener(OnRecyclerViewItemClickListener<AppModel> listener) {
		this.itemClickListener = listener;
	}

	@Override
	public void onClick(View v) {
		if (itemClickListener != null) {
			AppModel model = (AppModel) v.getTag();
			itemClickListener.onItemClick(v, model);
		}
	}
}