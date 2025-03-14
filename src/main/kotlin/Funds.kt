/**
 * The `Funds` class manages a financial ledger system that tracks deposit and withdrawal records
 * for multiple accounts. It provides functionalities to handle the balance for individual accounts
 * through basic operations such as depositing and withdrawing funds.
 */
class Funds {
    private val ledger = Array<MutableList<Record>>(10) { mutableListOf() }

    /**
     * Calculates the total available funds for a specific account by aggregating
     * all deposit and withdrawal records associated with the account.
     *
     * @param account The identifier of the account whose total balance is to be calculated.
     * @return The total balance of the account after accounting for all deposits and withdrawals.
     */
    fun getFundFor(account: Int): Int {
        var total = 0

        // accumilate records
        ledger[account].forEach { when(it) {
            is Withdrawal -> total -= it.amount
            is Deposit -> total += it.amount
        }}
        return total
    }

    /**
     * Adds a deposit record to the ledger for the specified account.
     *
     * @param account The identifier of the account into which the deposit is being made.
     * @param amount The amount to be deposited into the account.
     */
    fun deposit(account: Int, amount: Int) {
        ledger[account].add(Deposit(amount))
    }

    /**
     * Adds a withdrawal record to the ledger for the specified account.
     *
     * @param account The identifier of the account from which the withdrawal is being made.
     * @param amount The amount to be withdrawn from the account.
     */
    fun withdraw(account: Int, amount: Int) {
        ledger[account].add(Withdrawal(amount))
    }
}

/**
 * Represents a financial transaction record in the ledger system.
 *
 * This interface serves as a marker for transaction types such as deposits
 * and withdrawals, allowing a unified approach to handling various types
 * of financial operations within an account ledger.
 *
 * Implementations of this interface should encapsulate the specific
 * attributes and behavior of a transaction (e.g., the amount of funds
 * involved).
 */
interface Record
data class Withdrawal(val amount: Int) : Record
data class Deposit(val amount: Int) : Record


/**
 * The `Bank` class represents a basic banking system that manages accounts and their corresponding
 * financial activities. It allows transferring funds between accounts, checking account suspension
 * status, and validating the total funds across accounts.
 *
 * Functions include:
 * - Retrieving the available fund for a specific account.
 * - Performing transfer operations between accounts, ensuring funds are sufficient and accounts
 *   are not suspended.
 * - Checking if an account is suspended.
 * - Validating and displaying the total fund balances across all accounts.
 *
 * The class internally interacts with the `Funds` class to manage account balances and track financial
 * transactions, such as deposits and withdrawals.
 */
class Bank {
    private val funds = Funds()
    private val suspendedAccounts = arrayOf(5)

    /**
     * Retrieves the total available funds for the specified account.
     *
     * @param account The identifier of the account for which funds are to be retrieved.
     * @return The total balance of the account after accounting for all deposits and withdrawals.
     */
    fun getFundFor(account: Int) = funds.getFundFor(account)

    /**
     * Transfers a specified amount of funds from one account to another.
     * The operation involves withdrawing the amount from the sender's account
     * and depositing it into the receiver's account, provided that certain
     * conditions such as sufficient balance and account status are met.
     *
     * @param from The account identifier of the sender.
     * @param to The account identifier of the receiver.
     * @param amount The amount of funds to be transferred.
     */
    fun transfer(from: Int, to: Int, amount: Int) {
        val currentAmountFrom = funds.getFundFor(from)

        if (currentAmountFrom - amount < -1000) {
            println("Insufficient funds")
            return
        }

        if (!isAccountSuspended(from)) {
            funds.withdraw(
                account = from,
                amount = amount,
            )
        }

        if (!isAccountSuspended(to)) {
            funds.deposit(
                account = to,
                amount = amount
            )
        }

    }

    /**
     * Checks if the specified account is suspended.
     *
     * @param account The identifier of the account to be checked for suspension status.
     * @return `true` if the account is suspended, `false` otherwise.
     */
    fun isAccountSuspended(account: Int) = suspendedAccounts.contains(account)


    /**
     * Validates the total funds across all accounts in the system.
     *
     * This method calculates the aggregate balance of all accounts by iterating
     * through each account in a predefined range and summing up their respective
     * balances. It retrieves the balance of each account using the `getFundFor(account)`
     * method. The total funds are then printed to the console for verification purposes.
     *
     * This function is primarily used to ensure that the system's accounting records
     * are consistent and accurate.
     */
    fun validateFunds(): Int {
        var total = 0
        repeat(10) { i ->
            total += funds.getFundFor(i)
        }
        return total
    }
}