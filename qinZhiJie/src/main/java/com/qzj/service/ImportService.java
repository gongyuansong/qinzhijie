package com.qzj.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.qzj.commos.Constant;
import com.qzj.dao.BookDao;
import com.qzj.dao.BookDetailDao;
import com.qzj.dao.BookLyricDao;
import com.qzj.dao.FingeringDao;
import com.qzj.dao.MusicianDao;
import com.qzj.dto.Book;
import com.qzj.dto.BookDetail;
import com.qzj.dto.BookLyric;
import com.qzj.dto.Fingering;
import com.qzj.dto.Musician;
import com.qzj.dto.PageRequest;
import com.qzj.util.ImportExcel;
import com.qzj.util.WordUtil;

@Service
public class ImportService {

	@Autowired
	private MusicianDao musicianDao;

	@Autowired
	private BookDao bookDao;

	@Autowired
	private BookDetailDao bookDetailDao;

	@Autowired
	private BookLyricDao bookLyricDao;

	@Autowired
	private FingeringDao fingeringDao;

	@Value("${basePath}")
	private String basePath;

	public void importMusican() {
		String dirPath = "Z:\\01资料馆\\04琴百科\\琴人\\琴人名录";
		File dir = new File(dirPath);
		if (dir != null && dir.isDirectory()) {
			File[] fileList = dir.listFiles();
			Date date = new Date();
			for (int i = 0; i < fileList.length; i++) {
				File f = fileList[i];
				if (".txt".equals(f.getName().substring(f.getName().lastIndexOf(".")))) {
					Musician musician = new Musician();
					musician.setName(f.getName().substring(0, f.getName().lastIndexOf(".")));
					musician.setDesc(getStringFromFile(f));
					musician.setDescUrl(dirPath + "\\" + f.getName());
					musician.setUpdateBy(Constant.SYS_USER);
					musician.setCreateBy(Constant.SYS_USER);
					musician.setUpdateTime(date);
					musician.setCreateTime(date);
					musicianDao.add(musician);
				}

			}
		}
	}

	// 导入琴人整合这个文件夹里的数据
	public void ipmortMusicianPic1() {
		String dirPath = "Z:\\06听古琴\\琴人\\01琴人整合";
		File dir = new File(dirPath);
		if (dir != null && dir.isDirectory()) {
			File[] fileList = dir.listFiles();
			Date date = new Date();
			for (int i = 0; i < fileList.length; i++) {
				File f = fileList[i];
				if (".jpg".equals(f.getName().substring(f.getName().lastIndexOf(".")))) {
					Musician musician = new Musician();
					musician.setName(f.getName().substring(0, f.getName().lastIndexOf(".")));

					// 看当前这个人在数据库存在不
					PageRequest<Musician> page = new PageRequest<Musician>();
					page.setQueryObj(musician);
					List<Musician> list = musicianDao.getList(page);
					if (list == null || list.size() == 0) {
						// 如果数据库不存在就新增
						musician.setDesc("");
						musician.setDescUrl("");
						musician.setPicUrl1(dirPath + "\\" + f.getName());
						musician.setUpdateBy(Constant.SYS_USER);
						musician.setCreateBy(Constant.SYS_USER);
						musician.setUpdateTime(date);
						musician.setCreateTime(date);
						musicianDao.add(musician);
					} else {
						// 如果存在就更新
						musician = list.get(0);
						musician.setPicUrl1(dirPath + "\\" + f.getName());
						musician.setUpdateTime(new Date());
						musicianDao.update(musician);
					}
				}
			}
		}
	}

	// 导入(Z:\\06听古琴\\琴人\\800X800)对应的图片
	public void ipmortMusicianPic2() {
		String dirPath = "Z:\\06听古琴\\琴人\\800X800";
		File dir = new File(dirPath);
		if (dir != null && dir.isDirectory()) {
			File[] fileList = dir.listFiles();
			Date date = new Date();
			for (int i = 0; i < fileList.length; i++) {
				File f = fileList[i];
				if (".jpg".equals(f.getName().substring(f.getName().lastIndexOf(".")))) {
					Musician musician = new Musician();
					musician.setName(f.getName().substring(0, f.getName().lastIndexOf(".")));

					// 看当前这个人在数据库存在不
					PageRequest page = new PageRequest();
					page.setQueryObj(musician);
					List<Musician> list = musicianDao.getList(page);
					if (list == null || list.size() == 0) {
						// 如果数据库不存在就新增
						musician.setDesc("");
						musician.setDescUrl("");
						musician.setPicUrl2(dirPath + "\\" + f.getName());
						musician.setUpdateBy(Constant.SYS_USER);
						musician.setCreateBy(Constant.SYS_USER);
						musician.setUpdateTime(date);
						musician.setCreateTime(date);
						musicianDao.add(musician);
					} else {
						// 如果存在就更新
						musician = list.get(0);
						musician.setPicUrl2(dirPath + "\\" + f.getName());
						musician.setUpdateTime(new Date());
						musicianDao.update(musician);
					}
				}
			}
		}
	}

