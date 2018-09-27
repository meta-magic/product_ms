package com.metamagic.productms.repository;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.springframework.stereotype.Repository;

import com.metamagic.productms.PMFConfig;
import com.metamagic.productms.entity.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	@Override
	public Product save(Product product) {
		PersistenceManager pm = PMFConfig.getPersistenceManagerFactory().getPersistenceManager();
		return pm.makePersistent(product);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAll() {
		PersistenceManager pm = PMFConfig.getPersistenceManagerFactory().getPersistenceManager();
		Query query = pm.newQuery(Product.class);
		return (List<Product>) query.execute();
	}

	@Override
	public Product findById(Long productId) {
		PersistenceManager pm = PMFConfig.getPersistenceManagerFactory().getPersistenceManager();
		Product product = pm.getObjectById(Product.class, productId);
		return product;
	}	

}
