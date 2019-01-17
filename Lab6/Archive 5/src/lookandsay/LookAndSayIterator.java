package lookandsay;

import java.math.BigInteger;

/**
 * Implements {@link RIterator} to support lookandsay for look-and-say sequence. It iterates over
 * the BigInteger collection to find next values, previous values, method that says if it is
 * possible to find next values or previous values.
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
   * Represents a constructor that takes no arguments. This should start the sequence with a seed of
   * 1 (e.g. calling next and an end value as a number with 100 9s (the largest 100 digit number).
   */
  public LookAndSayIterator() {
    this.end = createDefaultEndValue();
    this.seed = new BigInteger("1");
  }

  /**
   * Appends "9" 100 times to a Stringbuilder object and returns BigInteger equivalent of the same.
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
   * Returns true if seed is valid for this lookandsay.
   *
   * @param seed start value for this lookandsay.
   * @return true if the seed is valid otherwise false.
   */
  private boolean isSeedValid(BigInteger seed) {
    return seed.intValue() > 1 && seed.compareTo(end) < 0;
  }

  /**
   * Returns the current number in the sequence and revert to the previous number in the sequence.
   *
   * @return previous value in the list of type BigInteger.
   */
  @Override
  public BigInteger prev() {
    BigInteger result = findLookAndSaySequence(seed);
    BigInteger prevSeed = seed.subtract(new BigInteger(1 + ""));
    if (isSeedValid(prevSeed)) {
      seed = seed.subtract(new BigInteger(1 + ""));
    }
    return result;
  }

  /**
   * Returns true if it is possible to go back one step, false otherwise.
   *
   * @return previous value in the list of type BigInteger.
   */
  @Override
  public boolean hasPrevious() {
    BigInteger prevSeed = seed.subtract(new BigInteger(1 + ""));
    return (prevSeed.compareTo(new BigInteger(1 + "")) > 0);
  }

  /**
   * Returns true if the next number to be returned is still lesser than end (specified in the
   * constructors), false otherwise.
   */
  @Override
  public boolean hasNext() {
    BigInteger nextSeed = seed.add(new BigInteger(1 + ""));
    return findLookAndSaySequence(nextSeed).compareTo(end) <= 0;
  }

  /**
   * Returns the current number in the sequence and advance to the next number.
   *
   * @return next value in the list of type BigInteger.
   */
  @Override
  public BigInteger next() {
    BigInteger result = findLookAndSaySequence(seed);
    BigInteger nextSeed = seed.add(new BigInteger(1 + ""));
    if (isSeedValid(nextSeed)) {
      seed = seed.add(new BigInteger(1 + ""));
    }
    return result;
  }

  /**
   * Finds look-and-say sequence value for current counter.
   *
   * @return next value in the list of type BigInteger.
   */
  private BigInteger findLookAndSaySequence(BigInteger number) {
    String value = number.toString();
    int i = 0;
    int j = 0;
    int count = 1;
    StringBuilder sb = new StringBuilder();
    while (i < value.length() - 1) {
      if (value.charAt(i) == value.charAt(i + 1)) {
        count++;
      } else {
        sb.append(count);
        sb.append(value.charAt(i));
        j = j + count;
        count = 1;
      }
      i++;
    }
    sb.append(count);
    sb.append(value.charAt(i));
    return new BigInteger(sb.toString());
  }
}
