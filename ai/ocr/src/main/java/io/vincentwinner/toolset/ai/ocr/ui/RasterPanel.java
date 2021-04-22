package io.vincentwinner.toolset.ai.ocr.ui;

import javax.swing.*;
import java.awt.*;

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
public class RasterPanel extends JFrame{
	private int width = 1350;
    private int height = 300;
    private Canvas canvas = null;
    
    public RasterPanel(){
    	super();
        this.setTitle("EasyOCR Raster Panel");
        this.setSize(width, height);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocation(350, 650);
        this.setLayout(null);
        
        this.canvas = new Canvas(1100,100);
        this.canvas.setBounds(new Rectangle(75, 30, 1100,100));
        this.add(this.canvas);
        
        this.setVisible(true);
    }
}
