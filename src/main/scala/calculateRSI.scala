import scala.collection.convert.ImplicitConversions.`buffer AsJavaList`
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object calculateRSI {

  def RSI(listValue : Array[dailyValue],n : Int) : List[Double] = {
    // init ListBuffer
    val resRSI = new ListBuffer[Double]()

    // resolution du RSI
    for(y <- 0 to (n-1)){
      val H:ListBuffer[Double] = ListBuffer()
      val B:ListBuffer[Double] = ListBuffer()
      listValue.map{
        c => H+=c.getHigh();
        B+=c.getLow()
      }
      //resRSI+= HoB(H.toArray)(y)/(HoB(H.toArray)(y)-HoB(B.toArray)(y))
      var res = 100 - (100 / (1 + (HoB(H.toArray, n)(y) / HoB(B.toArray, n)(y))))
      if (res.isNaN()){
        res = 0.0
      }
      resRSI+=res
    }
    resRSI.toList
  }

  // definition de la Moyenne Mobile Exponnetiel d'une List de valeurs (Haute ou Basse) values sur n jours
  def MME(values : ArrayBuffer[Double], n: Int) : List[Double] = {
    val result = new ListBuffer[Double]()

    // definition de MME d'indice t (Recursivit√©)
    def MMEt(t : Int) : Double = {
      //println("INDICE: "+t)
      if ( t==0 ) 0.0
      else {
        val zyx = values.get(t) * (2.00/(n+1.00)) + MMEt(t - 1) * (1 - (2.00/(n+1.00)))
        zyx
      }
    }
    for( x <- 0 to (n-1)){
      result+=MMEt(x)
    }
    //print("RESULT: "+result.toList)
    result.toList
  }

  // MME des valeurs hautes ou basse
  def HoB(values: Array[Double], n: Int):List[Double] = {
    var buf = collection.mutable.ArrayBuffer(values: _*)
    MME(buf, n)
  }

}
