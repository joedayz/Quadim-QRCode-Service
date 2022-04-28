package ai.quadim.qrbuilder;

import static org.testng.AssertJUnit.assertEquals;

import ai.quadim.qrbuilder.exception.InvalidSizeException;
import java.awt.image.BufferedImage;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Tests for class ZXingBuilder
 */
@Test
public class QRCodeTest {

  @Test(invocationCount=10)
  public void whenBuildingSimpleQrCode_thenEncodedDataIsAsExpected() throws Exception {
    BufferedImage qrcode = QRCode.ZXingBuilder.build(builder ->
        builder.withSize(250, 250)
            .and()
            .withData("the answer to everything is 42")
    ).toImage();

    Assert.assertEquals("the answer to everything is 42",  TestHelpers.decode(qrcode));
  }

  @Test(invocationCount=10)
  public void whenBuildingSimpleQrCode_thenWidthIsAsExpected() throws Exception {
    BufferedImage qrcode = QRCode.ZXingBuilder.build(builder ->
        builder.withSize(200, 250)
            .and()
            .withData("To be or not to be that is...")
    ).toImage();

    assertEquals(200,  qrcode.getWidth());
  }

  @Test(invocationCount=10)
  public void whenBuildingSimpleQrCode_thenHeightIsAsExpected() throws Exception {
    BufferedImage qrcode = QRCode.ZXingBuilder.build(builder ->
        builder.withSize(300, 300)
            .and()
            .withData("Daffy duck is awesome")
    ).toImage();

    assertEquals(300,  qrcode.getHeight());
  }

  @Test(expectedExceptions= InvalidSizeException.class)
  public void whenWidthIsZero_thenThrowCouldNotCreateQRCodeException(){
    QRCode.ZXingBuilder.build(builder ->
        builder.withSize(0, 1)
    ).toImage();
  }

  @Test(expectedExceptions=InvalidSizeException.class)
  public void whenHeightIsZero_thenThrowCouldNotCreateQRCodeException(){
    QRCode.ZXingBuilder.build(builder ->
        builder.withSize(1, 0)
    ).toImage();
  }
}