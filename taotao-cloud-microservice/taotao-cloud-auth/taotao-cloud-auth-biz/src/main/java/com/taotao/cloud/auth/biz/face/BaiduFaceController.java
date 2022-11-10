package com.taotao.cloud.auth.biz.face;

import com.taotao.cloud.common.utils.io.ResourceUtils;
import com.taotao.cloud.common.utils.lang.StringUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;

@Validated
@Tag(name = "百度人脸识别API", description = "百度人脸识别API")
@RestController
@RequestMapping("/login/face")
public class BaiduFaceController {

	@Autowired
	private FaceUtils faceUtils;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(String userName, String faceBase) throws IOException {
		if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(faceBase)) {
			//文件上传的地址
			String upPath = ResourceUtils.getURL("classpath:").getPath() + "static\\photo";
			//用于查看路径是否正确
			System.out.println(upPath);
			// 图片名称
			String fileName = userName + System.currentTimeMillis() + ".png";
			System.out.println(upPath + "\\" + fileName);
			File file = new File(upPath + "\\" + fileName);

			//往自己demo数据库里插入一条用户数据
			// Users user = new Users();
			// user.setUserName(userName);
			// user.setUserPhoto(upPath + "\\" + fileName);
			// Users exitUser = userService.selectUserByName(user);
			// if (exitUser != null) {
			// 	return "2";
			// }
			// userService.addUsers(user);
			//
			// // 往自己demo服务器里面上传摄像头捕获的图片
			// GenerateImage(faceBase, file);

			//向百度云人脸库插入一张人脸
			faceUtils.upToStandard(upPath + "\\" + fileName);
			faceUtils.registerFace(userName, faceBase);

			// faceUtils.saveLocalImage(faceBase, file);
			// faceUtils.faceSetAddUser(faceBase, userName);
		}
		return "1";


	}
}