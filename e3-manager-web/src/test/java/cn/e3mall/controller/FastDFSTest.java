package cn.e3mall.controller;

import cn.e3mall.common.utils.FastDFSClient;
import org.csource.fastdfs.*;
import org.junit.Test;

public class FastDFSTest {

    public void testFileUpload() throws Exception {
        // 1、加载配置文件，配置文件中的内容就是tracker服务的地址。
        ClientGlobal.init("E:\\编程学习区\\工作空间\\IdeaWorkplace\\IdeaProjects\\e3-parent\\e3-manager-web\\src\\main\\resources\\conf\\client.conf");
        // 2、创建一个TrackerClient对象。直接new一个。
        TrackerClient trackerClient = new TrackerClient();
        // 3、使用TrackerClient对象创建连接，获得一个TrackerServer对象。
        TrackerServer trackerServer = trackerClient.getConnection();
        // 4、创建一个StorageServer的引用，值为null
        StorageServer storageServer = null;
        // 5、创建一个StorageClient对象，需要两个参数TrackerServer对象、StorageServer的引用
        StorageClient storageClient = new StorageClient(trackerServer, storageServer);
        /**扩展名不带“.”*/
        String[] strings = storageClient.upload_file("D:/Screenshot_2017-12-22-00-00-25.jpeg", "jpeg", null);

        for (String string : strings) {
            System.out.println(string);
        }
    }


    public void testFastDfsClient() throws Exception {
        FastDFSClient fastDFSClient = new FastDFSClient("D:/workspaces-itcast/e3-manager-web/src/main/resources/resource/client.conf");
        String file = fastDFSClient.uploadFile("D:/Documents/Pictures/images/2f2eb938943d.jpg");
        System.out.println(file);
    }


}
