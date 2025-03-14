import kotlin.random.Random
import kotlin.test.Test

class FundsTest {

    @Test
    fun testTransfers() {
        val bank = Bank()
        repeat(20) {
            val from = Random.nextInt(0, 10)
            val to = Random.nextInt(0, 10)
            val amount = Random.nextInt(1, 500)
            bank.transfer(from, to, amount)
        }
        check(bank.validateFunds() == 0) { "Funds don't sum to zero: ${bank.validateFunds()}" }
    }
}