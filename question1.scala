class Rational(n: Int, d: Int) {
  require(d != 0, "Denominator must be nonzero")

  private val gcdValue: Int = gcd(n.abs, d.abs)
  val numer: Int = n / gcdValue
  val denom: Int = d / gcdValue

  override def toString: String = s"$numer / $denom"

  def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)

  def neg: Rational = new Rational(-numer, denom)

  def sub(that: Rational): Rational =
    new Rational(numer * that.denom - that.numer * denom, denom * that.denom)
}

object Main {
  def main(args: Array[String]): Unit = {
    val n = new Rational(5,8)
    val x = new Rational(3, 4)
    val y = new Rational(5, 8)
    val z = new Rational(2, 7)

    val result1 = n.neg
    val result2 = x.sub(y).sub(z)
    println(result1)
    println(result2)
  }
}
