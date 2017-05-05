package com.spaceapps.garden.adapters;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.spaceapps.garden.R;
import com.spaceapps.garden.models.CultivationLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Seth-Pharès Gnavo (sethgnavo)
 */

public class CultivationLevelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CultivationLevel> mCultivationLevelList;
    private Context mContext;

    private CultivationLevelAdapter.OnItemClickListener mItemClickListener;
    private CultivationLevelAdapter.OnItemLongClickListener mItemLongClickListener;

    public CultivationLevelAdapter(Context context) {
        mCultivationLevelList = new ArrayList<>();
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cultivation_level, parent, false);
        return new LevelHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((LevelHolder) holder).bind(mContext, mCultivationLevelList.get(position),
                mItemClickListener, mItemLongClickListener);
    }

    @Override
    public int getItemCount() {
        return mCultivationLevelList.size();
    }


    public void setOnItemClickListener(CultivationLevelAdapter.OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void setOnItemLongClickListener(CultivationLevelAdapter.OnItemLongClickListener
                                                   listener) {
        mItemLongClickListener = listener;
    }

    public void setCultivationLevelList(List<CultivationLevel> cultivationLevels) {
        mCultivationLevelList = cultivationLevels;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(CultivationLevel channel);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(CultivationLevel channel);
    }

    /**
     * A ViewHolder that contains UI to display information about a GroupChannel.
     */
    private static class LevelHolder extends RecyclerView.ViewHolder {

        TextView levelName, dateRange, progressText;


        LevelHolder(View itemView) {
            super(itemView);
            levelName = (TextView) itemView.findViewById(R.id.text_level_name);
            dateRange = (TextView) itemView.findViewById(R.id.text_date_range);
            progressText = (TextView) itemView.findViewById(R.id.text_progress);
        }

        /**
         * Binds views in the ViewHolder to information contained within the Level.
         *
         * @param context
         * @param level
         * @param clickListener     A listener that handles simple clicks.
         * @param longClickListener A listener that handles long clicks.
         */
        void bind(final Context context, final CultivationLevel level,
                  @Nullable final CultivationLevelAdapter.OnItemClickListener clickListener,
                  @Nullable final CultivationLevelAdapter.OnItemLongClickListener
                          longClickListener) {
            levelName.setText(level.getLevelName());
            dateRange.setText("Date range");
            progressText.setText("Progress text");


            // Set an OnClickListener to this item.
            if (clickListener != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickListener.onItemClick(level);
                    }
                });
            }

            // Set an OnLongClickListener to this item.
            if (longClickListener != null) {
                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        longClickListener.onItemLongClick(level);

                        // return true if the callback consumed the long click
                        return true;
                    }
                });
            }
        }

    }
}
