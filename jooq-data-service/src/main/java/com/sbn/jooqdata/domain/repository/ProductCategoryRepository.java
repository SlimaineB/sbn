package com.sbn.jooqdata.domain.repository;

import static com.sbn.erp.domain.tables.Product.PRODUCT;
import static com.sbn.erp.domain.tables.ProductCategory.PRODUCT_CATEGORY;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sbn.jooqdata.domain.entity.Product;

@Repository
public class ProductCategoryRepository {

	@Autowired
	private DSLContext DSL;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * 
	 * @param name
	 * @return
	 */
	public List<Product> findByName(String name) {

		return DSL.select(PRODUCT.fields()).from(PRODUCT).join(PRODUCT_CATEGORY).on(PRODUCT.CATEGORY_ID.eq(PRODUCT_CATEGORY.ID))
				.where(PRODUCT_CATEGORY.NAME.eq(name)).fetch().into(Product.class);

	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public List<Product> findByNameWithJDBC(String name) {
		
		Query query = DSL.select(PRODUCT.NAME, PRODUCT.PRICE, PRODUCT.CREATION_DATE).from(PRODUCT).join(PRODUCT_CATEGORY).on(PRODUCT.CATEGORY_ID.eq(PRODUCT_CATEGORY.ID))
				.where(PRODUCT_CATEGORY.NAME.eq(name));
		
		Object[] bind = query.getBindValues().toArray(new Object[0]);
		
		List<Product> list = jdbcTemplate.query(query.getSQL(), bind,
				new RowMapper<Product>() {
					@Override
					public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
						return new Product(rs.getString(1), rs.getBigDecimal(2), rs.getDate(3));
					}
				});
		
		return list;
	}
}
