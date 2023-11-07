package br.com.samuel.snakegame.dao;

import java.io.*;
import java.util.Map;

public class DataRecoder {

    private final String FILE_NAME = "scores.dat";

    public Map<String, Integer> recover() throws IOException {

        Map<String, Integer> s = null;

        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(FILE_NAME));
            s = (Map<String, Integer>) in.readObject();
        }
        catch (ClassNotFoundException e) {
            System.err.print(e);
        }
        catch (IOException e) {
            throw e;
        }
        finally {
            if (in != null) in.close();
        }

        return s;
    }

    public void recorder(Map<String, Integer> scores) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
            out.writeObject(scores);
        }
        catch (IOException e) {
            throw e;
        }
        finally {
            if (out != null) out.close();
        }
    }
}
