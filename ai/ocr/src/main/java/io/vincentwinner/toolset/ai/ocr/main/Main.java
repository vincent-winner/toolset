package io.vincentwinner.toolset.ai.ocr.main;

import io.vincentwinner.toolset.ai.ocr.constant.Constant;
import io.vincentwinner.toolset.ai.ocr.ui.MainFrame;
import io.vincentwinner.toolset.ai.ocr.ui.RasterPanel;

import java.io.File;

/*
Copyright [2017] [Pi Jing]

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.
*/
public class Main {
    //the main function of whole project
    public static void main(String[] args){
        //String folderName = System.getProperty("java.io.tmpdir");
    	String folderName = "D:\\workspace\\git\\EasyOCR\\EasyOCR";
        //create training data store path
        String trainFolder = folderName + "/data";
        File folder = new File(trainFolder);
        if(!folder.isDirectory()){
            folder.mkdir();
        }
        Constant.trainFolder = trainFolder;
        Constant.mnistTrainFolder = folderName + "/mnist_digital_jpg";
        System.out.println("JerryDebug:TrainFolder:"+trainFolder);
        new MainFrame();
        new RasterPanel();
    }
}
