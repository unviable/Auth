package com.huawei.product.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huawei.commons.pojo.Product;
import com.huawei.product.service.ProductService;



@RestController	//成为注册中心的客户端
public class ProductHandler {
	@Autowired
	private ProductService productService;
	
	//@HasRole("admin")
	@GetMapping("/all")
	public List<Product> allProducts(){
		return productService.allProducts();
	}
	
	//find?pid=1001
	//rest:  find/1001/name/...
	@GetMapping("/find/{pid}")
	public Product findProductById(@PathVariable("pid") int pid){
		return productService.findProductById(pid);
	}
	
	@GetMapping("/test/{uname}/{pwd}")
	public String findTest(@PathVariable("uname")String uname,@PathVariable("pwd")String pwd){
		return "success";
	}
}