package me.kuuds.jline;

import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;
import org.jline.reader.impl.history.DefaultHistory;
import org.jline.terminal.TerminalBuilder;

import java.io.IOException;

public class JlineDemo {

  public static void main(String[] args) throws IOException {
    var terminal = TerminalBuilder.builder()
        .system(true)
        .build();
    var lineReader = LineReaderBuilder.builder()
        .history(new DefaultHistory())
        .terminal(terminal)
        .build();

    final var prompt = "ksh > ";

    while (true) {
      String line;
      try {
        line = lineReader.readLine(prompt);
        if ("show".equals(line)) {
          new KProgress(terminal).print();
        } else {
          terminal.writer().println("echo " + line);
        }
      } catch (UserInterruptException e) {
        // Do nothing
      } catch (EndOfFileException e) {
        terminal.writer().println("\nByte.");
        terminal.writer().flush();
        return;
      }
    }
  }

}
