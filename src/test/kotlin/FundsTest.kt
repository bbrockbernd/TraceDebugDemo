import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals

class FundsTest {

    @Test
    fun testTransfers() {
        val initialBalances = listOf(1000, 2000, 1500, 300, 1000, 650, 700, 1200, 900, 1100)
        val bank = Bank(initialBalances)
        val bankTotalBeforeTransfers = bank.bankTotal()
        assertEquals(initialBalances.sum(), bankTotalBeforeTransfers)
        repeat(20) {
            val from = Random.nextInt(0, 10)
            val to = Random.nextInt(0, 10)
            val amount = Random.nextInt(1, 500)
            bank.transfer(from, to, amount)
        }
        assertEquals(bankTotalBeforeTransfers, bank.bankTotal())
    }
}