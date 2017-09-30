package com.vinh.alarmclockandroid.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vinh.alarmclockandroid.database_giobaothuc.GioBaoThucModel;

import java.util.List;

/**
 * Created by Admin on 9/5/2017.
 */

public class GioBaoThucAdapter extends ArrayAdapter<GioBaoThucModel>{
    private Context context;
    private int resource;
    private List<GioBaoThucModel> gioBaoThucModelList;
    public static final String KEY_ALARM = "key_alarm";

    public GioBaoThucAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<GioBaoThucModel> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.gioBaoThucModelList = objects;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//        convertView = layoutInflater.inflate(resource, parent, false);
//
//        final GioBaoThucModel storyModel = gioBaoThucModelList.get(position);
//
//        View vBookmark = (View) convertView.findViewById(R.id.v_bookmark);
//        TextView tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
//        TextView tvAuthor = (TextView) convertView.findViewById(R.id.tv_author);
//        ImageView ivStory = (ImageView) convertView.findViewById(R.id.iv_story);
//
//        CardView cardView = (CardView) convertView.findViewById(R.id.cv_tem_story);
//        cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, DescriptionActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable(KEY_STORY, storyModel);
//                intent .putExtras(bundle);
//                context.startActivity(intent);
//            }
//        });
//
//        tvTitle.setText(storyModel.getTitle());
//        tvAuthor.setText(storyModel.getAuthor());
//
//        if (storyModel.isBookmark())
//        {
//            vBookmark.setBackgroundResource(R.color.colorAccent);
//        }
//        else
//        {
//            vBookmark.setBackgroundResource(R.color.colorPrimary);
//        }
//
//        return convertView;
    }
}
