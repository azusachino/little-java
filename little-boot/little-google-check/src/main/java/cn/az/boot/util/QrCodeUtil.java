package cn.az.boot.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * The type Qr code util.
 *
 * @author azusachino
 */
public class QrCodeUtil {

    /**
     * The constant BARCODE_WIDTH.
     */
    public static int BARCODE_WIDTH = 80, BARCODE_HEIGHT = 80;

    /**
     * The constant QRCODE_WIDTH.
     */
    public static int QRCODE_WIDTH = 200, QRCODE_HEIGHT = 200;

    /**
     * The constant FORMAT.
     */
    public static String FORMAT = "jpg";

    /**
     * The constant BLACK.
     */
    public static int BLACK = 0x000000;

    /**
     * The constant WHITE.
     */
    public static int WHITE = 0xFFFFFF;

    private static final int ICON_WIDTH = QRCODE_WIDTH / 6;
    private static final int ICON_HEIGHT = QRCODE_HEIGHT / 6;
    private static final int HALF_ICON_WIDTH = ICON_WIDTH / 2;

    private static final MultiFormatWriter WRITER = new MultiFormatWriter();
    private static final MultiFormatReader READER = new MultiFormatReader();

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        String iconPath = "C:\\icon.jpg";
        String content = "http://www.baidu.com";
        File qrCode = new File("C:\\QRCode." + FORMAT);
        File qrCodeWithIcon = new File("C:\\QRCodeWithIcon." + FORMAT);
        // 生成二维码
        writeToFile(createQrCode(content), qrCode);
        // 生成带图标的二维码
        writeToFile(createQrCodeWithIcon(content, iconPath), qrCodeWithIcon);
        // 解析二维码
        System.out.println(parseImage(qrCode));
        // 解析带图标的二维码
        System.out.println(parseImage(qrCodeWithIcon));
        // 编码成字节数组
        byte[] data = createQrCodeToBytes(content);
        String result = parseQrFromBytes(data);
        System.out.println(result);
        String barCodeContent = "6936983800013";
        File barCode = new File("C:\\BarCode." + FORMAT);
        // 生成一维码
        writeToFile(createBarCode(barCodeContent), barCode);
        // 解析一维码
        System.out.println(parseImage(barCode));
    }

    /**
     * 将String编码成二维码的图片后，使用字节数组表示，便于传输。
     *
     * @param content the content
     * @return byte [ ]
     * @throws WriterException the writer exception
     * @throws IOException     the io exception
     */
    public static byte[] createQrCodeToBytes(String content)
            throws WriterException, IOException {
        BufferedImage image = createQrCode(content);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image, FORMAT, os);
        return os.toByteArray();
    }


    /**
     * 把一个String编码成二维码的BufferedImage.
     *
     * @param content the content
     * @return buffered image
     * @throws WriterException the writer exception
     */
    public static BufferedImage createQrCode(String content) throws WriterException {
        // 长和宽一样，所以只需要定义一个SIZE即可
        BitMatrix matrix = WRITER.encode(content, BarcodeFormat.QR_CODE, QRCODE_WIDTH, QRCODE_HEIGHT);
        return toBufferedImage(matrix);
    }

    /**
     * 编码字符串为二维码，并在该二维码中央插入指定的图标。
     *
     * @param content  the content
     * @param iconPath the icon path
     * @return buffered image
     * @throws WriterException the writer exception
     */
    public static BufferedImage createQrCodeWithIcon(String content, String iconPath) throws WriterException, IOException {
        BitMatrix matrix = WRITER.encode(content, BarcodeFormat.QR_CODE, QRCODE_WIDTH, QRCODE_HEIGHT);
        // 读取Icon图像
        BufferedImage scaleImage = scaleImage(iconPath, ICON_HEIGHT, ICON_WIDTH, true);
        int[][] iconPixels = new int[ICON_WIDTH][ICON_WIDTH];
        for (int i = 0; i < scaleImage.getWidth(); i++) {
            for (int j = 0; j < scaleImage.getHeight(); j++) {
                iconPixels[i][j] = scaleImage.getRGB(i, j);
            }
        }
        // 二维码的宽和高
        int halfW = matrix.getWidth() / 2;
        int halfH = matrix.getHeight() / 2;
        // 计算图标的边界：
        int minX = halfW - HALF_ICON_WIDTH;
        int maxX = halfW + HALF_ICON_WIDTH;
        int minY = halfH - HALF_ICON_WIDTH;
        int maxY = halfH + HALF_ICON_WIDTH;
        int[] pixels = new int[QRCODE_WIDTH * QRCODE_WIDTH];
        // 修改二维码的字节信息，替换掉一部分为图标的内容。
        for (int y = 0; y < matrix.getHeight(); y++) {
            for (int x = 0; x < matrix.getWidth(); x++) {
                // 如果点在图标的位置，用图标的内容替换掉二维码的内容
                // Icon四周的边框宽度
                int frameWidth = 2;
                if (x > minX && x < maxX && y > minY && y < maxY) {
                    int indexX = x - halfW + HALF_ICON_WIDTH;
                    int indexY = y - halfH + HALF_ICON_WIDTH;
                    pixels[y * QRCODE_WIDTH + x] = iconPixels[indexX][indexY];
                }
                // 在图片四周形成边框
                else if ((x > minX - frameWidth && x < minX + frameWidth && y > minY - frameWidth && y < maxY + frameWidth)
                        || (x > maxX - frameWidth && x < maxX + frameWidth && y > minY - frameWidth && y < maxY + frameWidth)
                        || (x > minX - frameWidth && x < maxX + frameWidth && y > minY - frameWidth && y < minY + frameWidth)
                        || (x > minX - frameWidth && x < maxX + frameWidth && y > maxY - frameWidth && y < maxY + frameWidth)) {
                    pixels[y * QRCODE_WIDTH + x] = WHITE;
                } else {
                    // 这里是其他不属于图标的内容。即为二维码没有被图标遮盖的内容，用矩阵的值来显示颜色。
                    pixels[y * QRCODE_WIDTH + x] = matrix.get(x, y) ? BLACK : WHITE;
                }
            }
        }
        // 用修改后的字节数组创建新的BufferedImage.
        BufferedImage image = new BufferedImage(QRCODE_WIDTH, QRCODE_HEIGHT, BufferedImage.TYPE_INT_RGB);
        image.getRaster().setDataElements(0, 0, QRCODE_WIDTH, QRCODE_WIDTH, pixels);
        return image;
    }

    /**
     * 从一个二维码图片的字节信息解码出二维码中的内容。
     *
     * @param data the data
     * @return string string
     * @throws ReaderException the reader exception
     * @throws IOException     the io exception
     */
    public static String parseQrFromBytes(byte[] data) throws ReaderException, IOException {
        ByteArrayInputStream is = new ByteArrayInputStream(data);
        BufferedImage image = ImageIO.read(is);
        return parseImage(image);
    }


    /**
     * 从一个图片文件中解码出二维码中的内容。
     *
     * @param file the file
     * @return 解析后的内容 。
     * @throws IOException     the io exception
     * @throws ReaderException the reader exception
     */
    public static String parseImage(File file) throws IOException, ReaderException {
        BufferedImage image = ImageIO.read(file);
        return parseImage(image);
    }

    /**
     * 将字符串编码成一维码（条形码）。
     *
     * @param content the content
     * @return buffered image
     * @throws WriterException the writer exception
     */
    public static BufferedImage createBarCode(String content) throws WriterException {
        MultiFormatWriter writer = new MultiFormatWriter();
        // 一维码的宽>高。这里我设置为 宽:高=2:1
        BitMatrix matrix = writer.encode(content, BarcodeFormat.EAN_13, BARCODE_WIDTH * 3, BARCODE_HEIGHT);
        return toBufferedImage(matrix);
    }

    /**
     * 从图片中解析出一维码或者二维码的内容。如果解析失败，则抛出NotFoundException。
     *
     * @param image the image
     * @return string string
     * @throws NotFoundException the not found exception
     */
    public static String parseImage(BufferedImage image) throws NotFoundException {
        LuminanceSource source = new BufferedImageLuminanceSource(image);
        Binarizer binarizer = new HybridBinarizer(source);
        BinaryBitmap bitmap = new BinaryBitmap(binarizer);
        Result result = READER.decode(bitmap);
        // 这里丢掉了Result中其他一些数据
        return result.getText();
    }

    /**
     * 将BufferedImage对象输出到指定的文件中。
     *
     * @param image    the image
     * @param destFile the dest file
     * @throws IOException the io exception
     */
    public static void writeToFile(BufferedImage image, File destFile) throws IOException {
        ImageIO.write(image, FORMAT, destFile);
    }

    /**
     * 将一个BitMatrix对象转换成BufferedImage对象
     *
     * @param matrix m
     * @return bf img
     */
    private static BufferedImage toBufferedImage(BitMatrix matrix) {
        int width = matrix.getWidth();
        int height = matrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
            }
        }
        return image;
    }

    /**
     * 把传入的原始图像按高度和宽度进行缩放，生成符合要求的图标。
     *
     * @param srcImageFile 源文件地址
     * @param height       目标高度
     * @param width        目标宽度
     * @param hasFiller    比例不对时是否需要补白：true为补白; false为不补白;
     * @throws IOException i
     */
    private static BufferedImage scaleImage(String srcImageFile, int height, int width, boolean hasFiller) throws IOException {
        // 缩放比例
        double ratio;
        File file = new File(srcImageFile);
        BufferedImage srcImage = ImageIO.read(file);
        Image destImage = srcImage.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH);
        // 计算比例
        if ((srcImage.getHeight() > height) || (srcImage.getWidth() > width)) {
            if (srcImage.getHeight() > srcImage.getWidth()) {
                ratio = (new Integer(height)).doubleValue() / srcImage.getHeight();
            } else {
                ratio = (new Integer(width)).doubleValue() / srcImage.getWidth();
            }
            AffineTransformOp op = new AffineTransformOp(
                    AffineTransform.getScaleInstance(ratio, ratio), null);
            destImage = op.filter(srcImage, null);
        }
        // 补白
        if (hasFiller) {
            BufferedImage image = new BufferedImage(
                    width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphic = image.createGraphics();
            graphic.setColor(Color.white);
            graphic.fillRect(0, 0, width, height);
            if (width == destImage.getWidth(null)) {
                graphic.drawImage(destImage, 0, (height - destImage.getHeight(null)) / 2,
                        destImage.getWidth(null), destImage.getHeight(null), Color.white, null);
            } else {
                graphic.drawImage(destImage, (width - destImage.getWidth(null)) / 2, 0,
                        destImage.getWidth(null), destImage.getHeight(null), Color.white, null);
            }
            graphic.dispose();
            destImage = image;
        }
        return (BufferedImage) destImage;
    }
}
