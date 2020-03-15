package org.hdcd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.hdcd.domain.Item;

@Mapper
public interface ItemMapper {
	
	// 이미지 업로드
	public void create(Item item) throws Exception;
	public Item read(int itemId) throws Exception;
	public void update(Item item) throws Exception;
	public void delete(int itemId) throws Exception;
	public List<Item> list() throws Exception;
	public String getPicture(int itemId) throws Exception;
	
	// 여러 개의 이미지 업로드
	public void createMultiple(Item item) throws Exception;
	public Item readMultiple(int itemId) throws Exception;
	public void updateMultiple(Item item) throws Exception;
	public void deleteMultiple(int itemId) throws Exception;
	public List<Item> listMultiple() throws Exception;
	public String getPictureMultiple1(int itemId) throws Exception;
	public String getPictureMultiple2(int itemId) throws Exception;
	
	// 비동기 방식 이미지 업로드
	public void createAsync(Item item) throws Exception;
	public Item readAsync(int itemId) throws Exception;
	public void updateAsync(Item item) throws Exception;
	public void deleteAsync(int itemId) throws Exception;
	public List<Item> listAsync() throws Exception;
	public String getPictureAsync(int itemId) throws Exception;
	public void addAttachAsync(String fullName) throws Exception;
	public List<String> getAttachAsync(int itemId) throws Exception;
	public void deleteAttachAsync(int itemId) throws Exception;
	public void replaceAttachAsync(@Param("fullName") String fullName, @Param("itemId") int itemId) throws Exception;
}
