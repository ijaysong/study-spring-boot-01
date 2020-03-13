package org.hdcd.service;

import java.util.List;

import org.hdcd.domain.Item;
import org.hdcd.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemMapper mapper;
	
	// 이미지 업로드
	@Override
	public void regist(Item item) throws Exception {
		mapper.create(item);
	}

	@Override
	public Item read(int itemId) throws Exception {
		return mapper.read(itemId);
	}

	@Override
	public void modify(Item item) throws Exception {
		mapper.update(item);
	}

	@Override
	public void remove(int itemId) throws Exception {
		mapper.delete(itemId);
	}

	@Override
	public List<Item> list() throws Exception {
		return mapper.list();
	}

	@Override
	public String getPicture(int itemId) throws Exception {
		return mapper.getPicture(itemId);
	}

	// 여러개의 이미지 업로드
	@Override
	public void registMultiple(Item item) throws Exception {
		mapper.createMultiple(item);
	}

	@Override
	public Item readMultiple(int itemId) throws Exception {
		return mapper.readMultiple(itemId);
	}

	@Override
	public void modifyMultiple(Item item) throws Exception {
		mapper.updateMultiple(item);
	}

	@Override
	public void removeMultiple(int itemId) throws Exception {
		mapper.deleteMultiple(itemId);
	}

	@Override
	public List<Item> listMultiple() throws Exception {
		return mapper.listMultiple();
	}

	@Override
	public String getPictureMultiple1(int itemId) throws Exception {
		return mapper.getPictureMultiple1(itemId);
	}
	
	@Override
	public String getPictureMultiple2(int itemId) throws Exception {
		return mapper.getPictureMultiple2(itemId);
	}

}
