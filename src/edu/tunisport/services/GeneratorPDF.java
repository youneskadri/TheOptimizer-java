/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.tunisport.services;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
/**
 *
 * @author mohamed
 */
import java.io.FileOutputStream;
import java.time.LocalDate;

public class GeneratorPDF {
    public static void generer(String id, String nom_event, String heure_debut, String heyre_fin, String localisation) {
        try {
            // Créer un nouveau document PDF
            Document document = new Document();
            // Écrire dans un fichier de sortie
            String outputFilePath = "invitation.pdf";
            PdfWriter.getInstance(document, new FileOutputStream(outputFilePath));
            // Ouvrir le document
            document.open();
            
            // Ajouter l'image en haut du document
            
            Image image = Image.getInstance("C:\\Users\\belha\\OneDrive\\Desktop\\integration\\TuniSport\\src\\edu\\tunisport\\gui\\325937519_5862726462.png");
            
image.scaleToFit(100, 100); // définir la taille de l'image
image.setAbsolutePosition(document.right() - 100, document.top() - 100); // définir la position de l'image
document.add(image);
            
            // Ajouter le texte formaté
            Paragraph p = new Paragraph();
            p.setAlignment(Element.ALIGN_CENTER);
            p.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD));
            p.add("Invitation\n\n");
            p.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 14));
            p.add("Vous êtes cordialement invités à l'événement suivant :\n\n");
            p.add("Nom de l'événement : " + nom_event + "\n\n");
            p.add("Heure de début : " + heure_debut + "\n\n");
            p.add("Heure de fin : " + heyre_fin + "\n\n");
            p.add("Localisation : " + localisation + "\n\n");
            p.add("Nous espérons vous voir nombreux pour partager ce moment avec nous.\n\n");
            p.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD));
            p.add("Merci de confirmer votre présence avant le " + LocalDate.now().plusDays(7) + ".");
            
            // Ajouter le paragraphe au document
            document.add(p);
            // Fermer le document
            document.close();
            System.out.println("Le fichier PDF a été généré avec succès !");

            // Ouvrir le fichier PDF avec le programme par défaut
            File file = new File(outputFilePath);
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
