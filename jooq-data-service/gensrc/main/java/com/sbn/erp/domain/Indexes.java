/*
 * This file is generated by jOOQ.
*/
package com.sbn.erp.domain;


import com.sbn.erp.domain.tables.Product;
import com.sbn.erp.domain.tables.ProductCategory;

import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>erp_db</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.10.7"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index PRODUCT_FK5CYPB0K23BOVO3RN1A5JQS6J4 = Indexes0.PRODUCT_FK5CYPB0K23BOVO3RN1A5JQS6J4;
    public static final Index PRODUCT_PRIMARY = Indexes0.PRODUCT_PRIMARY;
    public static final Index PRODUCT_CATEGORY_PRIMARY = Indexes0.PRODUCT_CATEGORY_PRIMARY;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index PRODUCT_FK5CYPB0K23BOVO3RN1A5JQS6J4 = Internal.createIndex("FK5cypb0k23bovo3rn1a5jqs6j4", Product.PRODUCT, new OrderField[] { Product.PRODUCT.CATEGORY_ID }, false);
        public static Index PRODUCT_PRIMARY = Internal.createIndex("PRIMARY", Product.PRODUCT, new OrderField[] { Product.PRODUCT.ID }, true);
        public static Index PRODUCT_CATEGORY_PRIMARY = Internal.createIndex("PRIMARY", ProductCategory.PRODUCT_CATEGORY, new OrderField[] { ProductCategory.PRODUCT_CATEGORY.ID }, true);
    }
}