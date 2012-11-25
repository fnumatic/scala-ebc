package main

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{FunSpec}
import fnumatic.ebc.{Board, Fu, FuncUnit}
import reactive.Observing

class FuncUnitSpec extends FunSpec with ShouldMatchers  {

  import fnumatic.ebc.FuConvert._

  describe("a functional unit") {
    val fu1 = new FuncUnit[Int, Int] {
      def process(i: Int) = i + 3
    }

    it("should fire on apply") {
      val signal = fu1.outpin.hold(0)
      fu1(1)
      signal.now should be(4)
    }


  }

  describe("some functional units") {
    def meth(i: Int) = i + 2

    val fu1 = fu(meth)
    val fu2 = fu(meth)

    it("there should be flow between them") {
      fu1 >> fu2

      val signal = fu2.outpin.hold(0)
      fu1(0)

      signal.now should be(4)
    }
  }

  describe("some functional units and boards") {
    def meth(i: Int) = i + 2
    val fu1,fu2,fu3=fu(meth)

    implicit val o=new Observing{}

    it("there should be flow between them") {
      val board = new Board[Int, Int] {
        in >> fu1 >> fu2 >> fu3 >> out
      }

      val signal = board.outpin.hold(0)
      board(0)
      signal.now should be(6)
    }
  }

}