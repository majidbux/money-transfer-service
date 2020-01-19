/*
 * This file is generated by jOOQ.
 */
package jooq.generated.code.tables.records;


import javax.annotation.processing.Generated;

import jooq.generated.code.tables.AccountType;

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
public class AccountTypeRecord extends UpdatableRecordImpl<AccountTypeRecord> implements Record2<Integer, String> {

    private static final long serialVersionUID = 1428745727;

    /**
     * Setter for <code>PUBLIC.ACCOUNT_TYPE.ID</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>PUBLIC.ACCOUNT_TYPE.ID</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>PUBLIC.ACCOUNT_TYPE.TYPE</code>.
     */
    public void setType(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>PUBLIC.ACCOUNT_TYPE.TYPE</code>.
     */
    public String getType() {
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
        return AccountType.ACCOUNT_TYPE.ID;
    }

    @Override
    public Field<String> field2() {
        return AccountType.ACCOUNT_TYPE.TYPE;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getType();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getType();
    }

    @Override
    public AccountTypeRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public AccountTypeRecord value2(String value) {
        setType(value);
        return this;
    }

    @Override
    public AccountTypeRecord values(Integer value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AccountTypeRecord
     */
    public AccountTypeRecord() {
        super(AccountType.ACCOUNT_TYPE);
    }

    /**
     * Create a detached, initialised AccountTypeRecord
     */
    public AccountTypeRecord(Integer id, String type) {
        super(AccountType.ACCOUNT_TYPE);

        set(0, id);
        set(1, type);
    }
}
