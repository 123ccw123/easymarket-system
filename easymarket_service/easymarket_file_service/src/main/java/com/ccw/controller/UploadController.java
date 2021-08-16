package com.ccw.controller;

import com.ccw.entity.Result;
import com.ccw.entity.StatusCode;
import com.ccw.utils.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/*文件的上传*/
@RestController
public class UploadController {
    @Value("${FILE_SERVER_URL}")
    private String FILE_SERVER_URL;//服务器文件地址

    @PostMapping("/upload")
    public Result upload(@RequestParam(name = "file")MultipartFile file){
        //获取文件扩展名
        String filename = file.getOriginalFilename();
        String lastName = filename.substring(filename.lastIndexOf(".") + 1);

        try {
            //创建一个FASTDFS客户端
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:dfs_client.conf");
            //执行上传处理
            String s = fastDFSClient.uploadFile(file.getBytes(), lastName);
            //拼接返回的url和ip地址，拼装完整的url
            String url = FILE_SERVER_URL + s;
            //返回封装结果
            return new Result(true, StatusCode.OK,"上传成功!",url);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false,StatusCode.ERROR,"上传失败！");
        }
    }
}
