package team2.inventory.controller;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import team2.inventory.model.Inventory;

public class BarcodeGenImage {

	private static String saveLocation = "F:\\pdfsaves\\";
	private static String manufactureID = "987654";
	
	/** Public method to create a new Pallet.
	 * Generated barcodeNumber is composed of Pallet ID and Manufacturer number.
	 * 
	 * @param "pallet" Type: Item */
	public static String generateBarcode(Inventory pallet) {
		String barcodeString = manufactureID + pallet.getId();
		String palletName = pallet.getId() + ".pdf";
		makeBarcodePDF(barcodeString, palletName);
		return barcodeString;
	}

	/** Creates the new barcode and puts it out onto a PDF file.
	 * @param barcodeString
	 * @param pdfFilename */
	private static void makeBarcodePDF (String barcodeString, String pdfFilename) {
		// BarcodeGenImage generateInvoice = new BarcodeGenImage();
		Document doc = new Document();
		PdfWriter docWriter = null;

		try {
			//This filepath needs to be changed accordingly
			docWriter = PdfWriter.getInstance(doc , new FileOutputStream(saveLocation + pdfFilename));
			doc.addAuthor("WIMS");
			doc.addCreationDate();
			doc.addProducer();
			doc.addCreator("WIMS");
			doc.addTitle(pdfFilename);
			doc.setPageSize(PageSize.LETTER);

			doc.open();
			PdfContentByte cb = docWriter.getDirectContent();

			Barcode128 code128 = new Barcode128();
			code128.setCode(barcodeString.trim());
			code128.setCodeType(Barcode128.CODE128);
			Image code128Image = code128.createImageWithBarcode(cb, null, null);
			code128Image.setAbsolutePosition(10,700);
			code128Image.scalePercent(125);
			doc.add(code128Image);
		} catch (DocumentException dex) {
			dex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (doc != null) {
				doc.close();
			}
			if (docWriter != null) {
				docWriter.close();
			}
		}
	}
}