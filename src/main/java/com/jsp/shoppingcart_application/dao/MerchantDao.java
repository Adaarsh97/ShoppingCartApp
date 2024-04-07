package com.jsp.shoppingcart_application.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.shoppingcart_application.dto.Customer;
import com.jsp.shoppingcart_application.dto.Merchant;

@Repository
public class MerchantDao {
	@Autowired
	EntityManagerFactory emf;

	public void saveMerchant(Merchant m) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		em.persist(m);
		et.commit();
	}

	public void saveMerchantById(Merchant m) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		em.persist(m);
		et.commit();
	}

	public void updateMerchant(Merchant m) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		em.merge(m);
		et.commit();
	}

	public void deleteMerchantById(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();

		Customer c = em.find(Customer.class, id);
		et.begin();
		em.remove(c);
		et.begin();
	}

	public Merchant login(String email, String password) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery("select m from Merchant m where m.email=?1 and m.password=?2");
		query.setParameter(1, email);
		query.setParameter(2, password);
		try {
			Merchant merchant = (Merchant) query.getSingleResult();
			return merchant;
		} catch (NoResultException e) {
			return null;
		}

	}

	public Merchant findMerchantById(int id) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction et = em.getTransaction();
		Merchant m = em.find(Merchant.class, id);
		return m;
	}
	
}
