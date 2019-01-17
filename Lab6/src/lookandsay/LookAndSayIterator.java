package lookandsay;

import java.math.BigInteger;

/**
 * Implements {@link RIterator} to support returning of look-and-say sequence. It iterates over the
 * BigInteger collection elements to find next values, previous values, method that which finds if
 * it is possible to find next values or previous values.
 */
public class LookAndSayIterator implements RIterator<BigInteger> {
  private BigInteger seed;
  private BigInteger end;

  /**
   * Represents a constructor that takes two arguments: a starting seed and an end value. The seed
   * is the number at which the sequence must begin. All numbers returned by  next() and prev()
   * should be lesser than or equal to end (inclusive). If the seed is not a positive number, or is
   * not less than the end then it should throw an  IllegalArgumentException.
   */
  public LookAndSayIterator(BigInteger seed, BigInteger end) throws IllegalArgumentException {
    this.end = end;
    if (isSeedValid(seed)) {
      this.seed = seed;
    } else {
      throw new IllegalArgumentException("Seed should be less than end or greater than 0");
    }
  }

  /**
   * Represents a constructor that takes a starting seed as its only argument. The seed is the
   * number at which the sequence must begin. The end value will be a number with 100 9s (the
   * largest 100 digit number).If the seed is not a positive number, or is less than the end then it
   * should throw an IllegalArgumentException.
   */
  public LookAndSayIterator(BigInteger seed) throws IllegalArgumentException {
    this.end = createDefaultEndValue();
    if (isSeedValid(seed)) {
      this.seed = seed;
    } else {
      throw new IllegalArgumentException("Seed should be less than end or greater than 0");
    }
  }

  /**
   * Represents a constructor that takes no arguments. It should start the sequence with a seed of 1
   * (e.g. calling next and an end value as a number with 100 9s (the largest 100 digit number).
   */
  public LookAndSayIterator() {
    this.end = createDefaultEndValue();
    this.seed = new BigInteger("1");
  }

  /**
   * Appends "9" 100 times to a StringBuilder object and returns BigInteger equivalent of the same.
   *
   * @return end value in BigInteger.
   */
  private BigInteger createDefaultEndValue() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 100; i++) {
      sb.append("9");
    }
    return new BigInteger(sb.toString());
  }

  /**
   * Returns true if seed is valid for Look And Say sequence. Seed should be a positive number and
   * less than end value defined for this sequence.
   *
   * @param seed start value for lookAndSay sequence.
   * @return true if the seed is valid otherwise false.
   */
  private boolean isSeedValid(BigInteger seed) {
    return seed != null && seed.compareTo(BigInteger.ONE) >= 0 && seed.compareTo(end) < 0;
  }

  /**
   * Returns the current number in the sequence and revert to the previous number in the sequence.
   *
   * @return previous value in the list of type BigInteger.
   */
  @Override
  public BigInteger prev() {
    BigInteger current = seed;
    if (hasPrevious()) {
      seed = computeReverse(seed);
    }
    return current;
  }

  /**
   * Returns true if it is possible to go back one step in the lookAndSay sequence, false otherwise.
   * It is not possible to find previous value in the sequence if the length of the current value is
   * odd.
   *
   * @return true if the length of current number is even otherwise false.
   */
  @Override
  public boolean hasPrevious() {
    return seed.toString().length() % 2 == 0;
  }

  /**
   * Returns true if the next number to be returned is still lesser than end (specified in the
   * constructors), false otherwise. Since next can advance one value greater than the end, this
   * method compares seed with the end value.
   *
   * @return true if seed is less than or equal to end otherwise false.
   */
  @Override
  public boolean hasNext() {
    return seed.compareTo(end) <= 0;
  }

  /**
   * Returns the current number in the sequence and advances to the next number. If the next value
   * exceeds the end value, it doesn't advance but returns the same value on every call after that.
   *
   * @return next value in the sequence of type BigInteger.
   */
  @Override
  public BigInteger next() {
    BigInteger current = seed;
    if (hasNext()) {
      seed = findLookAndSaySequence(seed);
    }
    return current;
  }

  /**
   * Finds look-and-say sequence value for current number. It first converts BigInteger number to
   * string and starts incrementing i from 0th character. It increments the count until a new number
   * character is encountered. If a new character is encountered, it appends the value of the count
   * first and then that number and resets the count to original value to continue repeating the
   * same for the remaining characters.
   *
   * @return next value in the lookAndSay sequence of type BigInteger.
   */
  private BigInteger findLookAndSaySequence(BigInteger number) {
    String value = number.toString();
    int i = 0;
    int count = 1;
    StringBuilder sb = new StringBuilder();
    while (i < value.length() - 1) {
      if (value.charAt(i) == value.charAt(i + 1)) {
        count++;
      } else {
        sb.append(count);
        sb.append(value.charAt(i));
        count = 1;
      }
      i++;
    }
    sb.append(count);
    sb.append(value.charAt(i));
    return new BigInteger(sb.toString());
  }

  /**
   * Finds previous values in LookAndSay Sequence. It first converts BigInteger sequence to string
   * and takes two consecutive character values (i and i+1) at a time to generate a new value which
   * repeats second value(i+1) as many times the first(i). The newly generated sequence is appended
   * in StringBuilder object and returned as BigInteger in the end. It is not possible to generate
   * sequence if the lookAndSaySequence provided is odd.
   *
   * @param lookAndSaySequence value in BigInteger.
   * @return previous value of the sequence in BigInteger.
   */
  private BigInteger computeReverse(BigInteger lookAndSaySequence) {
    StringBuilder sb = new StringBuilder();
    String sequence = lookAndSaySequence.toString();
    for (int i = 0; i < sequence.length() - 1; ) {
      for (int j = 0; j < Character.getNumericValue(sequence.charAt(i)); j++) {
        sb.append(sequence.charAt(i + 1));
      }
      i = i + 2;
    }
    return new BigInteger(sb.toString());
  }
}
