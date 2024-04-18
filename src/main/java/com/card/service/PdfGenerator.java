package com.card.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;

import org.apache.commons.compress.utils.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.card.entity.WorkOrder;
import com.card.repo.WorkOrderRepo;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.card.controller.*;

@Service
public class PdfGenerator {
	
	private static final int QR_CODE_SIZE = 200;
	static Logger logger = LoggerFactory.getLogger(PdfGenerator.class);
	
	public byte[] generatePdf(WorkOrder workOrderData) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            logger.info(":::::::::::::Page added to document");
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
            	
            	//String photoPath = "src/main/resources/static/images/RTL_LOGO.png";  
           	
            	// Generate QR code for the work order details
//				String qrCodeData = workOrderData.getWorkOrderNum() + " " + workOrderData.getPolfNum() + " "
//						+ workOrderData.getPolfQty() + " " + workOrderData.getCustomerPoOrder() + " "
//						+ workOrderData.getCustomerName() + " " + workOrderData.getProductName() + " "
//						+ workOrderData.getCustArtworkRef() + " " + workOrderData.getSchemeAuthority() + " "
//						+ workOrderData.getChipType() + " " + workOrderData.getCardType() + " "
//						+ workOrderData.getElectricalInfo() + " " + workOrderData.getGraphicalInfo() + " "
//						+ workOrderData.getFoilInfo() + " " + workOrderData.getBinNumber() + " "
//						+ workOrderData.getPersoBatchName() + " " + workOrderData.getBatchQty() + " "
//						+ workOrderData.getRemarks();


            	
            	String WorkOrderNum1=workOrderData.getWorkOrderNum();
            	String PolfNum=workOrderData.getPolfNum();
            	String PolfQty=workOrderData.getPolfQty();
            	String CustomerPoOrder=workOrderData.getCustomerPoOrder();
            	String CustomerName=workOrderData.getCustomerName();
            	String ProductName=workOrderData.getProductName();
            	String CustArtworkRef=workOrderData.getCustArtworkRef();
            	String SchemeAuthority=workOrderData.getSchemeAuthority();
            	String ChipType=workOrderData.getChipType();
            	String CardType=workOrderData.getCardType();
            	String ElectricalInfo=workOrderData.getElectricalInfo();
            	String GraphicalInfo=workOrderData.getGraphicalInfo();
            	String FoilInfo=workOrderData.getFoilInfo();
            	String BinNumber=workOrderData.getBinNumber();
            	String getPersoBatch=workOrderData.getPersoBatchName();
            	String BatchQty=workOrderData.getBatchQty();
            	String Remarks=workOrderData.getRemarks();

            	
            	
            	// Generate QR code image
                byte[] qrCodeBytes = generateImageQRCode(WorkOrderNum1+"\t"+PolfNum+"\t"+PolfQty+"\t"+CustomerPoOrder+"\t"+CustomerName+"\t"+ProductName+"\t"+CustArtworkRef+"\t"+SchemeAuthority+"\t"+ChipType+"\t"+CardType+"\t"+ElectricalInfo+"\t"+GraphicalInfo+"\t"+FoilInfo+"\t"+BinNumber+"\t"+getPersoBatch+"\t"+BatchQty+"\t"+Remarks, 148, 148);
                logger.info("::::::::::::Qr genrated. QR bytes length" + qrCodeBytes.length);
                
                // Add QR code image to PDF
                PDImageXObject qrCodeImage = PDImageXObject.createFromByteArray(document, qrCodeBytes, "QR Code");
                contentStream.drawImage(qrCodeImage, 180, 315); 

            	//PDImageXObject image = PDImageXObject.createFromFile(photoPath, document);


             // Define the position and size of the image on the page
                float x = 55;
                float y = 750;
                float width = 130;
                float height = 45;

                // Add the image to the page
                //contentStream.drawImage(image, x, y, width, height);
            	
                
            	//vertical line 1(left border)
            	contentStream.setLineWidth(1f);
                contentStream.moveTo(30, 800); 
                contentStream.lineTo(30, 85); 
                contentStream.stroke();
                
