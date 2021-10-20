import scala.collection.convert.ImplicitConversions.`buffer AsJavaList`
import scala.collection.mutable.{ArrayBuffer, ListBuffer}

object calculateRSI {

  def RSI(listValue : Array[dailyValue],n : Int) : List[Double] = {
    // init ListBuffer
    val resRSI = new ListBuffer[Double]()

    // definition de A
    def A(n : Int) : Double = 2 / (1 + n)

    // definition de la Moyenne Mobile Exponnetiel d'une List de valeurs (Haute ou Basse) values sur n jours
    def MME(values : ArrayBuffer[Double]) : List[Double] = {
      val result = new ListBuffer[Double]()

      // definition de MME d'indice t (Recursivité)
      def MMEt(t : Int) : Double = {
        if ( t==0 ) 0.0
        else values.get(t)*A(n)+MMEt(t-1)*(1-A(n))
      }
      for( x <- 0 to n){
        result+=MMEt(x)
      }
      result.toList
    }

    // MME des valeurs hautes ou basse
    def HoB(values: Array[Double]):List[Double] = {
      var buf = collection.mutable.ArrayBuffer(values: _*)
      MME(buf)
    }

    // resolution du RSI
    for(y <- 0 to n){
      val H:ListBuffer[Double] = ListBuffer[Double]
      val B:ListBuffer[Double] = ListBuffer[Double]
      listValue.map{
        c => H+=c.getHigh()
          B+=c.getLow()
      }
      resRSI+= HoB(H.toArray)(y)*100/(HoB(H.toArray)(y)-HoB(B.toArray)(y))
    }
    resRSI.toList
  }



}
