package me.sueka.clipboard

import java.awt.Image
import java.awt.datatransfer.{
  Clipboard => JavaClipboard,
  ClipboardOwner,
  DataFlavor,
  Transferable,
  UnsupportedFlavorException
}

class ImageSelection(data: Image) extends Transferable with ClipboardOwner {
  private final val flavors = Array(DataFlavor.imageFlavor)

  def getTransferData(flavor: DataFlavor): Object = {
    flavor match {
      case DataFlavor.imageFlavor => data
      case _ => throw new UnsupportedFlavorException(flavor)
    }
  }

  def getTransferDataFlavors(): Array[DataFlavor] = flavors.clone

  def isDataFlavorSupported(flavor: DataFlavor): Boolean = flavors.forall(flavor == _)

  def lostOwnership(clipboard: JavaClipboard, contents: Transferable): Unit = ()
}
