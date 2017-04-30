package com.spaceapps.garden.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.spaceapps.garden.R;
import com.spaceapps.garden.activities.PlantDetailActivity;
import com.spaceapps.garden.models.Plant;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * @author Seth-Pharès Gnavo (sethgnavo)
 */

public class PlantAdapter extends RealmRecyclerViewAdapter<Plant, PlantAdapter.ViewHolder> {
    Context context;

    public PlantAdapter(Context context, @Nullable OrderedRealmCollection<Plant> data) {
        super(context, data, true);
        this.context = context;
    }

    @Override
    public PlantAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View plantView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plant,
                parent, false);
        return new PlantAdapter.ViewHolder(plantView);
    }

    @Override
    public void onBindViewHolder(PlantAdapter.ViewHolder holder, int position) {
        Plant obj = getData().get(position);
        holder.data = obj;
        holder.plantName.setText(obj.getPlantName());
        holder.plantImage.setImageResource(obj.getPlantImageRessource());
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Plant data;
        public TextView plantName;
        public ImageView plantImage;

        public ViewHolder(View view) {
            super(view);
            this.plantName = (TextView) view.findViewById(R.id.text_item_plant_name);
            this.plantImage = (ImageView) view.findViewById(R.id.image_item_plant);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(context, PlantDetailActivity.class);
            i.putExtra("jojo", data.getId());
            context.startActivity(i);
        }
    }
}
