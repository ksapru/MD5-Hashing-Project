import java.io.*;
import java.security.*;
import java.util.*;
import java.nio.file.*;

public class Dispatcher {

    public void dispatch(String fileName, int n, int limit) throws NoSuchAlgorithmException {

        Path inputFile = Paths.get(fileName);

        UnHash[] de_hash_func = new UnHash[n];

        for (int a = 0; a < de_hash_func.length; a++) {

            de_hash_func[a] = new UnHash();

            de_hash_func[a].start();

            de_hash_func[a].setl_im(limit);

        }

        LinkedList<String> resQueue = new LinkedList<String>();

        if (Files.exists(inputFile)) {

            File handle_file = inputFile.toFile();

            try (BufferedReader in = new BufferedReader(new FileReader(handle_file))) {

                String line_reader;

                while ((line_reader = in.readLine()) != null) {

                    resQueue.add(line_reader);

                }

                while (!resQueue.isEmpty()) {

                    for (int i = 0; i < de_hash_func.length; i++) {

                        if (de_hash_func[i].isIdle()) {

                            de_hash_func[i].Hashrecov(resQueue.remove());
                        }

                        if (resQueue.isEmpty()) {

                            break;
                        }
                    }
                }

                for (int i = 0; i < de_hash_func.length; i++) {

                    de_hash_func[i].finish_set();
                }

            }

            catch (FileNotFoundException e) {

                e.printStackTrace();

            }

            catch (IOException e) {

                e.printStackTrace();
            }

        }

        else {

        }

    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

        String inp_file = args[0];

        int n = Integer.parseInt(args[1]);

        int lim = 0;

        if (args.length == 3) {

            lim = Integer.parseInt(args[2]);

        }

        Dispatcher disp_func = new Dispatcher();

        disp_func.dispatch(inp_file, n, lim);
    }
}
