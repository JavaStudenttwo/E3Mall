package cn.e3mall.common.utils;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

/**
 * @Date 2017/12/23 16:42
 * @Author CycloneKid sk18810356@gmail.com
 * @PackageName: cn.e3mall.common.utils
 * @ClassName: FastDFSClient
 * @Description: 图片上传工具类
 *
 */
public class FastDFSClient {

/**
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
   //扩展名不带“.”
	String[] strings =
			storageClient.upload_file("E:\\下载文件\\新建文件夹\\Screenshot_2017-12-31-15-20-43.jpeg", "jpeg", null);

*/

	private TrackerClient trackerClient = null;
	private TrackerServer trackerServer = null;
	private StorageServer storageServer = null;
	private StorageClient1 storageClient = null;

	/**
	 * @Date 2017/12/23 16:42
	 * @Author CycloneKid sk18810356@gmail.com
	 * @MethodName: FastDFSClient
	 * @Params: [conf]
	 * @ReturnType:
	 * @Description: 构造方法，创建上传文件需要的工具类
	 *
	 */
	public FastDFSClient(String conf) throws Exception {

		/**修改配置文件路径名，将相对路径名由相对项目改为真实路径*/
		if (conf.contains("classpath:")) {
			conf = conf.replace("classpath:", this.getClass().getResource("/").getPath());
		}
		ClientGlobal.init(conf);
		trackerClient = new TrackerClient();
		trackerServer = trackerClient.getConnection();
		storageServer = null;
		storageClient = new StorageClient1(trackerServer, storageServer);
	}

	/**
	 * @Date 2017/12/23 16:43
	 * @Author CycloneKid sk18810356@gmail.com
	 * @MethodName: uploadFile
	 * @Params: [fileName, extName, metas]
	 * @ReturnType: java.lang.String
	 * @Description: 上传文件方法
	 *
	 */
	public String uploadFile(String fileName, String extName, NameValuePair[] metas) throws Exception {
		String result = storageClient.upload_file1(fileName, extName, metas);
		return result;

	}

	/**
	 * @Date 2017/12/23 16:43
	 * @Author CycloneKid sk18810356@gmail.com
	 * @MethodName: uploadFile
	 * @Params: [fileName]
	 * @ReturnType: java.lang.String
	 * @Description: 上传文件方法
	 *
	 */
	public String uploadFile(String fileName) throws Exception {
		return uploadFile(fileName, null, null);
	}
	
	public String uploadFile(String fileName, String extName) throws Exception {
		return uploadFile(fileName, extName, null);
	}
	

	public String uploadFile(byte[] fileContent, String extName, NameValuePair[] metas) throws Exception {
		
		String result = storageClient.upload_file1(fileContent, extName, metas);
		return result;
	}
	
	public String uploadFile(byte[] fileContent) throws Exception {
		return uploadFile(fileContent, null, null);
	}
	
	public String uploadFile(byte[] fileContent, String extName) throws Exception {
		return uploadFile(fileContent, extName, null);
	}
}
