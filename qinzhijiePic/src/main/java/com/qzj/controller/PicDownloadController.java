package com.qzj.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

@CrossOrigin
@RestController
@RequestMapping("/pic")
public class PicDownloadController {
	private static BufferedImage bi = null;
	
	/**
	 * 单例模式获取水印图片
	 * @return
	 * @throws IOException 
	 */
    private BufferedImage getShuiYinPic() throws IOException {
    	if(bi != null) {
    		return bi;
    	}else {
    		InputStream in = this.getClass().getClassLoader().getResourceAsStream("shuiyin/shuiyin.png");
    		bi = ImageIO.read(in);
    		return bi;
    	}
    }

	@Value("${picFileDir}")
	private String uploadDir;
	// 文件下载相关代码
	@RequestMapping(value = "/downloadImage", method = RequestMethod.GET)
	public void downloadImage(@RequestParam(value = "path") String path,
			@RequestParam(value = "scale", defaultValue = "1f", required = false) double scale,
			@RequestParam(value = "quality", defaultValue = "1f", required = false) double quality,
			HttpServletRequest request, HttpServletResponse response) {
		String fileUrl = uploadDir + path;
		System.out.println(fileUrl);
		try {
			
			if (fileUrl != null) {
				File file = new File(fileUrl);
				if (file.exists()) {
					
					OutputStream os = response.getOutputStream();
					if(path.contains("qinpuchongchuan")) {
						//加水印
						// 图片大小和质量压缩
						Thumbnails.of(fileUrl).scale(scale).watermark(Positions.BOTTOM_RIGHT, getShuiYinPic(), 0.35f).outputQuality(quality).toOutputStream(os);
						response.setContentType("application/force-download");// 设置强制下载不打开
						response.addHeader("Content-Disposition", "attachment;fileName=" +
								path.substring(path.lastIndexOf("/") + 1, path.length()));// 设置文件名
					}else {
						//不加水印
						Thumbnails.of(fileUrl).scale(scale).outputQuality(quality).toOutputStream(os);
						response.setContentType("application/force-download");// 设置强制下载不打开
						response.addHeader("Content-Disposition", "attachment;fileName=" +
								path.substring(path.lastIndexOf("/") + 1, path.length()));// 设置文件名
					}

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}
	
    public static void main(String[] args) throws IOException {
    	PicDownloadController s = new PicDownloadController();
    	
        Thumbnails.of(new File("C:\\Users\\YuanSongGong\\Desktop\\qinzhijie\\文會堂琴譜封面.jpg")).scale(1f).
        watermark(Positions.BOTTOM_RIGHT,s.getShuiYinPic(), 0.25f)
        .outputQuality(0.8f).toFile("C:\\\\Users\\\\YuanSongGong\\\\Desktop\\\\qinzhijie\\test5.jpg");
    }
}
