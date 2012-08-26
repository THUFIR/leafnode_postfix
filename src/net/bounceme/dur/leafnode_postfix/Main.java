package net.bounceme.dur.leafnode_postfix;

import static java.lang.System.out;
import java.util.List;
import javax.mail.Folder;
import javax.mail.MessagingException;

public class Main {

    private Usenet u = Usenet.INSTANCE;

    public static void main(String[] args) throws MessagingException {
        Main m = new Main();
    }

    private Main() throws MessagingException {
        List<Folder> folders = u.getFolders();
        out.println(folders);
        MailClient mc = new MailClient("user", "host");
        mc.sendMessage("thufir@dur", "some subj", "no content");
    }
}
