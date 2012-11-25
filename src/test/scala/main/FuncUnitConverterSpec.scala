package main

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{FunSpec}
import fnumatic.ebc.{ FuncUnit}

class FuncUnitConverterSpec extends FunSpec with ShouldMatchers  {

  describe("a FuncUnitConverter") {
    import fnumatic.ebc.FuConvert._

    it("should convert a function to a FuncUnit") {
      def test(i: Int) = i + 1
      val fu1 = fu(test)
      fu1.isInstanceOf[FuncUnit[Int, Int]] should be(true)

    }

    it("should extend a function with conversion method") {
      def test(i: Int): Int = i + 1

      val fu1 = (test _).tofu
      fu1.isInstanceOf[FuncUnit[Int, Int]] should be(true)
    }



  }

}