	// 导入(Z:\06听古琴\琴人\1200X1200)对应的图片
	public void ipmortMusicianPic3() {
		String dirPath = "Z:\\06听古琴\\琴人\\1200X1200";
		File dir = new File(dirPath);
		if (dir != null && dir.isDirectory()) {
			File[] fileList = dir.listFiles();
			Date date = new Date();
			for (int i = 0; i < fileList.length; i++) {
				File f = fileList[i];
				if (".jpg".equals(f.getName().substring(f.getName().lastIndexOf(".")))) {
					Musician musician = new Musician();
					musician.setName(f.getName().substring(0, f.getName().lastIndexOf(".")));

					// 看当前这个人在数据库存在不
					PageRequest page = new PageRequest();
					page.setQueryObj(musician);
					List<Musician> list = musicianDao.getList(page);
					if (list == null || list.size() == 0) {
						// 如果数据库不存在就新增
						musician.setDesc("");
						musician.setDescUrl("");
						musician.setPicUrl3(dirPath + "\\" + f.getName());
						musician.setUpdateBy(Constant.SYS_USER);
						musician.setCreateBy(Constant.SYS_USER);
						musician.setUpdateTime(date);
						musician.setCreateTime(date);
						musicianDao.add(musician);
					} else {
						// 如果存在就更新
						musician = list.get(0);
						musician.setPicUrl3(dirPath + "\\" + f.getName());
						musician.setUpdateTime(new Date());
						musicianDao.update(musician);
					}
				}
			}
		}
	}

	// 导入(Z:\06听古琴\琴人\1600X1600)对应的图片
	public void ipmortMusicianPic4() {
		String dirPath = "Z:\\06听古琴\\琴人\\1600X1600";
		File dir = new File(dirPath);
		if (dir != null && dir.isDirectory()) {
			File[] fileList = dir.listFiles();
			Date date = new Date();
			for (int i = 0; i < fileList.length; i++) {
				File f = fileList[i];
				if (".jpg".equals(f.getName().substring(f.getName().lastIndexOf(".")))) {
					Musician musician = new Musician();
					musician.setName(f.getName().substring(0, f.getName().lastIndexOf(".")));

					// 看当前这个人在数据库存在不
					PageRequest page = new PageRequest();
					page.setQueryObj(musician);
					List<Musician> list = musicianDao.getList(page);
					if (list == null || list.size() == 0) {
						// 如果数据库不存在就新增
						musician.setDesc("");
						musician.setDescUrl("");
						musician.setPicUrl4(dirPath + "\\" + f.getName());
						musician.setUpdateBy(Constant.SYS_USER);
						musician.setCreateBy(Constant.SYS_USER);
						musician.setUpdateTime(date);
						musician.setCreateTime(date);
						musicianDao.add(musician);
					} else {
						// 如果存在就更新
						musician = list.get(0);
						musician.setPicUrl4(dirPath + "\\" + f.getName());
						musician.setUpdateTime(new Date());
						musicianDao.update(musician);
					}
				}
			}
		}
	}

	// 导入(Z:\\06听古琴\\琴人)这里面的对应名字的文件夹的数据（就是除去上面1到4的文件夹的余下部分）
	public void ipmortMusicianPic5() {
		String dirPath = "Z:\\06听古琴\\琴人";
		File dir = new File(dirPath);
		if (dir != null && dir.isDirectory()) {
			File[] fileList = dir.listFiles();
			Date date = new Date();
			for (int i = 0; i < fileList.length; i++) {
				File f = fileList[i];
				if ("01琴人整合,800X800,1200X1200,1600X1600".contains(f.getName()) && !f.isDirectory()) {
					continue;
				}
				String name = "";
				if (f.getName().contains("-")) {
					name = f.getName().substring(0, f.getName().indexOf("-"));
				} else {
					name = f.getName();
				}
				File[] subFile = f.listFiles();
				StringBuilder sb = new StringBuilder();
				if (subFile != null && 0 != subFile.length) {
					for (int j = 0; j < subFile.length; j++) {
						if (subFile[j].getName().contains(".jpg") || subFile[j].getName().contains(".png")) {
							sb.append(subFile[j]).append(",");
						}
					}
				}
				Musician musician = new Musician();
				musician.setName(name);
				// 看当前这个人在数据库存在不
				PageRequest page = new PageRequest();
				page.setQueryObj(musician);
				List<Musician> list = musicianDao.getList(page);
				if (list == null || list.size() == 0) {
					// 如果数据库不存在就新增
					musician.setDesc("");
					musician.setDescUrl("");
					musician.setPicUrl5(sb.toString());
					musician.setUpdateBy(Constant.SYS_USER);
					musician.setCreateBy(Constant.SYS_USER);
					musician.setUpdateTime(date);
					musician.setCreateTime(date);
					musicianDao.add(musician);
				} else {
					// 如果存在就更新
					musician = list.get(0);
					musician.setPicUrl5(sb.toString());
					musician.setUpdateTime(new Date());
					musicianDao.update(musician);
				}
			}
		}
	}

