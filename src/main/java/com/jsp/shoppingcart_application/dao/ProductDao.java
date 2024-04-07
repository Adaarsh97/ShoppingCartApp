package com.jsp.shoppingcart_application.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.shoppingcart_application.dto.Merchant;
import com.jsp.shoppingcart_application.dto.Product;

@Repository
public class ProductDao {
	@Autowired
	EntityManagerFactory emf;
    @Autowired
    MerchantDao mdao;
	public List<Product> findAllProductsByBrand(String brand) {
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("select p from Product p where p.brand=?1");
		query.setParameter(1, brand);
		List<Product> products = query.getResultList();
		return products;
	}

	public List<Product> fetchAllProducts(int id) {
		EntityManager em = emf.createEntityManager();
		Merchant m = em.find(Merchant.class, id);
		return m.getProducts();
	}

	public void saveProduct(Product p) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		em.persist(p);
		et.commit();
	}

	public void updateProduct(Product p) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		em.merge(p);
		et.commit();
	}

	public void deleteProductById(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		Product p = em.find(Product.class, id);
		et.begin();
		em.remove(p);
		et.commit();
	}

	public Product updateProductById(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		Product p = em.find(Product.class, id);
		return p;
	}

	public List<Product> fetchAllproducts() {
		EntityManager em = emf.createEntityManager();

		Query query = em.createQuery("select p from Product p");
		List<Product> products = query.getResultList();
		System.out.println(products.size());
		return products;
	}

	public Product findProductById(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		Product p = em.find(Product.class, id);
		return p;
	}

	public Merchant removeProductFormMerchant(int mid, int pid) {
		Merchant m = mdao.findMerchantById(mid);
		List<Product> products = m.getProducts();

		List<Product> productList = new ArrayList<>();

		for (Product p : products) {
			if (p.getId() != pid) {
				productList.add(p);
			}
		}
		m.setProducts(productList);
		return m;

	}

}
