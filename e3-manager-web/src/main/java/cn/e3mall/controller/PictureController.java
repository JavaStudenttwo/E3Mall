package cn.e3mall.controller;

import cn.e3mall.common.utils.FastDFSClient;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date 2017/12/23 17:08
 * @Author CycloneKid sk18810356@gmail.com
 * @PackageName: cn.e3mall.controller
 * @ClassName: PictureController
 * @Description: 图片上传控制类
 *
 */
@Controller
public class PictureController {

    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    @RequestMapping(value = "/pic/upload",produces = MediaType.TEXT_PLAIN_VALUE+ ";charset=utf-8")
    @ResponseBody
    public Map fileUpload(MultipartFile uploadFile) {
        try {
            //取文件的扩展名
            String originalFilename = uploadFile.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);

            FastDFSClient fastDFSClient = new FastDFSClient("classpath:conf/client.conf");

            String path = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
            //拼接返回的url和ip地址，拼装成完整的url
            String url = IMAGE_SERVER_URL + path;

            Map result = new HashMap<>();
            result.put("error", 0);
            result.put("url", url);

            Gson gson = new Gson();
            String json = gson.toJson(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();

            Map result = new HashMap<>();
            result.put("error", 1);
            result.put("message", "图片上传失败");

            Gson gson = new Gson();
            String json = gson.toJson(result);
            return result;
        }
    }

}
