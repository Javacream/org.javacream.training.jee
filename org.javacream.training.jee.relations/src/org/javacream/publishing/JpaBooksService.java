package org.javacream.publishing;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.javacream.publishing.entities.Book;
import org.javacream.publishing.entities.BookStatistics;
import org.javacream.publishing.entities.Isbn;
import org.javacream.publishing.entities.Publisher;

public class JpaBooksService {

	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void createBook(String description, Isbn isbn, int pages,
			double price, String title, BookStatistics bookInfo) {
		Book book = new Book(description, isbn, pages, price, title, bookInfo,
				false);
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			entityManager.persist(book);
			transaction.commit();
		} catch (RuntimeException e) {
			transaction.rollback();
			throw e;
		}
	}

	public Book findBookById(Long id) {
		return entityManager.find(Book.class, id);
	}
	public Book findBookByIsbn(Isbn isbn) {
		Query query = entityManager.createQuery("select book from Book as book where book.isbn = :isbn");
		query.setParameter("isbn", isbn);
		return (Book) query.getSingleResult();
	}
	@SuppressWarnings("unchecked")
	public List<Book> findBooksByTitle(String title) {
		Query query = entityManager.createQuery("select book from Book as book where book.title like :title");
		query.setParameter("title", title + "%");
		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Book> findCheapBooks() {
		Query query = entityManager.createQuery("select book from Book as book where book.price < 100");
		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<Book> findBestsellers() {
		Query query = entityManager.createQuery("select book from Book as book where book.bookInfo.sold > 500");
		return query.getResultList();
	}

	public void deleteBookById(Long id) {
		entityManager.remove(findBookById(id));
	}

	public void update(Book book) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		transaction.commit();
	}
	@SuppressWarnings("unchecked")
	public List<Book> findBooksByTitleNative(String title) {
		Query query = entityManager.createNativeQuery("select * from PUBLISHING_BOOKS where title like :title", Book.class);
		query.setParameter("title", title + "%");
		return query.getResultList();
	}

	public int getBookCount(){
		Query query = entityManager.createNativeQuery("select count(*) from PUBLISHING_BOOKS");
		BigInteger result = (BigInteger) query.getSingleResult();
		return result.intValue();
	}
	public int getCheapBooksCount(){
		Query query = entityManager.createNativeQuery("select count(*) from PUBLISHING_BOOKS where price < 100");
		BigInteger result = (BigInteger) query.getSingleResult();
		return result.intValue();
	}
	public boolean deleteBookByIsbn(Isbn isbn){
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Query query = entityManager.createNativeQuery("delete from PUBLISHING_BOOKS where isbn1= :isbn1 and isbn2= :isbn2 and isbn3= :isbn3 and isbn4= :isbn4");
		query.setParameter("isbn1", isbn.getIsbn1());
		query.setParameter("isbn2", isbn.getIsbn2());
		query.setParameter("isbn3", isbn.getIsbn3());
		query.setParameter("isbn4", isbn.getIsbn4());
		int deletedRows = query.executeUpdate();
		boolean result;
		if (deletedRows > 0){
			result = true;
		}else{
			result = false;
		}
		transaction.commit();
		return result;
	}

	public List<Object> findBooksByTitleCriteria(String title) {
		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
		Root<Book> from = criteriaQuery.from(Book.class);
		CriteriaQuery<Object> selectionQuery = criteriaQuery.select(from);
		Expression<String> path = from.get("title");
		Predicate predicate = criteriaBuilder.like(path, title + "%");
		selectionQuery.where(predicate);
		TypedQuery<Object> query = entityManager.createQuery(selectionQuery);
		return query.getResultList();
	}

	public List<Book> findBooksByPriceRange(double minPrice, double maxPrice) {
		TypedQuery<Book> query = entityManager.createQuery("select book from Book as book where book.price <= :maxPrice AND book.price >= :minPrice", Book.class);
		query.setParameter("minPrice", minPrice);
		query.setParameter("maxPrice", maxPrice);
		return query.getResultList();
	}

	public List<Book> findExpensiveBooks() {
		TypedQuery<Book> query = entityManager.createQuery("select book from Book as book where book.price > 39", Book.class);
		return query.getResultList();
	}

	public List<BookStatistics> findAll(){
		return entityManager.createQuery("select new org.javacream.publishing.values.BookInfo(book.isbn, book.title, book.price) from Book as book", BookStatistics.class).getResultList();
	}

	public void adaptPrice(double adaptionInPerCent){
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		Query query = entityManager.createQuery("update Book as b set b.price = b.price * :adaption");
		query.setParameter("adaption", 1 + adaptionInPerCent);
		query.executeUpdate();
		transaction.commit();
	}

	public List<Publisher> findPublishersForAuthor(long authorId){
		TypedQuery<Publisher> q = entityManager.createQuery("select distinct p from Publisher as p, Author as a where a.authorId = :authorId and a member of p.books.authors", Publisher.class);
		q.setParameter("authorId", authorId);
		return q.getResultList();
	}
	
	
	public List<Book> findBestSellersOrdered(){
		TypedQuery<Book> query = entityManager.createQuery("select book from Book as book where book.bookInfo.sold > 500", Book.class);
		EntityGraph<Book> graph = entityManager.createEntityGraph(Book.class);
		graph.addAttributeNodes("title");
		graph.addSubgraph("bookInfo").addAttributeNodes("sold");
		//query.setHint("javax.persistence.fetchgraph", graph);
		return query.getResultList();
		
	}
}
