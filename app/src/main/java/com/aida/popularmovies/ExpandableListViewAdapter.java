package com.aida.popularmovies;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.aida.popularmovies.Model.Review;
import com.aida.popularmovies.Model.Video;
import com.aida.popularmovies.databinding.ListGroupItemBinding;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by aida on 5/26/18.
 */

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    Context context;
    HashMap<String, ArrayList<Object>> groups;

    public ExpandableListViewAdapter(Context context, ArrayList<Object> videos, ArrayList<Object> reviews) {
        groups = new HashMap<>();
        this.context = context;
        groups.put(Utils.TRAILERS, videos);
        groups.put(Utils.REVIEWS, reviews);
    }

    @Override
    public int getGroupCount() {
        return 2;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(getGroup(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.keySet().toArray()[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        String group = (String) getGroup(groupPosition);
        return groups.get(group).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group_header, null);
        }
        TextView textView = convertView.findViewById(R.id.lblListHeader);
        textView.setText(getGroup(groupPosition).toString());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ListGroupItemBinding itemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_group_item, parent, false);
        switch (groupPosition) {
            case 0:
                Review review = (Review) getChild(groupPosition, childPosition);
                itemBinding.setReview(review);
                itemBinding.setViewModel(((DetailMovieActivity) context).movieViewModel);
                itemBinding.lblListItem.setText(review.content);
                itemBinding.lblauthor.setText(review.author);
                break;
            case 1:
                Video video = (Video) getChild(groupPosition, childPosition);
                itemBinding.setVideo(video);
                itemBinding.setViewModel(((DetailMovieActivity) context).movieViewModel);
                itemBinding.lblListItem.setText(video.type);
                itemBinding.lblauthor.setVisibility(View.GONE);
                break;
        }
        return itemBinding.getRoot();
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
