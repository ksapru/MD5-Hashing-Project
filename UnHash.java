import java.security.*;

public class UnHash extends Thread {

    private int lim_set = 0;

    private long resume;

    public boolean finish_set = false;

    volatile private String hash = null;

    // run func

    public void run() {

        try {

            while (!finish_set) {
                if (hash != null) {

                    unhash(hash);
                }

            }
        } catch (Exception e) {

        }
    }

    // unhash func modified

    public int unhash(String to_unhash) throws NoSuchAlgorithmException {

        Hash hasher = new Hash();

        for (int temp = 0;; ++temp) {

            if (lim_set != 0 && (System.nanoTime() - resume) / 1e6 > lim_set) {

                System.out.println(hash);

                this.Hashrecov(null);

                return temp;

            }
            String tmpHash = hasher.hash(temp);

            if (tmpHash.equals(to_unhash)) {

                this.Hashrecov(null);

                System.out.println(temp);

                return temp;
            }
        }
    }

    public void Hashrecov(String hash) {

        this.hash = hash;

        this.resume = System.nanoTime();

    }

    public boolean isIdle() {

        return (hash == null);

    }

    public void finish_set() {

        finish_set = true;

    }

    public void setl_im(int lim_set) {

        this.lim_set = lim_set;

    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        String to_unhash = args[0];

        UnHash dehasher = new UnHash();

        System.out.println(dehasher.unhash(to_unhash));
    }
}
