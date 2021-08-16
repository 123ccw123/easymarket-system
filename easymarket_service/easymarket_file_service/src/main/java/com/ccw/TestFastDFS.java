package com.ccw;

import org.csource.common.MyException;
import org.csource.fastdfs.*;

import java.io.IOException;

public class TestFastDFS {
    public static void main(String[] args) throws IOException, MyException {
        //1、加载配置文件，其中配置文件中就是tracker的服务地址
        ClientGlobal.init("C:\\studysystem\\easymarket_system" +
                "\\easymarket_service\\easymarket_file_service\\src\\main" +
                "\\resources\\dfs_client.conf");
        //创建一个TrackerClient对象
        TrackerClient trackerClient = new TrackerClient();
        //通过trackerClient创建一个TrackerServer对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //创建一个storageServer对象的引用，将他的值设置为null
        StorageServer storageServer = null;
        //创建一个storageClient对象，需要使用storageServer和trackerServer对象
        StorageClient storageClient = new StorageClient(trackerServer,storageServer);
        //使用storageClient对象上传图片，不用带扩展名
        //String[] strings = storageClient.upload_file("C:/1.jpg", "jpg", null);
        String[] strings = storageClient.upload_file("C:/1.jpg", "jpg", null);
        //返回数组和图片的路径
        for (String string : strings) {
            System.out.println(string);
        }
        //docker run -d --name storage --net=host -e TRACKER_IP=192.168.15.111:22122 -e GROUP_NAME=group1 morunchang/fastdfs sh storage.sh
    }
}
