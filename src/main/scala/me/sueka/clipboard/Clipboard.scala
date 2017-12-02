package me.sueka.clipboard

import java.awt.{ Toolkit, Image }
import java.awt.datatransfer.{ DataFlavor, StringSelection }
import scala.util.Try
import scalaz.effect.IO
import scalaz.Scalaz.ToBindOps

object Clipboard {
  private val clipboard = Toolkit.getDefaultToolkit().getSystemClipboard()

  /** Returns IO that will set a given String to the clipboard.
   *
   *  @param string the string the clipboard will be set to
   */
  def setClipboardString(string: String): IO[Unit] = {
    val selection = new StringSelection(string)
    IO(clipboard.setContents(selection, selection))
  }

  /** Returns IO that get an optional String out of the clipboard. */
  def getClipboardString: IO[Option[String]] = {
    IO(Try(clipboard.getData(DataFlavor.stringFlavor).toString).toOption)
  }

  /** Returns IO that modify a String of the clipboard.
   *
   *  @param f the function for modifying a String.
   */
  def modifyClipboardString(f: String => String): IO[Boolean] = {
    for {
      s <- getClipboardString
    } yield s match {
      case None => false
      case Some(sc) => (setClipboardString(f(sc)) >> IO(true)).unsafePerformIO
    }
  }

  /** Returns IO that will set a given Image to the clipboard.
   *
   *  @param image the image the clipboard will be set to
   */
  def setClipboardImage(image: Image): IO[Unit] = {
    val selection = new ImageSelection(image)
    IO(clipboard.setContents(selection, selection))
  }

  /** Return IO that get an optional Image out of the clipboard. */
  def getClipboardImage: IO[Option[Image]] = {
    IO(Try(clipboard.getData(DataFlavor.imageFlavor).asInstanceOf[Image]).toOption)
  }
}
