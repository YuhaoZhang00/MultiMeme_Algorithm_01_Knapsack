package com.yuhao.utils;

import com.yuhao.config.Constant;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

import static com.yuhao.config.Constant.*;

public class MyFileWriter {

    private BufferedWriter bw;
    private int lineCount = 1;

    public MyFileWriter(int numberOdItems, int knapsackCapacity, int trialId) throws IOException {
        StringBuffer filePath = new StringBuffer(FOLDER_TO_WRITE);
        filePath.append('/');
        if (Objects.equals(INSTANCE_NAME, "")) {
            filePath.append(FILE_TO_READ.split("/")[FILE_TO_READ.split("/").length - 1].split("\\.")[0]);
        } else {
            filePath.append(INSTANCE_NAME);
        }
        filePath.append('_');
        filePath.append(numberOdItems);
        filePath.append('_');
        filePath.append(knapsackCapacity);
        filePath.append("_trial");
        filePath.append(trialId);
        filePath.append("_output.txt");

        File file = new File(filePath.toString());
        file.createNewFile();
        bw = new BufferedWriter(new FileWriter(file));
    }

    public void writeToFile(double value1, double value2) throws IOException {
        if (lineCount < MAX_NUMBER_OF_ENTRIES_TO_WRITE) {
            bw.write(value1 + " " + value2);
            bw.newLine();
            lineCount++;
        } else if (lineCount == MAX_NUMBER_OF_ENTRIES_TO_WRITE) {
            bw.write(value1 + " " + value2);
            bw.newLine();
            lineCount++;
            bw.close();
        }
    }
}
