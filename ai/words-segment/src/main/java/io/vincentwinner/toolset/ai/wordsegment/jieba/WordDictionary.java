package io.vincentwinner.toolset.ai.wordsegment.jieba;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.Map.Entry;

public class WordDictionary {

    private static WordDictionary singleton;
    private static final String MAIN_DICT = "/dict.txt";
    private static final String USER_DICT_SUFFIX = ".dict";

    private final Map<String, Double> freqs = new HashMap<String, Double>();
    private final Set<String> loadedPath = new HashSet<String>();
    private Double minFreq = Double.MAX_VALUE;
    private Double total = 0.0;
    private DictSegment _dict;

    private WordDictionary() {
        this.loadDict();
    }

    public static WordDictionary getInstance() {
        if (singleton == null) {
            synchronized (WordDictionary.class) {
                if (singleton == null) {
                    singleton = new WordDictionary();
                    return singleton;
                }
            }
        }
        return singleton;
    }

    protected void init(Path configFile) {
        String abspath = configFile.toAbsolutePath().toString();
        synchronized (WordDictionary.class) {
            if (loadedPath.contains(abspath))
                return;
            DirectoryStream<Path> stream;
            try {
                stream = Files.newDirectoryStream(
                        configFile,
                        String.format(Locale.getDefault(),
                        "*%s", USER_DICT_SUFFIX));
                for (Path path: stream){
                    singleton.loadUserDict(path);
                }
                loadedPath.add(abspath);
            } catch (IOException e) {
               throw new RuntimeException(e);
            }
        }
    }

    protected void init(String[] paths) {
        synchronized (WordDictionary.class) {
            for (String path: paths){
                if (!loadedPath.contains(path)) {
                    try {
                        singleton.loadUserDict(path);
                        loadedPath.add(path);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    /**
     * let user just use their own dict instead of the default dict
     */
    public void resetDict(){
    	_dict = new DictSegment((char) 0);
    	freqs.clear();
    }

    protected void loadDict() {
        _dict = new DictSegment((char) 0);
        try (InputStream is = this.getClass().getResourceAsStream(MAIN_DICT);){
            if (is == null) return;
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            long s = System.currentTimeMillis();
            while (br.ready()) {
                String line = br.readLine();
                String[] tokens = line.split("[\t ]+");
                if (tokens.length < 2) continue;
                String word = tokens[0];
                double freq = Double.parseDouble(tokens[1]);
                total += freq;
                word = addWord(word);
                freqs.put(word, freq);
            }
            for (Entry<String, Double> entry : freqs.entrySet()) {
                entry.setValue((Math.log(entry.getValue() / total)));
                minFreq = Math.min(entry.getValue(), minFreq);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected String addWord(String word) {
        if (null != word && !"".equals(word.trim())) {
            String key = word.trim().toLowerCase(Locale.getDefault());
            _dict.fillSegment(key.toCharArray());
            return key;
        } else {
            return null;
        }
    }

    protected void loadUserDict(Path userDict) {
        loadUserDict(userDict.toString());
    }

    protected void loadUserDict(String userDictPath) {
        try (InputStream is = this.getClass().getResourceAsStream(userDictPath);){
            if(is == null) return;
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            long s = System.currentTimeMillis();
            int count = 0;
            while (br.ready()) {
                String line = br.readLine();
                String[] tokens = line.split("[\t ]+");
                if (tokens.length < 1) continue;
                String word = tokens[0];
                double freq = 3.0d;
                if (tokens.length == 2)
                    freq = Double.parseDouble(tokens[1]);
                word = addWord(word);
                freqs.put(word, Math.log(freq / total));
                count++;
            }
            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected DictSegment getTrie() {
        return this._dict;
    }

    protected boolean containsWord(String word) {
        return freqs.containsKey(word);
    }

    protected Double getFreq(String key) {
        if (containsWord(key))
            return freqs.get(key);
        else
            return minFreq;
    }

}
