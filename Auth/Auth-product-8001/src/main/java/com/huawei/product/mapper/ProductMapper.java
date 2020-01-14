package com.huawei.product.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.huawei.commons.pojo.Product;

import org.apache.ibatis.annotations.Many;



public interface ProductMapper {
	@Select("select * from product")
	@Results({
		@Result(id=true,column="pid",property="pid"),
		@Result(column="pid",property="images",many=@Many(select="findImageByPid"))
	})
	public List<Product> allProducts();
	
	@Delete("delete from product where pid = #{pid}")
	public void delProductById(int pid);
	
	@Select("select name from images where pid=#{pid}")
	public List<String> findImageByPid(int pid);
	
	@Select("select * from product where pid = #{pid}")
	@Results({
		@Result(id=true,column="pid",property="pid"),
		@Result(column="pid",property="images",many=@Many(select="findImageByPid")),
		@Result(column="pid",property="productTypes",many=@Many(select="findProductTypeById"))
	})
	public Product findProductById(int pid);
	
	@Select("select producttype from producttype where pid=#{pid}")
	public List<String> findProductTypeById(int pid);
}