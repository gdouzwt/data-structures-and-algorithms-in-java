/**
 * Class for doing encryption and decryption using the Caesar Cipher.
 */
public class CaesarCipher {
    protected char[] encoder = new char[26];
    protected char[] decoder = new char[26];

    public CaesarCipher(int rotation) {
        for (int k = 0; k < 26; k++) {
            encoder[k] = (char) ('A' + (k + rotation) % 26);
            decoder[k] = (char) ('A' + (k - rotation + 26) % 26);
        }
    }

    public String encrypt(String message) {
        return transform(message, encoder);
    }

    public String decrypt(String secret) {
        return transform(secret, decoder);
    }

    private String transform(String original, char[] code) {
        char[] msg = original.toCharArray();
        for (int k = 0; k < msg.length; k++) {
            if (Character.isUpperCase(msg[k])) {  // we have a letter to change
                int j = msg[k] - 'A';             // will be value from 0 to 25
                msg[k] = code[j];                 // replace the character
            }
        }
        return new String(msg);
    }

    public static void main(String[] args) {
        CaesarCipher cipher = new CaesarCipher(3);
        System.out.println("Encryption code = " + new String(cipher.encoder));
        System.out.println("Decryption code = " + new String(cipher.decoder));
        String message = "THE EAGLE IS IN PLAY; MEET AT JOE'S";
        String coded = cipher.encrypt(message);
        System.out.println("Secret: " + coded);
        String answer = cipher.decrypt(coded);
        System.out.println("Message: " + answer);
        int x = 5 % (-3);
        int y = (-5) % (3);
        int z = (-5) % (-3);
        System.out.printf("%d, %d, %d", x, y, z);

        // 所以 % 是 remainder operator
        // modulo operator vs. remainder operator ?
        // As define in C, the  % operation for a % b as:
        // a == (a / b * b) + a % b

        // Modulo and remainder operators differ with respect to negative values.
        // With a remainder operator, the sign of the result is the same as the sign
        // of the dividend while with a modulo operator the sign of the result is the
        // same as the divisor.


    }
}
