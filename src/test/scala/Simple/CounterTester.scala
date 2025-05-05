package Simple

import chisel3._
import chiseltest.{ChiselScalatestTester, _}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CounterTester extends AnyFlatSpec with ChiselScalatestTester with Matchers {


  behavior of "CounterTester"

  it should "correctly CounterTester" in {
    test(new Counter10).withAnnotations(Seq(VerilatorBackendAnnotation,WriteVcdAnnotation)) { dut =>
     
        dut.clock.step(12) // 可选，给信号稳定时间
    }
  }
}

