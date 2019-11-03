package cci.algo;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.IntStream;

public class Base64 {

  private static final byte[] BASE_64_TABLE = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
      'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c',
      'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
      'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};

  /**
   * It's the lookup table for "URL and Filename safe Base64" as specified in Table 2 of the RFC
   * 4648, with the '+' and '/' changed to '-' and '_'. This table is used when BASE64_URL is
   * specified.
   */
  private static final byte[] toBase64URL = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
      'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd',
      'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
      'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'};

  public static void main(String[] args) {
    String str =
        "Man is distinguished, not only by his reason, but by this singular passion from other animals, "
            + "which is a lust of the mind, that by a perseverance of delight in the continued and indefatigable "
            + "generation of knowledge, exceeds the short vehemence of any carnal pleasure.";
    StringBuilder sb = new StringBuilder();
    IntStream.range(0, 15000).forEach(a -> sb.append(str));
    byte[] strBytes = sb.toString().getBytes();
    Instant now = Instant.now();
    byte[] bytes = new byte[5380000];
    String encodedString1 = new String(encode(strBytes, bytes));
    Instant end = Instant.now();

    System.out.println(Duration.between(now, end).toMillis());
    now = Instant.now();
    String encodedString2 = java.util.Base64.getEncoder().encodeToString(strBytes);
    end = Instant.now();
    System.out.println(Duration.between(now, end).toMillis());

    if (!encodedString1.equals(encodedString2)) {
      throw new IllegalArgumentException();
    }
    
    byte[] bytes3 = new byte[30];
    String encodedString3 = new String(encode("any + old & data".getBytes(), bytes3));
    System.out.println(encodedString3);
    

  }


  public static  byte[] encode(final byte[] str, final byte[] dest) {
    int mask = 0, indx = 2, current = 0, i = 0;
    for (int a = 0; a < str.length; a++) {
      current = str[a] >> indx;
      dest[i++] = BASE_64_TABLE[current ^ mask];
      mask = str[a] & ~(current << indx);
      if ((indx += 2) > 6) {
        dest[i++] = BASE_64_TABLE[mask];
        indx = 2;
        mask = 0;
      }
      mask <<= (8 - indx);
    }
    if (mask != 0) {
      dest[i++] = BASE_64_TABLE[mask];
      int count = (8 - indx) / 2;
      for (int a = 0; a < count; a++) {
        dest[i++] = '=';
      }
    }
    return dest;
  }
  
  public static  byte[] decode(final byte[] str, final byte[] dest) {
    
    
    return dest;
  }


  public static List<String> decode(String s) {
    return null;
  }



}
