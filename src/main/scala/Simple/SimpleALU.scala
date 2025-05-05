// See LICENSE.txt for license details.
package Simple

import chisel3._
// _root_ disambiguates from package chisel3.util.circt if user imports chisel3.util._
import _root_.circt.stage.ChiselStage

class BasicALU extends Module {
  val io = IO(new Bundle {
    val a = Input(UInt(4.W))
    val b = Input(UInt(4.W))
    val opcode = Input(UInt(4.W))
    val out = Output(UInt(4.W))
  })
  io.out := 0.U //THIS SEEMS LIKE A HACK/BUG
  when (io.opcode === 0.U) {
    io.out := io.a //pass A
  } .elsewhen (io.opcode === 1.U) {
    io.out := io.b //pass B
  } .elsewhen (io.opcode === 2.U) {
    io.out := io.a + 1.U //increment A by 1
  } .elsewhen (io.opcode === 3.U) {
    io.out := io.a - 1.U //increment B by 1
  } .elsewhen (io.opcode === 4.U) {
    io.out := io.a + 4.U //increment A by 4
  } .elsewhen (io.opcode === 5.U) {
    io.out := io.a - 4.U //decrement A by 4
  } .elsewhen (io.opcode === 6.U) {
    io.out := io.a + io.b //add A and B
  } .elsewhen (io.opcode === 7.U) {
    io.out := io.a - io.b //subtract B from A
  } .elsewhen (io.opcode === 8.U) {
    io.out := io.a < io.b //set on A less than B
  } .otherwise { 
    io.out :=  (io.a === io.b).asUInt //set on A equal to B
  }
}



object BasicALUApp extends App {

  ChiselStage.emitSystemVerilogFile(
    new BasicALU,
    firtoolOpts = Array(
      "-o", "generated/BasicALU.v",
      "-disable-all-randomization",
      "-strip-debug-info")
  )
}

class SimpleALU extends Module {
  val io = IO(new Bundle {
    val a      = Input(UInt(4.W))
    val b      = Input(UInt(4.W))
    val opcode = Input(UInt(2.W))
    val out = Output(UInt(4.W))
  })
  io.out := 0.U
  when (io.opcode === 0.U) {
    io.out := io.a + io.b //ADD
  } .elsewhen (io.opcode === 1.U) {
    io.out := io.a - io.b //SUB
  } .elsewhen (io.opcode === 2.U) {
    io.out := io.a  	     //PASS A
  } .otherwise {
    io.out := io.b        //PASS B
  }
}

/**
 * An object extending App to generate the Verilog code.
 */
/*
object SimpleALUApp extends App {
  (new chisel3.stage.ChiselStage).emitVerilog(new SimpleALU(),Array("--target-dir","generated"))
}
*/


object SimpleALUApp extends App {

  ChiselStage.emitSystemVerilogFile(
    new SimpleALU,
    firtoolOpts = Array(
      "-o", "generated/SimpleALU.v",
      "-disable-all-randomization",
      "-strip-debug-info")
  )
}