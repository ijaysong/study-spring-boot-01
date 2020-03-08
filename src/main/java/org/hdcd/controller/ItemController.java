package org.hdcd.controller;

import java.io.File;
import java.net.URI;
import java.util.UUID;

import org.hdcd.domain.Item;
import org.hdcd.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/items")
public class ItemController {
	
	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	private ItemService itemService;
	
	@Value("${upload.path}")
	private String uploadPath;
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public ResponseEntity<Void> register(@RequestPart("item") String itemString, @RequestPart("file") MultipartFile picture, 
			UriComponentsBuilder uriBuilder) throws Exception {
		
		logger.info("itemString: " + itemString);
		
		Item item = new ObjectMapper().readValue(itemString, Item.class);
		
		String itemName = item.getItemName();
		String description = item.getDescription();
		
		if(itemName != null) {
			logger.info("item.getItemName(): " + itemName);
			item.setItemName(itemName);
		}
		
		if(description != null) {
			logger.info("item.getDescription(): " + description);
			item.setDescription(description);
		}
		
		item.setPicture(picture);
		
		MultipartFile file = item.getPicture();
		
		logger.info("originalName: " + file.getOriginalFilename());
		logger.info("size: " + file.getSize());
		logger.info("contentType: " + file.getContentType());
		
		String createdFileName = uploadFile(file.getOriginalFilename(), file.getBytes());
		
		item.setPictureUrl(createdFileName);
		
		this.itemService.regist(item);
		
		logger.info("register item.getItemId() = " + item.getItemId());
		
		URI resourceUri = uriBuilder.path("items/{itemId}").buildAndExpand(item.getItemId()).encode().toUri();
		
		return ResponseEntity.created(resourceUri).build();
	}
	
	private String uploadFile(String originalName, byte[] fileData) throws Exception {
		UUID uid = UUID.randomUUID();
		
		String createdFileName = uid.toString() + "_" + originalName;
		
		File target = new File(uploadPath, createdFileName);
		
		FileCopyUtils.copy(fileData, target);
		
		return createdFileName;
	}
}
