package org.hdcd.service;

import java.util.List;

import org.hdcd.domain.Item;

public interface ItemService {
	
	public void regist(Item item) throws Exception;
	public Item read(int itemId) throws Exception;
	public void modify(Item item) throws Exception;
	public void remove(int itemId) throws Exception;
	public List<Item> list() throws Exception;
	public String getPicture(int itemId) throws Exception;
}
