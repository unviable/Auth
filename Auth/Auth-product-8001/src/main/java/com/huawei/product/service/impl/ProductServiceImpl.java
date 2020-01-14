package com.huawei.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huawei.commons.pojo.Product;
import com.huawei.product.mapper.ProductMapper;
import com.huawei.product.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductMapper productMapper;
	
	public List<Product> allProducts() {
		return productMapper.allProducts();
	}

	public Product findProductById(int pid) {
		return productMapper.findProductById(pid);
	}

}
