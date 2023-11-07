package br.com.samuel.snakegame.dao;

import br.com.samuel.snakegame.entities.Score;

import java.io.*;
import java.util.List;
import java.util.Map;

public class DataRecoder {

    private final String FILE_NAME = "scores.dat";

    public List<Score> recover() throws IOException {

        List<Score> s = null;

        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(FILE_NAME));
            s = (List<Score>) in.readObject();
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

    public void recorder(List<Score> scores) throws IOException {
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
