package main

import org.scalatest.matchers.ShouldMatchers
import org.scalatest.{FunSpec}
import fnumatic.ebc.Board
import reactive.Observing

class BoardSpec extends FunSpec with ShouldMatchers  {
implicit val observing=new Observing {}
  describe("a board") {
      val board= new Board[Unit,Unit]{ }

    it("should fire on apply") {
      var executed =false
      board.in.outpin.foreach(v => executed =true)
      board()
      executed should be(true)
    }

  }

}