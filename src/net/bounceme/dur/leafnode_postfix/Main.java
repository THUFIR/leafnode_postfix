package net.bounceme.dur.leafnode_postfix;

import static java.lang.System.out;
import java.util.List;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;

public class Main {

    private Usenet u = Usenet.INSTANCE;

    public static void main(String[] args) throws MessagingException, Exception {
        Main m = new Main();
    }

    private Main() throws MessagingException, Exception {
        List<Folder> folders = u.getFolders();
        out.println(folders);
        MailClient mc = new MailClient("user", "host");
        mc.sendMessage("thufir@dur", "some subj", folders.toString());
        Newsgroup newsgroup = new Newsgroup(folders.get(0).getFullName());
        Page page = new Page(newsgroup);
        List<Message> messages = u.getMessages(page);
        for (Message m : messages) {
            mc.sendMessage("thufir@dur", m.getSubject(), m.getContent().toString());
        }
    }
}
