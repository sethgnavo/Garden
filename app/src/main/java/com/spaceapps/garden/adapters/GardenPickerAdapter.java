package com.spaceapps.garden.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spaceapps.garden.R;
import com.spaceapps.garden.models.Garden;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * @author Seth-Pharès Gnavo (sethgnavo)
 */

public class GardenPickerAdapter extends RealmRecyclerViewAdapter<Garden, GardenPickerAdapter
        .ViewHolder> {
    public GardenPickerAdapter(Context context, @Nullable OrderedRealmCollection<Garden> data) {
        super(context, data, true);
    }

    @Override
    public GardenPickerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View basinView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_garden,
                parent, false);
        return new ViewHolder(basinView);
    }

    @Override
    public void onBindViewHolder(GardenPickerAdapter.ViewHolder holder, int position) {
        Garden obj = getData().get(position);
        holder.data = obj;
        holder.gardenName.setText(obj.getGardenName());
        holder.gardenSize.setText(obj.getGardenSize());
        holder.gardenPlantCount.setText(String.valueOf(obj.getGardenPlantsCount()));
        holder.gardenCreatedTime.setText(DateUtils.formatDateTime(context, obj.getCreatedTime()
                .getTime(), DateUtils.FORMAT_SHOW_YEAR));
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Garden data;
        public TextView gardenName;
        public TextView gardenSize;
        public TextView gardenCreatedTime;
        public TextView gardenPlantCount;

        public ViewHolder(View view) {
            super(view);
            this.gardenName = (TextView) view.findViewById(R.id.garden_name);
            this.gardenSize = (TextView) view.findViewById(R.id.garden_size);
            this.gardenCreatedTime = (TextView) view.findViewById(R.id.garden_date);
            this.gardenPlantCount = (TextView) view.findViewById(R.id.garden_plant_count);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            //TODO show the list of plants being cultivated
            /*Intent i = new Intent(activity, BasinActivity.class);
            i.putExtra("jojo", data.getId());
            activity.startActivity(i);*/
        }
    }
}
