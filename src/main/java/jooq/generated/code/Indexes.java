/*
 * This file is generated by jOOQ.
 */
package jooq.generated.code;


import javax.annotation.processing.Generated;

import jooq.generated.code.tables.Account;
import jooq.generated.code.tables.AccountType;
import jooq.generated.code.tables.Bank;
import jooq.generated.code.tables.Currency;
import jooq.generated.code.tables.Customer;
import jooq.generated.code.tables.FlywaySchemaHistory;
import jooq.generated.code.tables.Transaction;
import jooq.generated.code.tables.Transfer;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>PUBLIC</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.3"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index ACCOUNT_ACCOUNT_TYPE_ID_FK_INDEX_E = Indexes0.ACCOUNT_ACCOUNT_TYPE_ID_FK_INDEX_E;
    public static final Index ACCOUNT_BANK_ID_FK_INDEX_E = Indexes0.ACCOUNT_BANK_ID_FK_INDEX_E;
    public static final Index ACCOUNT_CURRENCY_ID_FK_INDEX_E = Indexes0.ACCOUNT_CURRENCY_ID_FK_INDEX_E;
    public static final Index ACCOUNT_CUSTOMER_ID_FK_INDEX_E = Indexes0.ACCOUNT_CUSTOMER_ID_FK_INDEX_E;
    public static final Index PRIMARY_KEY_E = Indexes0.PRIMARY_KEY_E;
    public static final Index PRIMARY_KEY_C = Indexes0.PRIMARY_KEY_C;
    public static final Index PRIMARY_KEY_1 = Indexes0.PRIMARY_KEY_1;
    public static final Index PRIMARY_KEY_5 = Indexes0.PRIMARY_KEY_5;
    public static final Index PRIMARY_KEY_52 = Indexes0.PRIMARY_KEY_52;
    public static final Index PRIMARY_KEY_F = Indexes0.PRIMARY_KEY_F;
    public static final Index TRANSACTION_ACCOUNT_ID_FK_INDEX_F = Indexes0.TRANSACTION_ACCOUNT_ID_FK_INDEX_F;
    public static final Index TRANSACTION_TRANSFER_ID_FK_INDEX_F = Indexes0.TRANSACTION_TRANSFER_ID_FK_INDEX_F;
    public static final Index PRIMARY_KEY_7 = Indexes0.PRIMARY_KEY_7;
    public static final Index TRANSFER_ACCOUNT_ID_FK_1_INDEX_7 = Indexes0.TRANSFER_ACCOUNT_ID_FK_1_INDEX_7;
    public static final Index TRANSFER_ACCOUNT_ID_FK_2_INDEX_7 = Indexes0.TRANSFER_ACCOUNT_ID_FK_2_INDEX_7;
    public static final Index PRIMARY_KEY_6 = Indexes0.PRIMARY_KEY_6;
    public static final Index FLYWAY_SCHEMA_HISTORY_S_IDX = Indexes0.FLYWAY_SCHEMA_HISTORY_S_IDX;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index ACCOUNT_ACCOUNT_TYPE_ID_FK_INDEX_E = Internal.createIndex("ACCOUNT_ACCOUNT_TYPE_ID_FK_INDEX_E", Account.ACCOUNT, new OrderField[] { Account.ACCOUNT.TYPE_ID }, false);
        public static Index ACCOUNT_BANK_ID_FK_INDEX_E = Internal.createIndex("ACCOUNT_BANK_ID_FK_INDEX_E", Account.ACCOUNT, new OrderField[] { Account.ACCOUNT.BANK_ID }, false);
        public static Index ACCOUNT_CURRENCY_ID_FK_INDEX_E = Internal.createIndex("ACCOUNT_CURRENCY_ID_FK_INDEX_E", Account.ACCOUNT, new OrderField[] { Account.ACCOUNT.CURRENCY_ID }, false);
        public static Index ACCOUNT_CUSTOMER_ID_FK_INDEX_E = Internal.createIndex("ACCOUNT_CUSTOMER_ID_FK_INDEX_E", Account.ACCOUNT, new OrderField[] { Account.ACCOUNT.CUSTOMER_ID }, false);
        public static Index PRIMARY_KEY_E = Internal.createIndex("PRIMARY_KEY_E", Account.ACCOUNT, new OrderField[] { Account.ACCOUNT.ID }, true);
        public static Index PRIMARY_KEY_C = Internal.createIndex("PRIMARY_KEY_C", AccountType.ACCOUNT_TYPE, new OrderField[] { AccountType.ACCOUNT_TYPE.ID }, true);
        public static Index PRIMARY_KEY_1 = Internal.createIndex("PRIMARY_KEY_1", Bank.BANK, new OrderField[] { Bank.BANK.ID }, true);
        public static Index PRIMARY_KEY_5 = Internal.createIndex("PRIMARY_KEY_5", Currency.CURRENCY, new OrderField[] { Currency.CURRENCY.ID }, true);
        public static Index PRIMARY_KEY_52 = Internal.createIndex("PRIMARY_KEY_52", Customer.CUSTOMER, new OrderField[] { Customer.CUSTOMER.ID }, true);
        public static Index PRIMARY_KEY_F = Internal.createIndex("PRIMARY_KEY_F", Transaction.TRANSACTION, new OrderField[] { Transaction.TRANSACTION.ID }, true);
        public static Index TRANSACTION_ACCOUNT_ID_FK_INDEX_F = Internal.createIndex("TRANSACTION_ACCOUNT_ID_FK_INDEX_F", Transaction.TRANSACTION, new OrderField[] { Transaction.TRANSACTION.ACCOUNT_ID }, false);
        public static Index TRANSACTION_TRANSFER_ID_FK_INDEX_F = Internal.createIndex("TRANSACTION_TRANSFER_ID_FK_INDEX_F", Transaction.TRANSACTION, new OrderField[] { Transaction.TRANSACTION.TRANSFER_ID }, false);
        public static Index PRIMARY_KEY_7 = Internal.createIndex("PRIMARY_KEY_7", Transfer.TRANSFER, new OrderField[] { Transfer.TRANSFER.ID }, true);
        public static Index TRANSFER_ACCOUNT_ID_FK_1_INDEX_7 = Internal.createIndex("TRANSFER_ACCOUNT_ID_FK_1_INDEX_7", Transfer.TRANSFER, new OrderField[] { Transfer.TRANSFER.FROM_ACCOUNT_ID }, false);
        public static Index TRANSFER_ACCOUNT_ID_FK_2_INDEX_7 = Internal.createIndex("TRANSFER_ACCOUNT_ID_FK_2_INDEX_7", Transfer.TRANSFER, new OrderField[] { Transfer.TRANSFER.TO_ACCOUNT_ID }, false);
        public static Index PRIMARY_KEY_6 = Internal.createIndex("PRIMARY_KEY_6", FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, new OrderField[] { FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.INSTALLED_RANK }, true);
        public static Index FLYWAY_SCHEMA_HISTORY_S_IDX = Internal.createIndex("flyway_schema_history_s_idx", FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY, new OrderField[] { FlywaySchemaHistory.FLYWAY_SCHEMA_HISTORY.SUCCESS }, false);
    }
}
