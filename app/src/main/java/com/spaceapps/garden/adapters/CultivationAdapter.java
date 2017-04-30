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
import com.spaceapps.garden.activities.CultivationActivity;
import com.spaceapps.garden.models.Cultivation;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

/**
 * @author Seth-Pharès Gnavo (sethgnavo)
 */

public class CultivationAdapter extends RealmRecyclerViewAdapter<Cultivation, CultivationAdapter
        .ViewHolder> {

    Context context;

    public CultivationAdapter(Context context, @Nullable OrderedRealmCollection<Cultivation> data) {
        super(context, data, true);
        this.context = context;
    }

    @Override
    public CultivationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View plantView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plant,
                parent, false);
        return new CultivationAdapter.ViewHolder(plantView);
    }

    @Override
    public void onBindViewHolder(CultivationAdapter.ViewHolder holder, int position) {
        Cultivation obj = getData().get(position);
        holder.data = obj;
        holder.cultivationName.setText(obj.getPlantBundle().getPlantName());
        holder.cultivationImage.setImageResource(obj.getPlantBundle().getPlantImageRessource());
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Cultivation data;
        public TextView cultivationName;
        public ImageView cultivationImage;

        public ViewHolder(View view) {
            super(view);
            this.cultivationName = (TextView) view.findViewById(R.id.text_item_plant_name);
            this.cultivationImage = (ImageView) view.findViewById(R.id.image_item_plant);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent i = new Intent(context, CultivationActivity.class);
            i.putExtra("jojo", data.getId());
            context.startActivity(i);
        }
    }
}
