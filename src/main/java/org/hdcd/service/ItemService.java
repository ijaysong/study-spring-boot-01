package org.hdcd.service;

import java.util.List;

import org.hdcd.domain.Item;

public interface ItemService {
	
	// 이미지 업로드
	public void regist(Item item) throws Exception;
	public Item read(int itemId) throws Exception;
	public void modify(Item item) throws Exception;
	public void remove(int itemId) throws Exception;
	public List<Item> list() throws Exception;
	public String getPicture(int itemId) throws Exception;
	
	
	// 여러개의 이미지 업로드
	public void registMultiple(Item item) throws Exception;
	public Item readMultiple(int itemId) throws Exception;
	public void modifyMultiple(Item item) throws Exception;
	public void removeMultiple(int itemId) throws Exception;
	public List<Item> listMultiple() throws Exception;
	public String getPictureMultiple(int itemId) throws Exception;
}
