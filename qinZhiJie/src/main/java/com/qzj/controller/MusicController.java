package com.qzj.controller;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.qzj.commos.vo.ResponseData;
import com.qzj.dto.Music;
import com.qzj.dto.PageRequest;
import com.qzj.dto.PageResult;
import com.qzj.service.MusicService;

/**
 * 音乐
 * 
 * @author YuanSongGong
 *
 */
@RestController
@RequestMapping("/music")
public class MusicController extends BaseTgController {

	@Autowired
	private MusicService musicService;

	@SuppressWarnings("unchecked")
	//@RequestMapping(value = "/musicInsert", method = RequestMethod.GET)
	public void musicInsert(HttpServletRequest request) throws Exception {
		try {
			JSch jsch = new JSch();
			Session session = jsch.getSession("root", "120.79.158.236", 22);
			if (session == null) {
				throw new Exception("session is null");
			}
			session.setPassword("gys_123456");
			java.util.Properties config = new java.util.Properties();
			// 第一次登陆
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect(30000);
			ChannelSftp channel = (ChannelSftp) session.openChannel("sftp");
			channel.connect();
			Vector<?> v = channel.ls("/nas/qinzhijie_resource/music/yinyuechuli");
			Iterator<LsEntry> i = (Iterator<LsEntry>) v.listIterator();
			while (i.hasNext()) {
				LsEntry s = i.next();
				if (!s.getFilename().equals(".") && !s.getFilename().equals("..")) {
					Vector<?> sv = channel.ls("/nas/qinzhijie_resource/music/yinyuechuli/" + s.getFilename());
					Iterator<LsEntry> si = (Iterator<LsEntry>) sv.listIterator();
					while (si.hasNext()) {
						LsEntry ss = si.next();
						if (!ss.getFilename().equals(".") && !ss.getFilename().equals("..")) {
							// 替换掉数字
							System.out.println(ss.getFilename());
							if (ss.getFilename().contains("老八张的错误") || ss.getFilename().contains("Thumbs")
									|| ss.getFilename().contains("Creative Cloud Files")) {
								continue;
							}

							String sss = ss.getFilename().substring(0, ss.getFilename().lastIndexOf("."))
									.replaceAll("\\d+", "-") + ".mp3";
							// 表示是以数字开头，需要特殊处理
							if (sss.startsWith("-")) {
								// TODO
							} else {
								// 这里是数据入库的逻辑
								String zuozhe = s.getFilename();
								String zhuanji = sss.substring(sss.indexOf("-"), sss.lastIndexOf("-")).replace("- ", "")
										.trim();
								String quming = sss.substring(sss.lastIndexOf("-")).replace("-", "").trim();
								String url = "yinyuechuli/" + ss.getFilename();
								Music music = new Music();
								Date date = new Date();
								music.setName(quming);
								music.setMp3Url(url);
								music.setAlbumName(zhuanji);
								music.setMusican(zuozhe);
								music.setUpdateBy("sys");
								music.setCreateBy("sys");
								music.setCreateTime(date);
								music.setUpdateTime(date);
								musicService.insert(music);
							}
						}
					}
				}
			}
			channel.disconnect();
			session.disconnect();
			// 返回数据
		} catch (Exception e) {
			throw new Exception(e);
		}
	}
	
	@RequestMapping(value = "/getList", method = RequestMethod.POST)
	public ResponseData<PageResult<Music>> getList(@RequestBody PageRequest<Music> page, HttpServletRequest request) {
		ResponseData<PageResult<Music>> result = new ResponseData<PageResult<Music>>();
		PageResult<Music> re = musicService.getList(page);
		List<Music> list = re.getDataList();
		for(Music m : list) {
			m.setMp3Url("");
			m.setCreateTime(null);
			m.setUpdateTime(null);
		}
		result.setData(re);
		result.setCode("200");
		result.setMessage("Success");
		return result;
	}
	
	@RequestMapping(value = "/getListById", method = RequestMethod.POST)
	public ResponseData<PageResult<Music>> getListById(@RequestBody PageRequest<Music> page, HttpServletRequest request) {
		ResponseData<PageResult<Music>> result = new ResponseData<PageResult<Music>>();
		result.setData(musicService.getList(page));
		result.setCode("200");
		result.setMessage("Success");
		return result;
	}
}
