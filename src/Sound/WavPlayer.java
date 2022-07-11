package Sound;

import javax.sound.sampled.*;
import java.io.File;

public class WavPlayer extends Thread {
    /**
     * 音效类
     */

    //各种音效标记
    static public final byte PICK = 0;
    static public final byte EAT = 1;
    static public final byte BACK = 2;
    static public final byte GO = 3;

    //各种音效的文件对象
    static private final File pick=new File("voice/pick.wav");
    static private final File go=new File("voice/go.wav");
    static private final File back=new File("voice/back.wav");
    static private final File eat=new File("voice/eat.wav");

    private final File file;    //该对象的声音文件

    /**
     * 构造方法，通过flag确定该对象要播放的声音文件
     * @param flag 声音种类标识
     */
    public WavPlayer(byte flag) {
        switch (flag) {
            case GO -> this.file = go;
            case PICK -> this.file = pick;
            case BACK -> this.file = back;
            default -> this.file = eat;
        }
    }

    /**
     * 该对象（线程）需要完成的任务：播放声音文件的声音
     */
    @Override
    public void run() {
        AudioInputStream audioInputStream;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(file);
        } catch (Exception e) {
            System.out.println("声音文件读取失败！");
            e.printStackTrace();
            return;
        }
        AudioFormat format = audioInputStream.getFormat();
        SourceDataLine auline;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

        try {
            auline = (SourceDataLine) AudioSystem.getLine(info);
            auline.open(format);
        } catch (Exception e) {
            System.out.println("声音文件转化失败！");
            e.printStackTrace();
            return;
        }

        auline.start();
        int nBytesRead;
        byte[] abbytes = new byte[512];
        try {
            while ((nBytesRead = audioInputStream.read(abbytes, 0, abbytes.length)) != -1) {
                if (nBytesRead >= 0) {
                    auline.write(abbytes, 0, nBytesRead);
                }
            }

        } catch (Exception e) {
            System.out.println("声音文件转化失败！");
            e.printStackTrace();
        } finally {
            auline.drain();
            auline.close();
        }
    }
}