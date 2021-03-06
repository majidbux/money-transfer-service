/*
 * This file is generated by jOOQ.
 */
package jooq.generated.code.tables.records;


import javax.annotation.processing.Generated;

import jooq.generated.code.tables.Currency;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class CurrencyRecord extends UpdatableRecordImpl<CurrencyRecord> implements Record2<Integer, String> {

    private static final long serialVersionUID = 825742922;

    /**
     * Setter for <code>PUBLIC.CURRENCY.ID</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>PUBLIC.CURRENCY.ID</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>PUBLIC.CURRENCY.ISO_CODE</code>.
     */
    public void setIsoCode(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>PUBLIC.CURRENCY.ISO_CODE</code>.
     */
    public String getIsoCode() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Integer, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Currency.CURRENCY.ID;
    }

    @Override
    public Field<String> field2() {
        return Currency.CURRENCY.ISO_CODE;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getIsoCode();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getIsoCode();
    }

    @Override
    public CurrencyRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public CurrencyRecord value2(String value) {
        setIsoCode(value);
        return this;
    }

    @Override
    public CurrencyRecord values(Integer value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached CurrencyRecord
     */
    public CurrencyRecord() {
        super(Currency.CURRENCY);
    }

    /**
     * Create a detached, initialised CurrencyRecord
     */
    public CurrencyRecord(Integer id, String isoCode) {
        super(Currency.CURRENCY);

        set(0, id);
        set(1, isoCode);
    }
}
