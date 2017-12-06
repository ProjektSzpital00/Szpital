/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szpital.util;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.LineSeparator;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.collections.ObservableList;
import szpital.model.Badania;
import szpital.model.Leki;
import szpital.model.Pacjent;

/**
 *
 * @author Olya Lebert
 */
public class Wypis {
     
  public static void wydrukuj(Pacjent pacjent,ObservableList<Badania> badania, ObservableList<Leki> leki) throws FileNotFoundException 
    { 
        CreateDocument wypis= new CreateDocument ();
        wypis.CreateDocument(pacjent,badania,leki);
    }
}

class CreateDocument{
   
       
       public Document CreateDocument(Pacjent pacjent,ObservableList<Badania> badania, ObservableList<Leki> leki) throws FileNotFoundException
       {
       Document document = new Document(PageSize.A4, 36, 20, 36, 20);  
        try 
        {
            PdfWriter.getInstance(document,new FileOutputStream("Wypis_"+pacjent.getNazwisko().getValue()+"_"+pacjent.getImie().getValue()+".pdf"));
            document.open();
 
            Font font = new Font(Font.FontFamily.COURIER, 14,Font.BOLD);
            Font font1 = new Font(Font.FontFamily.COURIER, 12,Font.NORMAL);
             
             //nazwa Hospital
            Paragraph title=new Paragraph("Nazwa Szpitalu",font);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            
            //Adress Hospital
            Paragraph adress=new Paragraph("Adress ",font1);
            adress.setAlignment(Element.ALIGN_CENTER);
            document.add(adress);
            adress.setSpacingAfter(2);
            LineSeparator separator = new LineSeparator();
            separator.setPercentage(59500f / 523f);
            Chunk linebreak = new Chunk(separator);
            document.add(linebreak);
            
            //nazwa wypisu
            Paragraph head=new Paragraph("KARTA INFORMACYJNA LECZENIA SZPITALNEGO",font);
            head.setAlignment(Element.ALIGN_CENTER);
            document.add(head);
            document.add( Chunk.NEWLINE );
           
            Paragraph t = new Paragraph("Dane pacjenta", font);
            t.setAlignment(Element.ALIGN_CENTER);
            t.setSpacingAfter(32);
            document.add(t);
             
            
            CreateTable obj = new CreateTable();
           
           
            document.add(obj.createImie(pacjent)); 
            document.add(obj.createNazwisko(pacjent));
            document.add(obj.createPesel(pacjent));
            document.add(obj.createGrKrwi(pacjent));
            document.add(obj.createOddzial(pacjent));
        
            document.add( Chunk.NEWLINE );
            document.add(obj.createBadania());
            document.add(obj.getBadania(badania));
           
          
            document.add( Chunk.NEWLINE );
            document.add(obj.createZalecenia());
            document.add(obj.getZalecenia(leki));
            
            document.add( Chunk.NEWLINE );
            document.add(obj.createLekarz());
           
           
            for(int i=0;i<=5;i++){document.add( Chunk.NEWLINE );}
            DottedLineSeparator dottedline = new DottedLineSeparator();
            document.add(dottedline);
        } 
        catch (Exception e) 
        {
            // handle exception
        }


        document.close();
        return document;
        }
    }
  
class CreateTable 
{
 
    public PdfPTable createImie(Pacjent pacjent)
    {
        Font f = new Font(Font.FontFamily.COURIER, 12,Font.NORMAL);
        float[] widths1 = {5,10};
        PdfPTable t = new PdfPTable(widths1);
       
        t.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        t.getDefaultCell().setPaddingBottom(3);
        t.getDefaultCell().setPaddingRight(3);

        Phrase p1 = new Phrase("Imie:",f);
        Phrase p2 = new Phrase(pacjent.getImie().getValue(),f);

        t.addCell(p1);
        t.addCell(p2);
        return t;
    }
    
    public PdfPTable createNazwisko(Pacjent pacjent)
    {
         Font f = new Font(Font.FontFamily.COURIER, 12,Font.NORMAL);
        float[] widths1 = {5,10};
        PdfPTable t = new PdfPTable(widths1);
       
        t.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        t.getDefaultCell().setPaddingBottom(3);
        t.getDefaultCell().setPaddingRight(3);

        Phrase p1 = new Phrase("Nazwisko:",f);
        Phrase p2 = new Phrase(pacjent.getNazwisko().getValue(),f);

        t.addCell(p1);
        t.addCell(p2);
        return t;
    }
     
