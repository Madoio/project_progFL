import org.joda.time.format.DateTimeFormat
import ujson.Value

import scala.collection.mutable.ListBuffer

object fetchJson {

  def requestJson():Value.Value={
    val token = sys.env.get("clé_API")

    val r = requests.get("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY_ADJUSTED&symbol=IBM&apikey="+token)

    val jsonR = ujson.read(r.text())

    jsonR
  }

  def extractDailyValueFromJson(jsonR:Value.Value):Array[dailyValue]={
    val today = jsonR("Time Series (Daily)").obj
    var dtf = DateTimeFormat.forPattern("yyyy-MM-dd")
    println(jsonR)

    val values:ListBuffer[dailyValue] = ListBuffer()

    today.map(
      c=> values+=new dailyValue(dtf.parseLocalDate(c._1),
        ujson.read(c._2)("1. open").toString().replace("\"","").toDouble,
        ujson.read(c._2)("2. high").toString().replace("\"","").toDouble,
        ujson.read(c._2)("3. low").toString().replace("\"","").toDouble,
        ujson.read(c._2)("4. close").toString().replace("\"","").toDouble,
        ujson.read(c._2)("5. adjusted close").toString().replace("\"","").toDouble,
        ujson.read(c._2)("6. volume").toString().replace("\"","").toDouble,
        ujson.read(c._2)("7. dividend amount").toString().replace("\"","").toDouble,
        ujson.read(c._2)("8. split coefficient").toString().replace("\"","").toDouble)
    )
    values.toArray
  }
}
