package com.example.edison.familyacoount;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class pictureAdapter extends BaseAdapter {           //创建基于BaseAdapter的子类

    private LayoutInflater inflater;                        //创建LayoutInflater对象
    private List<Picture> pictures;                         //创建List泛型集合


    //为类创建构造函数
    public pictureAdapter(String[] titles, int[] images, Context context){
        super();
        pictures = new ArrayList<Picture>();                    //初始化泛型集合对象
        inflater = LayoutInflater.from(context);                //初始化LayoutInflater对象
        for(int i = 0; i < images.length; i++){
            Picture picture = new Picture(titles[i], images[i]);    //使用标题和图像生成Picture
            pictures.add(picture);
        }
    }

    @Override
    public int getCount() {
        if (null != pictures){
            return pictures.size();
        }
        else {
            return 0;
        }

    }

    @Override
    public Object getItem(int i) {              //获取泛型集合指定索引出的项
        return pictures.get(i);
    }

    @Override
    public long getItemId(int i) {              //返回泛型集合的索引
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view == null){
            view  = inflater.inflate(R.layout.gvitem, null);
            viewHolder = new ViewHolder();                  //初始化ViewHolder对象
            viewHolder.title = (TextView)view.findViewById(R.id.itemTitle);

            //设置图像的二进制值
            viewHolder.image = (ImageView)view.findViewById(R.id.itemImage);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder)view.getTag();             //设置提示
        }
        viewHolder.title.setText(pictures.get(i).getTitle());

        //设置图像的二进制值
        viewHolder.image.setImageResource(pictures.get(i).getImageId());
        return view;
    }
}
