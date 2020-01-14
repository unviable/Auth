package com.huawei.product.service;

import java.util.List;

import com.huawei.commons.pojo.Product;

public interface ProductService {
	public List<Product> allProducts();
	public Product findProductById(int pid);
}
