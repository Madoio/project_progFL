import org.joda.time.LocalDate


class dailyValue(date:LocalDate, open:Double, high:Double, low:Double, close:Double, adjClose:Double, volume:Double, divAmount:Double, spliCoef:Double) {
  def getOpen():Double=this.open
  def getHigh():Double=this.high
  def getLow():Double=this.low
  def getClose():Double=this.close
  def getAdjClose():Double=this.adjClose
  def getDivAmount():Double=this.divAmount
  def getSpliCoef():Double=this.spliCoef
  def getDate():LocalDate=this.date
}
