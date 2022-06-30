package me.kuuds.jline;

import org.jline.terminal.Terminal;
import org.jline.utils.InfoCmp;

import java.util.stream.Stream;

public class KProgress {

  Terminal terminal;

  public KProgress(Terminal terminal) {
    this.terminal = terminal;
  }

  public void print() {
    var message = "Progress";
    int length = 10;
    var timeInterval = 1000;
    char incomplete = '░'; // U+2591 Unicode Character
    char complete = '█'; // U+2588 Unicode Character
    StringBuilder builder = new StringBuilder();
    Stream.generate(() -> incomplete).limit(length).forEach(builder::append);
    System.out.println(message);
    for (int i = 0; i < length; i++) {
      builder.replace(i, i + 1, String.valueOf(complete));
      terminal.puts(InfoCmp.Capability.delete_line);
      String progressBar = "\r" + builder;
      terminal.writer().write(progressBar);
      terminal.writer().flush();
      try {
        Thread.sleep(timeInterval);
      } catch (InterruptedException ignored) {

      }
    }
    terminal.writer().println();
    terminal.writer().flush();
  }

}
