package mocks

import shootemup.IRange

class MockRange(private val returnValue: Boolean = false): IRange {

  private val valuesUsed: MutableSet<Int>

  init{
    this.valuesUsed = hashSetOf()
  }

  override fun contains(value: Int): Boolean {
    valuesUsed.add(value)
    return returnValue
  }

  fun calledWith(value: Int) = valuesUsed.contains(value)
}
