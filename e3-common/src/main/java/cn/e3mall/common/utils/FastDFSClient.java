package cn.e3mall.common.utils;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

/**
 * @Date 2017/12/23 16:42
 * @Author CycloneKid sk18810356@gmail.com
 * @PackageName: cn.e3mall.common.utils
 * @ClassName: FastDFSClient
 * @Description: 图片上传工具类
 *
 */
public class FastDFSClient {

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
	 * @Description: 构造方法
	 *
	 */
	public FastDFSClient(String conf) throws Exception {

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
