package com.atguigu.gmall.manage;

import java.io.IOException;

import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.atguigu.gmall.manage.util.ManageWebConstant;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GmallManageWebApplicationTests {
	
	@Test
	public void contextLoads() throws IOException, MyException {

		// 配置fdfs的全局链接地址
		String tracker = GmallManageWebApplicationTests.class.getResource("/tracker.conf").getPath();// 获得配置文件的路径
		System.out.println("tracker地址: " + tracker);
		ClientGlobal.init(tracker);

		TrackerClient trackerClient = new TrackerClient();

		// 获得一个trackerServer的实例
		TrackerServer trackerServer = trackerClient.getConnection();

		// 通过tracker获得一个Storage链接客户端
		StorageClient storageClient = new StorageClient(trackerServer,null);

		String[] uploadInfos = storageClient.upload_file("C:\\Users\\13554\\Pictures\\Saved Pictures\\222.png", "png", null);

		String imgUrl = ManageWebConstant.imgUrl;
		for (String uploadInfo : uploadInfos) {
			imgUrl += "/"+uploadInfo;

		}

		System.out.println(imgUrl);

	}

}
