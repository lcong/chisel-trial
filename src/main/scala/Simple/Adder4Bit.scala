package Simple

import chisel3._

// _root_ disambiguates from package chisel3.util.circt if user imports chisel3.util._
import _root_.circt.stage.ChiselStage

class Adder4Bit extends Module {
  val io = IO(new Bundle {
    val a = Input(UInt(4.W))
    val b = Input(UInt(4.W))
    val sum = Output(UInt(4.W))
  })

  io.sum := io.a + io.b
}



object Adder4BitApp extends App {

  ChiselStage.emitSystemVerilogFile(
    new Adder4Bit,
    firtoolOpts = Array(
      "-o", "generated/Adder4Bit.v",
      "-disable-all-randomization",
      "-strip-debug-info")
  )
}
