package net.bounceme.dur.leafnode_postfix;

import java.io.IOException;
import static java.lang.System.out;
import java.util.List;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;

public class Main {

    private Usenet u = Usenet.INSTANCE;
    private MailClient mailClient = new MailClient(new UserHost("thufir", "localhost"));
    //private EntityManagerFactory emf = Persistence.createEntityManagerFactory("USENETPU");
    //private EntityManager em = emf.createEntityManager();

    public static void main(String[] args) throws Exception {
        Main m = new Main();
    }

    private Main() throws MessagingException, IOException  {
        readMail();
    }

    private void load() throws Exception {
        List<Folder> folders = u.getFolders();
        out.println(folders);
        for (Folder f : folders) {
            Newsgroup page = new Newsgroup(f);
            List<Message> messages = u.getMessages(page);
            for (Message m : messages) {
                mailClient.sendMessage(m);
            }
        }
    }

    private void readMail() throws MessagingException, IOException {
        mailClient.checkInbox(1);
    }
}
