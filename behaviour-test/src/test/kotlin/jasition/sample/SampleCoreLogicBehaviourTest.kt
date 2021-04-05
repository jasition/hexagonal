package jasition.sample

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

internal class SampleCoreLogicBehaviourTest: StringSpec({
    "Adds two numbers correctly" {
        forAll(
            row(4, 6, 10)
        ) { a, b, expected ->
            SampleCoreLogic().add(a, b) shouldBe expected
        }
    }
})