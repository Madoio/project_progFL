object Main {

  def main(args : Array[String]): Unit = {
    val arrayOfValue = fetchJson.extractDailyValueFromJson(fetchJson.requestJson())
    val rsi = calculateRSI.RSI(arrayOfValue,arrayOfValue.size)
    println(rsi)
  }
}
