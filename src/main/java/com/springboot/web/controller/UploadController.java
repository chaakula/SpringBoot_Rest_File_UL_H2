package com.springboot.web.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.web.model.MetaInfoModel;
import com.springboot.web.service.MetaInfoService;

/**
 * File upload controller
 * @author Chandu A
 *
 */
@Controller
@SessionAttributes("name")
public class UploadController {

	@Autowired
	MetaInfoService service;

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file) {

		String name = file.getOriginalFilename();
		if (!file.isEmpty()) {
			try {
				byte[] bytes = file.getBytes();
				UUID idOne = UUID.randomUUID();
				name = idOne + "_" + name;
				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(new File("upload" + File.separator + name)));
				stream.write(bytes);
				stream.close();

				MetaInfoModel metaInfo = new MetaInfoModel();
				metaInfo.setModifiedDate(new Date());
				metaInfo.setFileName(name);
				// instead we need to take login person user name
				metaInfo.setUser("admin");

				service.updateRecords(metaInfo);

				return "<br /><center>You successfully uploaded <b>" + file.getOriginalFilename()
						+ "</b>  as  <font color=\"green\"><b>" + name + "</b></font> !</center>";
			} catch (Exception e) {
				return "You failed to upload " + name + " => " + e.getMessage();
			}
		} else {
			return "You failed to upload " + name + " because the file was empty.";
		}
	}
}