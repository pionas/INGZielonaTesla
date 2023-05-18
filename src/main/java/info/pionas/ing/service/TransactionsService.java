package info.pionas.ing.service;

import info.pionas.ing.model.transactions.Account;
import info.pionas.ing.model.transactions.Accounts;
import info.pionas.ing.model.transactions.Transaction;
import info.pionas.ing.model.transactions.Transactions;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

@Service
public class TransactionsService {

    public Accounts getAccounts(Transactions transactions) {
        final var accounts = new Accounts();
        accounts.addAll(getAccountList(transactions));
        return accounts;
    }

    private Collection<Account> getAccountList(Transactions transactions) {
        Map<String, Account> accountMap = new TreeMap<>();
        transactions.forEach(transaction -> {
            debitAccount(accountMap, transaction);
            creditAccount(accountMap, transaction);
        });
        return accountMap.values();
    }

    private void debitAccount(Map<String, Account> accountMap, Transaction transaction) {
        String accountNumber = transaction.getDebitAccount();
        if (accountNotExists(accountMap, accountNumber)) {
            accountMap.put(accountNumber, createAccount(accountNumber));
        }
        Account account = accountMap.get(accountNumber);
        account.debitCount(account.getDebitCount() + 1);
        account.balance(getBalance(account.getBalance() - transaction.getAmount()));
    }

    private void creditAccount(Map<String, Account> accountMap, Transaction transaction) {
        String accountNumber = transaction.getCreditAccount();
        if (accountNotExists(accountMap, accountNumber)) {
            accountMap.put(accountNumber, createAccount(accountNumber));
        }
        Account account = accountMap.get(accountNumber);
        account.creditCount(account.getCreditCount() + 1);
        account.balance(getBalance(account.getBalance() + transaction.getAmount()));
    }

    private boolean accountNotExists(Map<String, Account> accountMap, String accountNumber) {
        return !accountMap.containsKey(accountNumber);
    }

    private Account createAccount(String accountNumber) {
        return new Account()
                .account(accountNumber)
                .balance(0f)
                .debitCount(0)
                .creditCount(0);
    }

    private float getBalance(float amount) {
        int pow = 10;
        for (int i = 1; i < 2; i++)
            pow *= 10;
        float tmp = amount * pow;
        return ((float) ((int) ((tmp - (int) tmp) >= 0.5f ? tmp + 1 : tmp))) / pow;
    }
}
