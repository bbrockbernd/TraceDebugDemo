import kotlin.test.Test

class FundsTest {

    @Test
    fun testTransfers() {
        val bank = Bank()
        bank.transfer(1, 4, 250)
        bank.transfer(3, 7, 175)
        bank.transfer(2, 5, 85)
        bank.transfer(6, 3, 320)
        bank.transfer(4, 9, 150)
        bank.transfer(7, 2, 435)
        bank.transfer(5, 8, 75)
        bank.transfer(8, 1, 200)
        bank.transfer(9, 6, 375)
        bank.transfer(3, 5, 42)
        bank.transfer(2, 7, 499)
        bank.transfer(1, 9, 123)
        bank.transfer(8, 4, 267)
        bank.transfer(6, 1, 305)
        bank.transfer(5, 3, 197)
        bank.transfer(4, 2, 88)
        bank.transfer(9, 8, 450)
        bank.transfer(7, 6, 15)
        bank.transfer(2, 9, 333)
        bank.transfer(5, 1, 275)

        check(bank.validateFunds() == 0) { "Funds don't sum to zero: ${bank.validateFunds()}" }
    }

}