package com.example.zhh.ec.main.sort.content;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author brett-zhu
 * created at 2019/3/10 21:02
 */
public class SectionDataConverter {
    final List<SectionBean> convert(String json) {
        final List<SectionBean> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(json).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final int id = data.getInteger("id");
            final String title = data.getString("section");

            //添加Title
            final SectionBean sectionTitleBean = new SectionBean(true, title);
            sectionTitleBean.setId(id);
            sectionTitleBean.setMore(true);
            dataList.add(sectionTitleBean);
            final JSONArray goods = data.getJSONArray("goods");
            //商品内容循环
            final int goodsSize = goods.size();
            for (int j = 0; j < goodsSize; j++) {
                final JSONObject contentItem = goods.getJSONObject(j);
                final int goodsId = contentItem.getInteger("goods_id");
                final String goodsThumb = contentItem.getString("goods_thumb");
                final String goodsName = contentItem.getString("goods_name");
                //获取内容
                final SectionContentItemEntity itemEntity = new SectionContentItemEntity();
                itemEntity.setGoodsId(goodsId);
                itemEntity.setGoodsName(goodsName);
                itemEntity.setGoodsThumb(goodsThumb);

                //添加内容
                dataList.add(new SectionBean(itemEntity));
            }
            //商品内容循环结束
        }
        //Section循环结束
        return dataList;
    }
}
