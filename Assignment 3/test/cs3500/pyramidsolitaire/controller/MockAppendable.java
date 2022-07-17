package cs3500.pyramidsolitaire.controller;

import java.io.IOException;

/**
 * Represents a mock implementation of an Appendable. For characters and strings to be appended to.
 * For the purpose of testing, only throws IOExceptions.
 */
public class MockAppendable implements Appendable {

  @Override
  public Appendable append(CharSequence csq) throws IOException {
    throw new IOException();
  }

  @Override
  public Appendable append(CharSequence csq, int start, int end) throws IOException {
    throw new IOException();
  }

  @Override
  public Appendable append(char c) throws IOException {
    throw new IOException();
  }
}