                //vertical line 2(right border)
                contentStream.setLineWidth(1f);
                contentStream.moveTo(580, 800); 
                contentStream.lineTo(580, 85); 
                contentStream.stroke();

            	
            	//horizontal line 1(upper border)
                contentStream.setLineWidth(1f);
                contentStream.moveTo(30, 800); 
                contentStream.lineTo(580, 800); 
                contentStream.stroke();
            	
//                //vertical line 3 (header)
//            	contentStream.setLineWidth(1f);
//                contentStream.moveTo(250, 800); 
//                contentStream.lineTo(250, 746); 
//                contentStream.stroke();
//                
                //vertical line 4(content)
                contentStream.setLineWidth(1f);
                contentStream.moveTo(175, 746); 
                contentStream.lineTo(175, 185); 
                contentStream.stroke();
                
                
            	// Add header with logo and company name
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 16);
                contentStream.beginText();
                contentStream.newLineAtOffset(180, 770); 
                contentStream.showText("Banking Personalization Work Order ");
                contentStream.endText();
                

                //horizontal line 2(header)
                contentStream.setLineWidth(1f);
                contentStream.moveTo(30, 745); 
                contentStream.lineTo(580, 745); 
                contentStream.stroke();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 730); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText("WORK ORDER NO :");
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(180, 730); // Adjust position
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText(workOrderData.getWorkOrderNum());
                contentStream.endText();
                
                //vertical line 5(left wo date)
                contentStream.setLineWidth(1f);
                contentStream.moveTo(380, 745); 
                contentStream.lineTo(380, 725); 
                contentStream.stroke();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(390, 730); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText("WO DATE:");
                contentStream.endText();
                
              //vertical line 6(right wo date)
                contentStream.setLineWidth(1f);
                contentStream.moveTo(450, 745); 
                contentStream.lineTo(450, 725); 
                contentStream.stroke();
                
             // Get the current time in milliseconds
                long millis = System.currentTimeMillis();

                // Instantiate java.sql.Date using the milliseconds
                java.sql.Date currentDate = new java.sql.Date(millis);

                // Create a SimpleDateFormat object to format the date
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

                // Format the date using the SimpleDateFormat object
                String formattedDate = dateFormat.format(currentDate);
                
                contentStream.beginText();
                contentStream.newLineAtOffset(455, 730); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText(formattedDate);
                contentStream.endText();
                
                //horizontal line 3
                contentStream.setLineWidth(1f);
                contentStream.moveTo(30, 725); 
                contentStream.lineTo(580, 725); 
                contentStream.stroke();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 710); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText("POLF NO :");
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(180, 710); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText(workOrderData.getPolfNum());
                contentStream.endText();
               
                // horizontal line 4
                contentStream.setLineWidth(1f);
                contentStream.moveTo(30, 705); 
                contentStream.lineTo(580, 705); 
                contentStream.stroke();

                contentStream.beginText();
                contentStream.newLineAtOffset(50, 690); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText("POLF QTY :");
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(180, 690); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText(workOrderData.getPolfQty());
                contentStream.endText();
                
             // horizontal line 5
                contentStream.setLineWidth(1f);
                contentStream.moveTo(30, 685); 
                contentStream.lineTo(580, 685); 
                contentStream.stroke();

                contentStream.beginText();
                contentStream.newLineAtOffset(50, 670); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText("CUSTOMER PO/ORDER :");
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(180, 670); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText(workOrderData.getCustomerPoOrder());
                contentStream.endText();
                
                //horizontal line 6
                contentStream.setLineWidth(1f);
                contentStream.moveTo(30, 665); 
                contentStream.lineTo(580, 665); 
                contentStream.stroke();

                contentStream.beginText();
                contentStream.newLineAtOffset(50, 650); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText("CUSTOMER NAME :");
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(180, 650); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText(workOrderData.getCustomerName());
                contentStream.endText();
                
              //horizontal line 7
                contentStream.setLineWidth(1f);
                contentStream.moveTo(30, 645); 
                contentStream.lineTo(580, 645); 
                contentStream.stroke();

                contentStream.beginText();
                contentStream.newLineAtOffset(50, 630); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText("PRODUCT NAME :");
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(180, 630); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText(workOrderData.getProductName());
                contentStream.endText();
                
              //horizontal line 8
                contentStream.setLineWidth(1f);
                contentStream.moveTo(30, 625); 
                contentStream.lineTo(580, 625); 
                contentStream.stroke();

                contentStream.beginText();
                contentStream.newLineAtOffset(50, 610); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText("CUST ARTWORK REF :");
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(180, 610); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText(workOrderData.getCustArtworkRef());
                contentStream.endText();
                
              //horizontal line 9
                contentStream.setLineWidth(1f);
                contentStream.moveTo(30, 605); 
                contentStream.lineTo(580, 605); 
                contentStream.stroke();

                contentStream.beginText();
                contentStream.newLineAtOffset(50, 590); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText("SCHEME AUTHORITY :");
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(180, 590); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText(workOrderData.getSchemeAuthority());
                contentStream.endText();
                
              //horizontal line 10
                contentStream.setLineWidth(1f);
                contentStream.moveTo(30, 585); 
                contentStream.lineTo(580, 585); 
                contentStream.stroke();

                contentStream.beginText();
                contentStream.newLineAtOffset(50, 570); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText("CHIP TYPE :");
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(180, 570); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText(workOrderData.getChipType());
                contentStream.endText();
                
              //horizontal line 11
                contentStream.setLineWidth(1f);
                contentStream.moveTo(30, 565); 
                contentStream.lineTo(580, 565); 
                contentStream.stroke();

                contentStream.beginText();
                contentStream.newLineAtOffset(50, 550); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText("CARD TYPE :");
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(180, 550); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText(workOrderData.getCardType());
                contentStream.endText();
                
              //horizontal line 12
                contentStream.setLineWidth(1f);
                contentStream.moveTo(30, 545); 
                contentStream.lineTo(580, 545); 
                contentStream.stroke();

                contentStream.beginText();
                contentStream.newLineAtOffset(50, 530); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText("ELECTRICAL INFO :");
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(180, 530); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText(workOrderData.getElectricalInfo());
                contentStream.endText();
                
              //horizontal line 13
                contentStream.setLineWidth(1f);
                contentStream.moveTo(30, 525); 
                contentStream.lineTo(580, 525); 
                contentStream.stroke();

                contentStream.beginText();
                contentStream.newLineAtOffset(50, 510); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText("GRAPHICAL INFO :");
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(180, 510); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText(workOrderData.getGraphicalInfo());
                contentStream.endText();
                
              //horizontal line 14
                contentStream.setLineWidth(1f);
                contentStream.moveTo(30, 505); 
                contentStream.lineTo(580, 505); 
                contentStream.stroke();

                contentStream.beginText();
                contentStream.newLineAtOffset(50, 490); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText("FOIL/ RIBBON INFO :");
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(180, 490); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText(workOrderData.getFoilInfo());
                contentStream.endText();
                
              //horizontal line 15
                contentStream.setLineWidth(1f);
                contentStream.moveTo(30, 485); 
                contentStream.lineTo(580, 485); 
                contentStream.stroke();

                contentStream.beginText();
                contentStream.newLineAtOffset(50, 470); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText("BIN NUMBER :");
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(180, 470); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText(workOrderData.getBinNumber());
                contentStream.endText();
                
              //horizontal line 16
                contentStream.setLineWidth(1f);
                contentStream.moveTo(30, 465); 
                contentStream.lineTo(580, 465); 
                contentStream.stroke();

                contentStream.beginText();
                contentStream.newLineAtOffset(50, 450); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText("PERSO BATCH NAME :");
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(180, 450); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText(workOrderData.getPersoBatchName());
                contentStream.endText();
                
              //horizontal line 17
                contentStream.setLineWidth(1f);
                contentStream.moveTo(30, 445); 
                contentStream.lineTo(580, 445); 
                contentStream.stroke();
                
              //horizontal line 18
                contentStream.setLineWidth(1f);
                contentStream.moveTo(30, 325); 
                contentStream.lineTo(580, 325); 
                contentStream.stroke();

                contentStream.beginText();
                contentStream.newLineAtOffset(50, 310); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText("BATCH QTY :");
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(180, 310); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText(workOrderData.getBatchQty());
                contentStream.endText();
                
              //horizontal line 18
                contentStream.setLineWidth(1f);
                contentStream.moveTo(30, 305); 
                contentStream.lineTo(580, 305); 
                contentStream.stroke();

                contentStream.beginText();
                contentStream.newLineAtOffset(50, 290); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText("NO. OF ENVLOPES :");
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(180, 290); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText("NA");
                contentStream.endText();
                
              //horizontal line 19
                contentStream.setLineWidth(1f);
                contentStream.moveTo(30, 285); 
                contentStream.lineTo(580, 285); 
                contentStream.stroke();

                contentStream.beginText();
                contentStream.newLineAtOffset(50, 270); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText("NO. OF CARRIERS :");
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(180, 270); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText("NA");
                contentStream.endText();
                
              //horizontal line 20
                contentStream.setLineWidth(1f);
                contentStream.moveTo(30, 265);
                contentStream.lineTo(580, 265); 
                contentStream.stroke();

                contentStream.beginText();
                contentStream.newLineAtOffset(50, 250); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText("NO. OF CARDS :");
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(180, 250); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText(workOrderData.getNumOfCards());
                contentStream.endText();
                
              //horizontal line 21
                contentStream.setLineWidth(1f);
                contentStream.moveTo(30, 245); 
                contentStream.lineTo(580, 245); 
                contentStream.stroke();

                contentStream.beginText();
                contentStream.newLineAtOffset(50, 230); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText("REMARKS :");
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(180, 230); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText(workOrderData.getRemarks());
                contentStream.endText();
                
              //horizontal line 22 
                contentStream.setLineWidth(1f);
                contentStream.moveTo(30, 225); 
                contentStream.lineTo(580, 225); 
                contentStream.stroke();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 210); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText("PREPARED BY :");
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(180, 210); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText(workOrderData.getPreparedBy());
                contentStream.endText();
                
              //vertical line 7(left data/time )
            	contentStream.setLineWidth(1f);
                contentStream.moveTo(395, 225); 
                contentStream.lineTo(395, 185); 
                contentStream.stroke();
                 
              //vertical line 8(right data/time )
              	contentStream.setLineWidth(1f);
                contentStream.moveTo(450, 225); 
                contentStream.lineTo(450, 145); 
                contentStream.stroke();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(397, 210); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 8);
                contentStream.showText("DATE/ TIME :");
                contentStream.endText();
                
             // Create a SimpleDateFormat object to format the date and time
                SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm a"); 
                
             // Format the date using the SimpleDateFormat object
                String formattedDateTime = dateTimeFormat.format(currentDate);
                
                
                contentStream.beginText();
                contentStream.newLineAtOffset(465, 210); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText(formattedDateTime);
                contentStream.endText();
                
              //horizontal line 23
                contentStream.setLineWidth(1f);
                contentStream.moveTo(30, 205); 
                contentStream.lineTo(580, 205); 
                contentStream.stroke();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 190); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText("VERIFIED BY :");
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(180, 190); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 8);
                contentStream.showText("");
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(397, 190); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 8);
                contentStream.showText("DATE/ TIME :");
                contentStream.endText();
                
             
                contentStream.beginText();
                contentStream.newLineAtOffset(420, 190); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 10);
                contentStream.showText("");
                contentStream.endText();
             
              //horizontal line 24
                contentStream.setLineWidth(1f);
                contentStream.moveTo(30, 185); 
                contentStream.lineTo(580, 185); 
                contentStream.stroke();
                
              //vertical line 9
              	contentStream.setLineWidth(1f);
                contentStream.moveTo(230, 185); 
                contentStream.lineTo(230, 145); 
                contentStream.stroke();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(40, 177); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 6);
                contentStream.showText("First Card Validation for all aspects (Embosing or Indent or/ and ");
                contentStream.endText();
                contentStream.beginText();
                contentStream.newLineAtOffset(97, 168); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 6);
                contentStream.showText(" Electrical Perso)");
                contentStream.endText();
                contentStream.beginText();
                contentStream.newLineAtOffset(290, 177); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 6);
                contentStream.showText("Validation Done By Production:");
                contentStream.endText();
                contentStream.beginText();
                contentStream.newLineAtOffset(470, 177); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 6);
                contentStream.showText("Validation Done By Quality:");
                contentStream.endText();
                
                
              //horizontal line 25
                contentStream.setLineWidth(1f);
                contentStream.moveTo(30, 145); 
                contentStream.lineTo(580, 145); 
                contentStream.stroke();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(32, 137); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 6);
                contentStream.showText("Note:");
                contentStream.endText();
                contentStream.beginText();
                contentStream.newLineAtOffset(32, 129); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 6);
                contentStream.showText("1. First card validation column should be filled by production supervisor & Quality supervisor only.");
                contentStream.endText();
                contentStream.beginText();
                contentStream.newLineAtOffset(32, 121); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 6);
                contentStream.showText("2.All the column should be filled properly.");
                contentStream.endText();
                contentStream.beginText();
                contentStream.newLineAtOffset(32, 113); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 6);
                contentStream.showText("3.Production supervisor should validate the provided information in work order before running the production.");
                contentStream.endText();
                
              
                
              //horizontal line 26
                contentStream.setLineWidth(1f);
                contentStream.moveTo(30, 105); 
                contentStream.lineTo(580, 105); 
                contentStream.stroke();
                
              //vertical line 10
              	contentStream.setLineWidth(1f);
                contentStream.moveTo(210, 105); 
                contentStream.lineTo(210, 85); 
                contentStream.stroke();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(33, 93); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 7);
                contentStream.showText("DOC NO.:CMSF-BKG-BANKING PERSO PROD 001");
                contentStream.endText();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(241, 93); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 8);
                contentStream.showText("ISSUE NO. : 00");
                contentStream.endText();
                
              //vertical line 11
              	contentStream.setLineWidth(1f);
                contentStream.moveTo(350, 105); 
                contentStream.lineTo(350, 85); 
                contentStream.stroke();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(382, 93); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 8);
                contentStream.showText("REV NO. : 00");
                contentStream.endText();
                
              //vertical line 12
              	contentStream.setLineWidth(1f);
                contentStream.moveTo(480, 105); 
                contentStream.lineTo(480, 85); 
                contentStream.stroke();
                
                contentStream.beginText();
                contentStream.newLineAtOffset(490, 93); 
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 8);
                contentStream.showText("Issue Date:15-03-2023 " );
                contentStream.endText();
                
              //horizontal line 27
                contentStream.setLineWidth(1f);
                contentStream.moveTo(30, 85); 
                contentStream.lineTo(580, 85); 
                contentStream.stroke();
               
                
            }

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            document.save(byteArrayOutputStream);
            document.close();

            return byteArrayOutputStream.toByteArray();
        }
	}
	public static byte[] generateImageQRCode(String text, int width, int height) {
		ByteArrayOutputStream outputStream=null;
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		try {
			BitMatrix bitMatrix=qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
			outputStream=new ByteArrayOutputStream() ;
			MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
		} catch (WriterException | IOException e) {
			// TODO Auto-generated catch block
			logger.error(":::::" + e.getStackTrace());
			e.printStackTrace();
		}
		return outputStream.toByteArray();
	}
}




