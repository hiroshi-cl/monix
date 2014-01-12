package monifu.concurrent.atomic

import java.util.concurrent.atomic.{AtomicLong => JavaAtomicLong}
import scala.annotation.tailrec

final class AtomicLong private (ref: JavaAtomicLong) extends AtomicNumber[Long] {
  type Underlying = JavaAtomicLong
  def asJava = ref

  def get: Long = ref.get()

  def set(update: Long) = ref.set(update)

  def lazySet(update: Long) = ref.lazySet(update)

  def compareAndSet(expect: Long, update: Long): Boolean =
    ref.compareAndSet(expect, update)

  def weakCompareAndSet(expect: Long, update: Long): Boolean =
    ref.weakCompareAndSet(expect, update)

  def getAndSet(update: Long): Long =
    ref.getAndSet(update)

  def plusOp(a: Long, b: Long) = a + b
  def minusOp(a: Long, b: Long) = a - b
  def incrOp(a: Long, b: Int): Long = a + b
}

object AtomicLong {
  def apply(initialValue: Long): AtomicLong = new AtomicLong(new JavaAtomicLong(initialValue))
  def apply(ref: JavaAtomicLong): AtomicLong = new AtomicLong(ref)
}