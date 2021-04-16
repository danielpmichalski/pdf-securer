package pl.archontsoft.administration.security.pdf;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

public class Main {

  public static void main(String[] args) throws IOException {
    // absolute path
    String path = "";
    // fileName without extension
    String fileName = "";
    String ext = ".pdf";

    // fill ownerPassword to make the document non-editable unless this pwd is entered
    String ownerPassword = "";

    PDDocument doc = PDDocument.load(new File(path + File.separator + fileName + ext));

    // Define the length of the encryption key.
    // Possible values are 40, 128 or 256.
    int keyLength = 256;

    AccessPermission ap = new AccessPermission();

    // printing
    ap.setCanPrint(true);
    ap.setCanPrintDegraded(true);
    // copying
    ap.setCanExtractContent(true);
    // Disable other things if needed...
    ap.setCanModify(false);
    ap.setCanFillInForm(false);
    ap.setCanModifyAnnotations(false);

    // Owner password (to open the file with all permissions) is "12345"
    // User password (to open the file but with restricted permissions, is empty here)
    StandardProtectionPolicy spp = new StandardProtectionPolicy(ownerPassword, "", ap);
    spp.setEncryptionKeyLength(keyLength);

    // Apply protection
    doc.protect(spp);

    doc.save(new File(path + File.separator + fileName + "-protected" + ext));
    doc.close();
  }
}
