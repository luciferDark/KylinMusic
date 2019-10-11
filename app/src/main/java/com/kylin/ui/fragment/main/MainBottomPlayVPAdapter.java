package com.kylin.ui.fragment.main;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kylin.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Kylin on 2018/4/7.
 */

public class MainBottomPlayVPAdapter extends PagerAdapter {
    private Context mContext;
    private List<String> mPlayList;
    private View mView;
    public TextView nameTxtView, songerTxtView;

    public MainBottomPlayVPAdapter(Context context, List<String> mPlayList) {
        super();
        this.mContext = context;
        this.mPlayList = mPlayList;
    }

    @Override
    public int getCount() {
        return mPlayList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // TODO Auto-generated method stub
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        // TODO Auto-generated method stub
        String mJsonStr = mPlayList.get(position);
        try {
            mView = LayoutInflater.from(mContext).inflate(
                    R.layout.kylin_ui_main_bottom_music_play_vp_adapter,
                    null, false);
            nameTxtView = mView.findViewById(R.id.ui_main_bottom_music_play_vp_adapter_music_name);
            songerTxtView = mView.findViewById(R.id.ui_main_bottom_music_play_vp_adapter_music_songer);
            JSONObject mMusicJson = new JSONObject(mJsonStr);
            String name = mMusicJson.getString("name");
            String songer = mMusicJson.getString("songer");
            nameTxtView.setText(name);
            songerTxtView.setText(songer);
            container.addView(mView);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mView;
    }

    public void setPlayList(List<String> mPlayList) {
        this.mPlayList = mPlayList;
        notifyDataSetChanged();
    }
}
