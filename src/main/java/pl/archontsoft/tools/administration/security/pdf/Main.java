package pl.archontsoft.tools.administration.security.pdf;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

public class Main {

  public static void main(String[] args) throws IOException {
    // todo extract 2 functions - 1 for fully unprotecting the pdf, 1 for protecting the pdf
    // todo consider running a simple webapp to allow others to do the same for free - must be very secure!

    // absolute path
    String path = "";
    // fileName without extension
    String fileName = "";
    String ext = ".pdf";

    PDDocument document = PDDocument.load(
      new File(path + File.separator + fileName + ext),
      "" // password
    );

    // Define the length of the encryption key.
    // Possible values are 40, 128 or 256.

    AccessPermission accessPermission = new AccessPermission();

    // printing
    accessPermission.setCanPrint(false);
    accessPermission.setCanPrintDegraded(false);
    // copying
    accessPermission.setCanExtractContent(false);
    // Disable other things if needed...
    accessPermission.setCanModify(false);
    accessPermission.setCanFillInForm(false);
    accessPermission.setCanModifyAnnotations(false);

    // Owner password (to open the file with all permissions) is "12345"
    String ownerPassword = "to jest bardzo dlugie haslo malo kto je zlamie";
    // User password (to open the file but with restricted permissions, is empty here)
    String userPassword = "";

    StandardProtectionPolicy protectionPolicy = new StandardProtectionPolicy(ownerPassword, userPassword, accessPermission);
    protectionPolicy.setEncryptionKeyLength(256);

    // Apply protection
//    document.protect(protectionPolicy);

    // remove all security
    document.setAllSecurityToBeRemoved(true);

//    document.save(new File(path + File.separator + fileName + "-protected" + ext));
    document.save(new File(path + File.separator + fileName + "-unprotected" + ext));
    document.close();
  }
}
