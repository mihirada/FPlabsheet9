class Account(accountNumber: Int, initialBalance: Double) {
  private var balance: Double = initialBalance

  def getBalance: Double = balance

  def deposit(amount: Double): Unit = {
    if (amount > 0) {
      balance += amount
      println(s"Deposited: $amount")
    } else {
      println("Invalid deposit amount.")
    }
  }

  def withdraw(amount: Double): Unit = {
    if (amount > 0 && amount <= balance) {
      balance -= amount
      println(s"Withdrawn: $amount")
    } else {
      println("Insufficient balance or invalid withdrawal amount.")
    }
  }

  def transfer(amount: Double, toAccount: Account): Unit = {
    if (amount > 0 && amount <= balance) {
      balance -= amount
      toAccount.balance += amount
      println(s"Transferred: $amount from Account ${this.accountNumber} to Account ${toAccount.accountNumber}")
    } else {
      println("Insufficient balance or invalid transfer amount.")
    }
  }

  def applyInterest(): Unit = {
    if (balance > 0) {
      balance += balance * 0.05
    } else {
      balance += balance * 0.1
    }
  }

  override def toString: String = f"Account $accountNumber - Balance: $balance%.2f"
}

class Bank(val accounts: List[Account]) {
  def negativeBalanceAccounts: List[Account] = accounts.filter(_.getBalance < 0)

  def totalBalance: Double = accounts.map(_.getBalance).sum

  def applyInterestToAll(): Unit = {
    accounts.foreach(_.applyInterest())
  }
}

object Main {
  def main(args: Array[String]): Unit = {
    val account1 = new Account(1, 100.0)
    val account2 = new Account(2, -50.0)
    val account3 = new Account(3, 500.0)

    val bank = new Bank(List(account1, account2, account3))

    println("Negative Balance Accounts:")
    val negativeBalances = bank.negativeBalanceAccounts
    negativeBalances.foreach(account => println(account))

    println("\nTotal Balance of Bank: " + bank.totalBalance)

    println("\nInitial Balances:")
    bank.accounts.foreach(account => println(account))

    bank.applyInterestToAll()

    println("\nBalances after Applying Interest:")
    bank.accounts.foreach(account => println(account))
  }
}
