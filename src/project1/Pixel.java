package project1;

public class Pixel {
    private short red;
    private short green;
    private short blue;

    public Pixel() {
        this.red = 0;
        this.green = 0;
        this.blue = 0;
    }

    public short getRed() {
        return red;
    }

    public void setRed(short red) {
        if (!isValidPixel(red)) {
            return;
        }
        this.red = red;
    }

    public short getGreen() {
        return green;
    }

    public void setGreen(short green) {
        if (!isValidPixel(green)) {
            return;
        }
        this.green = green;
    }

    public short getBlue() {
        return blue;
    }

    public void setBlue(short blue) {
        if (!isValidPixel(blue)) {
            return;
        }
        this.blue = blue;
    }

    private boolean isValidPixel(int val) {
        return val >= 0 && val <= 255;
    }
}
