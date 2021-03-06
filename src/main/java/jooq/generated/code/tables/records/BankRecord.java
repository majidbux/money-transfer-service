/*
 * This file is generated by jOOQ.
 */
package jooq.generated.code.tables.records;


import java.util.UUID;

import javax.annotation.processing.Generated;

import jooq.generated.code.tables.Bank;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
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
public class BankRecord extends UpdatableRecordImpl<BankRecord> implements Record3<UUID, String, String> {

    private static final long serialVersionUID = -1755801581;

    /**
     * Setter for <code>PUBLIC.BANK.ID</code>.
     */
    public void setId(UUID value) {
        set(0, value);
    }

    /**
     * Getter for <code>PUBLIC.BANK.ID</code>.
     */
    public UUID getId() {
        return (UUID) get(0);
    }

    /**
     * Setter for <code>PUBLIC.BANK.NAME</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>PUBLIC.BANK.NAME</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>PUBLIC.BANK.SWIFT_CODE</code>.
     */
    public void setSwiftCode(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>PUBLIC.BANK.SWIFT_CODE</code>.
     */
    public String getSwiftCode() {
        return (String) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<UUID> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<UUID, String, String> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<UUID, String, String> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<UUID> field1() {
        return Bank.BANK.ID;
    }

    @Override
    public Field<String> field2() {
        return Bank.BANK.NAME;
    }

    @Override
    public Field<String> field3() {
        return Bank.BANK.SWIFT_CODE;
    }

    @Override
    public UUID component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public String component3() {
        return getSwiftCode();
    }

    @Override
    public UUID value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public String value3() {
        return getSwiftCode();
    }

    @Override
    public BankRecord value1(UUID value) {
        setId(value);
        return this;
    }

    @Override
    public BankRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public BankRecord value3(String value) {
        setSwiftCode(value);
        return this;
    }

    @Override
    public BankRecord values(UUID value1, String value2, String value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached BankRecord
     */
    public BankRecord() {
        super(Bank.BANK);
    }

    /**
     * Create a detached, initialised BankRecord
     */
    public BankRecord(UUID id, String name, String swiftCode) {
        super(Bank.BANK);

        set(0, id);
        set(1, name);
        set(2, swiftCode);
    }
}
