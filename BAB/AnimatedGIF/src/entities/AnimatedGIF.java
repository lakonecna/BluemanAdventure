package entities;

import imageprocessing.GifDecoder;

import java.awt.*;
import java.awt.image.BufferedImage;

public class AnimatedGIF {
    private Point position;
    private BufferedImage[] frames;
    private int frameCount = -1;
    private int currentFrame = -1;
    private boolean isReady = false;
    private GifDecoder decoder;

    public AnimatedGIF(int x,int y) {
        position = new Point();
        position.x = x;
        position.y = y;
    }

    public void readGif(String gifFile) {
        decoder = new GifDecoder();
        decoder.read(getClass().getResourceAsStream(gifFile));
        frameCount = decoder.getFrameCount();
        currentFrame = 0;
        frames = new BufferedImage[frameCount];
        for(int i = 0; i < frameCount; ++i) {
            frames[i] = decoder.getFrame(i);
        }
        isReady = true;
    }

    public void draw(Graphics2D g) {
        if(isReady) {
            g.drawImage(frames[currentFrame],position.x,position.y,null);
        }
    }

    public void nextFrame() {
        //System.out.println(currentFrame + " becomes " + currentFrame + 1);
        ++currentFrame;
        if(currentFrame >= frameCount) {
            currentFrame = 0;
        }
    }
}
