# Clipboard

is a library to set, get, and modify the system clipboard.

## Usage

The main method of

``` scala
import me.sueka.clipboard.Clipboard
import scalaz.Scalaz.ToBindOps
import scalaz.effect.IO.putStrLn

object ClipboardExample extends App {
  val read = Clipboard.getClipboardString
  (read.map(_ getOrElse null) >>= putStrLn).unsafePerformIO
}
```

prints a String of the system clipboard.

## License

[3-Clause BSD License](./LICENSE.txt)
