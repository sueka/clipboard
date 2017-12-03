package me.sueka.clipboard

import java.awt.{ Toolkit, Image }
import java.awt.datatransfer.{ DataFlavor, StringSelection }
import scala.util.{ Try, Success, Failure }
import scalaz.effect.IO
import scalaz.Scalaz.ToBindOps

object Clipboard {
  private val clipboard = Toolkit.getDefaultToolkit().getSystemClipboard()

  /** Returns IO that will set the clipboard to a given String.
   *
   *  @param string the string the clipboard will be set to
   */
  def setClipboardString(string: String): IO[Unit] = {
    val selection = new StringSelection(string)
    IO(clipboard.setContents(selection, selection))
  }

  /** Returns IO that gets an optional String out of the clipboard. */
  def getClipboardString: IO[Try[String]] = {
    IO(Try(clipboard.getData(DataFlavor.stringFlavor).toString))
  }

  /** Returns IO that modifies a String of the clipboard.
   *
   *  @param f the function for modifying a String.
   */
  def modifyClipboardString(f: String => String): IO[Boolean] = {
    for {
      s <- getClipboardString
    } yield s match {
      case Failure(_) => false
      case Success(sc) => (setClipboardString(f(sc)) >> IO(true)).unsafePerformIO
    }
  }

  /** Returns IO that will set the clipboard to a given Image.
   *
   *  @param image the image the clipboard will be set to
   */
  def setClipboardImage(image: Image): IO[Unit] = {
    val selection = new ImageSelection(image)
    IO(clipboard.setContents(selection, selection))
  }

  /** Return IO that gets an optional Image out of the clipboard. */
  def getClipboardImage: IO[Try[Image]] = {
    IO(Try(clipboard.getData(DataFlavor.imageFlavor).asInstanceOf[Image]))
  }
}
