package me.sueka.clipboard

import scala.util.Success
import org.scalatest._
import scalaz.effect.IO.putStrLn
import scalaz.Scalaz.ToBindOps

class ClipboardSpec extends FlatSpec with Matchers {
  "Clipboard" should "set the clipboard String and get the same String" in {
    val setClipboard = Clipboard.setClipboardString("Hello, world!")
    val getClipboard = Clipboard.getClipboardString

    val Success(string) = (setClipboard >> getClipboard).unsafePerformIO
    string should be("Hello, world!")
  }

  it should "set the clipboard to \"Hello, world!\", reverse it, and get \"!dlrow ,olleH\" of the clipboard" in {
    val setClipboard = Clipboard.setClipboardString("Hello, world!")
    val modifyClipboard = Clipboard.modifyClipboardString(_.reverse)
    val getClipboard = Clipboard.getClipboardString

    val Success(string) = (setClipboard >> modifyClipboard >> getClipboard).unsafePerformIO
    string should be("!dlrow ,olleH")
  }

  it should "get/set the clipboard at the time of calling unsafePerformIO" in {
    val writeApple = Clipboard.setClipboardString("apple")
    val writeBanana = Clipboard.setClipboardString("banana")
    val reverse = Clipboard.modifyClipboardString(_.reverse)
    val read = Clipboard.getClipboardString

    val Success(apple) = (writeApple >> read).unsafePerformIO
    val Success(banana) = (writeBanana >> read).unsafePerformIO
    val Success(ananab) = (reverse >> read).unsafePerformIO

    apple should be("apple")
    banana should be("banana")
    ananab should be("ananab")
  }
}
