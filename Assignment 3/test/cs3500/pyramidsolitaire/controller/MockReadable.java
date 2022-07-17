package cs3500.pyramidsolitaire.controller;

import java.io.IOException;
import java.nio.CharBuffer;

/**
 * Represents a mock implementation of a Readable. For characters and strings to be read from.
 * For the purpose of testing, only throws IOExceptions.
 */
public class MockReadable implements Readable {

  @Override
  public int read(CharBuffer cb) throws IOException {
    throw new IOException();
  }
}
