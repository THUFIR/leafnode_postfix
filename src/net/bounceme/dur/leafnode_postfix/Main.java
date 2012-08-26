package net.bounceme.dur.leafnode_postfix;

import static java.lang.System.out;
import java.util.List;
import java.util.Properties;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;

public class Main {

    private Usenet u = Usenet.INSTANCE;
    //private EntityManagerFactory emf = Persistence.createEntityManagerFactory("USENETPU");
    //private EntityManager em = emf.createEntityManager();

    public static void main(String[] args) throws Exception {
        Main m = new Main();
    }

    private Main() throws  Exception {
        List<Folder> folders = u.getFolders();
        out.println(folders);
        MailClient mailClient = new MailClient(new UserHost("user", "host"));
        Newsgroup newsgroup = new Newsgroup(folders.get(0));
        Page page = new Page(newsgroup);
        List<Message> messages = u.getMessages(page);
        Properties p = new Properties();
        for (Message m : messages) {
            mailClient.sendMessage(m);
        }
    }
}
