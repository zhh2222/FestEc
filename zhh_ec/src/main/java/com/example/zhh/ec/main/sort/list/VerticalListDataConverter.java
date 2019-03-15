package com.example.zhh.ec.main.sort.list;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.zhh_ui.recycler.DataConverter;
import com.example.zhh_ui.recycler.ItemType;
import com.example.zhh_ui.recycler.MultipleFields;
import com.example.zhh_ui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * @author brett-zhu
 * created at 2019/3/10 16:25
 */
public class VerticalListDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONObject("data")
                .getJSONArray("list");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            int id = data.getInteger("id");
            String name = data.getString("name");
            final MultipleItemEntity entity = MultipleItemEntity.builder().setField(MultipleFields.ITEM_TYPE, ItemType.VERTICAL_MENU_LIST)
                    .setField(MultipleFields.ID, id).setField(MultipleFields.TEXT, name)
                    .setField(MultipleFields.TAG, false).build();
            dataList.add(entity);
            //设置第一个被选中
            dataList.get(0).setField(MultipleFields.TAG, true);
        }
        return dataList;
    }
}
