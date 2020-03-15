package org.hdcd.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.hdcd.domain.Item;
import org.hdcd.service.ItemService;
import org.hdcd.util.UploadFileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	// 이미지 업로드
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
	
	@RequestMapping("/display")
	public ResponseEntity<byte[]> displayFile(int itemId) throws Exception {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		String fileName = itemService.getPicture(itemId);
		
		logger.info("FILE NAME: " + fileName);
		
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			MediaType mType = getMediaType(formatName);
			
			HttpHeaders headers = new HttpHeaders();
			
			in = new FileInputStream(uploadPath + File.separator + fileName);
			
			if(mType != null) {
				headers.setContentType(mType);
			}
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		return entity;
	}
	
	private MediaType getMediaType(String formatName) {
		if(formatName != null) {
			if(formatName.equals("JPG")) {
				return MediaType.IMAGE_JPEG;
			}
			
			if(formatName.equals("GIF")) {
				return MediaType.IMAGE_GIF;
			}
			
			if(formatName.equals("PNG")) {
				return MediaType.IMAGE_PNG;
			}
		}
		return null;
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity<List<Item>> list() throws Exception {
		logger.info("list");
		List<Item> itemList = this.itemService.list();
		
		return new ResponseEntity<>(itemList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{itemId}", method=RequestMethod.GET)
	public ResponseEntity<Item> read(@PathVariable("itemId") int itemId) throws Exception {
		logger.info("read");
		
		Item item = this.itemService.read(itemId);
		
		return new ResponseEntity<>(item, HttpStatus.OK);
	}
	
	@RequestMapping(value="/{itemId}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> remove(@PathVariable("itemId") int itemId) throws Exception {
		logger.info("remove");
		
		this.itemService.remove(itemId);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="", method=RequestMethod.PUT)
	public ResponseEntity<Void> modify(@RequestPart("item") String itemString, @RequestPart("file") MultipartFile picture) throws Exception {
		logger.info("itemString: " + itemString);
		
		Item item = new ObjectMapper().readValue(itemString, Item.class);
		
		String itemName = item.getItemName();
		String description = item.getDescription();
		
		if(itemName != null) {
			logger.info("item.getItemName(): " + itemName);
			
			item.setItemName(itemName);
		}
		
		if(description != null) {
			logger.info("item.getDescription()" + description);
			
			item.setDescription(description);
		}
		
		item.setPicture(picture);
		
		MultipartFile file = item.getPicture();
		
		logger.info("originalName: " + file.getOriginalFilename());
		logger.info("size: " + file.getSize());
		logger.info("contentType: " + file.getContentType());
		
		String createdFileName = uploadFile(file.getOriginalFilename(), file.getBytes());
		
		item.setPictureUrl(createdFileName);
		
		this.itemService.modify(item);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	// 여러개의 이미지 업로드
	@RequestMapping(value="/multiple", method=RequestMethod.POST)
	public ResponseEntity<String> registerMultiple(@RequestPart("item") String itemString, @RequestPart("file")MultipartFile picture, 
			@RequestPart("file2") MultipartFile picture2, UriComponentsBuilder uriBuilder) throws Exception {
		logger.info("itemString: " + itemString);
		
		Item item = new ObjectMapper().readValue(itemString, Item.class);
		
		String itemName = item.getItemName();
		String description = item.getDescription();
		
		if(itemName != null) {
			logger.info("item.getItemName():" + itemName);
			
			item.setItemName(itemName);
		}
		
		if(description != null) {
			logger.info("item.getDescription():" + description);
			
			item.setDescription(description);
		}
		
		List<MultipartFile> pictures = new ArrayList<MultipartFile>();
		pictures.add(picture);
		pictures.add(picture2);
		
		item.setPictures(pictures);
		
		for(int i = 0; i < pictures.size(); i++) {
			MultipartFile file = pictures.get(i);
			
			logger.info("originalName:" + file.getOriginalFilename());
			logger.info("size:" + file.getSize());
			logger.info("contentType:" + file.getContentType());
			
			String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());
			
			if(i == 0) {
				item.setPictureUrl(savedName);
			} else if(i == 1) {
				item.setPictureUrl2(savedName);
			}
		}
		
		this.itemService.registMultiple(item);
		
		logger.info("register item.getItemId() = " + item.getItemId());
		
		URI resourceUri = uriBuilder.path("items/{itemId}").buildAndExpand(item.getItemId()).encode().toUri();
		
		return ResponseEntity.created(resourceUri).build();
	}
	
	@RequestMapping(value="/multiple", method=RequestMethod.GET)
	public ResponseEntity<List<Item>> listMultiple() throws Exception {
		logger.info("list");
		List<Item> itemList = this.itemService.listMultiple();
		
		return new ResponseEntity<>(itemList, HttpStatus.OK);
	}
	
	@RequestMapping(value="/multiple/{itemId}", method=RequestMethod.GET)
	public ResponseEntity<Item> readMultiple(@PathVariable("itemId") int itemId) throws Exception {
		logger.info("read");
		
		Item item = this.itemService.readMultiple(itemId);
		
		return new ResponseEntity<>(item, HttpStatus.OK);
	}
	
	@RequestMapping(value="/multiple/{itemId}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> removeMultiple(@PathVariable("itemId") int itemId) throws Exception {
		logger.info("remove");
		
		this.itemService.removeMultiple(itemId);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/multiple", method=RequestMethod.PUT)
	public ResponseEntity<Void> modifyMutiple(@RequestPart("item") String itemString
			, @RequestPart("file") MultipartFile picture, @RequestPart("file2") MultipartFile picture2) throws Exception {
		logger.info("itemString: " + itemString);
		
		Item item = new ObjectMapper().readValue(itemString, Item.class);
		
		String itemName = item.getItemName();
		String description = item.getDescription();
		
		if (itemName != null) {
			logger.info("item.getItemName(): " + itemName);
			
			item.setItemName(itemName);
		}
		
		if (description != null) {
			logger.info("item.getDescription(): " + description);
			
			item.setDescription(description);
		}
		
		List<MultipartFile> pictures = new ArrayList<MultipartFile>();
		pictures.add(picture);
		pictures.add(picture2);

		item.setPictures(pictures);
		
		for(int i = 0; i < pictures.size(); i++) {
			MultipartFile file = pictures.get(i);
			
			if(file != null && file.getSize() > 0) {
				logger.info("originalName: " + file.getOriginalFilename());
				logger.info("size: " + file.getSize());
				logger.info("contentType: " + file.getContentType());
				
				String savedName = uploadFile(file.getOriginalFilename(), file.getBytes());
				
				if(i == 0) {
					item.setPictureUrl(savedName);
				} else if(i == 1) {
					item.setPictureUrl2(savedName);
				}
			}
		}
		this.itemService.modifyMultiple(item);
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping("/display1")
	public ResponseEntity<byte[]> displayFile1(int itemId) throws Exception {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		String fileName = itemService.getPictureMultiple1(itemId);
		
		logger.info("FILE NAME: " + fileName);
		
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			MediaType mType = getMediaType(formatName);
			
			HttpHeaders headers = new HttpHeaders();
			
			in = new FileInputStream(uploadPath + File.separator + fileName);
			
			if(mType != null) {
				headers.setContentType(mType);
			}
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
			
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		return entity;
	}
	
	@RequestMapping("/display2")
	public ResponseEntity<byte[]> displayFile2(int itemId) throws Exception {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		String fileName = itemService.getPictureMultiple2(itemId);
		
		logger.info("FILE NAME: " + fileName);
		
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			
			MediaType mType = getMediaType(formatName);
			
			HttpHeaders headers = new HttpHeaders();
			
			in = new FileInputStream(uploadPath + File.separator + fileName);
			
			if(mType != null) {
				headers.setContentType(mType);
			}
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);
			
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		return entity;
	}
	
	// 비동기식 이미지 업로드
	@RequestMapping(value="/upload", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public ResponseEntity<String> upload(MultipartFile file) throws Exception {
		logger.info("originalName: " + file.getOriginalFilename());
		
		String savedName = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
		
		return new ResponseEntity<String>(savedName, HttpStatus.CREATED);
	}
	
	
}
