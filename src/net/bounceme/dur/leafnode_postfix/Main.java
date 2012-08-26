package net.bounceme.dur.leafnode_postfix;

import static java.lang.System.out;
import java.util.List;
import javax.mail.Folder;
import javax.mail.Message;

public class Main {

    private Usenet u = Usenet.INSTANCE;
    private MailClient mailClient = new MailClient(new UserHost("user", "host"));
    //private EntityManagerFactory emf = Persistence.createEntityManagerFactory("USENETPU");
    //private EntityManager em = emf.createEntityManager();
    public static void main(String[] args) throws Exception {
        Main m = new Main();
    }

    private Main() throws Exception {
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
}
