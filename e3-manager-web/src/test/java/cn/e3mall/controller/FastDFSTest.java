package cn.e3mall.controller;

import cn.e3mall.common.utils.FastDFSClient;
import org.csource.fastdfs.*;
import org.junit.Test;

/**
 * @Date 2017/12/29 22:00
 * @Author CycloneKid sk18810356@gmail.com
 * @PackageName: cn.e3mall.controller
 * @ClassName: FastDFSTest
 * @Description: 文件上传服务器测试
 *
 */
public class FastDFSTest {

    /**
     * @Date 2017/12/29 22:01
     * @Author CycloneKid sk18810356@gmail.com
     * @MethodName: testFileUpload
     * @Params: []
     * @ReturnType: void
     * @Description: 测试方法，文件上传
     *
     */
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
        String[] strings =
                storageClient.upload_file("E:\\下载文件\\新建文件夹\\Screenshot_2017-12-31-15-20-43.jpeg", "jpeg", null);

        for (String string : strings) {
            System.out.println(string);
        }
    }




}
