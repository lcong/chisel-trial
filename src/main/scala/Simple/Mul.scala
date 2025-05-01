// See LICENSE.txt for license details.
package Simple

import chisel3._
// _root_ disambiguates from package chisel3.util.circt if user imports chisel3.util._
import _root_.circt.stage.ChiselStage


// Problem:
//
// Implement a four-by-four multiplier using a look-up table.
//
class Mul extends Module {
  val io = IO(new Bundle {
    val x   = Input(UInt(4.W))
    val y   = Input(UInt(4.W))
    val z   = Output(UInt(8.W))
  })

  // Perform multiplication
  io.z := io.x * io.y

}


/**
 * An object extending App to generate the Verilog code.
 */
/*object MulApp extends App {
  (new chisel3.stage.ChiselStage).emitVerilog(new Mul(),Array("--target-dir","generated"))
}*/


object MulApp extends App {

  ChiselStage.emitSystemVerilogFile(
    new Mul,
    firtoolOpts = Array(
      "-o", "generated/Mul.v",
      "-disable-all-randomization",
      "-strip-debug-info")
  )
}