package Simple

import chisel3._
// _root_ disambiguates from package chisel3.util.circt if user imports chisel3.util._
import _root_.circt.stage.ChiselStage

class Adder extends Module{
    val io = IO(new Bundle{
        val a= Input(UInt(8.W))
        val b = Input(UInt(8.W))
        val y = Output(UInt(8.W))

    })
    io.y:=io.a+io.b
}


class Register extends Module{
    val io= IO(new Bundle {
        val d = Input(UInt(8.W))
        val q = Output(UInt(8.W))
    })
    val reg = RegInit(0.U)
    reg:=io.d
    io.q := reg
}

class Counter10 extends  Module{
    val io = IO(new Bundle {
        val dout= Output(UInt(8.W))
    })


    val add = Module(new  Adder())
    val reg = Module(new Register())

    val count = reg.io.q

    add.io.a := 1.U
    add.io.b := count

    val result = add.io.y

    val next = Mux(count===9.U,0.U,result)

    reg.io.d := next
    io.dout := count

}


object Counter10App extends App {

    ChiselStage.emitSystemVerilogFile(
        new Counter10,
        firtoolOpts = Array(
            "-o", "generated/SimpleALU.v",
            "-disable-all-randomization",
            "-strip-debug-info")
    )
}