package com.huawei.commons.pojo;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer pid;		//商品id
	private String pname;		//商品名
	private Double price;		//商品价格
	private Integer count;		//商品库存
	private List<String> images;	//商品图片
	private String detail;			//商品详情
	private List<String> productTypes;	//商品类型
}