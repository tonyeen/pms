package org.qianrenxi.pms.rest;

import org.qianrenxi.pms.entity.Product;
import org.qianrenxi.pms.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductApiController {
	@Autowired
	private ProductService productService;

	@RequestMapping(value="", method = RequestMethod.GET)
	public Page<Product> allProducts(Product product, Pageable pageable) {
		Page<Product> products = productService.findAll(product, pageable);
		return products;
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public Product getOne(@PathVariable("id") Long id) {
		Product product = productService.findOne(id);
		return product;
	}
	
	@RequestMapping(value="", method= RequestMethod.PUT)
	public Product create(Product product) {
		product = productService.save(product);
		return product;
	}
	
	@RequestMapping(value="/{id}", method= RequestMethod.POST)
	public Product update(@PathVariable("id") Long id, Product product) {
		product.setId(id);
		product = productService.save(product);
		return product;
	}
	
	@RequestMapping(value="", method= RequestMethod.DELETE)
	public void update(@RequestParam("ids")Long[] ids) {
		productService.delete(ids);
	}
}
