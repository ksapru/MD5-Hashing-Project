import java.io.*;
import java.security.*;

public class Hash {

    public String hash(int num) {

        int inNum = num;
        String inputString = Integer.toString(inNum);
        String out_hash = null;

        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(inputString.getBytes());

            byte[] bytes = md.digest();

            StringBuilder string_one = new StringBuilder();

            for (int i = 0; i < bytes.length; i++) {
                string_one.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            
            out_hash = string_one.toString();
        }

        catch (NoSuchAlgorithmException e) {

            e.printStackTrace();
        }

        return out_hash;
    }

    public static void main(String[] args) throws Exception {

        Hash obj = new Hash();

        BufferedReader reeader = new BufferedReader(new InputStreamReader(System.in));
        int inNum = Integer.parseInt(reeader.readLine());

        String out_hash = obj.hash(inNum);
        System.out.println(out_hash);

    }

    public String hash(String numString) {
        return null;
    }

}