    public PdfPTable createPesel(Pacjent pacjent)
    {
        Font f = new Font(Font.FontFamily.COURIER, 12,Font.NORMAL);
        float[] widths1 = {5,10};
        PdfPTable t = new PdfPTable(widths1);
       
        t.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        t.getDefaultCell().setPaddingBottom(3);
        t.getDefaultCell().setPaddingRight(3);

        Phrase p1 = new Phrase("Pesel:",f);
        Phrase p2 = new Phrase(pacjent.getPesel().getValue(),f);

        t.addCell(p1);
        t.addCell(p2);
        return t;
    }
      
    public PdfPTable createGrKrwi(Pacjent pacjent)
    {
        Font f = new Font(Font.FontFamily.COURIER, 12,Font.NORMAL);
        float[] widths1 = {5,10};
        PdfPTable t = new PdfPTable(widths1);
       
        t.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        t.getDefaultCell().setPaddingBottom(3);
        t.getDefaultCell().setPaddingRight(3);

        Phrase p1 = new Phrase("Grupa krwi:",f);
        Phrase p2 = new Phrase(pacjent.getGrKrwii().getValue(),f);

        t.addCell(p1);
        t.addCell(p2);
        return t;
    }
    
    public PdfPTable createLekarz()
    {
        Font f = new Font(Font.FontFamily.COURIER, 12,Font.NORMAL);
        float[] widths1 = {10,5};
        PdfPTable t = new PdfPTable(widths1);
       
        t.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        t.getDefaultCell().setPaddingBottom(3);
        t.getDefaultCell().setPaddingLeft(10);
        t.setHorizontalAlignment(Element.ALIGN_LEFT);
        Phrase p1 = new Phrase("Lekarz prowadzacy ",f);
        Phrase p2 = new Phrase("Ordynator oddzialu ",f);
        t.addCell(p1);
        t.addCell(p2);
        return t;
    }

    public PdfPTable createOddzial(Pacjent pacjent)
    {
        Font f = new Font(Font.FontFamily.COURIER, 12,Font.NORMAL);
        float[] widths1 = {5,10};
        PdfPTable t = new PdfPTable(widths1);
       
        t.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        t.getDefaultCell().setPaddingBottom(3);
        t.getDefaultCell().setPaddingRight(3);

        Phrase p1 = new Phrase("Oddzial: ",f);
        Phrase p2 = new Phrase(pacjent.getOddzial().getValue(),f);

        t.addCell(p1);
        t.addCell(p2);
        return t;
    }
           
    public PdfPTable createBadania()
    {
        Font f = new Font(Font.FontFamily.COURIER, 12,Font.NORMAL);
        float[] widths1 = {5};
        PdfPTable t = new PdfPTable(widths1);
       
        t.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        t.getDefaultCell().setPaddingBottom(3);
        t.getDefaultCell().setPaddingRight(3);

        Phrase p1 = new Phrase("Badania: ",f);
        t.addCell(p1);
       
        return t;
    }  
    
    public PdfPTable getBadania(ObservableList<Badania> badania)
    {
        Font f = new Font(Font.FontFamily.COURIER, 12,Font.NORMAL);
        float[] widths1 = {5,5};
        PdfPTable t = new PdfPTable(widths1);
       
        t.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        t.getDefaultCell().setPaddingBottom(3);
        t.getDefaultCell().setPaddingRight(3);
       
        for(Badania badanie: badania)
        {
        Phrase p1 = new Phrase(badanie.getNazwaBadania().getValue(),f);
        Phrase p2 = new Phrase(badanie.getWynikBadania().getValue(),f);   
        t.addCell(p1);
        t.addCell(p2);
        }
        return t;
    }       

    public PdfPTable createZalecenia()
    {
         Font f = new Font(Font.FontFamily.COURIER, 12,Font.NORMAL);
        float[] widths1 = {5};
        PdfPTable t = new PdfPTable(widths1);
       
        t.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        t.getDefaultCell().setPaddingBottom(5);
        t.getDefaultCell().setPaddingRight(3);

        Phrase p1 = new Phrase("Zastosowane zalecenia: ",f);
        t.addCell(p1);  
        return t;
    }   
    
    public PdfPTable getZalecenia(ObservableList<Leki> leki)
    {
        Font f = new Font(Font.FontFamily.COURIER, 12,Font.NORMAL);
        float[] widths1 = {5,5};
        PdfPTable t = new PdfPTable(widths1);
       
        t.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        t.getDefaultCell().setPaddingBottom(5);
        t.getDefaultCell().setPaddingRight(3);
        for(Leki lek : leki)
        {
        Phrase p1 = new Phrase(lek.getNazwa().getValue(),f);
        Phrase p2 = new Phrase(lek.getDawkowanie().getValue(),f);
        t.addCell(p1);  
        t.addCell(p2); 
        }
        return t;
    } 
}