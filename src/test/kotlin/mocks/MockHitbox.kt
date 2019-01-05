import shootemup.*

class MockHitbox(private val returnValue: Boolean = false): IRange {

  override fun contains(value: Int):Boolean {
    return returnValue
  }
}
