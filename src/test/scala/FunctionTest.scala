import org.joda.time.LocalDate
import org.scalatest.freespec.AnyFreeSpec

import scala.collection.mutable.ArrayBuffer

class FunctionTest extends AnyFreeSpec{

  "test MMET " - {
    ": MME for (100, 105, 110) and n=3 (A=0.5)" - {
      "should return 0 for 1st element, 105/2 =  52,5 for the 2nd and (110/2 + 105/4) = 81,25" in {
        val ints = ArrayBuffer[Double]()
        ints += 100
        ints += 105
        ints += 110
        assert( calculateRSI.MME(ints, 3) == ArrayBuffer(0.0,52.5,81.25))
      }
    }
  }

  "test HOB " - {
    ": HOB for (100, 105, 110) and n=3 (A=0.5)" - {
      "should return 0 for 1st element, 105/2 =  52,5 for the 2nd and (110/2 + 105/4) = 81,25" +
        ", which is the same as before because it is a wrapper to the MME" in {
        val ints = Array(100.0, 105, 110)
        assert( calculateRSI.HoB(ints, 3) == List(0.0,52.5,81.25))
      }
    }
  }

  "test RSI " - {
    ": RSI for D1 (H:100, B:95) D2 (H:105, B:100) and D3 (H:110, B:105) and n=3 (A=0.5)" - {
      "should return (0, 51.219, 51.181) " in {
        val d1 = new dailyValue(new LocalDate(System.currentTimeMillis()),0.0,100,95,0,0,0,0,0)
        val d2 = new dailyValue(new LocalDate(System.currentTimeMillis()),0.0,105,100,0,0,0,0,0)
        val d3 = new dailyValue(new LocalDate(System.currentTimeMillis()),0.0,110,105,0,0,0,0,0)
        val dailyArray = Array(d1, d2, d3)
        assert( calculateRSI.RSI(dailyArray, 3)(0) == 0)
        assert( 51.21 <= calculateRSI.RSI(dailyArray, 3)(1) && calculateRSI.RSI(dailyArray, 3)(1) <= 51.22)
        assert( 51.18 <= calculateRSI.RSI(dailyArray, 3)(2) && calculateRSI.RSI(dailyArray, 3)(2) <= 51.185)
      }
    }
  }

}
