package info.pionas.ing.service;

import info.pionas.ing.model.transactions.Account;
import info.pionas.ing.model.transactions.Transaction;
import info.pionas.ing.model.transactions.Transactions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class TransactionsServiceTest {

    private final TransactionsService service = new TransactionsService();

    @Test
    void getAccounts() {
        // given
        // when
        final var accounts = service.getAccounts(getTransactions());
        //then
        Assertions.assertThat(accounts).hasSize(4);
        Assertions.assertThat(accounts.get(0)).isEqualTo(getAccount("06105023389842834748547303", 0, 1, 10.90f));
        Assertions.assertThat(accounts.get(1)).isEqualTo(getAccount("31074318698137062235845814", 1, 0, -200.90f));
        Assertions.assertThat(accounts.get(2)).isEqualTo(getAccount("32309111922661937852684864", 1, 1, 39.20f));
        Assertions.assertThat(accounts.get(3)).isEqualTo(getAccount("66105036543749403346524547", 1, 1, 150.80f));
    }

    private Transactions getTransactions() {
        final var transactions = new Transactions();
        transactions.add(getTransaction("32309111922661937852684864", "06105023389842834748547303", 10.90f));
        transactions.add(getTransaction("31074318698137062235845814", "66105036543749403346524547", 200.90f));
        transactions.add(getTransaction("66105036543749403346524547", "32309111922661937852684864", 50.10f));
        return transactions;
    }

    private Transaction getTransaction(String debitAccount, String creditAccount, float amount) {
        return new Transaction()
                .debitAccount(debitAccount)
                .creditAccount(creditAccount)
                .amount(amount);
    }

    private Account getAccount(String account, int debitCount, int creditCount, float balance) {
        return new Account()
                .account(account)
                .debitCount(debitCount)
                .creditCount(creditCount)
                .balance(balance);
    }
}