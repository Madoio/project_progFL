import breeze.linalg._
import breeze.numerics._
import breeze.plot._

import scala.collection.mutable.ListBuffer

object plot {

  def plot(list: List[Double]) = {
    val f = Figure()
    val p = f.subplot(0)
    val x = list
    p += plot(x)
    p.title = "rsi plotting"
    f.saveas("rsi.png")
  }


}
