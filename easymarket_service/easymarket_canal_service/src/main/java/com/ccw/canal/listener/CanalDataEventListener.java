package com.ccw.canal.listener;

import com.alibaba.otter.canal.protocol.CanalEntry;
import com.xpand.starter.canal.annotation.*;

@CanalEventListener
public class CanalDataEventListener {

    //rowData.getBeforeColumnsList(); //更新之前的数据修改、删除
    //rowData.getAfterColumnsList(); //更新以后的数据增加、修改
    /*增加数据监听*/
    @InsertListenPoint
    public void onEventInsert(CanalEntry.EventType eventType,CanalEntry.RowData rowData){
        for (CanalEntry.Column column : rowData.getAfterColumnsList()) {
            System.out.println("插入后的数据"+column.getName()+"---"+column.getValue());
        }
    }

    /*监听数据修改*/
    @UpdateListenPoint
    public void onEventUpdate(CanalEntry.EventType eventType,CanalEntry.RowData rowData){
        for (CanalEntry.Column column : rowData.getAfterColumnsList()) {
            System.out.println("修改后的数据"+column.getName()+"---"+column.getValue());
        }
    }
    /*监听数据删除*/
    @DeleteListenPoint
    public void onEventDelete(CanalEntry.EventType eventType,CanalEntry.RowData rowData){
        for (CanalEntry.Column column : rowData.getAfterColumnsList()) {
            System.out.println("删除后的数据"+column.getName()+"---"+column.getValue());
        }
    }
}
