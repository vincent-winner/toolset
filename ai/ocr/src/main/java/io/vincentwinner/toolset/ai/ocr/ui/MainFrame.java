package io.vincentwinner.toolset.ai.ocr.ui;


import io.vincentwinner.toolset.ai.ocr.constant.Constant;
import io.vincentwinner.toolset.ai.ocr.model.ImageModel;
import io.vincentwinner.toolset.ai.ocr.neuralnetwork.Network;
import io.vincentwinner.toolset.ai.ocr.util.ImageUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
public class MainFrame extends JFrame {
    private int width = 1350;
    private int height = 450;
    private Canvas canvas = null;

    //four buttons: clear,tell num,train,test
    private JButton jbClear = null;
    private JButton jbNum = null;
    private JButton jbTrain = null;
    private JButton jbMnistTrain = null;
    private JButton jbLoad = null;
    private JButton jbTest = null;

    private Network network = null;

    public MainFrame(){
        super();
        this.setTitle("EasyOCR");
        this.setSize(width, height);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(350, 200);
        this.setLayout(null);

        this.canvas = new Canvas(1080,280);
        this.canvas.setBounds(new Rectangle(85, 30, 1080,280));
        this.add(this.canvas);

        this.network = new Network(new int[]{28*28,30,10});

        this.jbClear = new JButton();
        this.jbClear.setText("clear");
        this.jbClear.setBounds(340, 360, 80, 30);
        this.add(jbClear);
        this.jbClear.addActionListener(e -> {
            canvas.clear();
            Constant.digit = -1;
        });

        this.jbNum = new JButton();
        this.jbNum.setText("tell num");
        this.jbNum.setBounds(440, 360, 80, 30);
        this.add(jbNum);
        this.jbNum.addActionListener(e -> {
            int[] outline = getOutline();
            if(outline[0] == -10){
                canvas.clear();
                JOptionPane.showMessageDialog(null, "Please draw one number into the rectangle");
            }else{
                String str = (String) JOptionPane.showInputDialog(null, "Please input the number you write：\n", "Tell Me Number", JOptionPane.PLAIN_MESSAGE, null, null,
                        "");
                try {
                    int digit = Integer.parseInt(str);
                    if (digit < 0 || digit > 9) {
                        canvas.clear();
                        JOptionPane.showMessageDialog(null, "I can only learn number 0~9");
                        Constant.digit = -1;
                    } else {
                        Constant.digit = digit;
                        //save image and digit
                        String fileName = saveJPanel(outline);
                        canvas.clear();
                        JOptionPane.showMessageDialog(null, "I have remember the number:" + digit + ". Image file path:" + fileName);
                    }
                } catch (Exception ex) {
                    //ex.printStackTrace();
                    canvas.clear();
                    Constant.digit = -1;
                    JOptionPane.showMessageDialog(null, "I can only learn number 0~9");
                }
            }
        });

        this.jbTrain = new JButton();
        this.jbTrain.setText("train");
        this.jbTrain.setBounds(540, 360, 80, 30);
        this.add(jbTrain);
        this.jbTrain.addActionListener(e -> {
            try {
                List<String> fileList = ImageUtil.getInstance().getImageList();
                if (fileList.size() < 500) {
                    JOptionPane.showMessageDialog(null, "You should create at least 500 train jpg. Try to use \"tell num\".");
                } else {
                    List<ImageModel> modelList = ImageUtil.getInstance().getImageModel(fileList);
                    //use modelList to train neural network
                    network.SGD(modelList, 10000, 0.1);
                }
            } catch (Exception ex){
                ex.printStackTrace();
            }
        });
        
        this.jbMnistTrain = new JButton();
        this.jbMnistTrain.setText("mnist train");
        this.jbMnistTrain.setBounds(640, 360, 100, 30);
        this.add(jbMnistTrain);
        this.jbMnistTrain.addActionListener(e -> {
            try {
                List<String> fileList = ImageUtil.getInstance().getMnistImageList();
                if (fileList.size() < 2000) {
                    JOptionPane.showMessageDialog(null, "You should create at least 2000 train png. Try to use \"tell num\".");
                } else {
                    List<ImageModel> modelList = ImageUtil.getInstance().getImageModel(random1000Pics(fileList));
                    //use modelList to train neural network
                    network.SGD(modelList, 10000, 0.1);
                }
            } catch (Exception ex){
                ex.printStackTrace();
            }
        });
        
        this.jbLoad = new JButton();
        this.jbLoad.setText("load");
        this.jbLoad.setBounds(760, 360, 80, 30);
        this.add(jbLoad);
        this.jbLoad.addActionListener(e -> {
        });

        this.jbTest = new JButton();
        this.jbTest.setText("recog");
        this.jbTest.setBounds(860, 360, 80, 30);
        this.add(jbTest);
        this.jbTest.addActionListener(e -> {
            try {
                File f = new File(Constant.trainFolder+"/train.data");
                if(!network.isTrain()&&!f.exists()){
                    JOptionPane.showMessageDialog(null,"You should train neural network first");
                }else{
                    if (!network.isTrain()&&f.exists()){
                        network.restore();
                    }
                    double[] raster = ImageUtil.getInstance().getRasterMatrixFromCanvas(canvas);
                    System.out.println("JerryDebug:Raster:"+raster.length);
                    StringBuilder sb = new StringBuilder();
                    for (int i=0;i<50;i++) sb.append(raster[i]).append(",");
                    for (int i=raster.length-50;i<raster.length;i++) sb.append(raster[i]).append(",");
                    System.out.println("JerryDebug:RasterContent:"+sb.toString());

                    int[] outline = getOutline();
                    int digit = network.predict(ImageUtil.getInstance().getGrayMatrixFromPanel(canvas, outline));
                    if(digit == -1){
                        JOptionPane.showMessageDialog(null,"I can not recognize this number");
                    }else{
                        JOptionPane.showMessageDialog(null,"I guess this number is:"+digit);
                    }
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
        });

        this.setVisible(true);
    }
    
    public List<String> random1000Pics(List<String> fileNames){    	
    	if (fileNames == null || fileNames.size()<1000) return fileNames;
    	else {
    		List<String> retVals = new ArrayList<String>();
    		while (retVals.size()<1000){
    			int pos = (int)(Math.random()*fileNames.size());
    			retVals.add(fileNames.remove(pos));
    		}
    		return retVals;
    	}
    }

    public int[] getOutline(){
//    	double[] canvasRasterMatrix = ImageUtil.getInstance().getRasterMatrixFromCanvas(canvas);
        double[] grayMatrix = ImageUtil.getInstance().getRawGrayMatrixFromPanel(canvas);
        int[] binaryArray = ImageUtil.getInstance().transGrayToBinaryValue(grayMatrix);
        int minRow = Integer.MAX_VALUE;
        int maxRow = Integer.MIN_VALUE;
        int minCol = Integer.MAX_VALUE;
        int maxCol = Integer.MIN_VALUE;
        for(int i=0;i<binaryArray.length;i++){
            int row = i/108;
            int col = i%108;
            if(binaryArray[i] == 1){
                if(minRow > row){
                    minRow = row;
                }
                if(maxRow < row){
                    maxRow = row;
                }
                if(minCol > col){
                    minCol = col;
                }
                if(maxCol < col){
                    maxCol = col;
                }
            }
        }
        int len = Math.max((maxCol-minCol+1)*10, (maxRow-minRow+1)*10);
        canvas.setOutLine(minCol*10, minRow*10, len, len);

        return new int[]{minCol*10, minRow*10, len, len};
    }

    public String saveJPanel(int[] outline){
        Dimension imageSize = this.canvas.getSize();
        BufferedImage image = new BufferedImage(imageSize.width,imageSize.height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        this.canvas.paint(graphics);
        graphics.dispose();
        try {
            //cut
            if(outline[0] + outline[2] > canvas.getWidth()){
                outline[2] = canvas.getWidth()-outline[0];
            }
            if(outline[1] + outline[3] > canvas.getHeight()){
                outline[3] = canvas.getHeight()-outline[1];
            }
            image = image.getSubimage(outline[0],outline[1],outline[2],outline[3]);
            //resize
            Image smallImage = image.getScaledInstance(Constant.smallWidth, Constant.smallHeight, Image.SCALE_SMOOTH);
            BufferedImage bSmallImage = new BufferedImage(Constant.smallWidth,Constant.smallHeight,BufferedImage.TYPE_INT_RGB);
            Graphics graphics1 = bSmallImage.getGraphics();
            graphics1.drawImage(smallImage, 0, 0, null);
            graphics1.dispose();

            String fileName = String.format("%s/%d_%s.jpg",Constant.trainFolder,Constant.digit,java.util.UUID.randomUUID());
            ImageIO.write(bSmallImage, "jpg", new File(fileName));
            return fileName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
