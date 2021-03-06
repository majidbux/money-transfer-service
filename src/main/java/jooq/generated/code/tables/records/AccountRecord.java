/*
 * This file is generated by jOOQ.
 */
package jooq.generated.code.tables.records;


import java.math.BigDecimal;
import java.util.UUID;

import javax.annotation.processing.Generated;

import jooq.generated.code.tables.Account;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
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
public class AccountRecord extends UpdatableRecordImpl<AccountRecord> implements Record8<UUID, UUID, String, UUID, BigDecimal, Integer, Integer, Integer> {

    private static final long serialVersionUID = -1152121405;

    /**
     * Setter for <code>PUBLIC.ACCOUNT.ID</code>.
     */
    public void setId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>PUBLIC.ACCOUNT.ID</code>.
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>PUBLIC.ACCOUNT.BANK_ID</code>.
     */
    public void setBankId(UUID value) {
        set(1, value);
    }

    /**
     * Getter for <code>PUBLIC.ACCOUNT.BANK_ID</code>.
     */
    public UUID getBankId() {
        return (UUID) get(1);
    }

    /**
     * Setter for <code>PUBLIC.ACCOUNT.NAME</code>.
     */
    public void setName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>PUBLIC.ACCOUNT.NAME</code>.
     */
    public String getName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>PUBLIC.ACCOUNT.CUSTOMER_ID</code>.
     */
    public void setCustomerId(UUID value) {
        set(3, value);
    }

    /**
     * Getter for <code>PUBLIC.ACCOUNT.CUSTOMER_ID</code>.
     */
    public UUID getCustomerId() {
        return (UUID) get(3);
    }

    /**
     * Setter for <code>PUBLIC.ACCOUNT.CURRENT_BALANCE</code>.
     */
    public void setCurrentBalance(BigDecimal value) {
        set(4, value);
    }

    /**
     * Getter for <code>PUBLIC.ACCOUNT.CURRENT_BALANCE</code>.
     */
    public BigDecimal getCurrentBalance() {
        return (BigDecimal) get(4);
    }

    /**
     * Setter for <code>PUBLIC.ACCOUNT.CURRENCY_ID</code>.
     */
    public void setCurrencyId(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>PUBLIC.ACCOUNT.CURRENCY_ID</code>.
     */
    public Integer getCurrencyId() {
        return (Integer) get(5);
    }

    /**
     * Setter for <code>PUBLIC.ACCOUNT.STATUS</code>.
     */
    public void setStatus(Integer value) {
        set(6, value);
    }

    /**
     * Getter for <code>PUBLIC.ACCOUNT.STATUS</code>.
     */
    public Integer getStatus() {
        return (Integer) get(6);
    }

    /**
     * Setter for <code>PUBLIC.ACCOUNT.TYPE_ID</code>.
     */
    public void setTypeId(Integer value) {
        set(7, value);
    }

    /**
     * Getter for <code>PUBLIC.ACCOUNT.TYPE_ID</code>.
     */
    public Integer getTypeId() {
        return (Integer) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row8<UUID, UUID, String, UUID, BigDecimal, Integer, Integer, Integer> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    @Override
    public Row8<UUID, UUID, String, UUID, BigDecimal, Integer, Integer, Integer> valuesRow() {
        return (Row8) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return Account.ACCOUNT.ID;
    }

    @Override
    public Field<UUID> field2() {
        return Account.ACCOUNT.BANK_ID;
    }

    @Override
    public Field<String> field3() {
        return Account.ACCOUNT.NAME;
    }

    @Override
    public Field<UUID> field4() {
        return Account.ACCOUNT.CUSTOMER_ID;
    }

    @Override
    public Field<BigDecimal> field5() {
        return Account.ACCOUNT.CURRENT_BALANCE;
    }

    @Override
    public Field<Integer> field6() {
        return Account.ACCOUNT.CURRENCY_ID;
    }

    @Override
    public Field<Integer> field7() {
        return Account.ACCOUNT.STATUS;
    }

    @Override
    public Field<Integer> field8() {
        return Account.ACCOUNT.TYPE_ID;
    }

    @Override
    public UUID component1() {
        return getId();
    }

    @Override
    public UUID component2() {
        return getBankId();
    }

    @Override
    public String component3() {
        return getName();
    }

    @Override
    public UUID component4() {
        return getCustomerId();
    }

    @Override
    public BigDecimal component5() {
        return getCurrentBalance();
    }

    @Override
    public Integer component6() {
        return getCurrencyId();
    }

    @Override
    public Integer component7() {
        return getStatus();
    }

    @Override
    public Integer component8() {
        return getTypeId();
    }

    @Override
    public UUID value1() {
        return getId();
    }

    @Override
    public UUID value2() {
        return getBankId();
    }

    @Override
    public String value3() {
        return getName();
    }

    @Override
    public UUID value4() {
        return getCustomerId();
    }

    @Override
    public BigDecimal value5() {
        return getCurrentBalance();
    }

    @Override
    public Integer value6() {
        return getCurrencyId();
    }

    @Override
    public Integer value7() {
        return getStatus();
    }

    @Override
    public Integer value8() {
        return getTypeId();
    }

    @Override
    public AccountRecord value1(UUID value) {
        setId(value);
        return this;
    }

    @Override
    public AccountRecord value2(UUID value) {
        setBankId(value);
        return this;
    }

    @Override
    public AccountRecord value3(String value) {
        setName(value);
        return this;
    }

    @Override
    public AccountRecord value4(UUID value) {
        setCustomerId(value);
        return this;
    }

    @Override
    public AccountRecord value5(BigDecimal value) {
        setCurrentBalance(value);
        return this;
    }

    @Override
    public AccountRecord value6(Integer value) {
        setCurrencyId(value);
        return this;
    }

    @Override
    public AccountRecord value7(Integer value) {
        setStatus(value);
        return this;
    }

    @Override
    public AccountRecord value8(Integer value) {
        setTypeId(value);
        return this;
    }

    @Override
    public AccountRecord values(UUID value1, UUID value2, String value3, UUID value4, BigDecimal value5, Integer value6, Integer value7, Integer value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AccountRecord
     */
    public AccountRecord() {
        super(Account.ACCOUNT);
    }

    /**
     * Create a detached, initialised AccountRecord
     */
    public AccountRecord(UUID id, UUID bankId, String name, UUID customerId, BigDecimal currentBalance, Integer currencyId, Integer status, Integer typeId) {
        super(Account.ACCOUNT);

        set(0, id);
        set(1, bankId);
        set(2, name);
        set(3, customerId);
        set(4, currentBalance);
        set(5, currencyId);
        set(6, status);
        set(7, typeId);
    }
}