	public String getStringFromFile(File file) {
		try {
			StringBuilder sb = new StringBuilder();
			if (file.isFile() && file.exists()) {// 文件存在
				InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GB2312");
				BufferedReader br = new BufferedReader(isr);
				String lineTxt = null;
				while ((lineTxt = br.readLine()) != null) {
					if (!"".equals(lineTxt)) {
						sb.append(lineTxt);
					}
				}
				isr.close();
				br.close();
			}
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void importBookAll() {
		List<String> list = new ArrayList<>();
		// 一下add的注释的都需要特殊处理
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\02第二册");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\03第三册 已審");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\05第五册");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\06第六册");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\07第七册");
		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\08册 审核OK 成龙");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\09第九册");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\10第十册");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\11第十一册");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\12第十二册");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\14第十四册");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\15第十五册");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\16册修整\\16第十六册");
		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\17第十七册");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\18第十八册");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\19第十九册");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\20第二十册");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\21第二十一册");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\22第二十二册");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\23第二十三册");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\24第二十四 已審完");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\25第二十五册");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\26第二十六册");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\27第二十七册");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\28 第二十八册");
		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\29第二十九册 已审核 成龙");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\30第三十册");
		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\31");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\31第三十一册琴谱合璧");
		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\32第三十二册 琴史");
		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\33第三十三册 琴學");
		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\34第三十四册");
		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\35第三十五册");
		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\36第三十六册\\琴学汇成");
		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\37第三十七册");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\38第三十八册");
		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\39第三十九册");
		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\40第四十册琴府");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\41第四十一册");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\42第四十二");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\43第四十三");
		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\古指法考---管平湖");
		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\碣石调幽兰");
		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\清湖琴谱");
		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\媛媛");

		for (String path : list) {
			ipmortBook(path);
		}
	}

	public void importBookAll1() {
		List<String> list = new ArrayList<>();
		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\08册 审核OK 成龙")
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\08册   审核OK  成龙\\1适琴  已审");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\08册   审核OK  成龙\\2松弦馆琴谱 已审");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\08册   审核OK  成龙\\3新传理怀元雅  已审");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\08册   审核OK  成龙\\4乐仙琴谱   已审");
		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\17第十七册");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\17第十七册");
		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\29第二十九册 已审核 成龙");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\29第二十九册  已审核  成龙\\2十一絃館琴谱 已审核 上传 归类");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\29第二十九册  已审核  成龙\\3琴学摘要  已审核");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\29第二十九册  已审核  成龙\\4梅盦琴譜  已审核");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\29第二十九册  已审核  成龙\\5琴学管见  已审核");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\29第二十九册  已审核  成龙\\6琴学易知  已审核");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\29第二十九册  已审核  成龙\\7琴学秘诀  已审核");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\29第二十九册  已审核  成龙\\8沙堰琴编");
		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\31");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\31\\太古正音琴经\\太古正音琴经");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\31\\太古正音琴谱");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\31\\永乐琴书集成二");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\31\\永乐琴书集成三");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\31\\永乐琴书集成四");
		importBook1(basePath + "01资料馆\\03历代琴谱\\02修图完成\\31\\永乐琴书集成一第二部分", "永乐琴书集成一");
		importBook1(basePath + "01资料馆\\03历代琴谱\\02修图完成\\31\\永乐琴书集成一第一部分", "永乐琴书集成一");

		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\32第三十二册 琴史");
		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\33第三十三册 琴學");
		importBook1(basePath + "01资料馆\\03历代琴谱\\02修图完成\\32第三十二册 琴史", "琴史");
		importBook1(basePath + "01资料馆\\03历代琴谱\\02修图完成\\33第三十三册 琴學", "琴學");

		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\34第三十四册");
		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\35第三十五册");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\34第三十四册\\学海类编中的琴言十则及指法");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\35第三十五册");
		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\36第三十六册\\琴学汇成");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\36第三十六册\\琴学汇成\\1琴学汇成一");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\36第三十六册\\琴学汇成\\2琴学汇成二");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\36第三十六册\\琴学汇成\\3琴学汇成三");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\36第三十六册\\琴学汇成\\4琴学汇成四");
		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\37第三十七册");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\37第三十七册\\三馀斋琴铭");
		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\39第三十九册");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\39第三十九册\\琴翼");
		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\40第四十册琴府");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\40第四十册琴府\\修\\01白石道人诗词合集");
		importBook2(basePath + "01资料馆\\03历代琴谱\\02修图完成\\40第四十册琴府\\修\\02白石道人歌曲之古怨（张刻本）", "白石道人歌曲之古怨");
		importBook2(basePath + "01资料馆\\03历代琴谱\\02修图完成\\40第四十册琴府\\修\\03白石道人歌曲之古怨（榆园丛刻本）", "白石道人歌曲之古怨");
		importBook2(basePath + "01资料馆\\03历代琴谱\\02修图完成\\40第四十册琴府\\修\\04白石道人歌曲之古怨---江炳炎序（疆村丛书本）", "白石道人歌曲之古怨");
		importBook2(basePath + "01资料馆\\03历代琴谱\\02修图完成\\40第四十册琴府\\修\\05白石道人歌曲之古怨跋（疆村丛书本）", "白石道人歌曲之古怨");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\40第四十册琴府\\修\\06太古遗音卷之一----斫琴");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\40第四十册琴府\\修\\07太古遗音卷之一----琴制");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\40第四十册琴府\\修\\08太古遗音卷之二----历代琴式");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\40第四十册琴府\\修\\09太古遗音卷之三");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\40第四十册琴府\\修\\09太古遗音卷之四");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\40第四十册琴府\\修\\10太古遗音卷之五");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\40第四十册琴府\\修\\11龙湖琴谱");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\40第四十册琴府\\太古遗音注");
		// TODO
		// 太乱了，暂时没有搞上去
		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\古指法考---管平湖");

		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\碣石调幽兰");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\碣石调幽兰");
		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\清湖琴谱");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\清湖琴谱");
		// 特殊处理 list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\媛媛");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\媛媛\\琴苑要录");
		list.add(basePath + "01资料馆\\03历代琴谱\\02修图完成\\媛媛\\与古斋琴谱");
		for (String path : list) {
			importBook1(path, null);
		}
	}

	public void importBook2(String partPath, String bookName) {

		File f = new File(partPath);
		Date date = new Date();
		int maxId = bookDao.selectMaxId();
		Book book = new Book();
		book.setId(new Long(maxId + 1));
		book.setBookName(bookName);
		book.setDirPath(f.getAbsolutePath().replace(basePath, ""));
		book.setUpdateBy(Constant.SYS_USER);
		book.setCreateBy(Constant.SYS_USER);
		book.setUpdateTime(date);
		book.setCreateTime(date);

		HashMap<String, Object> m = new HashMap<>();
		m.put("bookName", bookName);
		List<Book> l = bookDao.selectList(m);
		if (l != null && 0 != l.size()) {
			book = l.get(0);
		} else {
			// 插入book数据
			bookDao.add(book);
		}

		if (f.isDirectory()) {
			String partName = partPath.substring(partPath.lastIndexOf("\\") + 1, partPath.length())
					.replaceAll("\\d+", "").trim();
			BookDetail bookDetail = new BookDetail();
			bookDetail.setBookId(book.getId().intValue());
			bookDetail.setDirPath(f.getAbsolutePath().replace(basePath, ""));
			bookDetail.setPartName(partName);
			bookDetail.setOrderNum(getNumFromStr(partName));
			bookDetail.setUpdateBy(Constant.SYS_USER);
			bookDetail.setCreateBy(Constant.SYS_USER);
			bookDetail.setUpdateTime(date);
			bookDetail.setCreateTime(date);
			try {
				// 插入bookDetail数据
				bookDetailDao.add(bookDetail);
			} catch (Exception e) {
				// 这个路径导入有误：
				// 01资料馆\\03历代琴谱\\02修图完成\\20第二十册\\4指法滙参确解\\53法滙参确解 ---绒৯法
				System.out.println("这个路径导入有误：" + bookDetail.getDirPath());
				e.printStackTrace();
			}
		}

	}

	public void importBook1(String path, String bookName) {

		File sub = new File(path);

		if (bookName == null) {
			bookName = path.substring(path.lastIndexOf("\\") + 1, path.length()).replaceAll("\\d+", "").trim();
		}

		Date date = new Date();
		int maxId = bookDao.selectMaxId();
		Book book = new Book();
		book.setId(new Long(maxId + 1));
		book.setBookName(bookName);
		book.setDirPath(sub.getAbsolutePath().replace(basePath, ""));
		book.setUpdateBy(Constant.SYS_USER);
		book.setCreateBy(Constant.SYS_USER);
		book.setUpdateTime(date);
		book.setCreateTime(date);

		HashMap<String, Object> m = new HashMap<>();
		m.put("bookName", bookName);
		List<Book> l = bookDao.selectList(m);
		if (l != null && 0 != l.size()) {
			book = l.get(0);
		} else {
			// 插入book数据
			bookDao.add(book);
		}
		if (sub.isDirectory()) {
			for (String partPath : sub.list()) {
				File f = new File(path + "\\" + partPath);
				if (f.isDirectory()) {

					String partName = "";
					if (partPath.contains("-")) {
						partName = partPath.substring(partPath.lastIndexOf("-") + 1, partPath.length()).trim();
					} else {
						partName = partPath.replaceAll("\\d", "");
					}
					// 如果partName为空就用原来的
					if (StringUtils.isBlank(partName)) {
						partName = partPath.substring(partPath.lastIndexOf("-") + 1, partPath.length()).trim();
					}
					BookDetail bookDetail = new BookDetail();
					bookDetail.setBookId(book.getId().intValue());
					bookDetail.setDirPath(f.getAbsolutePath().replace(basePath, ""));
					bookDetail.setPartName(partName);
					bookDetail.setOrderNum(getNumFromStr(partPath));
					bookDetail.setUpdateBy(Constant.SYS_USER);
					bookDetail.setCreateBy(Constant.SYS_USER);
					bookDetail.setUpdateTime(date);
					bookDetail.setCreateTime(date);
					try {
						// 插入bookDetail数据
						bookDetailDao.add(bookDetail);
					} catch (Exception e) {
						// 这个路径导入有误：
						// 01资料馆\\03历代琴谱\\02修图完成\\20第二十册\\4指法滙参确解\\53法滙参确解 ---绒৯法
						System.out.println("这个路径导入有误：" + bookDetail.getDirPath());
						e.printStackTrace();
					}
				} else if (f.isFile()) {
					BookDetail bookDetail = new BookDetail();
					bookDetail.setBookId(book.getId().intValue());
					bookDetail.setDirPath(f.getAbsolutePath().replace(basePath, ""));
					bookDetail.setPartName(partPath);
					bookDetail.setOrderNum(-1);
					bookDetail.setUpdateBy(Constant.SYS_USER);
					bookDetail.setCreateBy(Constant.SYS_USER);
					bookDetail.setUpdateTime(date);
					bookDetail.setCreateTime(date);
					try {
						// 插入bookDetail数据
						bookDetailDao.add(bookDetail);
					} catch (Exception e) {
						// 这个路径导入有误：
						// 01资料馆\\03历代琴谱\\02修图完成\\20第二十册\\4指法滙参确解\\53法滙参确解 ---绒৯法
						System.out.println("这个路径导入有误：" + bookDetail.getDirPath());
						e.printStackTrace();
					}
				}
			}
		}
	}

	// 将 （G:\工作2019\01资料馆\03历代琴谱\02修图完成）导入到数据库,分成两步骤
	// 第一：先导入到book中
	// 第二：在导入每本书中的所有章节
	public void ipmortBook(String path) {
		File p = new File(path);
		if (!p.exists() || !p.isDirectory()) {
			System.out.println("路径有问题");
		}
		Date date = new Date();
		for (String subPath : p.list()) {
			File sub = new File(path + "\\" + subPath);

			String bookName = subPath.replaceAll("\\d+", "").trim();

			int maxId = bookDao.selectMaxId();
			Book book = new Book();
			book.setId(new Long(maxId + 1));
			book.setBookName(bookName);
			book.setDirPath(sub.getAbsolutePath().replace(basePath, ""));
			book.setUpdateBy(Constant.SYS_USER);
			book.setCreateBy(Constant.SYS_USER);
			book.setUpdateTime(date);
			book.setCreateTime(date);

			HashMap<String, Object> m = new HashMap<>();
			m.put("bookName", bookName);
			List<Book> l = bookDao.selectList(m);
			if (l != null && 0 != l.size()) {
				book = l.get(0);
			} else {
				// 插入book数据
				bookDao.add(book);
			}
			if (sub.isDirectory()) {
				for (String partPath : sub.list()) {
					File f = new File(path + "\\" + subPath + "\\" + partPath);
					if (f.isDirectory()) {

						String partName = "";
						if (partPath.contains("-")) {
							partName = partPath.substring(partPath.lastIndexOf("-") + 1, partPath.length()).trim();
						} else {
							partName = partPath.replaceAll("\\d", "");
						}
						BookDetail bookDetail = new BookDetail();
						bookDetail.setBookId(book.getId().intValue());
						bookDetail.setDirPath(f.getAbsolutePath().replace(basePath, ""));
						bookDetail.setPartName(partName);
						bookDetail.setOrderNum(getNumFromStr(partPath));
						bookDetail.setUpdateBy(Constant.SYS_USER);
						bookDetail.setCreateBy(Constant.SYS_USER);
						bookDetail.setUpdateTime(date);
						bookDetail.setCreateTime(date);
						try {
							// 插入bookDetail数据
							bookDetailDao.add(bookDetail);
						} catch (Exception e) {
							// 这个路径导入有误：
							// 01资料馆\\03历代琴谱\\02修图完成\\20第二十册\\4指法滙参确解\\53法滙参确解 ---绒৯法
							System.out.println("这个路径导入有误：" + bookDetail.getDirPath());
							e.printStackTrace();
						}
					} else if (f.isFile()) {
						BookDetail bookDetail = new BookDetail();
						bookDetail.setBookId(book.getId().intValue());
						bookDetail.setDirPath(f.getAbsolutePath().replace(basePath, ""));
						bookDetail.setPartName(partPath);
						bookDetail.setOrderNum(-1);
						bookDetail.setUpdateBy(Constant.SYS_USER);
						bookDetail.setCreateBy(Constant.SYS_USER);
						bookDetail.setUpdateTime(date);
						bookDetail.setCreateTime(date);
						try {
							// 插入bookDetail数据
							bookDetailDao.add(bookDetail);
						} catch (Exception e) {
							// 这个路径导入有误：
							// 01资料馆\\03历代琴谱\\02修图完成\\20第二十册\\4指法滙参确解\\53法滙参确解 ---绒৯法
							System.out.println("这个路径导入有误：" + bookDetail.getDirPath());
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	// 获取字符串中的数字
	public Integer getNumFromStr(String str) {
		String[] list = str.split("-");
		str = list[0];
		String regEx = "[^0-9]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		String returnStr = m.replaceAll("").trim();
		Integer returnNum = new Integer(StringUtils.isBlank(returnStr) ? "-1" : returnStr);
		if (returnNum == 0) {
			returnNum = -1;
		}
		return returnNum;
	}

	// 更新年代到历代琴谱上去
	public void updateTimeTOBook() {
		try {
			String path = basePath + "01资料馆\\03历代琴谱\\时间轴.xlsx";
			ImportExcel poi = new ImportExcel();
			List<List<String>> list = poi.read(path);
			HashMap<String, Object> m = new HashMap<>();
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					List<String> cellList = list.get(i);
					String bookName = cellList.get(0);
					String yearStr = cellList.get(1);
					String dynasty = cellList.get(2);
					String author = cellList.get(3);
					m.put("bookName", bookName.trim());
					List<Book> l = bookDao.selectList(m);
					if (l != null && 0 != l.size()) {
						Book book = l.get(0);
						if (yearStr.contains(".0")) {
							yearStr = yearStr.replaceAll(".0", "");
						}
						book.setYear(yearStr);
						book.setDynasty(dynasty);
						book.setAuthor(author);
						book.setOrderNum(i);
						book.setUpdateBy(Constant.SYS_USER);
						book.setUpdateTime(new Date());
						bookDao.update(book);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 将历代琴谱的每个图片都写到bookDetail的allPath字段中
	public void importAllPath() {
		PageRequest<BookDetail> page = new PageRequest<BookDetail>();
		BookDetail bb = new BookDetail();
		page.setQueryObj(bb);
		long total = bookDetailDao.getTotal(page);
		page.setPageSize(total);
		List<BookDetail> list = bookDetailDao.getList(page);
		for (BookDetail b : list) {
			StringBuilder sb = new StringBuilder();
			String path = b.getDirPath();
			File f = new File(basePath + path);
			if (f.isDirectory()) {
				String[] l = f.list();
				for (String s : l) {
					sb.append(path + "\\" + s + ";");
				}
				if (sb.length() > 0) {
					b.setAllPath(sb.substring(0, sb.length() - 1));
					bookDetailDao.update(b);
				}
			}
		}
	}

	public void ipmortBookLyric() throws IOException {
		try {
			ipmortBookLyric1("01资料馆\\02琴曲--歌词、解意\\1 一畫", 1);
			ipmortBookLyric1("01资料馆\\02琴曲--歌词、解意\\2  二畫", 2);
			ipmortBookLyric1("01资料馆\\02琴曲--歌词、解意\\3  三畫", 3);
			ipmortBookLyric1("01资料馆\\02琴曲--歌词、解意\\4  四畫", 4);
			ipmortBookLyric1("01资料馆\\02琴曲--歌词、解意\\5  五畫", 5);
			ipmortBookLyric1("01资料馆\\02琴曲--歌词、解意\\6  六畫", 6);
			ipmortBookLyric1("01资料馆\\02琴曲--歌词、解意\\7  七畫", 7);
			ipmortBookLyric1("01资料馆\\02琴曲--歌词、解意\\8  八畫", 8);
			ipmortBookLyric1("01资料馆\\02琴曲--歌词、解意\\9  九畫", 9);
			ipmortBookLyric1("01资料馆\\02琴曲--歌词、解意\\10  十畫 ok", 10);
			ipmortBookLyric1("01资料馆\\02琴曲--歌词、解意\\11  十一畫 已传", 11);
			ipmortBookLyric1("01资料馆\\02琴曲--歌词、解意\\12  十二畫 已传", 12);
			ipmortBookLyric1("01资料馆\\02琴曲--歌词、解意\\13  十三畫 已传", 13);
			ipmortBookLyric1("01资料馆\\02琴曲--歌词、解意\\14  十四畫 已传", 14);
			ipmortBookLyric1("01资料馆\\02琴曲--歌词、解意\\15  十五畫 已传", 15);
			ipmortBookLyric1("01资料馆\\02琴曲--歌词、解意\\16   十六畫 已传", 16);
			ipmortBookLyric1("01资料馆\\02琴曲--歌词、解意\\17  十七畫 已传", 17);
			ipmortBookLyric1("01资料馆\\02琴曲--歌词、解意\\18  十八畫 已传", 18);
			ipmortBookLyric1("01资料馆\\02琴曲--歌词、解意\\19  十九畫 已传", 19);
			ipmortBookLyric1("01资料馆\\02琴曲--歌词、解意\\20  二十畫 已传", 20);
			ipmortBookLyric1("01资料馆\\02琴曲--歌词、解意\\21  二十一畫 已传", 21);
			ipmortBookLyric1("01资料馆\\02琴曲--歌词、解意\\22  二十二畫 已传", 22);
			ipmortBookLyric1("01资料馆\\02琴曲--歌词、解意\\23  二十三畫 已传", 23);
			ipmortBookLyric1("01资料馆\\02琴曲--歌词、解意\\30  三十畫 已传", 30);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void ipmortBookLyric1(String path, int strokes) throws IOException {
		File f = new File(basePath + path);
		if (f != null && f.isDirectory()) {
			Date date = new Date();
			for (String subPath : f.list()) {
				StringBuilder sb = new StringBuilder();
				File sub = new File(basePath + path + "\\" + subPath);
				if (sub.isFile() && f.exists()) {
					InputStreamReader isr = new InputStreamReader(new FileInputStream(sub), "GBK");
					BufferedReader br = new BufferedReader(isr);
					String lineTxt = null;
					while ((lineTxt = br.readLine()) != null) {
						sb.append(lineTxt);
					}
					br.close();
					String name = "";
					if (sb.length() != 0 && sb.toString().contains("曲名")) {
						String desc = sb.toString();
						System.out.println(sub.getAbsolutePath());
						name = desc.substring(desc.indexOf("：") + 1, desc.indexOf("：", desc.indexOf("：") + 1));
						name = name.replaceAll("時期", "").replaceAll("时期", "");
					} else {
						name = subPath.replace(".txt", "").replaceAll("ok", "").trim();
					}
					BookLyric bookLyric = new BookLyric();
					bookLyric.setMusicofviolin(name);
					bookLyric.setDesc(sb.toString());
					bookLyric.setStrokes(strokes);
					bookLyric.setUrl(path + "\\" + subPath);
					bookLyric.setUpdateBy(Constant.SYS_USER);
					bookLyric.setCreateBy(Constant.SYS_USER);
					bookLyric.setUpdateTime(date);
					bookLyric.setCreateTime(date);
					bookLyricDao.add(bookLyric);
				}
			}
		}
	}

	public void ipmortFingering() throws IOException {

		String path = "01资料馆\\02指法词典\\谱字释义";
		File f = new File(basePath + path);
		if (f != null && f.isDirectory()) {
			Date date = new Date();
			for (String subPath : f.list()) {
				File sub = new File(basePath + path + "\\" + subPath);
				if (f != null && f.isDirectory()) {
					for (String subsubPath : sub.list()) {
						if (!subsubPath.contains("$") && subsubPath.endsWith(".doc")) {
							String url = basePath + path + "\\" + subPath + "\\" + subsubPath;
							String content = "";
							try {
								content = WordUtil.readWord2003(url);
							} catch (Exception e) {
								content = WordUtil.readWord2007(url);
							}
							if (StringUtils.isBlank(content)) {
								System.out.println("这个url：（" + url + "）读取有误");
								continue;
							}
							String name = subsubPath.replace(".doc", "").replace("ok", "").replace(" 完", "").trim();
							System.out.println(url);
							while (true) {
								if (content.contains("\"_blank\"")) {
									content = content
											.replace(content.substring(content.indexOf(""), content.indexOf("") + 1),
													"")
											.replace("", "");
								} else {
									break;
								}
							}
							Fingering fingering = new Fingering();
							fingering.setName(name);
							fingering.setContent(content.trim());
							fingering.setFileUrl(path + "\\" + subPath + "\\" + subsubPath);
							fingering.setPaintings(getNumFromStr(subPath));
							fingering.setUpdateBy(Constant.SYS_USER);
							fingering.setCreateBy(Constant.SYS_USER);
							fingering.setUpdateTime(date);
							fingering.setCreateTime(date);
							fingeringDao.add(fingering);
						}
					}
				}
			}
		}
	}

	/**
	 * 这里最新的bookDetal 的数据导入
	 * 
	 * 
	 */
	public void insertBookDetail() {
		ChannelSftp sftp = null;
		Session session = null;
		try {
			String host = "120.79.158.236";
			int port = 22;
			String username = "root";
			String password = "gys_123456";
			// 创建JSch
			JSch jSch = new JSch();
			// 获取session
			java.util.Properties prop = new java.util.Properties();
			prop.put("StrictHostKeyChecking", "no");
			session = jSch.getSession(username, host, port);
			session.setConfig(prop);
			session.setPassword(password);

			// 启动连接
			session.connect();
			sftp = (ChannelSftp) session.openChannel("sftp");

			sftp.connect();
			// 获得书籍目录
			Vector vector = sftp.ls("//root//qinpuchongchuan");
			Iterator iterator = vector.iterator();
			while (iterator.hasNext()) {
				ChannelSftp.LsEntry file = (ChannelSftp.LsEntry) iterator.next();
				String bookName = file.getFilename();
				if (bookName.equals(".") || bookName.equals("..") || bookName.equals(".wps")) {
					continue;
				}
				String bookpath = "/root/qinpuchongchuan/" + bookName;
				System.out.println("--------------------------");
				System.out.println("@@@@@@@@@@@@@@@@" + bookpath);

				// 获取boodetail目录

				Vector boodetailvector = sftp.ls(bookpath);
				Iterator boodetailiterator = boodetailvector.iterator();
				while (boodetailiterator.hasNext()) {
					ChannelSftp.LsEntry boodetailfile = (ChannelSftp.LsEntry) boodetailiterator.next();
					String bookdetailfileName = boodetailfile.getFilename();
					if (bookdetailfileName.equals(".") || bookdetailfileName.equals("..")
							|| bookdetailfileName.endsWith(".wps") || bookdetailfileName.endsWith(".db") || bookdetailfileName.endsWith(".psd")) {
						continue;
					}
					String boodetailfilepath = "/root/qinpuchongchuan/" + bookName + "/" + bookdetailfileName;

					// 获取 章节名字
					String zhangjiemingzhi = bookdetailfileName.substring(bookdetailfileName.lastIndexOf("-") + 1,
							bookdetailfileName.length());
					//TODO 这里乱码 需要处理
					if("绒𧯞法".contains(zhangjiemingzhi)) {
						continue;
					}
					// 获得排序order 和 sub 排序order
					char[] array = bookdetailfileName.toCharArray();
					char lastnumber = 'a';
					for (int i = 0; i < array.length; i++) {
						int num = (int) array[i];
						if (num > 47 && num < 58) {
							lastnumber = array[i];
						}
					}
					//TODO
					if(bookdetailfileName.contains("太古正音琴经")) {
						continue;
						
					}
					
					System.out.println("qqss:" + bookdetailfileName);
					String orders = bookdetailfileName.substring(0, bookdetailfileName.lastIndexOf(lastnumber) + 1);
					System.out.println("aaa: " + orders);
					String[] orderArray = new String[]{};	
					if(orders.contains("-")) {
						orderArray = orders.split("-");	
					}else if(orders.contains(".")){
						orderArray = orders.split(".");	
					}else {
						orderArray = orders.split("@");	
					}
					if(orderArray.length > 0){
						System.out.println("www1ww:" + orderArray[0]);						
					}
					if(orderArray.length > 1){
						System.out.println("www2ww:" + orderArray[1]);						
					}
					//获取所有章节的图片url集合
					Vector urlvector = sftp.ls(boodetailfilepath);
					Iterator urlIterator = urlvector.iterator();
					StringBuffer sb = new StringBuffer();
					while (urlIterator.hasNext()) {
						ChannelSftp.LsEntry url = (ChannelSftp.LsEntry) urlIterator.next();
						String u = url.getFilename();
						if (u.equals(".") || u.equals("..")
								|| u.endsWith(".wps") || u.endsWith(".db")) {
							continue;
						}
						sb.append(boodetailfilepath).append("/").append(u).append(";");
					}
					String urlarray = "";
					if(sb.length() != 0) {
						urlarray = sb.substring(0, sb.length()-1);
					}
					//插入数据库
					HashMap<String, Object> m = new HashMap<>();
					m.put("bookName", bookName.trim());
					List<Book> l = bookDao.selectList(m);
					Date date = new Date();
					BookDetail bookDetail = new BookDetail();
					if(l.size() != 1) {
						System.out.println("这本书有问题：" + bookName);
					}else {
						bookDetail.setBookId(l.get(0).getId().intValue());
					}
					bookDetail.setDirPath(boodetailfilepath);
					bookDetail.setPartName(zhangjiemingzhi);
					if(orderArray.length > 0){
						bookDetail.setOrderNum(new Integer(orderArray[0]));				
					}
					if(orderArray.length > 1){
						
						//获取子order
						Integer suboOrder = 0;
						for(int i = 1 ; i< orderArray.length ; i ++ ) {
							try {
								System.out.println(orderArray[i]);
								suboOrder = new Integer(orderArray[i]);
								break;
							}catch(Exception e) {
							}
						}
						
						bookDetail.setSubOrderNum(suboOrder);						
					}
					bookDetail.setAllPath(urlarray);
					bookDetail.setUpdateBy(Constant.SYS_USER);
					bookDetail.setCreateBy(Constant.SYS_USER);
					bookDetail.setUpdateTime(date);
					bookDetail.setCreateTime(date);
					bookDetail.setStatus(1);
					//System.out.println("----" + bookDetail);
					bookDetailDao.add(bookDetail);
					
						
				}
				System.out.print("--------------------------");
			}

			// getSubFile("//root//qinpuchongchuan", sftp);
		} catch (Exception e) {
			e.printStackTrace();
			if (sftp != null) {
				sftp.disconnect();
			}
			if (session != null) {
				session.disconnect();
			}
		}

		System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
	}

	public static void getSubFile(String pathath, ChannelSftp sftp) throws Exception {

		Vector vector = sftp.ls(pathath);
		Iterator iterator = vector.iterator();
		while (iterator.hasNext()) {
			ChannelSftp.LsEntry file = (ChannelSftp.LsEntry) iterator.next();
			String fileName = file.getFilename();
			if (fileName.equals(".") || fileName.equals("..") || fileName.equals(".wps")) {
				continue;
			}
			if (file.getAttrs().isDir()) {
				getSubFile(pathath + "//" + fileName + "//", sftp);
				System.out.println(" --" + pathath + fileName);
			} else {
				System.out.println(" ------------" + pathath + "//" + fileName);
			}
		}

	}

	public void ipmortRemotePic() throws IOException {

		// 先链接linux服务器

		String path = "01资料馆\\02指法词典\\谱字释义";
		File f = new File(basePath + path);
		if (f != null && f.isDirectory()) {

		}
	}

	public static void main(String[] args) {
		try {
			String bookdetailfileName = "040琴谱合璧一----杨伦像";
			// 获得排序order 和 sub 排序order
			char[] array = bookdetailfileName.toCharArray();
			char lastnumber = 'a';
			for (int i = 0; i < array.length; i++) {
				int num = (int) array[i];
				if (num > 47 && num < 58) {
					lastnumber = array[i];
				}
			}
			String orders = bookdetailfileName.substring(0, bookdetailfileName.lastIndexOf(lastnumber) + 1);
			System.out.println(orders);
			String[] orderArray = new String[]{};	
			if(orders.contains("-")) {
				orderArray = orders.split("-");	
			}else if(orders.contains(".")){
				orderArray = orders.split(".");	
			}else {
				orderArray = orders.split("@");	
			}
			if(orderArray.length > 0){
				System.out.println("wwwww:" + orderArray[0]);						
			}
			if(orderArray.length > 1){
				System.out.println("wwwww:" + orderArray[1]);						
			}
			//getConnection();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
