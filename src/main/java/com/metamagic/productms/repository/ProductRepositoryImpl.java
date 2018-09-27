package com.metamagic.productms.repository;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.metamagic.productms.entity.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	@Autowired
	private PersistenceManagerFactory pmf;
	@Override
	public Product save(Product product) {
		PersistenceManager pm = pmf.getPersistenceManager();
		return pm.makePersistent(product);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAll() {
		PersistenceManager pm = pmf.getPersistenceManager();
		Query query = pm.newQuery(Product.class);
		return (List<Product>) query.execute();
	}

	@Override
	public Product findById(Long productId) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Product product = pm.getObjectById(Product.class, productId);
		return product;
	}	

}
